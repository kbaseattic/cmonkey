
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
 * <p>Original spec-file type: load_media_from_bio_params</p>
 * <pre>
 * Input parameters for the "load_media_from_bio" function.
 *         workspace_id mediaWS - ID of workspace where media will be loaded (an optional argument with default "KBaseMedia")
 *         object_id bioid - ID of biochemistry from which media will be loaded (an optional argument with default "default")
 *         workspace_id bioWS - ID of workspace with biochemistry from which media will be loaded (an optional argument with default "kbase")
 *         bool clearExisting - A boolean indicating if existing media in the specified workspace should be cleared (an optional argument with default "0")
 *         bool overwrite - A boolean indicating if a matching existing media should be overwritten (an optional argument with default "0")
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "mediaWS",
    "bioid",
    "bioWS",
    "clearExisting",
    "overwrite",
    "auth",
    "asHash"
})
public class LoadMediaFromBioParams {

    @JsonProperty("mediaWS")
    private String mediaWS;
    @JsonProperty("bioid")
    private String bioid;
    @JsonProperty("bioWS")
    private String bioWS;
    @JsonProperty("clearExisting")
    private Long clearExisting;
    @JsonProperty("overwrite")
    private Long overwrite;
    @JsonProperty("auth")
    private String auth;
    @JsonProperty("asHash")
    private Long asHash;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("mediaWS")
    public String getMediaWS() {
        return mediaWS;
    }

    @JsonProperty("mediaWS")
    public void setMediaWS(String mediaWS) {
        this.mediaWS = mediaWS;
    }

    public LoadMediaFromBioParams withMediaWS(String mediaWS) {
        this.mediaWS = mediaWS;
        return this;
    }

    @JsonProperty("bioid")
    public String getBioid() {
        return bioid;
    }

    @JsonProperty("bioid")
    public void setBioid(String bioid) {
        this.bioid = bioid;
    }

    public LoadMediaFromBioParams withBioid(String bioid) {
        this.bioid = bioid;
        return this;
    }

    @JsonProperty("bioWS")
    public String getBioWS() {
        return bioWS;
    }

    @JsonProperty("bioWS")
    public void setBioWS(String bioWS) {
        this.bioWS = bioWS;
    }

    public LoadMediaFromBioParams withBioWS(String bioWS) {
        this.bioWS = bioWS;
        return this;
    }

    @JsonProperty("clearExisting")
    public Long getClearExisting() {
        return clearExisting;
    }

    @JsonProperty("clearExisting")
    public void setClearExisting(Long clearExisting) {
        this.clearExisting = clearExisting;
    }

    public LoadMediaFromBioParams withClearExisting(Long clearExisting) {
        this.clearExisting = clearExisting;
        return this;
    }

    @JsonProperty("overwrite")
    public Long getOverwrite() {
        return overwrite;
    }

    @JsonProperty("overwrite")
    public void setOverwrite(Long overwrite) {
        this.overwrite = overwrite;
    }

    public LoadMediaFromBioParams withOverwrite(Long overwrite) {
        this.overwrite = overwrite;
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

    public LoadMediaFromBioParams withAuth(String auth) {
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

    public LoadMediaFromBioParams withAsHash(Long asHash) {
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
        return ((((((((((((((((("LoadMediaFromBioParams"+" [mediaWS=")+ mediaWS)+", bioid=")+ bioid)+", bioWS=")+ bioWS)+", clearExisting=")+ clearExisting)+", overwrite=")+ overwrite)+", auth=")+ auth)+", asHash=")+ asHash)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
