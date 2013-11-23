
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
 * <p>Original spec-file type: import_map_params</p>
 * <pre>
 * Input parameters for the "import_map" function.
 *         object_id mapid - ID of mapping to be imported (an optional argument with default "default")
 *         workspace_id mapWS - ID of workspace to which mapping will be imported (an optional argument with default "kbase")
 *         string url - URL from which mapping should be retrieved
 *         bool compressed - boolean indicating if mapping is compressed
 *         bool overwrite - A boolean indicating if a matching existing mapping should be overwritten (an optional argument with default "0")
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "bioid",
    "bioWS",
    "mapid",
    "mapWS",
    "url",
    "compressed",
    "overwrite",
    "auth",
    "asHash"
})
public class ImportMapParams {

    @JsonProperty("bioid")
    private String bioid;
    @JsonProperty("bioWS")
    private String bioWS;
    @JsonProperty("mapid")
    private String mapid;
    @JsonProperty("mapWS")
    private String mapWS;
    @JsonProperty("url")
    private String url;
    @JsonProperty("compressed")
    private Long compressed;
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

    public ImportMapParams withBioid(String bioid) {
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

    public ImportMapParams withBioWS(String bioWS) {
        this.bioWS = bioWS;
        return this;
    }

    @JsonProperty("mapid")
    public String getMapid() {
        return mapid;
    }

    @JsonProperty("mapid")
    public void setMapid(String mapid) {
        this.mapid = mapid;
    }

    public ImportMapParams withMapid(String mapid) {
        this.mapid = mapid;
        return this;
    }

    @JsonProperty("mapWS")
    public String getMapWS() {
        return mapWS;
    }

    @JsonProperty("mapWS")
    public void setMapWS(String mapWS) {
        this.mapWS = mapWS;
    }

    public ImportMapParams withMapWS(String mapWS) {
        this.mapWS = mapWS;
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

    public ImportMapParams withUrl(String url) {
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

    public ImportMapParams withCompressed(Long compressed) {
        this.compressed = compressed;
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

    public ImportMapParams withOverwrite(Long overwrite) {
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

    public ImportMapParams withAuth(String auth) {
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

    public ImportMapParams withAsHash(Long asHash) {
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
        return ((((((((((((((((((((("ImportMapParams"+" [bioid=")+ bioid)+", bioWS=")+ bioWS)+", mapid=")+ mapid)+", mapWS=")+ mapWS)+", url=")+ url)+", compressed=")+ compressed)+", overwrite=")+ overwrite)+", auth=")+ auth)+", asHash=")+ asHash)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
