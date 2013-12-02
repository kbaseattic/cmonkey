#!/usr/bin/env python
from wsgiref.simple_server import make_server
import sys, json, traceback
from multiprocessing import Process
from getopt import getopt, GetoptError
from jsonrpcbase import JSONRPCService, InvalidParamsError, KeywordError,\
  JSONRPCError, log, ServerError, ParseError, InvalidRequestError
from os import environ
from ConfigParser import ConfigParser
import nexus

DEPLOY = 'KB_DEPLOYMENT_CONFIG'
SERVICE = 'KB_SERVICE_NAME'

# Note that the error fields do not match the 2.0 JSONRPC spec

def getConfigs():
    if DEPLOY not in environ or SERVICE not in environ:
        return None
    retconfig = {}
    config = ConfigParser()
    config.read(environ[DEPLOY])
    for nameval in config.items(environ[SERVICE]):
        retconfig[nameval[0]] = nameval[1]
    return retconfig
    
config = getConfigs()
    
from CmonkeyImpl import Cmonkey
impl_Cmonkey = Cmonkey(config)

class JSONObjectEncoder(json.JSONEncoder):
  
    def default(self, obj):
        if isinstance(obj, set):
            return list(obj)
        if isinstance(obj, frozenset):
            return list(obj)
        if hasattr(obj, 'toJSONable'):
            return obj.toJSONable()
        return json.JSONEncoder.default(self, obj)

class JSONRPCServiceCustom(JSONRPCService):
  
    def call(self, jsondata):
        """
        Calls jsonrpc service's method and returns its return value in a JSON string or None if there is none.

        Arguments:
        jsondata -- remote method call in jsonrpc format
        """
        result = self.call_py(jsondata)
        if result != None:
            return json.dumps(result, cls = JSONObjectEncoder)

        return None
  
    def _call_method(self, request):
        """Calls given method with given params and returns it value."""
        method = self.method_data[request['method']]['method']
        params = request['params']
        result = None
        try:
            if isinstance(params, list):
                # Does it have enough arguments?
                if len(params) < self._man_args(method):
                    raise InvalidParamsError('not enough arguments')
                # Does it have too many arguments?
                if not self._vargs(method) and len(params) > self._max_args(method):
                    raise InvalidParamsError('too many arguments')

                result = method(*params)
            elif isinstance(params, dict):
                # Do not accept keyword arguments if the jsonrpc version is not >=1.1.
                if request['jsonrpc'] < 11:
                    raise KeywordError

                result = method(**params)
            else: # No params
                result = method()
        except JSONRPCError:
            raise
        except Exception:
            log.exception('method %s threw an exception' % request['method'])
            # Exception was raised inside the method.
            newerr = ServerError()
            newerr.data = traceback.format_exc()
            raise newerr
        return result
      
    def call_py(self, jsondata):
        """
        Calls jsonrpc service's method and returns its return value in python object format or None if there is none.

        This method is same as call() except the return value is a python object instead of JSON string. This method
        is mainly only useful for debugging purposes.
        """
        try:
            rdata = json.loads(jsondata)
        except ValueError:
            raise ParseError

        # set some default values for error handling
        request = self._get_default_vals()

        if isinstance(rdata, dict) and rdata:
            # It's a single request.
            self._fill_request(request, rdata)
            respond = self._handle_request(request)

            # Don't respond to notifications
            if respond is None:
                return None

            return respond
        elif isinstance(rdata, list) and rdata:
            # It's a batch.
            requests = []
            responds = []

            for rdata_ in rdata:
                # set some default values for error handling
                request_ = self._get_default_vals()
                self._fill_request(request_, rdata_)
                requests.append(request_)

            for request_ in requests:
                respond = self._handle_request(request_)
                # Don't respond to notifications
                if respond is not None:
                    responds.append(respond)

            if responds:
                return responds

            # Nothing to respond.
            return None
        else:
            # empty dict, list or wrong type
            raise InvalidRequestError


