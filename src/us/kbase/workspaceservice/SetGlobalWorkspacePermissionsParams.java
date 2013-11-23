
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
 * <p>Original spec-file type: set_global_workspace_permissions_params</p>
 * <pre>
 * Input parameters for the "set_global_workspace_permissions" function.
 *         workspace_id workspace - ID of the workspace for which permissions will be set (an essential argument)
 *         permission new_permission - New default permissions to which the workspace should be set. Accepted values are 'a' => admin, 'w' => write, 'r' => read, 'n' => none (an essential argument)
 *         string auth - the authentication token of the KBase account changing workspace default permissions; must have 'admin' privelages to workspace
 *         bool asHash - a boolean indicating if metadata should be returned as a hash
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "new_permission",
    "workspace",
    "auth",
    "asHash"
})
public class SetGlobalWorkspacePermissionsParams {

    @JsonProperty("new_permission")
    private String newPermission;
    @JsonProperty("workspace")
    private String workspace;
    @JsonProperty("auth")
    private String auth;
    @JsonProperty("asHash")
    private Long asHash;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("new_permission")
    public String getNewPermission() {
        return newPermission;
    }

    @JsonProperty("new_permission")
    public void setNewPermission(String newPermission) {
        this.newPermission = newPermission;
    }

    public SetGlobalWorkspacePermissionsParams withNewPermission(String newPermission) {
        this.newPermission = newPermission;
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

    public SetGlobalWorkspacePermissionsParams withWorkspace(String workspace) {
        this.workspace = workspace;
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

    public SetGlobalWorkspacePermissionsParams withAuth(String auth) {
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

    public SetGlobalWorkspacePermissionsParams withAsHash(Long asHash) {
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
        return ((((((((((("SetGlobalWorkspacePermissionsParams"+" [newPermission=")+ newPermission)+", workspace=")+ workspace)+", auth=")+ auth)+", asHash=")+ asHash)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
