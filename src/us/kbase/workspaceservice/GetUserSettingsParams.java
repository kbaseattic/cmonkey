
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
 * <p>Original spec-file type: get_user_settings_params</p>
 * <pre>
 * Input parameters for the "get_user_settings" function.
 *         string auth - the authentication token of the KBase account changing workspace permissions; must have 'admin' privelages to workspace
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "auth"
})
public class GetUserSettingsParams {

    @JsonProperty("auth")
    private String auth;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("auth")
    public String getAuth() {
        return auth;
    }

    @JsonProperty("auth")
    public void setAuth(String auth) {
        this.auth = auth;
    }

    public GetUserSettingsParams withAuth(String auth) {
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
        return ((((("GetUserSettingsParams"+" [auth=")+ auth)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
