
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
 * <p>Original spec-file type: Interaction</p>
 * <pre>
 * Represents a single entity-entity interaction
 * string id - id of interaction
 *                string entity1_id - entity1 identifier
 *                string entity2_id - entity2 identifier
 * string type          - type of interaction
 * float strength          - strength of interaction
 * float confidence  - confidence of interaction
 * mapping<string,float> scores - various types of scores. 
 *         Known score types: 
 *                 GENE_DISTANCE - distance between genes on a chromosome 
 *                 CONSERVATION_SCORE - conservation, ranging from 0 (not conserved) to 1 (100% conserved)
 *                 GO_SCORE - Smallest shared GO category, as a fraction of the genome, or missing if one of the genes is not characterized
 *                 STRING_SCORE - STRING score
 *                 COG_SIM        - whether the genes share (1) a COG category or not (0)
 *                 EXPR_SIM - correlation of expression patterns
 *                 SAME_OPERON - whether the pair is predicted to lie (1) in the same operon or not (0)
 *                 SAME_OPERON_PROB - estimated probability that the pair is in the same operon. Values near 1 or 0 are confident predictions of being in the same operon or not, while values near 0.5 are low-confidence predictions.
 *                @optional id type strength confidence scores
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "entity1_id",
    "entity2_id",
    "type",
    "strength",
    "confidence",
    "scores"
})
public class Interaction {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("entity1_id")
    private java.lang.String entity1Id;
    @JsonProperty("entity2_id")
    private java.lang.String entity2Id;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("strength")
    private java.lang.Double strength;
    @JsonProperty("confidence")
    private java.lang.Double confidence;
    @JsonProperty("scores")
    private Map<String, Double> scores;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Interaction withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("entity1_id")
    public java.lang.String getEntity1Id() {
        return entity1Id;
    }

    @JsonProperty("entity1_id")
    public void setEntity1Id(java.lang.String entity1Id) {
        this.entity1Id = entity1Id;
    }

    public Interaction withEntity1Id(java.lang.String entity1Id) {
        this.entity1Id = entity1Id;
        return this;
    }

    @JsonProperty("entity2_id")
    public java.lang.String getEntity2Id() {
        return entity2Id;
    }

    @JsonProperty("entity2_id")
    public void setEntity2Id(java.lang.String entity2Id) {
        this.entity2Id = entity2Id;
    }

    public Interaction withEntity2Id(java.lang.String entity2Id) {
        this.entity2Id = entity2Id;
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

    public Interaction withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("strength")
    public java.lang.Double getStrength() {
        return strength;
    }

    @JsonProperty("strength")
    public void setStrength(java.lang.Double strength) {
        this.strength = strength;
    }

    public Interaction withStrength(java.lang.Double strength) {
        this.strength = strength;
        return this;
    }

    @JsonProperty("confidence")
    public java.lang.Double getConfidence() {
        return confidence;
    }

    @JsonProperty("confidence")
    public void setConfidence(java.lang.Double confidence) {
        this.confidence = confidence;
    }

    public Interaction withConfidence(java.lang.Double confidence) {
        this.confidence = confidence;
        return this;
    }

    @JsonProperty("scores")
    public Map<String, Double> getScores() {
        return scores;
    }

    @JsonProperty("scores")
    public void setScores(Map<String, Double> scores) {
        this.scores = scores;
    }

    public Interaction withScores(Map<String, Double> scores) {
        this.scores = scores;
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
        return ((((((((((((((((("Interaction"+" [id=")+ id)+", entity1Id=")+ entity1Id)+", entity2Id=")+ entity2Id)+", type=")+ type)+", strength=")+ strength)+", confidence=")+ confidence)+", scores=")+ scores)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
