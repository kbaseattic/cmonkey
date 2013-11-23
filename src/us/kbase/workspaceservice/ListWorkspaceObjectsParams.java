
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
 * <p>Original spec-file type: list_workspace_objects_params</p>
 * <pre>
 * Input parameters for the "list_workspace_objects" function.
 *         workspace_id workspace - ID of the workspace for which objects should be listed (an essential argument)
 *         string type - type of the objects to be listed (an optional argument; all object types will be listed if left unspecified)
 *         bool showDeletedObject - a flag that, if set to '1', causes any deleted objects to be included in the output (an optional argument; default is '0')
 *         string auth - the authentication token of the KBase account listing workspace objects; must have at least 'read' privileges (an optional argument)
 *         bool asHash - a boolean indicating if metadata should be returned as a hash
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "workspace",
    "type",
    "showDeletedObject",
    "auth",
    "asHash"
})
public class ListWorkspaceObjectsParams {

    @JsonProperty("workspace")
    private String workspace;
    @JsonProperty("type")
    private String type;
    @JsonProperty("showDeletedObject")
    private Long showDeletedObject;
    @JsonProperty("auth")
    private String auth;
    @JsonProperty("asHash")
    private Long asHash;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("workspace")
    public String getWorkspace() {
        return workspace;
    }

    @JsonProperty("workspace")
    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public ListWorkspaceObjectsParams withWorkspace(String workspace) {
        this.workspace = workspace;
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

    public ListWorkspaceObjectsParams withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("showDeletedObject")
    public Long getShowDeletedObject() {
        return showDeletedObject;
    }

    @JsonProperty("showDeletedObject")
    public void setShowDeletedObject(Long showDeletedObject) {
        this.showDeletedObject = showDeletedObject;
    }

    public ListWorkspaceObjectsParams withShowDeletedObject(Long showDeletedObject) {
        this.showDeletedObject = showDeletedObject;
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

    public ListWorkspaceObjectsParams withAuth(String auth) {
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

    public ListWorkspaceObjectsParams withAsHash(Long asHash) {
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
        return ((((((((((((("ListWorkspaceObjectsParams"+" [workspace=")+ workspace)+", type=")+ type)+", showDeletedObject=")+ showDeletedObject)+", auth=")+ auth)+", asHash=")+ asHash)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
