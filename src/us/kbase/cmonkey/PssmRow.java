
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
 * <p>Original spec-file type: PssmRow</p>
 * <pre>
 * Represents a single row of PSSM
 * int rowNumber - number of PSSM row
 * float aWeight 
 * float cWeight
 * float gWeight
 * float tWeight
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "row_number",
    "a_weight",
    "c_weight",
    "g_weight",
    "t_weight"
})
public class PssmRow {

    @JsonProperty("row_number")
    private Long rowNumber;
    @JsonProperty("a_weight")
    private Double aWeight;
    @JsonProperty("c_weight")
    private Double cWeight;
    @JsonProperty("g_weight")
    private Double gWeight;
    @JsonProperty("t_weight")
    private Double tWeight;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("row_number")
    public Long getRowNumber() {
        return rowNumber;
    }

    @JsonProperty("row_number")
    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public PssmRow withRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    @JsonProperty("a_weight")
    public Double getAWeight() {
        return aWeight;
    }

    @JsonProperty("a_weight")
    public void setAWeight(Double aWeight) {
        this.aWeight = aWeight;
    }

    public PssmRow withAWeight(Double aWeight) {
        this.aWeight = aWeight;
        return this;
    }

    @JsonProperty("c_weight")
    public Double getCWeight() {
        return cWeight;
    }

    @JsonProperty("c_weight")
    public void setCWeight(Double cWeight) {
        this.cWeight = cWeight;
    }

    public PssmRow withCWeight(Double cWeight) {
        this.cWeight = cWeight;
        return this;
    }

    @JsonProperty("g_weight")
    public Double getGWeight() {
        return gWeight;
    }

    @JsonProperty("g_weight")
    public void setGWeight(Double gWeight) {
        this.gWeight = gWeight;
    }

    public PssmRow withGWeight(Double gWeight) {
        this.gWeight = gWeight;
        return this;
    }

    @JsonProperty("t_weight")
    public Double getTWeight() {
        return tWeight;
    }

    @JsonProperty("t_weight")
    public void setTWeight(Double tWeight) {
        this.tWeight = tWeight;
    }

    public PssmRow withTWeight(Double tWeight) {
        this.tWeight = tWeight;
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
        return ((((((((((((("PssmRow"+" [rowNumber=")+ rowNumber)+", aWeight=")+ aWeight)+", cWeight=")+ cWeight)+", gWeight=")+ gWeight)+", tWeight=")+ tWeight)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
