
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
 * <p>Original spec-file type: FullMeasurement</p>
 * <pre>
 * Measurement data structure
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "value",
    "n",
    "stddev",
    "z_score",
    "p_value",
    "median",
    "mean"
})
public class FullMeasurement {

    @JsonProperty("value")
    private Double value;
    @JsonProperty("n")
    private Double n;
    @JsonProperty("stddev")
    private Double stddev;
    @JsonProperty("z_score")
    private Double zScore;
    @JsonProperty("p_value")
    private Double pValue;
    @JsonProperty("median")
    private Double median;
    @JsonProperty("mean")
    private Double mean;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Double value) {
        this.value = value;
    }

    public FullMeasurement withValue(Double value) {
        this.value = value;
        return this;
    }

    @JsonProperty("n")
    public Double getN() {
        return n;
    }

    @JsonProperty("n")
    public void setN(Double n) {
        this.n = n;
    }

    public FullMeasurement withN(Double n) {
        this.n = n;
        return this;
    }

    @JsonProperty("stddev")
    public Double getStddev() {
        return stddev;
    }

    @JsonProperty("stddev")
    public void setStddev(Double stddev) {
        this.stddev = stddev;
    }

    public FullMeasurement withStddev(Double stddev) {
        this.stddev = stddev;
        return this;
    }

    @JsonProperty("z_score")
    public Double getZScore() {
        return zScore;
    }

    @JsonProperty("z_score")
    public void setZScore(Double zScore) {
        this.zScore = zScore;
    }

    public FullMeasurement withZScore(Double zScore) {
        this.zScore = zScore;
        return this;
    }

    @JsonProperty("p_value")
    public Double getPValue() {
        return pValue;
    }

    @JsonProperty("p_value")
    public void setPValue(Double pValue) {
        this.pValue = pValue;
    }

    public FullMeasurement withPValue(Double pValue) {
        this.pValue = pValue;
        return this;
    }

    @JsonProperty("median")
    public Double getMedian() {
        return median;
    }

    @JsonProperty("median")
    public void setMedian(Double median) {
        this.median = median;
    }

    public FullMeasurement withMedian(Double median) {
        this.median = median;
        return this;
    }

    @JsonProperty("mean")
    public Double getMean() {
        return mean;
    }

    @JsonProperty("mean")
    public void setMean(Double mean) {
        this.mean = mean;
    }

    public FullMeasurement withMean(Double mean) {
        this.mean = mean;
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
        return ((((((((((((((((("FullMeasurement"+" [value=")+ value)+", n=")+ n)+", stddev=")+ stddev)+", zScore=")+ zScore)+", pValue=")+ pValue)+", median=")+ median)+", mean=")+ mean)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
