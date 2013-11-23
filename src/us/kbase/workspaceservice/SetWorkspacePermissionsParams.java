
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
 * <p>Original spec-file type: set_workspace_permissions_params</p>
 * <pre>
 * Input parameters for the "set_workspace_permissions" function.
 *         workspace_id workspace - ID of the workspace for which permissions will be set (an essential argument)
 *         list<username> users - list of users for which workspace privileges are to be reset (an essential argument)
 *         permission new_permission - New permissions to which all users in the user list will be set for the workspace. Accepted values are 'a' => admin, 'w' => write, 'r' => read, 'n' => none (an essential argument)
 *         string auth - the authentication token of the KBase account changing workspace permissions; must have 'admin' privelages to workspace
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "users",
    "new_permission",
    "workspace",
    "auth"
})
public class SetWorkspacePermissionsParams {

    @JsonProperty("users")
    private List<String> users;
    @JsonProperty("new_permission")
    private java.lang.String newPermission;
    @JsonProperty("workspace")
    private java.lang.String workspace;
    @JsonProperty("auth")
    private java.lang.String auth;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("users")
    public List<String> getUsers() {
        return users;
    }

    @JsonProperty("users")
    public void setUsers(List<String> users) {
        this.users = users;
    }

    public SetWorkspacePermissionsParams withUsers(List<String> users) {
        this.users = users;
        return this;
    }

    @JsonProperty("new_permission")
    public java.lang.String getNewPermission() {
        return newPermission;
    }

    @JsonProperty("new_permission")
    public void setNewPermission(java.lang.String newPermission) {
        this.newPermission = newPermission;
    }

    public SetWorkspacePermissionsParams withNewPermission(java.lang.String newPermission) {
        this.newPermission = newPermission;
        return this;
    }

    @JsonProperty("workspace")
    public java.lang.String getWorkspace() {
        return workspace;
    }

    @JsonProperty("workspace")
    public void setWorkspace(java.lang.String workspace) {
        this.workspace = workspace;
    }

    public SetWorkspacePermissionsParams withWorkspace(java.lang.String workspace) {
        this.workspace = workspace;
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

    public SetWorkspacePermissionsParams withAuth(java.lang.String auth) {
        this.auth = auth;
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
        return ((((((((((("SetWorkspacePermissionsParams"+" [users=")+ users)+", newPermission=")+ newPermission)+", workspace=")+ workspace)+", auth=")+ auth)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
