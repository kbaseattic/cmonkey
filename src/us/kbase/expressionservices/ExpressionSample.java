
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
 * <p>Original spec-file type: ExpressionSample</p>
 * <pre>
 * Data structure for the workspace expression sample.  The Expression Sample typed object.
 * protocol, persons and strain should need to eventually have common ws objects.  I will make expression ones for now.
 * we may need a link to experimentMetaID later.
 * @optional description title data_quality_level original_median expression_ontology_terms platform_id default_control_sample 
 * @optional averaged_from_samples protocol strain persons molecule data_source
 * @searchable ws_subset kb_id source_id type data_quality_level genome_id platform_id description title data_source keys_of(expression_levels) 
 * @searchable ws_subset persons.[*].email persons.[*].last_name persons.[*].institution  
 * @searchable ws_subset strain.genome_id strain.reference_strain strain.wild_type          
 * @searchable ws_subset protocol.name protocol.description 
 * @searchable ws_subset expression_ontology_terms.[*].expression_ontology_term_id expression_ontology_terms.[*].expression_ontology_term_name
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "kb_id",
    "source_id",
    "type",
    "numerical_interpretation",
    "description",
    "title",
    "data_quality_level",
    "original_median",
    "external_source_date",
    "expression_levels",
    "genome_id",
    "expression_ontology_terms",
    "platform_id",
    "default_control_sample",
    "averaged_from_samples",
    "protocol",
    "strain",
    "persons",
    "molecule",
    "data_source"
})
public class ExpressionSample {

    @JsonProperty("kb_id")
    private java.lang.String kbId;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("numerical_interpretation")
    private java.lang.String numericalInterpretation;
    @JsonProperty("description")
    private java.lang.String description;
    @JsonProperty("title")
    private java.lang.String title;
    @JsonProperty("data_quality_level")
    private Long dataQualityLevel;
    @JsonProperty("original_median")
    private java.lang.Double originalMedian;
    @JsonProperty("external_source_date")
    private java.lang.String externalSourceDate;
    @JsonProperty("expression_levels")
    private Map<String, Double> expressionLevels;
    @JsonProperty("genome_id")
    private java.lang.String genomeId;
    @JsonProperty("expression_ontology_terms")
    private List<ExpressionOntologyTerm> expressionOntologyTerms;
    @JsonProperty("platform_id")
    private java.lang.String platformId;
    @JsonProperty("default_control_sample")
    private java.lang.String defaultControlSample;
    @JsonProperty("averaged_from_samples")
    private List<String> averagedFromSamples;
    /**
     * <p>Original spec-file type: Protocol</p>
     * <pre>
     * Data structure for Protocol  (TEMPORARY WORKSPACE TYPED OBJECT SHOULD BE HANDLED IN THE FUTURE IN WORKSPACE COMMON)
     * </pre>
     * 
     */
    @JsonProperty("protocol")
    private Protocol protocol;
    /**
     * <p>Original spec-file type: Strain</p>
     * <pre>
     * Data structure for Strain  (TEMPORARY WORKSPACE TYPED OBJECT SHOULD BE HANDLED IN THE FUTURE IN WORKSPACE COMMON)
     * </pre>
     * 
     */
    @JsonProperty("strain")
    private Strain strain;
    @JsonProperty("persons")
    private List<Person> persons;
    @JsonProperty("molecule")
    private java.lang.String molecule;
    @JsonProperty("data_source")
    private java.lang.String dataSource;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("kb_id")
    public java.lang.String getKbId() {
        return kbId;
    }

    @JsonProperty("kb_id")
    public void setKbId(java.lang.String kbId) {
        this.kbId = kbId;
    }

    public ExpressionSample withKbId(java.lang.String kbId) {
        this.kbId = kbId;
        return this;
    }

