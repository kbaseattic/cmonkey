package us.kbase.workspaceservice;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.Tuple6;
import us.kbase.common.service.UnauthorizedException;

/**
 * <p>Original spec-file module name: workspaceService</p>
 * <pre>
 * =head1 workspaceService
 * =head2 SYNOPSIS
 * Workspaces are used in KBase to provide an online location for all data, models, and
 * analysis results. Workspaces are a powerful tool for managing private data, tracking 
 * workflow provenance, storing and sharing large datasets, and tracking work history. They
 * have a number of useful characteristics which you will learn about over the course of the
 * workspace tutorials:
 * 1.) Multiple users can read and write from the same workspace at the same time, 
 * facilitating collaboration
 * 2.) When an object is overwritten in a workspace, the previous version is preserved and
 * easily accessible at any time, enabling the use of workspaces to track object versions
 * 3.) Workspaces have default permissions and user-specific permissions, providing total 
 * control over the sharing and access of workspace contents
 * =head2 EXAMPLE OF API USE IN PERL
 * To use the API, first you need to instantiate a workspace client object:
 * my $client = Bio::KBase::workspaceService::Client->new(user_id => "user", 
 *                 password => "password");
 *    
 * Next, you can run API commands on the client object:
 *    
 * my $ws = $client->create_workspace({
 *         workspace => "foo",
 *         default_permission => "n"
 * });
 * my $objs = $client->list_workspace_objects({
 *         workspace => "foo"
 * });
 * print map { $_->[0] } @$objs;
 * =head2 AUTHENTICATION
 * There are several ways to provide authentication for using the workspace
 * service.
 * Firstly, one can provide a username and password as in the example above.
 * Secondly, one can obtain an authorization token via the C<AuthToken.pm> module
 * (see the documentation for that module) and provide it to the Client->new()
 * method with the keyword argument C<token>.
 * Finally, one can provide the token directly to a method via the C<auth>
 * parameter. If a token is provided directly to a method, this token takes
 * precedence over any previously provided authorization.
 * If no authorization is provided only unauthenticated read operations are
 * allowed.
 * =head2 WORKSPACE
 * A workspace is a named collection of objects owned by a specific
 * user, that may be viewable or editable by other users. Functions that operate
 * on workspaces take a C<workspace_id>, which is an alphanumeric string that
 * uniquely identifies a workspace among all workspaces.
 * </pre>
 */
public class WorkspaceServiceClient {
    private JsonClientCaller caller;

    public WorkspaceServiceClient(URL url) {
        caller = new JsonClientCaller(url);
    }

