
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
 * <p>Original spec-file type: ExpressionDataPoint</p>
 * <pre>
 * Represents a particular data point from gene expression data set
 * string geneId - KBase gene identifier
 * float expressionValue - relative expression value
 * </pre>
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "geneId",
    "expressionValue"
})
public class ExpressionDataPoint {

    @JsonProperty("geneId")
    private String geneId;
    @JsonProperty("expressionValue")
    private Double expressionValue;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("geneId")
    public String getGeneId() {
        return geneId;
    }

    @JsonProperty("geneId")
    public void setGeneId(String geneId) {
        this.geneId = geneId;
    }

    public ExpressionDataPoint withGeneId(String geneId) {
        this.geneId = geneId;
        return this;
    }

    @JsonProperty("expressionValue")
    public Double getExpressionValue() {
        return expressionValue;
    }

    @JsonProperty("expressionValue")
    public void setExpressionValue(Double expressionValue) {
        this.expressionValue = expressionValue;
    }

    public ExpressionDataPoint withExpressionValue(Double expressionValue) {
        this.expressionValue = expressionValue;
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
