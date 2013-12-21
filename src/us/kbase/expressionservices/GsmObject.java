
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
 * <p>Original spec-file type: GsmObject</p>
 * <pre>
 * GSM OBJECT
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "gsm_id",
    "gsm_title",
    "gsm_description",
    "gsm_molecule",
    "gsm_submission_date",
    "gsm_tax_id",
    "gsm_sample_organism",
    "gsm_sample_characteristics",
    "gsm_protocol",
    "gsm_value_type",
    "gsm_platform",
    "gsm_contact_people",
    "gsm_data",
    "gsm_feature_mapping_approach",
    "ontology_ids",
    "gsm_warning",
    "gsm_errors"
})
public class GsmObject {

    @JsonProperty("gsm_id")
    private java.lang.String gsmId;
    @JsonProperty("gsm_title")
    private java.lang.String gsmTitle;
    @JsonProperty("gsm_description")
    private java.lang.String gsmDescription;
    @JsonProperty("gsm_molecule")
    private java.lang.String gsmMolecule;
    @JsonProperty("gsm_submission_date")
    private java.lang.String gsmSubmissionDate;
    @JsonProperty("gsm_tax_id")
    private java.lang.String gsmTaxId;
    @JsonProperty("gsm_sample_organism")
    private java.lang.String gsmSampleOrganism;
    @JsonProperty("gsm_sample_characteristics")
    private List<String> gsmSampleCharacteristics;
    @JsonProperty("gsm_protocol")
    private java.lang.String gsmProtocol;
    @JsonProperty("gsm_value_type")
    private java.lang.String gsmValueType;
    /**
     * <p>Original spec-file type: GPL</p>
     * <pre>
     * Data structure for a GEO Platform
     * </pre>
     * 
     */
    @JsonProperty("gsm_platform")
    private GPL gsmPlatform;
    @JsonProperty("gsm_contact_people")
    private Map<String, ContactPerson> gsmContactPeople;
    @JsonProperty("gsm_data")
    private Map<String, GenomeDataGSM> gsmData;
    @JsonProperty("gsm_feature_mapping_approach")
    private java.lang.String gsmFeatureMappingApproach;
    @JsonProperty("ontology_ids")
    private List<String> ontologyIds;
    @JsonProperty("gsm_warning")
    private List<String> gsmWarning;
    @JsonProperty("gsm_errors")
    private List<String> gsmErrors;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("gsm_id")
    public java.lang.String getGsmId() {
        return gsmId;
    }

    @JsonProperty("gsm_id")
    public void setGsmId(java.lang.String gsmId) {
        this.gsmId = gsmId;
    }

    public GsmObject withGsmId(java.lang.String gsmId) {
        this.gsmId = gsmId;
        return this;
    }

    @JsonProperty("gsm_title")
    public java.lang.String getGsmTitle() {
        return gsmTitle;
    }

    @JsonProperty("gsm_title")
    public void setGsmTitle(java.lang.String gsmTitle) {
        this.gsmTitle = gsmTitle;
    }

    public GsmObject withGsmTitle(java.lang.String gsmTitle) {
        this.gsmTitle = gsmTitle;
        return this;
    }

    @JsonProperty("gsm_description")
    public java.lang.String getGsmDescription() {
        return gsmDescription;
    }

    @JsonProperty("gsm_description")
    public void setGsmDescription(java.lang.String gsmDescription) {
        this.gsmDescription = gsmDescription;
    }

    public GsmObject withGsmDescription(java.lang.String gsmDescription) {
        this.gsmDescription = gsmDescription;
        return this;
    }

    @JsonProperty("gsm_molecule")
    public java.lang.String getGsmMolecule() {
        return gsmMolecule;
    }

    @JsonProperty("gsm_molecule")
    public void setGsmMolecule(java.lang.String gsmMolecule) {
        this.gsmMolecule = gsmMolecule;
    }

    public GsmObject withGsmMolecule(java.lang.String gsmMolecule) {
        this.gsmMolecule = gsmMolecule;
        return this;
    }

    @JsonProperty("gsm_submission_date")
    public java.lang.String getGsmSubmissionDate() {
        return gsmSubmissionDate;
    }

    @JsonProperty("gsm_submission_date")
    public void setGsmSubmissionDate(java.lang.String gsmSubmissionDate) {
        this.gsmSubmissionDate = gsmSubmissionDate;
    }

    public GsmObject withGsmSubmissionDate(java.lang.String gsmSubmissionDate) {
        this.gsmSubmissionDate = gsmSubmissionDate;
        return this;
    }

    @JsonProperty("gsm_tax_id")
    public java.lang.String getGsmTaxId() {
        return gsmTaxId;
    }

    @JsonProperty("gsm_tax_id")
    public void setGsmTaxId(java.lang.String gsmTaxId) {
        this.gsmTaxId = gsmTaxId;
    }

    public GsmObject withGsmTaxId(java.lang.String gsmTaxId) {
        this.gsmTaxId = gsmTaxId;
        return this;
    }

    @JsonProperty("gsm_sample_organism")
    public java.lang.String getGsmSampleOrganism() {
        return gsmSampleOrganism;
    }

    @JsonProperty("gsm_sample_organism")
    public void setGsmSampleOrganism(java.lang.String gsmSampleOrganism) {
        this.gsmSampleOrganism = gsmSampleOrganism;
    }

    public GsmObject withGsmSampleOrganism(java.lang.String gsmSampleOrganism) {
        this.gsmSampleOrganism = gsmSampleOrganism;
        return this;
    }

    @JsonProperty("gsm_sample_characteristics")
    public List<String> getGsmSampleCharacteristics() {
        return gsmSampleCharacteristics;
    }

