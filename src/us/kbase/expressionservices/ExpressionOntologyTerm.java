
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
 * <p>Original spec-file type: ExpressionOntologyTerm</p>
 * <pre>
 * Temporary workspace typed object for ontology.  Should be replaced by a ontology workspace typed object.
 * Currently supports EO, PO and ENVO ontology terms.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "expression_ontology_term_id",
    "expression_ontology_term_name",
    "expression_ontology_term_definition"
})
public class ExpressionOntologyTerm {

    @JsonProperty("expression_ontology_term_id")
    private String expressionOntologyTermId;
    @JsonProperty("expression_ontology_term_name")
    private String expressionOntologyTermName;
    @JsonProperty("expression_ontology_term_definition")
    private String expressionOntologyTermDefinition;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("expression_ontology_term_id")
    public String getExpressionOntologyTermId() {
        return expressionOntologyTermId;
    }

    @JsonProperty("expression_ontology_term_id")
    public void setExpressionOntologyTermId(String expressionOntologyTermId) {
        this.expressionOntologyTermId = expressionOntologyTermId;
    }

    public ExpressionOntologyTerm withExpressionOntologyTermId(String expressionOntologyTermId) {
        this.expressionOntologyTermId = expressionOntologyTermId;
        return this;
    }

    @JsonProperty("expression_ontology_term_name")
    public String getExpressionOntologyTermName() {
        return expressionOntologyTermName;
    }

    @JsonProperty("expression_ontology_term_name")
    public void setExpressionOntologyTermName(String expressionOntologyTermName) {
        this.expressionOntologyTermName = expressionOntologyTermName;
    }

    public ExpressionOntologyTerm withExpressionOntologyTermName(String expressionOntologyTermName) {
        this.expressionOntologyTermName = expressionOntologyTermName;
        return this;
    }

    @JsonProperty("expression_ontology_term_definition")
    public String getExpressionOntologyTermDefinition() {
        return expressionOntologyTermDefinition;
    }

    @JsonProperty("expression_ontology_term_definition")
    public void setExpressionOntologyTermDefinition(String expressionOntologyTermDefinition) {
        this.expressionOntologyTermDefinition = expressionOntologyTermDefinition;
    }

    public ExpressionOntologyTerm withExpressionOntologyTermDefinition(String expressionOntologyTermDefinition) {
        this.expressionOntologyTermDefinition = expressionOntologyTermDefinition;
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
        return ((((((((("ExpressionOntologyTerm"+" [expressionOntologyTermId=")+ expressionOntologyTermId)+", expressionOntologyTermName=")+ expressionOntologyTermName)+", expressionOntologyTermDefinition=")+ expressionOntologyTermDefinition)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
