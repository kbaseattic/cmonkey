
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
 * <p>Original spec-file type: ExpressionDataSample</p>
 * <pre>
 * Data structure for all the top level metadata and value data for an expression sample.  Essentially a expression Sample object.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "sample_id",
    "source_id",
    "sample_title",
    "sample_description",
    "molecule",
    "sample_type",
    "data_source",
    "external_source_id",
    "external_source_date",
    "kbase_submission_date",
    "custom",
    "original_log2_median",
    "strain_id",
    "reference_strain",
    "wildtype",
    "strain_description",
    "genome_id",
    "genome_scientific_name",
    "platform_id",
    "platform_title",
    "platform_technology",
    "experimental_unit_id",
    "experiment_meta_id",
    "experiment_title",
    "experiment_description",
    "environment_id",
    "environment_description",
    "protocol_id",
    "protocol_description",
    "protocol_name",
    "sample_annotations",
    "series_ids",
    "person_ids",
    "sample_ids_averaged_from",
    "data_expression_levels_for_sample"
})
public class ExpressionDataSample {

    @JsonProperty("sample_id")
    private java.lang.String sampleId;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    @JsonProperty("sample_title")
    private java.lang.String sampleTitle;
    @JsonProperty("sample_description")
    private java.lang.String sampleDescription;
    @JsonProperty("molecule")
    private java.lang.String molecule;
    @JsonProperty("sample_type")
    private java.lang.String sampleType;
    @JsonProperty("data_source")
    private java.lang.String dataSource;
    @JsonProperty("external_source_id")
    private java.lang.String externalSourceId;
    @JsonProperty("external_source_date")
    private java.lang.String externalSourceDate;
    @JsonProperty("kbase_submission_date")
    private java.lang.String kbaseSubmissionDate;
    @JsonProperty("custom")
    private java.lang.String custom;
    @JsonProperty("original_log2_median")
    private java.lang.Double originalLog2Median;
    @JsonProperty("strain_id")
    private java.lang.String strainId;
    @JsonProperty("reference_strain")
    private java.lang.String referenceStrain;
    @JsonProperty("wildtype")
    private java.lang.String wildtype;
    @JsonProperty("strain_description")
    private java.lang.String strainDescription;
    @JsonProperty("genome_id")
    private java.lang.String genomeId;
    @JsonProperty("genome_scientific_name")
    private java.lang.String genomeScientificName;
    @JsonProperty("platform_id")
    private java.lang.String platformId;
    @JsonProperty("platform_title")
    private java.lang.String platformTitle;
    @JsonProperty("platform_technology")
    private java.lang.String platformTechnology;
    @JsonProperty("experimental_unit_id")
    private java.lang.String experimentalUnitId;
    @JsonProperty("experiment_meta_id")
    private java.lang.String experimentMetaId;
    @JsonProperty("experiment_title")
    private java.lang.String experimentTitle;
    @JsonProperty("experiment_description")
    private java.lang.String experimentDescription;
    @JsonProperty("environment_id")
    private java.lang.String environmentId;
    @JsonProperty("environment_description")
    private java.lang.String environmentDescription;
    @JsonProperty("protocol_id")
    private java.lang.String protocolId;
    @JsonProperty("protocol_description")
    private java.lang.String protocolDescription;
    @JsonProperty("protocol_name")
    private java.lang.String protocolName;
    @JsonProperty("sample_annotations")
    private List<SampleAnnotation> sampleAnnotations;
    @JsonProperty("series_ids")
    private List<String> seriesIds;
    @JsonProperty("person_ids")
    private List<String> personIds;
    @JsonProperty("sample_ids_averaged_from")
    private List<String> sampleIdsAveragedFrom;
    @JsonProperty("data_expression_levels_for_sample")
    private Map<String, Double> dataExpressionLevelsForSample;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("sample_id")
    public java.lang.String getSampleId() {
        return sampleId;
    }

    @JsonProperty("sample_id")
    public void setSampleId(java.lang.String sampleId) {
        this.sampleId = sampleId;
    }

