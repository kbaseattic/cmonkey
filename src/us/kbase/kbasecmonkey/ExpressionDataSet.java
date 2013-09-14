
package us.kbase.kbasecmonkey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * <p>Original spec-file type: ExpressionDataSet</p>
 * <pre>
 * Represents set of expression data
 * string expressionDataSetId - identifier of data set
 * string expressionDataSetDescription - description of data set`
 * list<ExpressionDataPoint> expressionDataPoints - data points
 * </pre>
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "expressionDataSetId",
    "expressionDataSetDescription",
    "expressionDataPoints"
})
public class ExpressionDataSet {

    @JsonProperty("expressionDataSetId")
    private String expressionDataSetId;
    @JsonProperty("expressionDataSetDescription")
    private String expressionDataSetDescription;
    @JsonProperty("expressionDataPoints")
    private List<ExpressionDataPoint> expressionDataPoints = new ArrayList<ExpressionDataPoint>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("expressionDataSetId")
    public String getExpressionDataSetId() {
        return expressionDataSetId;
    }

    @JsonProperty("expressionDataSetId")
    public void setExpressionDataSetId(String expressionDataSetId) {
        this.expressionDataSetId = expressionDataSetId;
    }

    public ExpressionDataSet withExpressionDataSetId(String expressionDataSetId) {
        this.expressionDataSetId = expressionDataSetId;
        return this;
    }

    @JsonProperty("expressionDataSetDescription")
    public String getExpressionDataSetDescription() {
        return expressionDataSetDescription;
    }

    @JsonProperty("expressionDataSetDescription")
    public void setExpressionDataSetDescription(String expressionDataSetDescription) {
        this.expressionDataSetDescription = expressionDataSetDescription;
    }

    public ExpressionDataSet withExpressionDataSetDescription(String expressionDataSetDescription) {
        this.expressionDataSetDescription = expressionDataSetDescription;
        return this;
    }

    @JsonProperty("expressionDataPoints")
    public List<ExpressionDataPoint> getExpressionDataPoints() {
        return expressionDataPoints;
    }

    @JsonProperty("expressionDataPoints")
    public void setExpressionDataPoints(List<ExpressionDataPoint> expressionDataPoints) {
        this.expressionDataPoints = expressionDataPoints;
    }

    public ExpressionDataSet withExpressionDataPoints(List<ExpressionDataPoint> expressionDataPoints) {
        this.expressionDataPoints = expressionDataPoints;
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
