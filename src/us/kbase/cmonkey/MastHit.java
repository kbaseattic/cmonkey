
package us.kbase.cmonkey;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: MastHit</p>
 * <pre>
 * Represents a particular MAST hit
 * string sequence_id - name of sequence
 * string strand - strand ("+" or "-")
 * string pssm_id - name of motif
 * int hit_start - start position of hit
 * int hit_end - end position of hit
 * float score - hit score
 * float hit_pvalue - hit p-value
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "sequence_id",
    "strand",
    "pssm_id",
    "hit_start",
    "hit_end",
    "score",
    "hit_pvalue"
})
public class MastHit {

    @JsonProperty("sequence_id")
    private String sequenceId;
    @JsonProperty("strand")
    private String strand;
    @JsonProperty("pssm_id")
    private String pssmId;
    @JsonProperty("hit_start")
    private Long hitStart;
    @JsonProperty("hit_end")
    private Long hitEnd;
    @JsonProperty("score")
    private Double score;
    @JsonProperty("hit_pvalue")
    private Double hitPvalue;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sequence_id")
    public String getSequenceId() {
        return sequenceId;
    }

    @JsonProperty("sequence_id")
    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public MastHit withSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
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

    public MastHit withStrand(String strand) {
        this.strand = strand;
        return this;
    }

    @JsonProperty("pssm_id")
    public String getPssmId() {
        return pssmId;
    }

    @JsonProperty("pssm_id")
    public void setPssmId(String pssmId) {
        this.pssmId = pssmId;
    }

    public MastHit withPssmId(String pssmId) {
        this.pssmId = pssmId;
        return this;
    }

    @JsonProperty("hit_start")
    public Long getHitStart() {
        return hitStart;
    }

    @JsonProperty("hit_start")
    public void setHitStart(Long hitStart) {
        this.hitStart = hitStart;
    }

    public MastHit withHitStart(Long hitStart) {
        this.hitStart = hitStart;
        return this;
    }

    @JsonProperty("hit_end")
    public Long getHitEnd() {
        return hitEnd;
    }

    @JsonProperty("hit_end")
    public void setHitEnd(Long hitEnd) {
        this.hitEnd = hitEnd;
    }

    public MastHit withHitEnd(Long hitEnd) {
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

    public MastHit withScore(Double score) {
        this.score = score;
        return this;
    }

    @JsonProperty("hit_pvalue")
    public Double getHitPvalue() {
        return hitPvalue;
    }

    @JsonProperty("hit_pvalue")
    public void setHitPvalue(Double hitPvalue) {
        this.hitPvalue = hitPvalue;
    }

    public MastHit withHitPvalue(Double hitPvalue) {
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

    @Override
    public String toString() {
        return ((((((((((((((((("MastHit"+" [sequenceId=")+ sequenceId)+", strand=")+ strand)+", pssmId=")+ pssmId)+", hitStart=")+ hitStart)+", hitEnd=")+ hitEnd)+", score=")+ score)+", hitPvalue=")+ hitPvalue)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
