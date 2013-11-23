
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
 * <p>Original spec-file type: revert_object_params</p>
 * <pre>
 * Input parameters for the "revert_object" function.
 *         object_type type - type of the object to be reverted (an essential argument)
 *         workspace_id workspace - ID of the workspace containing the object to be reverted (an essential argument)
 *         object_id id - ID of the object to be reverted (an essential argument)
 *         int instance - Previous version of the object to which the object should be reset (an essential argument)
 *         string auth - the authentication token of the KBase account to associate with this object reversion command
 *         bool asHash - a boolean indicating if metadata should be returned as a hash
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
    "asHash"
})
public class RevertObjectParams {

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
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public RevertObjectParams withId(String id) {
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

    public RevertObjectParams withType(String type) {
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

    public RevertObjectParams withWorkspace(String workspace) {
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

    public RevertObjectParams withInstance(Long instance) {
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

    public RevertObjectParams withAuth(String auth) {
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

    public RevertObjectParams withAsHash(Long asHash) {
        this.asHash = asHash;
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
        return ((((((((((((((("RevertObjectParams"+" [id=")+ id)+", type=")+ type)+", workspace=")+ workspace)+", instance=")+ instance)+", auth=")+ auth)+", asHash=")+ asHash)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
