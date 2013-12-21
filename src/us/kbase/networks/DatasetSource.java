
package us.kbase.networks;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: DatasetSource</p>
 * <pre>
 * Provides detailed information about the source of a dataset.
 * string id - A unique  identifier of a dataset source
 * string name - A name of a dataset source
 *         dataset_source_ref reference - Reference to a dataset source
 *         string description - General description of a dataset source
 *         string resourceURL - URL of the public web resource hosting the data represented by this dataset source
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "reference",
    "description",
    "resource_url"
})
public class DatasetSource {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("description")
    private String description;
    @JsonProperty("resource_url")
    private String resourceUrl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public DatasetSource withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public DatasetSource withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    public DatasetSource withReference(String reference) {
        this.reference = reference;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public DatasetSource withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("resource_url")
    public String getResourceUrl() {
        return resourceUrl;
    }

    @JsonProperty("resource_url")
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public DatasetSource withResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
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
        return ((((((((((((("DatasetSource"+" [id=")+ id)+", name=")+ name)+", reference=")+ reference)+", description=")+ description)+", resourceUrl=")+ resourceUrl)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
