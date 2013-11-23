
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
 * <p>Original spec-file type: import_bio_params</p>
 * <pre>
 * Input parameters for the "import_bio" function.
 *         object_id bioid - ID of biochemistry to be imported (an optional argument with default "default")
 *         workspace_id bioWS - ID of workspace to which biochemistry will be imported (an optional argument with default "kbase")
 *         string url - URL from which biochemistry should be retrieved
 *         bool compressed - boolean indicating if biochemistry is compressed
 *         bool overwrite - A boolean indicating if a matching existing biochemistry should be overwritten (an optional argument with default "0")
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "bioid",
    "bioWS",
    "url",
    "compressed",
    "clearExisting",
    "overwrite",
    "auth",
    "asHash"
})
public class ImportBioParams {

    @JsonProperty("bioid")
    private String bioid;
    @JsonProperty("bioWS")
    private String bioWS;
    @JsonProperty("url")
    private String url;
    @JsonProperty("compressed")
    private Long compressed;
    @JsonProperty("clearExisting")
    private Long clearExisting;
    @JsonProperty("overwrite")
    private Long overwrite;
    @JsonProperty("auth")
    private String auth;
    @JsonProperty("asHash")
    private Long asHash;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bioid")
    public String getBioid() {
        return bioid;
    }

    @JsonProperty("bioid")
    public void setBioid(String bioid) {
        this.bioid = bioid;
    }

    public ImportBioParams withBioid(String bioid) {
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

    public ImportBioParams withBioWS(String bioWS) {
        this.bioWS = bioWS;
        return this;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public ImportBioParams withUrl(String url) {
        this.url = url;
        return this;
    }

    @JsonProperty("compressed")
    public Long getCompressed() {
        return compressed;
    }

    @JsonProperty("compressed")
    public void setCompressed(Long compressed) {
        this.compressed = compressed;
    }

    public ImportBioParams withCompressed(Long compressed) {
        this.compressed = compressed;
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

    public ImportBioParams withClearExisting(Long clearExisting) {
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

    public ImportBioParams withOverwrite(Long overwrite) {
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

    public ImportBioParams withAuth(String auth) {
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

    public ImportBioParams withAsHash(Long asHash) {
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
        return ((((((((((((((((((("ImportBioParams"+" [bioid=")+ bioid)+", bioWS=")+ bioWS)+", url=")+ url)+", compressed=")+ compressed)+", clearExisting=")+ clearExisting)+", overwrite=")+ overwrite)+", auth=")+ auth)+", asHash=")+ asHash)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