    public ExpressionDataSample withSampleId(java.lang.String sampleId) {
        this.sampleId = sampleId;
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

    public ExpressionDataSample withSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("sample_title")
    public java.lang.String getSampleTitle() {
        return sampleTitle;
    }

    @JsonProperty("sample_title")
    public void setSampleTitle(java.lang.String sampleTitle) {
        this.sampleTitle = sampleTitle;
    }

    public ExpressionDataSample withSampleTitle(java.lang.String sampleTitle) {
        this.sampleTitle = sampleTitle;
        return this;
    }

    @JsonProperty("sample_description")
    public java.lang.String getSampleDescription() {
        return sampleDescription;
    }

    @JsonProperty("sample_description")
    public void setSampleDescription(java.lang.String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public ExpressionDataSample withSampleDescription(java.lang.String sampleDescription) {
        this.sampleDescription = sampleDescription;
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

    public ExpressionDataSample withMolecule(java.lang.String molecule) {
        this.molecule = molecule;
        return this;
    }

    @JsonProperty("sample_type")
    public java.lang.String getSampleType() {
        return sampleType;
    }

    @JsonProperty("sample_type")
    public void setSampleType(java.lang.String sampleType) {
        this.sampleType = sampleType;
    }

    public ExpressionDataSample withSampleType(java.lang.String sampleType) {
        this.sampleType = sampleType;
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

    public ExpressionDataSample withDataSource(java.lang.String dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    @JsonProperty("external_source_id")
    public java.lang.String getExternalSourceId() {
        return externalSourceId;
    }

    @JsonProperty("external_source_id")
    public void setExternalSourceId(java.lang.String externalSourceId) {
        this.externalSourceId = externalSourceId;
    }

    public ExpressionDataSample withExternalSourceId(java.lang.String externalSourceId) {
        this.externalSourceId = externalSourceId;
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

    public ExpressionDataSample withExternalSourceDate(java.lang.String externalSourceDate) {
        this.externalSourceDate = externalSourceDate;
        return this;
    }

    @JsonProperty("kbase_submission_date")
    public java.lang.String getKbaseSubmissionDate() {
        return kbaseSubmissionDate;
    }

    @JsonProperty("kbase_submission_date")
    public void setKbaseSubmissionDate(java.lang.String kbaseSubmissionDate) {
        this.kbaseSubmissionDate = kbaseSubmissionDate;
    }

    public ExpressionDataSample withKbaseSubmissionDate(java.lang.String kbaseSubmissionDate) {
        this.kbaseSubmissionDate = kbaseSubmissionDate;
        return this;
    }

    @JsonProperty("custom")
    public java.lang.String getCustom() {
        return custom;
    }

    @JsonProperty("custom")
    public void setCustom(java.lang.String custom) {
        this.custom = custom;
    }

    public ExpressionDataSample withCustom(java.lang.String custom) {
        this.custom = custom;
        return this;
    }

    @JsonProperty("original_log2_median")
    public java.lang.Double getOriginalLog2Median() {
        return originalLog2Median;
    }

    @JsonProperty("original_log2_median")
    public void setOriginalLog2Median(java.lang.Double originalLog2Median) {
        this.originalLog2Median = originalLog2Median;
    }

    public ExpressionDataSample withOriginalLog2Median(java.lang.Double originalLog2Median) {
        this.originalLog2Median = originalLog2Median;
        return this;
    }

    @JsonProperty("strain_id")
    public java.lang.String getStrainId() {
        return strainId;
    }

    @JsonProperty("strain_id")
    public void setStrainId(java.lang.String strainId) {
        this.strainId = strainId;
    }

    public ExpressionDataSample withStrainId(java.lang.String strainId) {
        this.strainId = strainId;
        return this;
    }

    @JsonProperty("reference_strain")
    public java.lang.String getReferenceStrain() {
        return referenceStrain;
    }

    @JsonProperty("reference_strain")
    public void setReferenceStrain(java.lang.String referenceStrain) {
        this.referenceStrain = referenceStrain;
    }

    public ExpressionDataSample withReferenceStrain(java.lang.String referenceStrain) {
        this.referenceStrain = referenceStrain;
        return this;
    }

    @JsonProperty("wildtype")
    public java.lang.String getWildtype() {
        return wildtype;
    }

    @JsonProperty("wildtype")
    public void setWildtype(java.lang.String wildtype) {
        this.wildtype = wildtype;
    }

    public ExpressionDataSample withWildtype(java.lang.String wildtype) {
        this.wildtype = wildtype;
        return this;
    }

    @JsonProperty("strain_description")
    public java.lang.String getStrainDescription() {
        return strainDescription;
    }

    @JsonProperty("strain_description")
    public void setStrainDescription(java.lang.String strainDescription) {
        this.strainDescription = strainDescription;
    }

    public ExpressionDataSample withStrainDescription(java.lang.String strainDescription) {
        this.strainDescription = strainDescription;
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

    public ExpressionDataSample withGenomeId(java.lang.String genomeId) {
        this.genomeId = genomeId;
        return this;
    }

    @JsonProperty("genome_scientific_name")
    public java.lang.String getGenomeScientificName() {
        return genomeScientificName;
    }

    @JsonProperty("genome_scientific_name")
    public void setGenomeScientificName(java.lang.String genomeScientificName) {
        this.genomeScientificName = genomeScientificName;
    }

    public ExpressionDataSample withGenomeScientificName(java.lang.String genomeScientificName) {
        this.genomeScientificName = genomeScientificName;
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

    public ExpressionDataSample withPlatformId(java.lang.String platformId) {
        this.platformId = platformId;
        return this;
    }

    @JsonProperty("platform_title")
    public java.lang.String getPlatformTitle() {
        return platformTitle;
    }

    @JsonProperty("platform_title")
    public void setPlatformTitle(java.lang.String platformTitle) {
        this.platformTitle = platformTitle;
    }

    public ExpressionDataSample withPlatformTitle(java.lang.String platformTitle) {
        this.platformTitle = platformTitle;
        return this;
    }

    @JsonProperty("platform_technology")
    public java.lang.String getPlatformTechnology() {
        return platformTechnology;
    }

    @JsonProperty("platform_technology")
    public void setPlatformTechnology(java.lang.String platformTechnology) {
        this.platformTechnology = platformTechnology;
    }

    public ExpressionDataSample withPlatformTechnology(java.lang.String platformTechnology) {
        this.platformTechnology = platformTechnology;
        return this;
    }

    @JsonProperty("experimental_unit_id")
    public java.lang.String getExperimentalUnitId() {
        return experimentalUnitId;
    }

    @JsonProperty("experimental_unit_id")
    public void setExperimentalUnitId(java.lang.String experimentalUnitId) {
        this.experimentalUnitId = experimentalUnitId;
    }

    public ExpressionDataSample withExperimentalUnitId(java.lang.String experimentalUnitId) {
        this.experimentalUnitId = experimentalUnitId;
        return this;
    }

    @JsonProperty("experiment_meta_id")
    public java.lang.String getExperimentMetaId() {
        return experimentMetaId;
    }

    @JsonProperty("experiment_meta_id")
    public void setExperimentMetaId(java.lang.String experimentMetaId) {
        this.experimentMetaId = experimentMetaId;
    }

    public ExpressionDataSample withExperimentMetaId(java.lang.String experimentMetaId) {
        this.experimentMetaId = experimentMetaId;
        return this;
    }

    @JsonProperty("experiment_title")
    public java.lang.String getExperimentTitle() {
        return experimentTitle;
    }

    @JsonProperty("experiment_title")
    public void setExperimentTitle(java.lang.String experimentTitle) {
        this.experimentTitle = experimentTitle;
    }

    public ExpressionDataSample withExperimentTitle(java.lang.String experimentTitle) {
        this.experimentTitle = experimentTitle;
        return this;
    }

    @JsonProperty("experiment_description")
    public java.lang.String getExperimentDescription() {
        return experimentDescription;
    }

    @JsonProperty("experiment_description")
    public void setExperimentDescription(java.lang.String experimentDescription) {
        this.experimentDescription = experimentDescription;
    }

    public ExpressionDataSample withExperimentDescription(java.lang.String experimentDescription) {
        this.experimentDescription = experimentDescription;
        return this;
    }

    @JsonProperty("environment_id")
    public java.lang.String getEnvironmentId() {
        return environmentId;
    }

    @JsonProperty("environment_id")
    public void setEnvironmentId(java.lang.String environmentId) {
        this.environmentId = environmentId;
    }

    public ExpressionDataSample withEnvironmentId(java.lang.String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    @JsonProperty("environment_description")
    public java.lang.String getEnvironmentDescription() {
        return environmentDescription;
    }

    @JsonProperty("environment_description")
    public void setEnvironmentDescription(java.lang.String environmentDescription) {
        this.environmentDescription = environmentDescription;
    }

    public ExpressionDataSample withEnvironmentDescription(java.lang.String environmentDescription) {
        this.environmentDescription = environmentDescription;
        return this;
    }

    @JsonProperty("protocol_id")
    public java.lang.String getProtocolId() {
        return protocolId;
    }

    @JsonProperty("protocol_id")
    public void setProtocolId(java.lang.String protocolId) {
        this.protocolId = protocolId;
    }

    public ExpressionDataSample withProtocolId(java.lang.String protocolId) {
        this.protocolId = protocolId;
        return this;
    }

    @JsonProperty("protocol_description")
    public java.lang.String getProtocolDescription() {
        return protocolDescription;
    }

    @JsonProperty("protocol_description")
    public void setProtocolDescription(java.lang.String protocolDescription) {
        this.protocolDescription = protocolDescription;
    }

    public ExpressionDataSample withProtocolDescription(java.lang.String protocolDescription) {
        this.protocolDescription = protocolDescription;
        return this;
    }

    @JsonProperty("protocol_name")
    public java.lang.String getProtocolName() {
        return protocolName;
    }

    @JsonProperty("protocol_name")
    public void setProtocolName(java.lang.String protocolName) {
        this.protocolName = protocolName;
    }

    public ExpressionDataSample withProtocolName(java.lang.String protocolName) {
        this.protocolName = protocolName;
        return this;
    }

    @JsonProperty("sample_annotations")
    public List<SampleAnnotation> getSampleAnnotations() {
        return sampleAnnotations;
    }

    @JsonProperty("sample_annotations")
    public void setSampleAnnotations(List<SampleAnnotation> sampleAnnotations) {
        this.sampleAnnotations = sampleAnnotations;
    }

    public ExpressionDataSample withSampleAnnotations(List<SampleAnnotation> sampleAnnotations) {
        this.sampleAnnotations = sampleAnnotations;
        return this;
    }

    @JsonProperty("series_ids")
    public List<String> getSeriesIds() {
        return seriesIds;
    }

    @JsonProperty("series_ids")
    public void setSeriesIds(List<String> seriesIds) {
        this.seriesIds = seriesIds;
    }

    public ExpressionDataSample withSeriesIds(List<String> seriesIds) {
        this.seriesIds = seriesIds;
        return this;
    }

    @JsonProperty("person_ids")
    public List<String> getPersonIds() {
        return personIds;
    }

    @JsonProperty("person_ids")
    public void setPersonIds(List<String> personIds) {
        this.personIds = personIds;
    }

    public ExpressionDataSample withPersonIds(List<String> personIds) {
        this.personIds = personIds;
        return this;
    }

    @JsonProperty("sample_ids_averaged_from")
    public List<String> getSampleIdsAveragedFrom() {
        return sampleIdsAveragedFrom;
    }

    @JsonProperty("sample_ids_averaged_from")
    public void setSampleIdsAveragedFrom(List<String> sampleIdsAveragedFrom) {
        this.sampleIdsAveragedFrom = sampleIdsAveragedFrom;
    }

    public ExpressionDataSample withSampleIdsAveragedFrom(List<String> sampleIdsAveragedFrom) {
        this.sampleIdsAveragedFrom = sampleIdsAveragedFrom;
        return this;
    }

    @JsonProperty("data_expression_levels_for_sample")
    public Map<String, Double> getDataExpressionLevelsForSample() {
        return dataExpressionLevelsForSample;
    }

    @JsonProperty("data_expression_levels_for_sample")
    public void setDataExpressionLevelsForSample(Map<String, Double> dataExpressionLevelsForSample) {
        this.dataExpressionLevelsForSample = dataExpressionLevelsForSample;
    }

    public ExpressionDataSample withDataExpressionLevelsForSample(Map<String, Double> dataExpressionLevelsForSample) {
        this.dataExpressionLevelsForSample = dataExpressionLevelsForSample;
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
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((("ExpressionDataSample"+" [sampleId=")+ sampleId)+", sourceId=")+ sourceId)+", sampleTitle=")+ sampleTitle)+", sampleDescription=")+ sampleDescription)+", molecule=")+ molecule)+", sampleType=")+ sampleType)+", dataSource=")+ dataSource)+", externalSourceId=")+ externalSourceId)+", externalSourceDate=")+ externalSourceDate)+", kbaseSubmissionDate=")+ kbaseSubmissionDate)+", custom=")+ custom)+", originalLog2Median=")+ originalLog2Median)+", strainId=")+ strainId)+", referenceStrain=")+ referenceStrain)+", wildtype=")+ wildtype)+", strainDescription=")+ strainDescription)+", genomeId=")+ genomeId)+", genomeScientificName=")+ genomeScientificName)+", platformId=")+ platformId)+", platformTitle=")+ platformTitle)+", platformTechnology=")+ platformTechnology)+", experimentalUnitId=")+ experimentalUnitId)+", experimentMetaId=")+ experimentMetaId)+", experimentTitle=")+ experimentTitle)+", experimentDescription=")+ experimentDescription)+", environmentId=")+ environmentId)+", environmentDescription=")+ environmentDescription)+", protocolId=")+ protocolId)+", protocolDescription=")+ protocolDescription)+", protocolName=")+ protocolName)+", sampleAnnotations=")+ sampleAnnotations)+", seriesIds=")+ seriesIds)+", personIds=")+ personIds)+", sampleIdsAveragedFrom=")+ sampleIdsAveragedFrom)+", dataExpressionLevelsForSample=")+ dataExpressionLevelsForSample)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
