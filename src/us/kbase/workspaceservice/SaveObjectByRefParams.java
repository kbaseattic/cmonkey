
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
 * <p>Original spec-file type: save_object_by_ref_params</p>
 * <pre>
 * Input parameters for the "save_object_by_ref" function.
 *         object_id id - ID to which the model should be saved (an essential argument)
 *         object_type type - type of the object for which metadata is to be retrieved (an essential argument)
 *         ObjectData data - string or reference to complex datastructure to be saved in the workspace (an essential argument)
 *         string command - the name of the KBase command that is calling the "save_object" function (an optional argument with default "unknown")
 *         mapping<string,string> metadata - a hash of metadata to be associated with the object (an optional argument with default "{}")
 *         workspace_ref reference - reference the object should be saved in
 *         bool json - a flag indicating if the input data is encoded as a JSON string (an optional argument with default "0")
 *         bool compressed - a flag indicating if the input data in zipped (an optional argument with default "0")
 *         bool retrieveFromURL - a flag indicating that the "data" argument contains a URL from which the actual data should be downloaded (an optional argument with default "0")
 *         bool replace - a flag indicating any existing object located at the specified reference should be overwritten (an optional argument with default "0")
 *         string auth - the authentication token of the KBase account to associate this save command
 *         bool asHash - a boolean indicating if metadata should be returned as a hash
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "type",
    "data",
    "command",
    "metadata",
    "reference",
    "json",
    "compressed",
    "retrieveFromURL",
    "replace",
    "auth",
    "asHash"
})
public class SaveObjectByRefParams {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("type")
    private java.lang.String type;
    /**
     * <p>Original spec-file type: ObjectData</p>
     * <pre>
     * Generic definition for object data stored in the workspace
     * Data objects stored in the workspace could be either a string or a reference to a complex perl data structure. So we can't really formulate a strict type definition for this data.
     * version - for complex data structures, the datastructure should include a version number to enable tracking of changes that may occur to the structure of the data over time
     * </pre>
     * 
     */
    @JsonProperty("data")
    private ObjectData data;
    @JsonProperty("command")
    private java.lang.String command;
    @JsonProperty("metadata")
    private Map<String, String> metadata;
    @JsonProperty("reference")
    private java.lang.String reference;
    @JsonProperty("json")
    private Long json;
    @JsonProperty("compressed")
    private Long compressed;
    @JsonProperty("retrieveFromURL")
    private Long retrieveFromURL;
    @JsonProperty("replace")
    private Long replace;
    @JsonProperty("auth")
    private java.lang.String auth;
    @JsonProperty("asHash")
    private Long asHash;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public SaveObjectByRefParams withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("type")
    public java.lang.String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(java.lang.String type) {
        this.type = type;
    }

    public SaveObjectByRefParams withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    /**
     * <p>Original spec-file type: ObjectData</p>
     * <pre>
     * Generic definition for object data stored in the workspace
     * Data objects stored in the workspace could be either a string or a reference to a complex perl data structure. So we can't really formulate a strict type definition for this data.
     * version - for complex data structures, the datastructure should include a version number to enable tracking of changes that may occur to the structure of the data over time
     * </pre>
     * 
     */
    @JsonProperty("data")
    public ObjectData getData() {
        return data;
    }

    /**
     * <p>Original spec-file type: ObjectData</p>
     * <pre>
     * Generic definition for object data stored in the workspace
     * Data objects stored in the workspace could be either a string or a reference to a complex perl data structure. So we can't really formulate a strict type definition for this data.
     * version - for complex data structures, the datastructure should include a version number to enable tracking of changes that may occur to the structure of the data over time
     * </pre>
     * 
     */
    @JsonProperty("data")
    public void setData(ObjectData data) {
        this.data = data;
    }

    public SaveObjectByRefParams withData(ObjectData data) {
        this.data = data;
        return this;
    }

    @JsonProperty("command")
    public java.lang.String getCommand() {
        return command;
    }

    @JsonProperty("command")
    public void setCommand(java.lang.String command) {
        this.command = command;
    }

    public SaveObjectByRefParams withCommand(java.lang.String command) {
        this.command = command;
        return this;
    }

    @JsonProperty("metadata")
    public Map<String, String> getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public SaveObjectByRefParams withMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    @JsonProperty("reference")
    public java.lang.String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(java.lang.String reference) {
        this.reference = reference;
    }

    public SaveObjectByRefParams withReference(java.lang.String reference) {
        this.reference = reference;
        return this;
    }

    @JsonProperty("json")
    public Long getJson() {
        return json;
    }

    @JsonProperty("json")
    public void setJson(Long json) {
        this.json = json;
    }

    public SaveObjectByRefParams withJson(Long json) {
        this.json = json;
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

    public SaveObjectByRefParams withCompressed(Long compressed) {
        this.compressed = compressed;
        return this;
    }

    @JsonProperty("retrieveFromURL")
    public Long getRetrieveFromURL() {
        return retrieveFromURL;
    }

    @JsonProperty("retrieveFromURL")
    public void setRetrieveFromURL(Long retrieveFromURL) {
        this.retrieveFromURL = retrieveFromURL;
    }

    public SaveObjectByRefParams withRetrieveFromURL(Long retrieveFromURL) {
        this.retrieveFromURL = retrieveFromURL;
        return this;
    }

    @JsonProperty("replace")
    public Long getReplace() {
        return replace;
    }

    @JsonProperty("replace")
    public void setReplace(Long replace) {
        this.replace = replace;
    }

    public SaveObjectByRefParams withReplace(Long replace) {
        this.replace = replace;
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

    public SaveObjectByRefParams withAuth(java.lang.String auth) {
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

    public SaveObjectByRefParams withAsHash(Long asHash) {
        this.asHash = asHash;
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
        return ((((((((((((((((((((((((((("SaveObjectByRefParams"+" [id=")+ id)+", type=")+ type)+", data=")+ data)+", command=")+ command)+", metadata=")+ metadata)+", reference=")+ reference)+", json=")+ json)+", compressed=")+ compressed)+", retrieveFromURL=")+ retrieveFromURL)+", replace=")+ replace)+", auth=")+ auth)+", asHash=")+ asHash)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