    @JsonProperty("source_id")
    public java.lang.String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
    }

    public ExpressionSample withSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("type")
    public java.lang.String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(java.lang.String type) {
        this.type = type;
    }

    public ExpressionSample withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("numerical_interpretation")
    public java.lang.String getNumericalInterpretation() {
        return numericalInterpretation;
    }

    @JsonProperty("numerical_interpretation")
    public void setNumericalInterpretation(java.lang.String numericalInterpretation) {
        this.numericalInterpretation = numericalInterpretation;
    }

    public ExpressionSample withNumericalInterpretation(java.lang.String numericalInterpretation) {
        this.numericalInterpretation = numericalInterpretation;
        return this;
    }

    @JsonProperty("description")
    public java.lang.String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public ExpressionSample withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("title")
    public java.lang.String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public ExpressionSample withTitle(java.lang.String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("data_quality_level")
    public Long getDataQualityLevel() {
        return dataQualityLevel;
    }

    @JsonProperty("data_quality_level")
    public void setDataQualityLevel(Long dataQualityLevel) {
        this.dataQualityLevel = dataQualityLevel;
    }

    public ExpressionSample withDataQualityLevel(Long dataQualityLevel) {
        this.dataQualityLevel = dataQualityLevel;
        return this;
    }

    @JsonProperty("original_median")
    public java.lang.Double getOriginalMedian() {
        return originalMedian;
    }

    @JsonProperty("original_median")
    public void setOriginalMedian(java.lang.Double originalMedian) {
        this.originalMedian = originalMedian;
    }

    public ExpressionSample withOriginalMedian(java.lang.Double originalMedian) {
        this.originalMedian = originalMedian;
        return this;
    }

    @JsonProperty("external_source_date")
    public java.lang.String getExternalSourceDate() {
        return externalSourceDate;
    }

    @JsonProperty("external_source_date")
    public void setExternalSourceDate(java.lang.String externalSourceDate) {
        this.externalSourceDate = externalSourceDate;
    }

    public ExpressionSample withExternalSourceDate(java.lang.String externalSourceDate) {
        this.externalSourceDate = externalSourceDate;
        return this;
    }

    @JsonProperty("expression_levels")
    public Map<String, Double> getExpressionLevels() {
        return expressionLevels;
    }

    @JsonProperty("expression_levels")
    public void setExpressionLevels(Map<String, Double> expressionLevels) {
        this.expressionLevels = expressionLevels;
    }

    public ExpressionSample withExpressionLevels(Map<String, Double> expressionLevels) {
        this.expressionLevels = expressionLevels;
        return this;
    }

    @JsonProperty("genome_id")
    public java.lang.String getGenomeId() {
        return genomeId;
    }

    @JsonProperty("genome_id")
    public void setGenomeId(java.lang.String genomeId) {
        this.genomeId = genomeId;
    }

    public ExpressionSample withGenomeId(java.lang.String genomeId) {
        this.genomeId = genomeId;
        return this;
    }

    @JsonProperty("expression_ontology_terms")
    public List<ExpressionOntologyTerm> getExpressionOntologyTerms() {
        return expressionOntologyTerms;
    }

    @JsonProperty("expression_ontology_terms")
    public void setExpressionOntologyTerms(List<ExpressionOntologyTerm> expressionOntologyTerms) {
        this.expressionOntologyTerms = expressionOntologyTerms;
    }

    public ExpressionSample withExpressionOntologyTerms(List<ExpressionOntologyTerm> expressionOntologyTerms) {
        this.expressionOntologyTerms = expressionOntologyTerms;
        return this;
    }

    @JsonProperty("platform_id")
    public java.lang.String getPlatformId() {
        return platformId;
    }

    @JsonProperty("platform_id")
    public void setPlatformId(java.lang.String platformId) {
        this.platformId = platformId;
    }

    public ExpressionSample withPlatformId(java.lang.String platformId) {
        this.platformId = platformId;
        return this;
    }

    @JsonProperty("default_control_sample")
    public java.lang.String getDefaultControlSample() {
        return defaultControlSample;
    }

    @JsonProperty("default_control_sample")
    public void setDefaultControlSample(java.lang.String defaultControlSample) {
        this.defaultControlSample = defaultControlSample;
    }

    public ExpressionSample withDefaultControlSample(java.lang.String defaultControlSample) {
        this.defaultControlSample = defaultControlSample;
        return this;
    }

    @JsonProperty("averaged_from_samples")
    public List<String> getAveragedFromSamples() {
        return averagedFromSamples;
    }

    @JsonProperty("averaged_from_samples")
    public void setAveragedFromSamples(List<String> averagedFromSamples) {
        this.averagedFromSamples = averagedFromSamples;
    }

    public ExpressionSample withAveragedFromSamples(List<String> averagedFromSamples) {
        this.averagedFromSamples = averagedFromSamples;
        return this;
    }

    /**
     * <p>Original spec-file type: Protocol</p>
     * <pre>
     * Data structure for Protocol  (TEMPORARY WORKSPACE TYPED OBJECT SHOULD BE HANDLED IN THE FUTURE IN WORKSPACE COMMON)
     * </pre>
     * 
     */
    @JsonProperty("protocol")
    public Protocol getProtocol() {
        return protocol;
    }

    /**
     * <p>Original spec-file type: Protocol</p>
     * <pre>
     * Data structure for Protocol  (TEMPORARY WORKSPACE TYPED OBJECT SHOULD BE HANDLED IN THE FUTURE IN WORKSPACE COMMON)
     * </pre>
     * 
     */
    @JsonProperty("protocol")
    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public ExpressionSample withProtocol(Protocol protocol) {
        this.protocol = protocol;
        return this;
    }

    /**
     * <p>Original spec-file type: Strain</p>
     * <pre>
     * Data structure for Strain  (TEMPORARY WORKSPACE TYPED OBJECT SHOULD BE HANDLED IN THE FUTURE IN WORKSPACE COMMON)
     * </pre>
     * 
     */
    @JsonProperty("strain")
    public Strain getStrain() {
        return strain;
    }

    /**
     * <p>Original spec-file type: Strain</p>
     * <pre>
     * Data structure for Strain  (TEMPORARY WORKSPACE TYPED OBJECT SHOULD BE HANDLED IN THE FUTURE IN WORKSPACE COMMON)
     * </pre>
     * 
     */
    @JsonProperty("strain")
    public void setStrain(Strain strain) {
        this.strain = strain;
    }

    public ExpressionSample withStrain(Strain strain) {
        this.strain = strain;
        return this;
    }

    @JsonProperty("persons")
    public List<Person> getPersons() {
        return persons;
    }

    @JsonProperty("persons")
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public ExpressionSample withPersons(List<Person> persons) {
        this.persons = persons;
        return this;
    }

    @JsonProperty("molecule")
    public java.lang.String getMolecule() {
        return molecule;
    }

    @JsonProperty("molecule")
    public void setMolecule(java.lang.String molecule) {
        this.molecule = molecule;
    }

    public ExpressionSample withMolecule(java.lang.String molecule) {
        this.molecule = molecule;
        return this;
    }

    @JsonProperty("data_source")
    public java.lang.String getDataSource() {
        return dataSource;
    }

    @JsonProperty("data_source")
    public void setDataSource(java.lang.String dataSource) {
        this.dataSource = dataSource;
    }

    public ExpressionSample withDataSource(java.lang.String dataSource) {
        this.dataSource = dataSource;
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
        return ((((((((((((((((((((((((((((((((((((((((((("ExpressionSample"+" [kbId=")+ kbId)+", sourceId=")+ sourceId)+", type=")+ type)+", numericalInterpretation=")+ numericalInterpretation)+", description=")+ description)+", title=")+ title)+", dataQualityLevel=")+ dataQualityLevel)+", originalMedian=")+ originalMedian)+", externalSourceDate=")+ externalSourceDate)+", expressionLevels=")+ expressionLevels)+", genomeId=")+ genomeId)+", expressionOntologyTerms=")+ expressionOntologyTerms)+", platformId=")+ platformId)+", defaultControlSample=")+ defaultControlSample)+", averagedFromSamples=")+ averagedFromSamples)+", protocol=")+ protocol)+", strain=")+ strain)+", persons=")+ persons)+", molecule=")+ molecule)+", dataSource=")+ dataSource)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
