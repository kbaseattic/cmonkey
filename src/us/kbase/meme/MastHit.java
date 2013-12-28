
package us.kbase.meme;

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
 * Represents a particluar MAST hit
 * string seq_id - name of sequence
 * string strand - strand ("+" or "-")
 * string pspm_id - id of MemePSPM
 * int hit_start - start position of hit
 * int hit_end - end position of hit
 * float score - hit score
 * float hitPvalue - hit p-value
 * @optional strand hit_start hit_end score hit_pvalue
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "seq_id",
    "pspm_id",
    "strand",
    "hit_start",
    "hit_end",
    "score",
    "hit_pvalue"
})
public class MastHit {

    @JsonProperty("seq_id")
    private String seqId;
    @JsonProperty("pspm_id")
    private String pspmId;
    @JsonProperty("strand")
    private String strand;
    @JsonProperty("hit_start")
    private Long hitStart;
    @JsonProperty("hit_end")
    private Long hitEnd;
    @JsonProperty("score")
    private Double score;
    @JsonProperty("hit_pvalue")
    private Double hitPvalue;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("seq_id")
    public String getSeqId() {
        return seqId;
    }

    @JsonProperty("seq_id")
    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public MastHit withSeqId(String seqId) {
        this.seqId = seqId;
        return this;
    }

    @JsonProperty("pspm_id")
    public String getPspmId() {
        return pspmId;
    }

    @JsonProperty("pspm_id")
    public void setPspmId(String pspmId) {
        this.pspmId = pspmId;
    }

    public MastHit withPspmId(String pspmId) {
        this.pspmId = pspmId;
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
        return ((((((((((((((((("MastHit"+" [seqId=")+ seqId)+", pspmId=")+ pspmId)+", strand=")+ strand)+", hitStart=")+ hitStart)+", hitEnd=")+ hitEnd)+", score=")+ score)+", hitPvalue=")+ hitPvalue)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
