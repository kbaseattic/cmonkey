
package us.kbase.expressionservices;

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
 * <p>Original spec-file type: GenomeDataGSM</p>
 * <pre>
 * Data structure that has the GSM data, warnings, errors and originalLog2Median for that GSM and Genome ID combination
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "warnings",
    "errors",
    "features",
    "originalLog2Median"
})
public class GenomeDataGSM {

    @JsonProperty("warnings")
    private List<String> warnings;
    @JsonProperty("errors")
    private List<String> errors;
    @JsonProperty("features")
    private Map<String, FullMeasurement> features;
    @JsonProperty("originalLog2Median")
    private Double originalLog2Median;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("warnings")
    public List<String> getWarnings() {
        return warnings;
    }

    @JsonProperty("warnings")
    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public GenomeDataGSM withWarnings(List<String> warnings) {
        this.warnings = warnings;
        return this;
    }

    @JsonProperty("errors")
    public List<String> getErrors() {
        return errors;
    }

    @JsonProperty("errors")
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public GenomeDataGSM withErrors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    @JsonProperty("features")
    public Map<String, FullMeasurement> getFeatures() {
        return features;
    }

    @JsonProperty("features")
    public void setFeatures(Map<String, FullMeasurement> features) {
        this.features = features;
    }

    public GenomeDataGSM withFeatures(Map<String, FullMeasurement> features) {
        this.features = features;
        return this;
    }

    @JsonProperty("originalLog2Median")
    public Double getOriginalLog2Median() {
        return originalLog2Median;
    }

    @JsonProperty("originalLog2Median")
    public void setOriginalLog2Median(Double originalLog2Median) {
        this.originalLog2Median = originalLog2Median;
    }

    public GenomeDataGSM withOriginalLog2Median(Double originalLog2Median) {
        this.originalLog2Median = originalLog2Median;
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
        return ((((((((((("GenomeDataGSM"+" [warnings=")+ warnings)+", errors=")+ errors)+", features=")+ features)+", originalLog2Median=")+ originalLog2Median)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
