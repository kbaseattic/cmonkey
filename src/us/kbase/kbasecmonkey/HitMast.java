
package us.kbase.kbasecmonkey;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * <p>Original spec-file type: HitMast</p>
 * <pre>
 * Represents a particular MAST hit
 * string sequenceName - name of sequence
 * string strand - strand ("+" or "-")
 * string motifName - name of motif
 * int hitStart - start position of hit
 * int hitEnd - end position of hit
 * float score - hit score
 * float hitPvalue - hit p-value
 * </pre>
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "sequenceName",
    "strand",
    "motifName",
    "hitStart",
    "hitEnd",
    "score",
    "hitPvalue"
})
public class HitMast {

    @JsonProperty("sequenceName")
    private String sequenceName;
    @JsonProperty("strand")
    private String strand;
    @JsonProperty("motifName")
    private String motifName;
    @JsonProperty("hitStart")
    private Integer hitStart;
    @JsonProperty("hitEnd")
    private Integer hitEnd;
    @JsonProperty("score")
    private Double score;
    @JsonProperty("hitPvalue")
    private Double hitPvalue;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sequenceName")
    public String getSequenceName() {
        return sequenceName;
    }

    @JsonProperty("sequenceName")
    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public HitMast withSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
        return this;
    }

    @JsonProperty("strand")
    public String getStrand() {
        return strand;
    }

    @JsonProperty("strand")
    public void setStrand(String strand) {
        this.strand = strand;
    }

    public HitMast withStrand(String strand) {
        this.strand = strand;
        return this;
    }

    @JsonProperty("motifName")
    public String getMotifName() {
        return motifName;
    }

    @JsonProperty("motifName")
    public void setMotifName(String motifName) {
        this.motifName = motifName;
    }

    public HitMast withMotifName(String motifName) {
        this.motifName = motifName;
        return this;
    }

    @JsonProperty("hitStart")
    public Integer getHitStart() {
        return hitStart;
    }

    @JsonProperty("hitStart")
    public void setHitStart(Integer hitStart) {
        this.hitStart = hitStart;
    }

    public HitMast withHitStart(Integer hitStart) {
        this.hitStart = hitStart;
        return this;
    }

    @JsonProperty("hitEnd")
    public Integer getHitEnd() {
        return hitEnd;
    }

    @JsonProperty("hitEnd")
    public void setHitEnd(Integer hitEnd) {
        this.hitEnd = hitEnd;
    }

    public HitMast withHitEnd(Integer hitEnd) {
        this.hitEnd = hitEnd;
        return this;
    }

    @JsonProperty("score")
    public Double getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Double score) {
        this.score = score;
    }

    public HitMast withScore(Double score) {
        this.score = score;
        return this;
    }

    @JsonProperty("hitPvalue")
    public Double getHitPvalue() {
        return hitPvalue;
    }

    @JsonProperty("hitPvalue")
    public void setHitPvalue(Double hitPvalue) {
        this.hitPvalue = hitPvalue;
    }

    public HitMast withHitPvalue(Double hitPvalue) {
        this.hitPvalue = hitPvalue;
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

}
