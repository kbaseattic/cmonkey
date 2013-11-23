
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
 * <p>Original spec-file type: clone_workspace_params</p>
 * <pre>
 * Input parameters for the "clone_workspace" function.
 *         workspace_id current_workspace - ID of the workspace to be cloned (an essential argument)
 *         workspace_id new_workspace - ID of the workspace to which the cloned workspace will be copied (an essential argument)
 *         string new_workspace_url - URL of workspace server where workspace should be cloned (an optional argument - workspace will be cloned in the same server if not provided)
 *         permission default_permission - Default permissions of the workspace created by the cloning process. Accepted values are 'a' => admin, 'w' => write, 'r' => read, 'n' => none (an essential argument)
 *         string auth - the authentication token of the KBase account that will own the cloned workspace (an optional argument)
 *         bool asHash - a boolean indicating if metadata should be returned as a hash
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "new_workspace",
    "new_workspace_url",
    "current_workspace",
    "default_permission",
    "auth",
    "asHash"
})
public class CloneWorkspaceParams {

    @JsonProperty("new_workspace")
    private String newWorkspace;
    @JsonProperty("new_workspace_url")
    private String newWorkspaceUrl;
    @JsonProperty("current_workspace")
    private String currentWorkspace;
    @JsonProperty("default_permission")
    private String defaultPermission;
    @JsonProperty("auth")
    private String auth;
    @JsonProperty("asHash")
    private Long asHash;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("new_workspace")
    public String getNewWorkspace() {
        return newWorkspace;
    }

    @JsonProperty("new_workspace")
    public void setNewWorkspace(String newWorkspace) {
        this.newWorkspace = newWorkspace;
    }

    public CloneWorkspaceParams withNewWorkspace(String newWorkspace) {
        this.newWorkspace = newWorkspace;
        return this;
    }

    @JsonProperty("new_workspace_url")
    public String getNewWorkspaceUrl() {
        return newWorkspaceUrl;
    }

    @JsonProperty("new_workspace_url")
    public void setNewWorkspaceUrl(String newWorkspaceUrl) {
        this.newWorkspaceUrl = newWorkspaceUrl;
    }

    public CloneWorkspaceParams withNewWorkspaceUrl(String newWorkspaceUrl) {
        this.newWorkspaceUrl = newWorkspaceUrl;
        return this;
    }

    @JsonProperty("current_workspace")
    public String getCurrentWorkspace() {
        return currentWorkspace;
    }

    @JsonProperty("current_workspace")
    public void setCurrentWorkspace(String currentWorkspace) {
        this.currentWorkspace = currentWorkspace;
    }

    public CloneWorkspaceParams withCurrentWorkspace(String currentWorkspace) {
        this.currentWorkspace = currentWorkspace;
        return this;
    }

    @JsonProperty("default_permission")
    public String getDefaultPermission() {
        return defaultPermission;
    }

    @JsonProperty("default_permission")
    public void setDefaultPermission(String defaultPermission) {
        this.defaultPermission = defaultPermission;
    }

    public CloneWorkspaceParams withDefaultPermission(String defaultPermission) {
        this.defaultPermission = defaultPermission;
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

    public CloneWorkspaceParams withAuth(String auth) {
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

    public CloneWorkspaceParams withAsHash(Long asHash) {
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
        return ((((((((((((((("CloneWorkspaceParams"+" [newWorkspace=")+ newWorkspace)+", newWorkspaceUrl=")+ newWorkspaceUrl)+", currentWorkspace=")+ currentWorkspace)+", defaultPermission=")+ defaultPermission)+", auth=")+ auth)+", asHash=")+ asHash)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
