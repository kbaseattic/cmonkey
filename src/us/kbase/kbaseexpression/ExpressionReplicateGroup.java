
package us.kbase.kbaseexpression;

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
 * <p>Original spec-file type: ExpressionReplicateGroup</p>
 * <pre>
 * Simple Grouping of Samples that belong to the same replicate group.  ExpressionReplicateGroup typed object.
 * @searchable ws_subset id expression_sample_ids
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "expression_sample_ids"
})
public class ExpressionReplicateGroup {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("expression_sample_ids")
    private List<String> expressionSampleIds;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ExpressionReplicateGroup withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("expression_sample_ids")
    public List<String> getExpressionSampleIds() {
        return expressionSampleIds;
    }

    @JsonProperty("expression_sample_ids")
    public void setExpressionSampleIds(List<String> expressionSampleIds) {
        this.expressionSampleIds = expressionSampleIds;
    }

    public ExpressionReplicateGroup withExpressionSampleIds(List<String> expressionSampleIds) {
        this.expressionSampleIds = expressionSampleIds;
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
        return ((((((("ExpressionReplicateGroup"+" [id=")+ id)+", expressionSampleIds=")+ expressionSampleIds)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
