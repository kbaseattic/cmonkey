
package us.kbase.workspaceservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: get_objects_params</p>
 * <pre>
 * Input parameters for the "get_object" function.
 *         list<object_id> ids - ID of the object to be retrieved (an essential argument)
 *         list<object_type> types - type of the object to be retrieved (an essential argument)
 *         list<workspace_id> workspaces - ID of the workspace containing the object to be retrieved (an essential argument)
 *         list<int> instances  - Version of the object to be retrieved, enabling retrieval of any previous version of an object (an optional argument; the current version is retrieved if no version is provides)
 *         string auth - the authentication token of the KBase account to associate with this object retrieval command (an optional argument; user is "public" if auth is not provided)
 *         bool asHash - a boolean indicating if metadata should be returned as a hash
 *         bool asJSON - indicates that data should be returned in JSON format (an optional argument; default is '0')
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ids",
    "types",
    "workspaces",
    "instances",
    "auth",
    "asHash",
    "asJSON"
})
public class GetObjectsParams {

    @JsonProperty("ids")
    private List<String> ids;
    @JsonProperty("types")
    private List<String> types;
    @JsonProperty("workspaces")
    private List<String> workspaces;
    @JsonProperty("instances")
    private List<Long> instances;
    @JsonProperty("auth")
    private java.lang.String auth;
    @JsonProperty("asHash")
    private java.lang.Long asHash;
    @JsonProperty("asJSON")
    private java.lang.Long asJSON;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ids")
    public List<String> getIds() {
        return ids;
    }

    @JsonProperty("ids")
    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public GetObjectsParams withIds(List<String> ids) {
        this.ids = ids;
        return this;
    }

    @JsonProperty("types")
    public List<String> getTypes() {
        return types;
    }

    @JsonProperty("types")
    public void setTypes(List<String> types) {
        this.types = types;
    }

    public GetObjectsParams withTypes(List<String> types) {
        this.types = types;
        return this;
    }

    @JsonProperty("workspaces")
    public List<String> getWorkspaces() {
        return workspaces;
    }

    @JsonProperty("workspaces")
    public void setWorkspaces(List<String> workspaces) {
        this.workspaces = workspaces;
    }

    public GetObjectsParams withWorkspaces(List<String> workspaces) {
        this.workspaces = workspaces;
        return this;
    }

    @JsonProperty("instances")
    public List<Long> getInstances() {
        return instances;
    }

    @JsonProperty("instances")
    public void setInstances(List<Long> instances) {
        this.instances = instances;
    }

    public GetObjectsParams withInstances(List<Long> instances) {
        this.instances = instances;
        return this;
    }

    @JsonProperty("auth")
    public java.lang.String getAuth() {
        return auth;
    }

    @JsonProperty("auth")
    public void setAuth(java.lang.String auth) {
        this.auth = auth;
    }

    public GetObjectsParams withAuth(java.lang.String auth) {
        this.auth = auth;
        return this;
    }

    @JsonProperty("asHash")
    public java.lang.Long getAsHash() {
        return asHash;
    }

    @JsonProperty("asHash")
    public void setAsHash(java.lang.Long asHash) {
        this.asHash = asHash;
    }

    public GetObjectsParams withAsHash(java.lang.Long asHash) {
        this.asHash = asHash;
        return this;
    }

    @JsonProperty("asJSON")
    public java.lang.Long getAsJSON() {
        return asJSON;
    }

    @JsonProperty("asJSON")
    public void setAsJSON(java.lang.Long asJSON) {
        this.asJSON = asJSON;
    }

    public GetObjectsParams withAsJSON(java.lang.Long asJSON) {
        this.asJSON = asJSON;
        return this;
    }

    @JsonAnyGetter
    public Map<java.lang.String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(java.lang.String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public java.lang.String toString() {
        return ((((((((((((((((("GetObjectsParams"+" [ids=")+ ids)+", types=")+ types)+", workspaces=")+ workspaces)+", instances=")+ instances)+", auth=")+ auth)+", asHash=")+ asHash)+", asJSON=")+ asJSON)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
