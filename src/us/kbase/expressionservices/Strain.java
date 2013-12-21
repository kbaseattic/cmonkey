
package us.kbase.expressionservices;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: Strain</p>
 * <pre>
 * Data structure for Strain  (TEMPORARY WORKSPACE TYPED OBJECT SHOULD BE HANDLED IN THE FUTURE IN WORKSPACE COMMON)
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "genome_id",
    "reference_strain",
    "wild_type",
    "description",
    "name"
})
public class Strain {

    @JsonProperty("genome_id")
    private String genomeId;
    @JsonProperty("reference_strain")
    private String referenceStrain;
    @JsonProperty("wild_type")
    private String wildType;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("genome_id")
    public String getGenomeId() {
        return genomeId;
    }

    @JsonProperty("genome_id")
    public void setGenomeId(String genomeId) {
        this.genomeId = genomeId;
    }

    public Strain withGenomeId(String genomeId) {
        this.genomeId = genomeId;
        return this;
    }

    @JsonProperty("reference_strain")
    public String getReferenceStrain() {
        return referenceStrain;
    }

    @JsonProperty("reference_strain")
    public void setReferenceStrain(String referenceStrain) {
        this.referenceStrain = referenceStrain;
    }

    public Strain withReferenceStrain(String referenceStrain) {
        this.referenceStrain = referenceStrain;
        return this;
    }

    @JsonProperty("wild_type")
    public String getWildType() {
        return wildType;
    }

    @JsonProperty("wild_type")
    public void setWildType(String wildType) {
        this.wildType = wildType;
    }

    public Strain withWildType(String wildType) {
        this.wildType = wildType;
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

    public Strain withDescription(String description) {
        this.description = description;
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

    public Strain withName(String name) {
        this.name = name;
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
        return ((((((((((((("Strain"+" [genomeId=")+ genomeId)+", referenceStrain=")+ referenceStrain)+", wildType=")+ wildType)+", description=")+ description)+", name=")+ name)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
