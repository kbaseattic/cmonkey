
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
 * <p>Original spec-file type: copy_object_params</p>
 * <pre>
 * Input parameters for the "copy_object" function.
 *         object_type type - type of the object to be copied (an essential argument)
 *         workspace_id source_workspace - ID of the workspace containing the object to be copied (an essential argument)
 *         object_id source_id - ID of the object to be copied (an essential argument)
 *         int instance - Version of the object to be copied, enabling retrieval of any previous version of an object (an optional argument; the current object is copied if no version is provides)
 *         workspace_id new_workspace - ID of the workspace the object to be copied to (an essential argument)
 *         object_id new_id - ID the object is to be copied to (an essential argument)
 *         string new_workspace_url - URL of workspace server where object should be copied (an optional argument - object will be saved in the same server if not provided)
 *         string auth - the authentication token of the KBase account to associate with this object copy command (an optional argument)
 *         bool asHash - a boolean indicating if metadata should be returned as a hash
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "new_workspace_url",
    "new_id",
    "new_workspace",
    "source_id",
    "instance",
    "type",
    "source_workspace",
    "auth",
    "asHash"
})
public class CopyObjectParams {

    @JsonProperty("new_workspace_url")
    private String newWorkspaceUrl;
    @JsonProperty("new_id")
    private String newId;
    @JsonProperty("new_workspace")
    private String newWorkspace;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("instance")
    private Long instance;
    @JsonProperty("type")
    private String type;
    @JsonProperty("source_workspace")
    private String sourceWorkspace;
    @JsonProperty("auth")
    private String auth;
    @JsonProperty("asHash")
    private Long asHash;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("new_workspace_url")
    public String getNewWorkspaceUrl() {
        return newWorkspaceUrl;
    }

    @JsonProperty("new_workspace_url")
    public void setNewWorkspaceUrl(String newWorkspaceUrl) {
        this.newWorkspaceUrl = newWorkspaceUrl;
    }

    public CopyObjectParams withNewWorkspaceUrl(String newWorkspaceUrl) {
        this.newWorkspaceUrl = newWorkspaceUrl;
        return this;
    }

    @JsonProperty("new_id")
    public String getNewId() {
        return newId;
    }

    @JsonProperty("new_id")
    public void setNewId(String newId) {
        this.newId = newId;
    }

    public CopyObjectParams withNewId(String newId) {
        this.newId = newId;
        return this;
    }

    @JsonProperty("new_workspace")
    public String getNewWorkspace() {
        return newWorkspace;
    }

    @JsonProperty("new_workspace")
    public void setNewWorkspace(String newWorkspace) {
        this.newWorkspace = newWorkspace;
    }

    public CopyObjectParams withNewWorkspace(String newWorkspace) {
        this.newWorkspace = newWorkspace;
        return this;
    }

    @JsonProperty("source_id")
    public String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public CopyObjectParams withSourceId(String sourceId) {
        this.sourceId = sourceId;
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

    public CopyObjectParams withInstance(Long instance) {
        this.instance = instance;
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

    public CopyObjectParams withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("source_workspace")
    public String getSourceWorkspace() {
        return sourceWorkspace;
    }

    @JsonProperty("source_workspace")
    public void setSourceWorkspace(String sourceWorkspace) {
        this.sourceWorkspace = sourceWorkspace;
    }

    public CopyObjectParams withSourceWorkspace(String sourceWorkspace) {
        this.sourceWorkspace = sourceWorkspace;
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

    public CopyObjectParams withAuth(String auth) {
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

    public CopyObjectParams withAsHash(Long asHash) {
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
        return ((((((((((((((((((((("CopyObjectParams"+" [newWorkspaceUrl=")+ newWorkspaceUrl)+", newId=")+ newId)+", newWorkspace=")+ newWorkspace)+", sourceId=")+ sourceId)+", instance=")+ instance)+", type=")+ type)+", sourceWorkspace=")+ sourceWorkspace)+", auth=")+ auth)+", asHash=")+ asHash)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
