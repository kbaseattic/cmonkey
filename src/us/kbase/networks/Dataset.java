
package us.kbase.networks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: Dataset</p>
 * <pre>
 * Represents a particular dataset.
 * string id - A unique  identifier of a dataset 
 *         string name - The name of a dataset
 *         string description - Description of a dataset
 *         network_type networkType - Type of network that can be generated from a given dataset
 * dataset_source_ref sourceReference - Reference to a dataset source
 * list<taxon> taxons - A list of NCBI taxonomy ids of all organisms for which genomic features (genes, proteins, etc) are used in a given dataset 
 *         mapping<string,string> properties - Other properties
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "description",
    "type",
    "source_ref",
    "taxons",
    "properties"
})
public class Dataset {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("description")
    private java.lang.String description;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("source_ref")
    private java.lang.String sourceRef;
    @JsonProperty("taxons")
    private List<String> taxons;
    @JsonProperty("properties")
    private Map<String, String> properties;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Dataset withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public Dataset withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("description")
    public java.lang.String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public Dataset withDescription(java.lang.String description) {
        this.description = description;
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

    public Dataset withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("source_ref")
    public java.lang.String getSourceRef() {
        return sourceRef;
    }

    @JsonProperty("source_ref")
    public void setSourceRef(java.lang.String sourceRef) {
        this.sourceRef = sourceRef;
    }

    public Dataset withSourceRef(java.lang.String sourceRef) {
        this.sourceRef = sourceRef;
        return this;
    }

    @JsonProperty("taxons")
    public List<String> getTaxons() {
        return taxons;
    }

    @JsonProperty("taxons")
    public void setTaxons(List<String> taxons) {
        this.taxons = taxons;
    }

    public Dataset withTaxons(List<String> taxons) {
        this.taxons = taxons;
        return this;
    }

    @JsonProperty("properties")
    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonProperty("properties")
    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Dataset withProperties(Map<String, String> properties) {
        this.properties = properties;
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
        return ((((((((((((((((("Dataset"+" [id=")+ id)+", name=")+ name)+", description=")+ description)+", type=")+ type)+", sourceRef=")+ sourceRef)+", taxons=")+ taxons)+", properties=")+ properties)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
