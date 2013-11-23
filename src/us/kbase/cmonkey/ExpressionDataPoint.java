
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
 * <p>Original spec-file type: ExpressionDataPoint</p>
 * <pre>
 * Represents a particular data point from gene expression data set
 * string gene_id - KBase gene identifier
 * float expression_value - relative expression value
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "gene_id",
    "expression_value"
})
public class ExpressionDataPoint {

    @JsonProperty("gene_id")
    private String geneId;
    @JsonProperty("expression_value")
    private Double expressionValue;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("gene_id")
    public String getGeneId() {
        return geneId;
    }

    @JsonProperty("gene_id")
    public void setGeneId(String geneId) {
        this.geneId = geneId;
    }

    public ExpressionDataPoint withGeneId(String geneId) {
        this.geneId = geneId;
        return this;
    }

    @JsonProperty("expression_value")
    public Double getExpressionValue() {
        return expressionValue;
    }

    @JsonProperty("expression_value")
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

    @Override
    public String toString() {
        return ((((((("ExpressionDataPoint"+" [geneId=")+ geneId)+", expressionValue=")+ expressionValue)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