    @JsonProperty("gsm_sample_characteristics")
    public void setGsmSampleCharacteristics(List<String> gsmSampleCharacteristics) {
        this.gsmSampleCharacteristics = gsmSampleCharacteristics;
    }

    public GsmObject withGsmSampleCharacteristics(List<String> gsmSampleCharacteristics) {
        this.gsmSampleCharacteristics = gsmSampleCharacteristics;
        return this;
    }

    @JsonProperty("gsm_protocol")
    public java.lang.String getGsmProtocol() {
        return gsmProtocol;
    }

    @JsonProperty("gsm_protocol")
    public void setGsmProtocol(java.lang.String gsmProtocol) {
        this.gsmProtocol = gsmProtocol;
    }

    public GsmObject withGsmProtocol(java.lang.String gsmProtocol) {
        this.gsmProtocol = gsmProtocol;
        return this;
    }

    @JsonProperty("gsm_value_type")
    public java.lang.String getGsmValueType() {
        return gsmValueType;
    }

    @JsonProperty("gsm_value_type")
    public void setGsmValueType(java.lang.String gsmValueType) {
        this.gsmValueType = gsmValueType;
    }

    public GsmObject withGsmValueType(java.lang.String gsmValueType) {
        this.gsmValueType = gsmValueType;
        return this;
    }

    /**
     * <p>Original spec-file type: GPL</p>
     * <pre>
     * Data structure for a GEO Platform
     * </pre>
     * 
     */
    @JsonProperty("gsm_platform")
    public GPL getGsmPlatform() {
        return gsmPlatform;
    }

    /**
     * <p>Original spec-file type: GPL</p>
     * <pre>
     * Data structure for a GEO Platform
     * </pre>
     * 
     */
    @JsonProperty("gsm_platform")
    public void setGsmPlatform(GPL gsmPlatform) {
        this.gsmPlatform = gsmPlatform;
    }

    public GsmObject withGsmPlatform(GPL gsmPlatform) {
        this.gsmPlatform = gsmPlatform;
        return this;
    }

    @JsonProperty("gsm_contact_people")
    public Map<String, ContactPerson> getGsmContactPeople() {
        return gsmContactPeople;
    }

    @JsonProperty("gsm_contact_people")
    public void setGsmContactPeople(Map<String, ContactPerson> gsmContactPeople) {
        this.gsmContactPeople = gsmContactPeople;
    }

    public GsmObject withGsmContactPeople(Map<String, ContactPerson> gsmContactPeople) {
        this.gsmContactPeople = gsmContactPeople;
        return this;
    }

    @JsonProperty("gsm_data")
    public Map<String, GenomeDataGSM> getGsmData() {
        return gsmData;
    }

    @JsonProperty("gsm_data")
    public void setGsmData(Map<String, GenomeDataGSM> gsmData) {
        this.gsmData = gsmData;
    }

    public GsmObject withGsmData(Map<String, GenomeDataGSM> gsmData) {
        this.gsmData = gsmData;
        return this;
    }

    @JsonProperty("gsm_feature_mapping_approach")
    public java.lang.String getGsmFeatureMappingApproach() {
        return gsmFeatureMappingApproach;
    }

    @JsonProperty("gsm_feature_mapping_approach")
    public void setGsmFeatureMappingApproach(java.lang.String gsmFeatureMappingApproach) {
        this.gsmFeatureMappingApproach = gsmFeatureMappingApproach;
    }

    public GsmObject withGsmFeatureMappingApproach(java.lang.String gsmFeatureMappingApproach) {
        this.gsmFeatureMappingApproach = gsmFeatureMappingApproach;
        return this;
    }

    @JsonProperty("ontology_ids")
    public List<String> getOntologyIds() {
        return ontologyIds;
    }

    @JsonProperty("ontology_ids")
    public void setOntologyIds(List<String> ontologyIds) {
        this.ontologyIds = ontologyIds;
    }

    public GsmObject withOntologyIds(List<String> ontologyIds) {
        this.ontologyIds = ontologyIds;
        return this;
    }

    @JsonProperty("gsm_warning")
    public List<String> getGsmWarning() {
        return gsmWarning;
    }

    @JsonProperty("gsm_warning")
    public void setGsmWarning(List<String> gsmWarning) {
        this.gsmWarning = gsmWarning;
    }

    public GsmObject withGsmWarning(List<String> gsmWarning) {
        this.gsmWarning = gsmWarning;
        return this;
    }

    @JsonProperty("gsm_errors")
    public List<String> getGsmErrors() {
        return gsmErrors;
    }

    @JsonProperty("gsm_errors")
    public void setGsmErrors(List<String> gsmErrors) {
        this.gsmErrors = gsmErrors;
    }

    public GsmObject withGsmErrors(List<String> gsmErrors) {
        this.gsmErrors = gsmErrors;
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
        return ((((((((((((((((((((((((((((((((((((("GsmObject"+" [gsmId=")+ gsmId)+", gsmTitle=")+ gsmTitle)+", gsmDescription=")+ gsmDescription)+", gsmMolecule=")+ gsmMolecule)+", gsmSubmissionDate=")+ gsmSubmissionDate)+", gsmTaxId=")+ gsmTaxId)+", gsmSampleOrganism=")+ gsmSampleOrganism)+", gsmSampleCharacteristics=")+ gsmSampleCharacteristics)+", gsmProtocol=")+ gsmProtocol)+", gsmValueType=")+ gsmValueType)+", gsmPlatform=")+ gsmPlatform)+", gsmContactPeople=")+ gsmContactPeople)+", gsmData=")+ gsmData)+", gsmFeatureMappingApproach=")+ gsmFeatureMappingApproach)+", ontologyIds=")+ ontologyIds)+", gsmWarning=")+ gsmWarning)+", gsmErrors=")+ gsmErrors)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
