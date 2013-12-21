
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
 * <p>Original spec-file type: ExpressionPlatform</p>
 * <pre>
 * Data structure for the workspace expression platform.  The ExpressionPlatform typed object.
 * source_id defaults to kb_id if not set, but typically referes to a GPL if the data is from GEO.
 * @optional strain
 * @searchable ws_subset source_id kb_id genome_id title technology
 * @searchable ws_subset strain.genome_id  strain.reference_strain strain.wild_type
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "kb_id",
    "source_id",
    "genome_id",
    "strain",
    "technology",
    "title"
})
public class ExpressionPlatform {

    @JsonProperty("kb_id")
    private String kbId;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("genome_id")
    private String genomeId;
    /**
     * <p>Original spec-file type: Strain</p>
     * <pre>
     * Data structure for Strain  (TEMPORARY WORKSPACE TYPED OBJECT SHOULD BE HANDLED IN THE FUTURE IN WORKSPACE COMMON)
     * </pre>
     * 
     */
    @JsonProperty("strain")
    private Strain strain;
    @JsonProperty("technology")
    private String technology;
    @JsonProperty("title")
    private String title;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("kb_id")
    public String getKbId() {
        return kbId;
    }

    @JsonProperty("kb_id")
    public void setKbId(String kbId) {
        this.kbId = kbId;
    }

    public ExpressionPlatform withKbId(String kbId) {
        this.kbId = kbId;
        return this;
    }

    @JsonProperty("source_id")
    public String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public ExpressionPlatform withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("genome_id")
    public String getGenomeId() {
        return genomeId;
    }

    @JsonProperty("genome_id")
    public void setGenomeId(String genomeId) {
        this.genomeId = genomeId;
    }

    public ExpressionPlatform withGenomeId(String genomeId) {
        this.genomeId = genomeId;
        return this;
    }

    /**
     * <p>Original spec-file type: Strain</p>
     * <pre>
     * Data structure for Strain  (TEMPORARY WORKSPACE TYPED OBJECT SHOULD BE HANDLED IN THE FUTURE IN WORKSPACE COMMON)
     * </pre>
     * 
     */
    @JsonProperty("strain")
    public Strain getStrain() {
        return strain;
    }

    /**
     * <p>Original spec-file type: Strain</p>
     * <pre>
     * Data structure for Strain  (TEMPORARY WORKSPACE TYPED OBJECT SHOULD BE HANDLED IN THE FUTURE IN WORKSPACE COMMON)
     * </pre>
     * 
     */
    @JsonProperty("strain")
    public void setStrain(Strain strain) {
        this.strain = strain;
    }

    public ExpressionPlatform withStrain(Strain strain) {
        this.strain = strain;
        return this;
    }

    @JsonProperty("technology")
    public String getTechnology() {
        return technology;
    }

    @JsonProperty("technology")
    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public ExpressionPlatform withTechnology(String technology) {
        this.technology = technology;
        return this;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public ExpressionPlatform withTitle(String title) {
        this.title = title;
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
        return ((((((((((((((("ExpressionPlatform"+" [kbId=")+ kbId)+", sourceId=")+ sourceId)+", genomeId=")+ genomeId)+", strain=")+ strain)+", technology=")+ technology)+", title=")+ title)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
