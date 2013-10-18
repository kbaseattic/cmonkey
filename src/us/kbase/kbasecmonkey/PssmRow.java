
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
 * <p>Original spec-file type: PssmRow</p>
 * <pre>
 * Represents a particular row of PSSM
 * int rowNumber - number of PSSM row
 * float aWeight 
 * float cWeight
 * float gWeight
 * float tWeight
 * </pre>
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "rowNumber",
    "aWeight",
    "cWeight",
    "gWeight",
    "tWeight"
})
public class PssmRow {

    @JsonProperty("rowNumber")
    private Integer rowNumber;
    @JsonProperty("aWeight")
    private Double aWeight;
    @JsonProperty("cWeight")
    private Double cWeight;
    @JsonProperty("gWeight")
    private Double gWeight;
    @JsonProperty("tWeight")
    private Double tWeight;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("rowNumber")
    public Integer getRowNumber() {
        return rowNumber;
    }

    @JsonProperty("rowNumber")
    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public PssmRow withRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    @JsonProperty("aWeight")
    public Double getAWeight() {
        return aWeight;
    }

    @JsonProperty("aWeight")
    public void setAWeight(Double aWeight) {
        this.aWeight = aWeight;
    }

    public PssmRow withAWeight(Double aWeight) {
        this.aWeight = aWeight;
        return this;
    }

    @JsonProperty("cWeight")
    public Double getCWeight() {
        return cWeight;
    }

    @JsonProperty("cWeight")
    public void setCWeight(Double cWeight) {
        this.cWeight = cWeight;
    }

    public PssmRow withCWeight(Double cWeight) {
        this.cWeight = cWeight;
        return this;
    }

    @JsonProperty("gWeight")
    public Double getGWeight() {
        return gWeight;
    }

    @JsonProperty("gWeight")
    public void setGWeight(Double gWeight) {
        this.gWeight = gWeight;
    }

    public PssmRow withGWeight(Double gWeight) {
        this.gWeight = gWeight;
        return this;
    }

    @JsonProperty("tWeight")
    public Double getTWeight() {
        return tWeight;
    }

    @JsonProperty("tWeight")
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

}
