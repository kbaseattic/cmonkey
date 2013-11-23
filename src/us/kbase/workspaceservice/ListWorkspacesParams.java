
package us.kbase.workspaceservice;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: list_workspaces_params</p>
 * <pre>
 * Input parameters for the "list_workspaces" function.
 *         string auth - the authentication token of the KBase account accessing the list of workspaces (an optional argument)
 *         bool asHash - a boolean indicating if metadata should be returned as a hash
 *         bool excludeGlobal - if credentials are supplied and excludeGlobal is true exclude world readable workspaces
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "auth",
    "asHash",
    "excludeGlobal"
})
public class ListWorkspacesParams {

    @JsonProperty("auth")
    private String auth;
    @JsonProperty("asHash")
    private Long asHash;
    @JsonProperty("excludeGlobal")
    private Long excludeGlobal;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("auth")
    public String getAuth() {
        return auth;
    }

    @JsonProperty("auth")
    public void setAuth(String auth) {
        this.auth = auth;
    }

    public ListWorkspacesParams withAuth(String auth) {
        this.auth = auth;
        return this;
    }

    @JsonProperty("asHash")
    public Long getAsHash() {
        return asHash;
    }

    @JsonProperty("asHash")
    public void setAsHash(Long asHash) {
        this.asHash = asHash;
    }

    public ListWorkspacesParams withAsHash(Long asHash) {
        this.asHash = asHash;
        return this;
    }

    @JsonProperty("excludeGlobal")
    public Long getExcludeGlobal() {
        return excludeGlobal;
    }

    @JsonProperty("excludeGlobal")
    public void setExcludeGlobal(Long excludeGlobal) {
        this.excludeGlobal = excludeGlobal;
    }

    public ListWorkspacesParams withExcludeGlobal(Long excludeGlobal) {
        this.excludeGlobal = excludeGlobal;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return ((((((((("ListWorkspacesParams"+" [auth=")+ auth)+", asHash=")+ asHash)+", excludeGlobal=")+ excludeGlobal)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
