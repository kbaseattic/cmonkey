

function Cmonkey(url, auth, auth_cb) {

    var _url = url;
    var deprecationWarningSent = false;
    
    function deprecationWarning() {
        if (!deprecationWarningSent) {
            deprecationWarningSent = true;
            if (!window.console) return;
            console.log(
                "DEPRECATION WARNING: '*_async' method names will be removed",
                "in a future version. Please use the identical methods without",
                "the'_async' suffix.");
        }
    }

    var _auth = auth ? auth : { 'token' : '', 'user_id' : ''};
    var _auth_cb = auth_cb;


    this.build_cmonkey_network = function (series, params, _callback, _errorCallback) {
    return json_call_ajax("Cmonkey.build_cmonkey_network",
        [series, params], 1, _callback, _errorCallback);
};

    this.build_cmonkey_network_async = function (series, params, _callback, _error_callback) {
        deprecationWarning();
        return json_call_ajax("Cmonkey.build_cmonkey_network", [series, params], 1, _callback, _error_callback);
    };

    this.build_cmonkey_network_from_ws = function (ws_id, collection_id, params, _callback, _errorCallback) {
    return json_call_ajax("Cmonkey.build_cmonkey_network_from_ws",
        [ws_id, collection_id, params], 1, _callback, _errorCallback);
};

    this.build_cmonkey_network_from_ws_async = function (ws_id, collection_id, params, _callback, _error_callback) {
        deprecationWarning();
        return json_call_ajax("Cmonkey.build_cmonkey_network_from_ws", [ws_id, collection_id, params], 1, _callback, _error_callback);
    };

    this.build_cmonkey_network_job_from_ws = function (ws_id, series_id, params, _callback, _errorCallback) {
    return json_call_ajax("Cmonkey.build_cmonkey_network_job_from_ws",
        [ws_id, series_id, params], 1, _callback, _errorCallback);
};

    this.build_cmonkey_network_job_from_ws_async = function (ws_id, series_id, params, _callback, _error_callback) {
        deprecationWarning();
        return json_call_ajax("Cmonkey.build_cmonkey_network_job_from_ws", [ws_id, series_id, params], 1, _callback, _error_callback);
    };
 

    /*
     * JSON call using jQuery method.
     */
    function json_call_ajax(method, params, numRets, callback, errorCallback) {
        var deferred = $.Deferred();

        if (typeof callback === 'function') {
           deferred.done(callback);
        }

        if (typeof errorCallback === 'function') {
           deferred.fail(errorCallback);
        }

        var rpc = {
            params : params,
            method : method,
            version: "1.1",
            id: String(Math.random()).slice(2),
        };
        
        var beforeSend = null;
        var token = (_auth_cb && typeof _auth_cb === 'function') ? _auth_cb()
            : (_auth.token ? _auth.token : null);
        if (token != null) {
            beforeSend = function (xhr) {
                xhr.setRequestHeader("Authorization", token);
            }
        }

        jQuery.ajax({
            url: _url,
            dataType: "text",
            type: 'POST',
            processData: false,
            data: JSON.stringify(rpc),
            beforeSend: beforeSend,
            success: function (data, status, xhr) {
                var result;
                try {
                    var resp = JSON.parse(data);
                    result = (numRets === 1 ? resp.result[0] : resp.result);
                } catch (err) {
                    deferred.reject({
                        status: 503,
                        error: err,
                        url: _url,
                        resp: data
                    });
                    return;
                }
                deferred.resolve(result);
            },
            error: function (xhr, textStatus, errorThrown) {
                var error;
                if (xhr.responseText) {
                    try {
                        var resp = JSON.parse(xhr.responseText);
                        error = resp.error;
                    } catch (err) { // Not JSON
                        error = "Unknown error - " + xhr.responseText;
                    }
                } else {
                    error = "Unknown Error";
                }
                deferred.reject({
                    status: 500,
                    error: error
                });
            }
        });
        return deferred.promise();
    }
}


