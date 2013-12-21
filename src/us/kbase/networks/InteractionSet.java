
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
 * <p>Original spec-file type: InteractionSet</p>
 * <pre>
 * Represents a set of interactions
 * string id - interaction set identifier
 * string name - interaction set name
 *                 string type - interaction set type. If specified, all interactions are expected to be of the same type.
 * \                string description - interaction set description
 *                 DatasetSource source - source
 *                 list<taxon> taxons - taxons
 *               list<Interaction> interactions - list of interactions
 *                @optional description type taxons
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
    "source",
    "taxons",
    "interactions"
})
public class InteractionSet {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("description")
    private java.lang.String description;
    @JsonProperty("type")
    private java.lang.String type;
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
    @JsonProperty("source")
    private DatasetSource source;
    @JsonProperty("taxons")
    private List<String> taxons;
    @JsonProperty("interactions")
    private List<Interaction> interactions;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public InteractionSet withId(java.lang.String id) {
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

    public InteractionSet withName(java.lang.String name) {
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

    public InteractionSet withDescription(java.lang.String description) {
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

    public InteractionSet withType(java.lang.String type) {
        this.type = type;
        return this;
    }

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
    @JsonProperty("source")
    public DatasetSource getSource() {
        return source;
    }

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
    @JsonProperty("source")
    public void setSource(DatasetSource source) {
        this.source = source;
    }

    public InteractionSet withSource(DatasetSource source) {
        this.source = source;
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

    public InteractionSet withTaxons(List<String> taxons) {
        this.taxons = taxons;
        return this;
    }

    @JsonProperty("interactions")
    public List<Interaction> getInteractions() {
        return interactions;
    }

    @JsonProperty("interactions")
    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    public InteractionSet withInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
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
        return ((((((((((((((((("InteractionSet"+" [id=")+ id)+", name=")+ name)+", description=")+ description)+", type=")+ type)+", source=")+ source)+", taxons=")+ taxons)+", interactions=")+ interactions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
