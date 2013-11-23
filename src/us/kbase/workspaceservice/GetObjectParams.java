
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
 * <p>Original spec-file type: get_object_params</p>
 * <pre>
 * Input parameters for the "get_object" function.
 *         object_type type - type of the object to be retrieved (an essential argument)
 *         workspace_id workspace - ID of the workspace containing the object to be retrieved (an essential argument)
 *         object_id id - ID of the object to be retrieved (an essential argument)
 *         int instance - Version of the object to be retrieved, enabling retrieval of any previous version of an object (an optional argument; the current version is retrieved if no version is provides)
 *         string auth - the authentication token of the KBase account to associate with this object retrieval command (an optional argument)
 *         bool asHash - a boolean indicating if metadata should be returned as a hash
 *         bool asJSON - indicates that data should be returned in JSON format (an optional argument; default is '0')
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "type",
    "workspace",
    "instance",
    "auth",
    "asHash",
    "asJSON"
})
public class GetObjectParams {

    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("workspace")
    private String workspace;
    @JsonProperty("instance")
    private Long instance;
    @JsonProperty("auth")
    private String auth;
    @JsonProperty("asHash")
    private Long asHash;
    @JsonProperty("asJSON")
    private Long asJSON;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public GetObjectParams withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public GetObjectParams withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("workspace")
    public String getWorkspace() {
        return workspace;
    }

    @JsonProperty("workspace")
    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public GetObjectParams withWorkspace(String workspace) {
        this.workspace = workspace;
        return this;
    }

    @JsonProperty("instance")
    public Long getInstance() {
        return instance;
    }

    @JsonProperty("instance")
    public void setInstance(Long instance) {
        this.instance = instance;
    }

    public GetObjectParams withInstance(Long instance) {
        this.instance = instance;
        return this;
    }

    @JsonProperty("auth")
    public String getAuth() {
        return auth;
    }

    @JsonProperty("auth")
    public void setAuth(String auth) {
        this.auth = auth;
    }

    public GetObjectParams withAuth(String auth) {
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

    public GetObjectParams withAsHash(Long asHash) {
        this.asHash = asHash;
        return this;
    }

    @JsonProperty("asJSON")
    public Long getAsJSON() {
        return asJSON;
    }

    @JsonProperty("asJSON")
    public void setAsJSON(Long asJSON) {
        this.asJSON = asJSON;
    }

    public GetObjectParams withAsJSON(Long asJSON) {
        this.asJSON = asJSON;
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
        return ((((((((((((((((("GetObjectParams"+" [id=")+ id)+", type=")+ type)+", workspace=")+ workspace)+", instance=")+ instance)+", auth=")+ auth)+", asHash=")+ asHash)+", asJSON=")+ asJSON)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
