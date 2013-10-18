
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
 * <p>Original spec-file type: ExpressionDataCollection</p>
 * <pre>
 * Represents collection of expression data sets
 * string ExpressionDataCollectionId - identifier of the collection
 * list<ExpressionDataSet> expressionDataSets - data sets
 * </pre>
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ExpressionDataCollectionId",
    "expressionDataSets"
})
public class ExpressionDataCollection {

    @JsonProperty("ExpressionDataCollectionId")
    private String ExpressionDataCollectionId;
    @JsonProperty("expressionDataSets")
    private List<ExpressionDataSet> expressionDataSets = new ArrayList<ExpressionDataSet>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ExpressionDataCollectionId")
    public String getExpressionDataCollectionId() {
        return ExpressionDataCollectionId;
    }

    @JsonProperty("ExpressionDataCollectionId")
    public void setExpressionDataCollectionId(String ExpressionDataCollectionId) {
        this.ExpressionDataCollectionId = ExpressionDataCollectionId;
    }

    public ExpressionDataCollection withExpressionDataCollectionId(String ExpressionDataCollectionId) {
        this.ExpressionDataCollectionId = ExpressionDataCollectionId;
        return this;
    }

    @JsonProperty("expressionDataSets")
    public List<ExpressionDataSet> getExpressionDataSets() {
        return expressionDataSets;
    }

    @JsonProperty("expressionDataSets")
    public void setExpressionDataSets(List<ExpressionDataSet> expressionDataSets) {
        this.expressionDataSets = expressionDataSets;
    }

    public ExpressionDataCollection withExpressionDataSets(List<ExpressionDataSet> expressionDataSets) {
        this.expressionDataSets = expressionDataSets;
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
