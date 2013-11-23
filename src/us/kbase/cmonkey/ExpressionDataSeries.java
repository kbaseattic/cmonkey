
package us.kbase.cmonkey;

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
 * <p>Original spec-file type: ExpressionDataSeries</p>
 * <pre>
 * ExpressionDataSeries represents collection of expression data samples
 * string id - identifier of the collection
 * list<ExpressionDataSample> samples - data sets
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "samples"
})
public class ExpressionDataSeries {

    @JsonProperty("id")
    private String id;
    @JsonProperty("samples")
    private List<ExpressionDataSample> samples;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ExpressionDataSeries withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("samples")
    public List<ExpressionDataSample> getSamples() {
        return samples;
    }

    @JsonProperty("samples")
    public void setSamples(List<ExpressionDataSample> samples) {
        this.samples = samples;
    }

    public ExpressionDataSeries withSamples(List<ExpressionDataSample> samples) {
        this.samples = samples;
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
        return ((((((("ExpressionDataSeries"+" [id=")+ id)+", samples=")+ samples)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
