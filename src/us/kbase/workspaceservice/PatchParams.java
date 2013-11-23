
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
 * <p>Original spec-file type: patch_params</p>
 * <pre>
 * Input parameters for the "patch" function.
 * string patch_id - ID of the patch that should be run on the workspace
 * string auth - the authentication token of the KBase account removing a custom type
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "patch_id",
    "auth"
})
public class PatchParams {

    @JsonProperty("patch_id")
    private String patchId;
    @JsonProperty("auth")
    private String auth;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("patch_id")
    public String getPatchId() {
        return patchId;
    }

    @JsonProperty("patch_id")
    public void setPatchId(String patchId) {
        this.patchId = patchId;
    }

    public PatchParams withPatchId(String patchId) {
        this.patchId = patchId;
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

    public PatchParams withAuth(String auth) {
        this.auth = auth;
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
        return ((((((("PatchParams"+" [patchId=")+ patchId)+", auth=")+ auth)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