    public WorkspaceServiceClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, token);
    }

    public WorkspaceServiceClient(URL url, String user, String password) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password);
    }

	public void setConnectionReadTimeOut(Integer milliseconds) {
		this.caller.setConnectionReadTimeOut(milliseconds);
	}

    public boolean isAuthAllowedForHttp() {
        return caller.isAuthAllowedForHttp();
    }

    public void setAuthAllowedForHttp(boolean isAuthAllowedForHttp) {
        caller.setAuthAllowedForHttp(isAuthAllowedForHttp);
    }

    /**
     * <p>Original spec-file function name: load_media_from_bio</p>
     * <pre>
     * Creates "Media" objects in the workspace for all media contained in the specified biochemistry
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.LoadMediaFromBioParams LoadMediaFromBioParams} (original type "load_media_from_bio_params")
     * @return   parameter "mediaMetas" of list of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> loadMediaFromBio(LoadMediaFromBioParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>> retType = new TypeReference<List<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>>() {};
        List<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> res = caller.jsonrpcCall("workspaceService.load_media_from_bio", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: import_bio</p>
     * <pre>
     * Imports a biochemistry from a URL
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.ImportBioParams ImportBioParams} (original type "import_bio_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> importBio(ImportBioParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>() {};
        List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("workspaceService.import_bio", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: import_map</p>
     * <pre>
     * Imports a mapping from a URL
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.ImportMapParams ImportMapParams} (original type "import_map_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> importMap(ImportMapParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>() {};
        List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("workspaceService.import_map", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: save_object</p>
     * <pre>
     * Saves the input object data and metadata into the selected workspace, returning the object_metadata of the saved object
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.SaveObjectParams SaveObjectParams} (original type "save_object_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> saveObject(SaveObjectParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>() {};
        List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("workspaceService.save_object", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: delete_object</p>
     * <pre>
     * Deletes the specified object from the specified workspace, returning the object_metadata of the deleted object.
     * Object is only temporarily deleted and can be recovered by using the revert command.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.DeleteObjectParams DeleteObjectParams} (original type "delete_object_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> deleteObject(DeleteObjectParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>() {};
        List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("workspaceService.delete_object", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: delete_object_permanently</p>
     * <pre>
     * Permanently deletes the specified object from the specified workspace.
     * This permanently deletes the object and object history, and the data cannot be recovered.
     * Objects cannot be permanently deleted unless they've been deleted first.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.DeleteObjectPermanentlyParams DeleteObjectPermanentlyParams} (original type "delete_object_permanently_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> deleteObjectPermanently(DeleteObjectPermanentlyParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>() {};
        List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("workspaceService.delete_object_permanently", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_object</p>
     * <pre>
     * Retrieves the specified object from the specified workspace.
     * Both the object data and metadata are returned.
     * This commands provides access to all versions of the object via the instance parameter.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.GetObjectParams GetObjectParams} (original type "get_object_params")
     * @return   parameter "output" of type {@link us.kbase.workspaceservice.GetObjectOutput GetObjectOutput} (original type "get_object_output")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GetObjectOutput getObject(GetObjectParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<GetObjectOutput>> retType = new TypeReference<List<GetObjectOutput>>() {};
        List<GetObjectOutput> res = caller.jsonrpcCall("workspaceService.get_object", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_objects</p>
     * <pre>
     * Retrieves the specified objects from the specified workspaces.
     * Both the object data and metadata are returned.
     * This commands provides access to all versions of the objects via the instances parameter.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.GetObjectsParams GetObjectsParams} (original type "get_objects_params")
     * @return   parameter "output" of list of type {@link us.kbase.workspaceservice.GetObjectOutput GetObjectOutput} (original type "get_object_output")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<GetObjectOutput> getObjects(GetObjectsParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<GetObjectOutput>>> retType = new TypeReference<List<List<GetObjectOutput>>>() {};
        List<List<GetObjectOutput>> res = caller.jsonrpcCall("workspaceService.get_objects", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_object_by_ref</p>
     * <pre>
     * Retrieves the specified object from the specified workspace.
     * Both the object data and metadata are returned.
     * This commands provides access to all versions of the object via the instance parameter.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.GetObjectByRefParams GetObjectByRefParams} (original type "get_object_by_ref_params")
     * @return   parameter "output" of type {@link us.kbase.workspaceservice.GetObjectOutput GetObjectOutput} (original type "get_object_output")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GetObjectOutput getObjectByRef(GetObjectByRefParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<GetObjectOutput>> retType = new TypeReference<List<GetObjectOutput>>() {};
        List<GetObjectOutput> res = caller.jsonrpcCall("workspaceService.get_object_by_ref", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: save_object_by_ref</p>
     * <pre>
     * Retrieves the specified object from the specified workspace.
     * Both the object data and metadata are returned.
     * This commands provides access to all versions of the object via the instance parameter.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.SaveObjectByRefParams SaveObjectByRefParams} (original type "save_object_by_ref_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> saveObjectByRef(SaveObjectByRefParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>() {};
        List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("workspaceService.save_object_by_ref", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_objectmeta</p>
     * <pre>
     * Retrieves the metadata for a specified object from the specified workspace.
     * This commands provides access to metadata for all versions of the object via the instance parameter.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.GetObjectmetaParams GetObjectmetaParams} (original type "get_objectmeta_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> getObjectmeta(GetObjectmetaParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>() {};
        List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("workspaceService.get_objectmeta", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_objectmeta_by_ref</p>
     * <pre>
     * Retrieves the specified object from the specified workspace.
     * Both the object data and metadata are returned.
     * This commands provides access to all versions of the object via the instance parameter.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.GetObjectmetaByRefParams GetObjectmetaByRefParams} (original type "get_objectmeta_by_ref_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> getObjectmetaByRef(GetObjectmetaByRefParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>() {};
        List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("workspaceService.get_objectmeta_by_ref", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: revert_object</p>
     * <pre>
     * Reverts a specified object in a specifed workspace to a previous version of the object.
     * Returns the metadata of the newly reverted object.
     * This command still makes a new instance of the object, copying data related to the target instance to the new instance.
     * This ensures that the object instance always increases and no portion of the object history is ever lost.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.RevertObjectParams RevertObjectParams} (original type "revert_object_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> revertObject(RevertObjectParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>() {};
        List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("workspaceService.revert_object", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: copy_object</p>
     * <pre>
     * Copies a specified object in a specifed workspace to a new ID and/or workspace.
     * Returns the metadata of the newly copied object.
     * It is possible to use the version parameter to copy any version of a workspace object.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.CopyObjectParams CopyObjectParams} (original type "copy_object_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> copyObject(CopyObjectParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>() {};
        List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("workspaceService.copy_object", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: move_object</p>
     * <pre>
     * Moves a specified object in a specifed workspace to a new ID and/or workspace.
     * Returns the metadata of the newly moved object.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.MoveObjectParams MoveObjectParams} (original type "move_object_params")
     * @return   parameter "metadata" of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> moveObject(MoveObjectParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> retType = new TypeReference<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>() {};
        List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> res = caller.jsonrpcCall("workspaceService.move_object", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: has_object</p>
     * <pre>
     * Checks if a specified object in a specifed workspace exists.
     * Returns "1" if the object exists, "0" if not
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.HasObjectParams HasObjectParams} (original type "has_object_params")
     * @return   parameter "object_present" of original type "bool" (indicates true or false values, false <= 0, true >=1)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Long hasObject(HasObjectParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Long>> retType = new TypeReference<List<Long>>() {};
        List<Long> res = caller.jsonrpcCall("workspaceService.has_object", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: object_history</p>
     * <pre>
     * Returns the metadata associated with every version of a specified object in a specified workspace.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.ObjectHistoryParams ObjectHistoryParams} (original type "object_history_params")
     * @return   parameter "metadatas" of list of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> objectHistory(ObjectHistoryParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>> retType = new TypeReference<List<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>>() {};
        List<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> res = caller.jsonrpcCall("workspaceService.object_history", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: create_workspace</p>
     * <pre>
     * Creates a new workspace with the specified name and default permissions.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.CreateWorkspaceParams CreateWorkspaceParams} (original type "create_workspace_params")
     * @return   parameter "metadata" of original type "workspace_metadata" (Meta data associated with a workspace. workspace_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) username owner - name of the user who owns (who created) this object timestamp moddate - date when the workspace was last modified int objects - number of objects currently stored in the workspace permission user_permission - permissions for the currently logged user for the workspace permission global_permission - default permissions for the workspace for all KBase users) &rarr; tuple of size 6: parameter "id" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "objects" of Long, parameter "user_permission" of original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.), parameter "global_permission" of original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple6<String, String, String, Long, String, String> createWorkspace(CreateWorkspaceParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple6<String, String, String, Long, String, String>>> retType = new TypeReference<List<Tuple6<String, String, String, Long, String, String>>>() {};
        List<Tuple6<String, String, String, Long, String, String>> res = caller.jsonrpcCall("workspaceService.create_workspace", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_workspacemeta</p>
     * <pre>
     * Retreives the metadata associated with the specified workspace.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.GetWorkspacemetaParams GetWorkspacemetaParams} (original type "get_workspacemeta_params")
     * @return   parameter "metadata" of original type "workspace_metadata" (Meta data associated with a workspace. workspace_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) username owner - name of the user who owns (who created) this object timestamp moddate - date when the workspace was last modified int objects - number of objects currently stored in the workspace permission user_permission - permissions for the currently logged user for the workspace permission global_permission - default permissions for the workspace for all KBase users) &rarr; tuple of size 6: parameter "id" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "objects" of Long, parameter "user_permission" of original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.), parameter "global_permission" of original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple6<String, String, String, Long, String, String> getWorkspacemeta(GetWorkspacemetaParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple6<String, String, String, Long, String, String>>> retType = new TypeReference<List<Tuple6<String, String, String, Long, String, String>>>() {};
        List<Tuple6<String, String, String, Long, String, String>> res = caller.jsonrpcCall("workspaceService.get_workspacemeta", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_workspacepermissions</p>
     * <pre>
     * Retreives a list of all users with custom permissions to the workspace if an admin, returns 
     * the user's own permissions otherwise.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.GetWorkspacepermissionsParams GetWorkspacepermissionsParams} (original type "get_workspacepermissions_params")
     * @return   parameter "user_permissions" of mapping from original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped) to original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getWorkspacepermissions(GetWorkspacepermissionsParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("workspaceService.get_workspacepermissions", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: delete_workspace</p>
     * <pre>
     * Deletes a specified workspace with all objects.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.DeleteWorkspaceParams DeleteWorkspaceParams} (original type "delete_workspace_params")
     * @return   parameter "metadata" of original type "workspace_metadata" (Meta data associated with a workspace. workspace_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) username owner - name of the user who owns (who created) this object timestamp moddate - date when the workspace was last modified int objects - number of objects currently stored in the workspace permission user_permission - permissions for the currently logged user for the workspace permission global_permission - default permissions for the workspace for all KBase users) &rarr; tuple of size 6: parameter "id" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "objects" of Long, parameter "user_permission" of original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.), parameter "global_permission" of original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple6<String, String, String, Long, String, String> deleteWorkspace(DeleteWorkspaceParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple6<String, String, String, Long, String, String>>> retType = new TypeReference<List<Tuple6<String, String, String, Long, String, String>>>() {};
        List<Tuple6<String, String, String, Long, String, String>> res = caller.jsonrpcCall("workspaceService.delete_workspace", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: clone_workspace</p>
     * <pre>
     * Copies a specified workspace with all objects.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.CloneWorkspaceParams CloneWorkspaceParams} (original type "clone_workspace_params")
     * @return   parameter "metadata" of original type "workspace_metadata" (Meta data associated with a workspace. workspace_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) username owner - name of the user who owns (who created) this object timestamp moddate - date when the workspace was last modified int objects - number of objects currently stored in the workspace permission user_permission - permissions for the currently logged user for the workspace permission global_permission - default permissions for the workspace for all KBase users) &rarr; tuple of size 6: parameter "id" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "objects" of Long, parameter "user_permission" of original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.), parameter "global_permission" of original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple6<String, String, String, Long, String, String> cloneWorkspace(CloneWorkspaceParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple6<String, String, String, Long, String, String>>> retType = new TypeReference<List<Tuple6<String, String, String, Long, String, String>>>() {};
        List<Tuple6<String, String, String, Long, String, String>> res = caller.jsonrpcCall("workspaceService.clone_workspace", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: list_workspaces</p>
     * <pre>
     * Lists the metadata of all workspaces a user has access to.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.ListWorkspacesParams ListWorkspacesParams} (original type "list_workspaces_params")
     * @return   parameter "workspaces" of list of original type "workspace_metadata" (Meta data associated with a workspace. workspace_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) username owner - name of the user who owns (who created) this object timestamp moddate - date when the workspace was last modified int objects - number of objects currently stored in the workspace permission user_permission - permissions for the currently logged user for the workspace permission global_permission - default permissions for the workspace for all KBase users) &rarr; tuple of size 6: parameter "id" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "objects" of Long, parameter "user_permission" of original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.), parameter "global_permission" of original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple6<String, String, String, Long, String, String>> listWorkspaces(ListWorkspacesParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<Tuple6<String, String, String, Long, String, String>>>> retType = new TypeReference<List<List<Tuple6<String, String, String, Long, String, String>>>>() {};
        List<List<Tuple6<String, String, String, Long, String, String>>> res = caller.jsonrpcCall("workspaceService.list_workspaces", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: list_workspace_objects</p>
     * <pre>
     * Lists the metadata of all objects in the specified workspace with the specified type (or with any type).
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.ListWorkspaceObjectsParams ListWorkspaceObjectsParams} (original type "list_workspace_objects_params")
     * @return   parameter "objects" of list of original type "object_metadata" (Meta data associated with an object stored in a workspace. object_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) object_type type - type of the object (e.g. Genome) timestamp moddate - date when the object was modified by the user (e.g. 2012-12-17T23:24:06) int instance - instance of the object, which is equal to the number of times the user has overwritten the object string command - name of the command last used to modify or create the object username lastmodifier - name of the user who last modified the object username owner - name of the user who owns (who created) this object workspace_id workspace - ID of the workspace in which the object is currently stored workspace_ref ref - a 36 character ID that provides permanent undeniable access to this specific instance of this object string chsum - checksum of the associated data object mapping<string,string> metadata - custom metadata entered for data object during save operation) &rarr; tuple of size 11: parameter "id" of original type "object_id" (ID of an object stored in the workspace. Any string consisting of alphanumeric characters and "-" is acceptable), parameter "type" of original type "object_type" (A string indicating the "type" of an object stored in a workspace. Acceptable types are returned by the "get_types()" command), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "instance" of Long, parameter "command" of String, parameter "lastmodifier" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "workspace" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "ref" of original type "workspace_ref" (A 36 character string referring to a particular instance of an object in a workspace that lasts forever. Objects should always be retreivable using this ID), parameter "chsum" of String, parameter "metadata" of mapping from String to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>> listWorkspaceObjects(ListWorkspaceObjectsParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>> retType = new TypeReference<List<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>>>() {};
        List<List<Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>>>> res = caller.jsonrpcCall("workspaceService.list_workspace_objects", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: set_global_workspace_permissions</p>
     * <pre>
     * Sets the default permissions for accessing a specified workspace for all users.
     * Must have admin privelages to change workspace global permissions.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.SetGlobalWorkspacePermissionsParams SetGlobalWorkspacePermissionsParams} (original type "set_global_workspace_permissions_params")
     * @return   parameter "metadata" of original type "workspace_metadata" (Meta data associated with a workspace. workspace_id id - ID of the object assigned by the user or retreived from the IDserver (e.g. kb|g.0) username owner - name of the user who owns (who created) this object timestamp moddate - date when the workspace was last modified int objects - number of objects currently stored in the workspace permission user_permission - permissions for the currently logged user for the workspace permission global_permission - default permissions for the workspace for all KBase users) &rarr; tuple of size 6: parameter "id" of original type "workspace_id" (A string used as an ID for a workspace. Any string consisting of alphanumeric characters and "_" is acceptable), parameter "owner" of original type "username" (Login name of KBase useraccount to which permissions for workspaces are mapped), parameter "moddate" of original type "timestamp" (Exact time for workspace operations. e.g. 2012-12-17T23:24:06), parameter "objects" of Long, parameter "user_permission" of original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.), parameter "global_permission" of original type "permission" (Single letter indicating permissions on access to workspace. Options are: 'a' for administative access, 'w' for read/write access, 'r' for read access, and 'n' for no access. For default permissions (e.g. permissions for any user) only 'n' and 'r' are allowed.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Tuple6<String, String, String, Long, String, String> setGlobalWorkspacePermissions(SetGlobalWorkspacePermissionsParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Tuple6<String, String, String, Long, String, String>>> retType = new TypeReference<List<Tuple6<String, String, String, Long, String, String>>>() {};
        List<Tuple6<String, String, String, Long, String, String>> res = caller.jsonrpcCall("workspaceService.set_global_workspace_permissions", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: set_workspace_permissions</p>
     * <pre>
     * Sets the permissions for a list of users for accessing a specified workspace.
     * Must have admin privelages to change workspace permissions. Note that only the workspace owner can change the owner's permissions;
     * any other user's attempt to do will silently fail.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.SetWorkspacePermissionsParams SetWorkspacePermissionsParams} (original type "set_workspace_permissions_params")
     * @return   parameter "success" of original type "bool" (indicates true or false values, false <= 0, true >=1)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Long setWorkspacePermissions(SetWorkspacePermissionsParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Long>> retType = new TypeReference<List<Long>>() {};
        List<Long> res = caller.jsonrpcCall("workspaceService.set_workspace_permissions", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_user_settings</p>
     * <pre>
     * Retrieves settings for user account, including currently selected workspace
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.GetUserSettingsParams GetUserSettingsParams} (original type "get_user_settings_params")
     * @return   parameter "output" of type {@link us.kbase.workspaceservice.UserSettings UserSettings} (original type "user_settings")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public UserSettings getUserSettings(GetUserSettingsParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<UserSettings>> retType = new TypeReference<List<UserSettings>>() {};
        List<UserSettings> res = caller.jsonrpcCall("workspaceService.get_user_settings", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: set_user_settings</p>
     * <pre>
     * Retrieves settings for user account, including currently selected workspace
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.SetUserSettingsParams SetUserSettingsParams} (original type "set_user_settings_params")
     * @return   parameter "output" of type {@link us.kbase.workspaceservice.UserSettings UserSettings} (original type "user_settings")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public UserSettings setUserSettings(SetUserSettingsParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<UserSettings>> retType = new TypeReference<List<UserSettings>>() {};
        List<UserSettings> res = caller.jsonrpcCall("workspaceService.set_user_settings", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: queue_job</p>
     * <pre>
     * Queues a new job in the workspace.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.QueueJobParams QueueJobParams} (original type "queue_job_params")
     * @return   parameter "job" of type {@link us.kbase.workspaceservice.JobObject JobObject}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public JobObject queueJob(QueueJobParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<JobObject>> retType = new TypeReference<List<JobObject>>() {};
        List<JobObject> res = caller.jsonrpcCall("workspaceService.queue_job", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: set_job_status</p>
     * <pre>
     * Changes the current status of a currently queued jobs 
     * Used to manage jobs by ensuring multiple server don't claim the same job.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.SetJobStatusParams SetJobStatusParams} (original type "set_job_status_params")
     * @return   parameter "job" of type {@link us.kbase.workspaceservice.JobObject JobObject}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public JobObject setJobStatus(SetJobStatusParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<JobObject>> retType = new TypeReference<List<JobObject>>() {};
        List<JobObject> res = caller.jsonrpcCall("workspaceService.set_job_status", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_jobs</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.GetJobsParams GetJobsParams} (original type "get_jobs_params")
     * @return   parameter "jobs" of list of type {@link us.kbase.workspaceservice.JobObject JobObject}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<JobObject> getJobs(GetJobsParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<List<JobObject>>> retType = new TypeReference<List<List<JobObject>>>() {};
        List<List<JobObject>> res = caller.jsonrpcCall("workspaceService.get_jobs", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_types</p>
     * <pre>
     * Returns a list of all permanent and optional types currently accepted by the workspace service.
     * An object cannot be saved in any workspace if it's type is not on this list.
     * </pre>
     * @return   parameter "types" of list of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getTypes() throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("workspaceService.get_types", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: add_type</p>
     * <pre>
     * Adds a new custom type to the workspace service, so that objects of this type may be retreived.
     * Cannot add a type that already exists.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.AddTypeParams AddTypeParams} (original type "add_type_params")
     * @return   parameter "success" of original type "bool" (indicates true or false values, false <= 0, true >=1)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Long addType(AddTypeParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Long>> retType = new TypeReference<List<Long>>() {};
        List<Long> res = caller.jsonrpcCall("workspaceService.add_type", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: remove_type</p>
     * <pre>
     * Removes a custom type from the workspace service.
     * Permanent types cannot be removed.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.RemoveTypeParams RemoveTypeParams} (original type "remove_type_params")
     * @return   parameter "success" of original type "bool" (indicates true or false values, false <= 0, true >=1)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Long removeType(RemoveTypeParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Long>> retType = new TypeReference<List<Long>>() {};
        List<Long> res = caller.jsonrpcCall("workspaceService.remove_type", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: patch</p>
     * <pre>
     * This function patches the database after an update. Called remotely, but only callable by the admin user.
     * </pre>
     * @param   params   instance of type {@link us.kbase.workspaceservice.PatchParams PatchParams} (original type "patch_params")
     * @return   parameter "success" of original type "bool" (indicates true or false values, false <= 0, true >=1)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Long patch(PatchParams params) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<Long>> retType = new TypeReference<List<Long>>() {};
        List<Long> res = caller.jsonrpcCall("workspaceService.patch", args, retType, true, false);
        return res.get(0);
    }
}
