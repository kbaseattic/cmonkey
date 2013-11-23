
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
 * <p>Original spec-file type: create_workspace_params</p>
 * <pre>
 * Input parameters for the "create_workspace" function.
 *         workspace_id workspace - ID of the workspace to be created (an essential argument)
 *         permission default_permission - Default permissions of the workspace to be created. Accepted values are 'a' => admin, 'w' => write, 'r' => read, 'n' => none (optional argument with default "n")
 *         string auth - the authentication token of the KBase account that will own the created workspace
 *         bool asHash - a boolean indicating if metadata should be returned as a hash
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "workspace",
    "default_permission",
    "auth",
    "asHash"
})
public class CreateWorkspaceParams {

    @JsonProperty("workspace")
    private String workspace;
    @JsonProperty("default_permission")
    private String defaultPermission;
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

    public CreateWorkspaceParams withWorkspace(String workspace) {
        this.workspace = workspace;
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

    public CreateWorkspaceParams withDefaultPermission(String defaultPermission) {
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

    public CreateWorkspaceParams withAuth(String auth) {
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

    public CreateWorkspaceParams withAsHash(Long asHash) {
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
        return ((((((((((("CreateWorkspaceParams"+" [workspace=")+ workspace)+", defaultPermission=")+ defaultPermission)+", auth=")+ auth)+", asHash=")+ asHash)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
