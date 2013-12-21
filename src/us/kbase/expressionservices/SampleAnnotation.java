
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
 * <p>Original spec-file type: SampleAnnotation</p>
 * <pre>
 * Data structure for top level information for sample annotation and ontology
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "sample_annotation_id",
    "ontology_id",
    "ontology_name",
    "ontology_definition"
})
public class SampleAnnotation {

    @JsonProperty("sample_annotation_id")
    private String sampleAnnotationId;
    @JsonProperty("ontology_id")
    private String ontologyId;
    @JsonProperty("ontology_name")
    private String ontologyName;
    @JsonProperty("ontology_definition")
    private String ontologyDefinition;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sample_annotation_id")
    public String getSampleAnnotationId() {
        return sampleAnnotationId;
    }

    @JsonProperty("sample_annotation_id")
    public void setSampleAnnotationId(String sampleAnnotationId) {
        this.sampleAnnotationId = sampleAnnotationId;
    }

    public SampleAnnotation withSampleAnnotationId(String sampleAnnotationId) {
        this.sampleAnnotationId = sampleAnnotationId;
        return this;
    }

    @JsonProperty("ontology_id")
    public String getOntologyId() {
        return ontologyId;
    }

    @JsonProperty("ontology_id")
    public void setOntologyId(String ontologyId) {
        this.ontologyId = ontologyId;
    }

    public SampleAnnotation withOntologyId(String ontologyId) {
        this.ontologyId = ontologyId;
        return this;
    }

    @JsonProperty("ontology_name")
    public String getOntologyName() {
        return ontologyName;
    }

    @JsonProperty("ontology_name")
    public void setOntologyName(String ontologyName) {
        this.ontologyName = ontologyName;
    }

    public SampleAnnotation withOntologyName(String ontologyName) {
        this.ontologyName = ontologyName;
        return this;
    }

    @JsonProperty("ontology_definition")
    public String getOntologyDefinition() {
        return ontologyDefinition;
    }

    @JsonProperty("ontology_definition")
    public void setOntologyDefinition(String ontologyDefinition) {
        this.ontologyDefinition = ontologyDefinition;
    }

    public SampleAnnotation withOntologyDefinition(String ontologyDefinition) {
        this.ontologyDefinition = ontologyDefinition;
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
        return ((((((((((("SampleAnnotation"+" [sampleAnnotationId=")+ sampleAnnotationId)+", ontologyId=")+ ontologyId)+", ontologyName=")+ ontologyName)+", ontologyDefinition=")+ ontologyDefinition)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