class Application(object):
    # Wrap the wsgi handler in a class definition so that we can
    # do some initialization and avoid regenerating stuff over
    # and over

    def __init__(self):
        self.rpc_service = JSONRPCServiceCustom()
        self.method_authentication = dict()
        self.rpc_service.add(impl_Cmonkey.build_cmonkey_network, name = 'Cmonkey.build_cmonkey_network',
                             types = [dict, dict])
        self.method_authentication[ 'Cmonkey.build_cmonkey_network' ] = 'none'
        self.rpc_service.add(impl_Cmonkey.build_cmonkey_network_from_ws, name = 'Cmonkey.build_cmonkey_network_from_ws',
                             types = [basestring, basestring, dict])
        self.method_authentication[ 'Cmonkey.build_cmonkey_network_from_ws' ] = 'required'
        self.rpc_service.add(impl_Cmonkey.build_cmonkey_network_job_from_ws, name = 'Cmonkey.build_cmonkey_network_job_from_ws',
                             types = [basestring, basestring, dict])
        self.method_authentication[ 'Cmonkey.build_cmonkey_network_job_from_ws' ] = 'required'
        self.auth_client = nexus.Client( config = { 'server' : 'nexus.api.globusonline.org',
                                                    'verify_ssl' : False,
                                                    'client' : None,
                                                    'client_secret': None})


    def __call__( self, environ, start_response):
        # Context object, equivalent to the perl impl CallContext
        ctx = { 'client_ip' : environ.get('REMOTE_ADDR'),
                'user_id' : None,
                'authenticated' : None,
                'token' : None }

        status = '500 Internal Server Error'

        try:
            body_size = int( environ.get('CONTENT_LENGTH',0))
        except (ValueError):
            body_size = 0
        if environ['REQUEST_METHOD'] == 'OPTIONS': # we basically do nothing and just return headers
            status = '200 OK'
            rpc_result = ""
        else:
            request_body = environ['wsgi.input'].read(body_size)
            try:
                req = json.loads(request_body)
            except ValueError as ve:
                err = {'code': -32700,
                       'name': "Parse error",
                       'message': str(ve),
                       'version': '1.1'  # don't know the inc version
                       }
                rpc_result = json.dumps(err)
            else:
                try:
                    token = environ.get( 'HTTP_AUTHORIZATION')
                    # parse out the method being requested and check if it
                    # has an authentication requirement
                    auth_req = self.method_authentication.get(req['method'], "none")
                    if auth_req != "none":
                        if token is None and auth_req == 'required':
                            err = ServerError()
                            err.data = "Authentication required for Cmonkey but no authentication header was passed"
                            raise err
                        elif token is None and auth_req == 'optional':
                            pass
                        else:
                            try:
                                user, clientid, nexus_host = self.auth_client.validate_token(token)
                                ctx['user_id'] = user
                                ctx['authenticated'] = 1
                                ctx['token'] = token
                            except Exception, e:
                                if auth_req == 'required':
                                    err = ServerError()
                                    err.data = "Token validation failed: %s" % e
                                    raise err
                    # push the context object into the implementation instance's namespace
                    impl_Cmonkey.ctx = ctx
                    rpc_result = self.rpc_service.call( request_body)
                except JSONRPCError as jre:
                    err = {'error': {'code': jre.code,
                                     'name': jre.message,
                                     'message': jre.data
                                     }
                           }
                    self.complete_err(err, req)
                    rpc_result = json.dumps(err)
                except Exception, e:
                    err = {'error': {'code': 0,
                                     'name': 'Unexpected Server Error',
                                     'message': traceback.format_exc()
                                     }
                           }
                    self.complete_err(err, req)
                    rpc_result = json.dumps(err)

                else:
                    status = '200 OK'

        #print 'The request method was %s\n' % environ['REQUEST_METHOD']
        #print 'The environment dictionary is:\n%s\n' % pprint.pformat( environ )
        #print 'The request body was: %s' % request_body
        #print 'The result from the method call is:\n%s\n' % pprint.pformat(rpc_result)

        if rpc_result:
            response_body = rpc_result
        else:
            response_body = ''

        response_headers = [('Access-Control-Allow-Origin', '*'),
                             ('Access-Control-Allow-Headers', environ.get('HTTP_ACCESS_CONTROL_REQUEST_HEADERS',
                                                                          'authorization')),
                             ('content-type', 'application/json'),
                             ('content-length', str(len(response_body)))]
        start_response(status, response_headers)
        return [response_body]

    def complete_err(self, err, req):
        if 'id' in req:
            err['id'] = req['id']
        if 'version' in req:
            err['version'] = req['version']
        elif 'jsonrpc' in req:
            err['jsonrpc'] = req['jsonrpc']
        else:
            err['version'] = '1.0'

application = Application()

# This is the uwsgi application dictionary. On startup uwsgi will look
# for this dict and pull its configuration from here.
# This simply lists where to "mount" the application in the URL path
#
# This uwsgi module "magically" appears when running the app within
# uwsgi and is not available otherwise, so wrap an exception handler
# around it
# 
# To run this server in uwsgi with 4 workers listening on port 9999 use:
# uwsgi -M -p 4 --http :9999 --wsgi-file _this_file_
# To run a using the single threaded python BaseHTTP service
# listening on port 9999 by default execute this file
#
try:
    import uwsgi
# Before we do anything with the application, see if the
# configs specify patching all std routines to be asynch
# *ONLY* use this if you are going to wrap the service in
# a wsgi container that has enabled gevent, such as
# uwsgi with the --gevent option
    if config is not None and config.get('gevent_monkeypatch_all', False):
        print "Monkeypatching std libraries for async"
        from gevent import monkey;
        monkey.patch_all()
    uwsgi.applications = {
        '' : application
        }
except ImportError:
    # Not available outside of wsgi, ignore
    pass

_proc = None
  
def start_server(host = 'localhost', port = 0, newprocess = False):
    '''
    By default, will start the server on localhost on a system assigned port
    in the main thread. Excecution of the main thread will stay in the server 
    main loop until interrupted. To run the server in a separate thread, and 
    thus allow the stop_server method to be called, set thread = True. This
    will also allow returning of the port number.'''
    
    global _proc
    if _proc:
      raise RuntimeError("server is already running")
    httpd = make_server(host, port, application)
    port = httpd.server_address[1]
    print "Listening on port %s" % port
    if newprocess:
      _proc = Process(target = httpd.serve_forever)
      _proc.daemon = True
      _proc.start()
    else:
      httpd.serve_forever()
    return port
  
def stop_server():
    global _proc
    _proc.terminate()
    _proc = None

if __name__ == "__main__":
    try:
        opts, args = getopt(sys.argv[1:], "", ["port=","host="])
    except GetoptError as err:
        # print help information and exit:
        print str(err) # will print something like "option -a not recognized"
        sys.exit(2)      
    port = 9999
    host = 'localhost'
    for o, a in opts:
        if o == '--port':
            port = int(a)
        elif o == '--host':
            host = a
            print "Host set to %s" % host
        else:
            assert False, "unhandled option"
    
    start_server(host = host, port = port)
#    print "Listening on port %s" % port
#    httpd = make_server( host, port, application)
#
#    httpd.serve_forever()
