
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
 * <p>Original spec-file type: get_object_by_ref_params</p>
 * <pre>
 * Input parameters for the "get_object_by_ref" function.
 *         workspace_ref reference - reference to a specific instance of a specific object in a workspace (an essential argument)
 *         string auth - the authentication token of the KBase account to associate with this object retrieval command (an optional argument)
 *         bool asHash - a boolean indicating if metadata should be returned as a hash
 *         bool asJSON - indicates that data should be returned in JSON format (an optional argument; default is '0')
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "reference",
    "auth",
    "asHash",
    "asJSON"
})
public class GetObjectByRefParams {

    @JsonProperty("reference")
    private String reference;
    @JsonProperty("auth")
    private String auth;
    @JsonProperty("asHash")
    private Long asHash;
    @JsonProperty("asJSON")
    private Long asJSON;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    public GetObjectByRefParams withReference(String reference) {
        this.reference = reference;
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

    public GetObjectByRefParams withAuth(String auth) {
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

    public GetObjectByRefParams withAsHash(Long asHash) {
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

    public GetObjectByRefParams withAsJSON(Long asJSON) {
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
        return ((((((((((("GetObjectByRefParams"+" [reference=")+ reference)+", auth=")+ auth)+", asHash=")+ asHash)+", asJSON=")+ asJSON)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
