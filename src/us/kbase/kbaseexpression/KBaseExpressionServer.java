package us.kbase.kbaseexpression;

import java.util.List;
import java.util.Map;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;

//BEGIN_HEADER
//END_HEADER

/**
 * <p>Original spec-file module name: KBaseExpression</p>
 * <pre>
 * Service for all different sorts of Expression data (microarray, RNA_seq, proteomics, qPCR
 * </pre>
 */
public class KBaseExpressionServer extends JsonServerServlet {
    private static final long serialVersionUID = 1L;

    //BEGIN_CLASS_HEADER
    //END_CLASS_HEADER

    public KBaseExpressionServer() throws Exception {
        super("KBaseExpression");
        //BEGIN_CONSTRUCTOR
        //END_CONSTRUCTOR
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_data</p>
     * <pre>
     * core function used by many others.  Given a list of KBase SampleIds returns mapping of SampleId to expressionSampleDataStructure (essentially the core Expression Sample Object) : 
     * {sample_id -> expressionSampleDataStructure}
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "expression_data_samples_map" of original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.kbaseexpression.ExpressionDataSample ExpressionDataSample}
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_data")
    public Map<String,ExpressionDataSample> getExpressionSamplesData(List<String> sampleIds) throws Exception {
        Map<String,ExpressionDataSample> returnVal = null;
        //BEGIN get_expression_samples_data
        //END get_expression_samples_data
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_data_by_samples_and_features</p>
     * <pre>
     * given a list of sample ids and feature ids it returns a LabelDataMapping {sampleID}->{featureId => value}}.  
     * If feature list is an empty array [], all features with measurment values will be returned.
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @param   featureIds   instance of original type "feature_ids" (KBase list of Feature IDs , typically CDS/PEG) &rarr; list of original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future)
     * @return   parameter "label_data_mapping" of original type "label_data_mapping" (Mapping from Label (often a sample id, but free text to identify} to DataExpressionLevelsForSample) &rarr; mapping from String to original type "data_expression_levels_for_sample" (mapping kbase feature id as the key and measurement as the value) &rarr; mapping from original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future) to original type "measurement" (Measurement Value (Zero median normalized within a sample) for a given feature)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_data_by_samples_and_features")
    public Map<String,Map<String,Double>> getExpressionDataBySamplesAndFeatures(List<String> sampleIds, List<String> featureIds) throws Exception {
        Map<String,Map<String,Double>> returnVal = null;
        //BEGIN get_expression_data_by_samples_and_features
        //END get_expression_data_by_samples_and_features
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_data_by_series_ids</p>
     * <pre>
     * given a list of SeriesIDs returns mapping of SeriesID to expressionDataSamples : {series_id -> {sample_id -> expressionSampleDataStructure}}
     * </pre>
     * @param   seriesIds   instance of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @return   parameter "series_expression_data_samples_mapping" of original type "series_expression_data_samples_mapping" (mapping between seriesIDs and all Samples it contains) &rarr; mapping from original type "series_id" (Kbase Series ID) to original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.kbaseexpression.ExpressionDataSample ExpressionDataSample}
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_data_by_series_ids")
    public Map<String,Map<String,ExpressionDataSample>> getExpressionSamplesDataBySeriesIds(List<String> seriesIds) throws Exception {
        Map<String,Map<String,ExpressionDataSample>> returnVal = null;
        //BEGIN get_expression_samples_data_by_series_ids
        //END get_expression_samples_data_by_series_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_series_ids</p>
     * <pre>
     * given a list of SeriesIDs returns a list of Sample IDs
     * </pre>
     * @param   seriesIds   instance of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_sample_ids_by_series_ids")
    public List<String> getExpressionSampleIdsBySeriesIds(List<String> seriesIds) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_expression_sample_ids_by_series_ids
        //END get_expression_sample_ids_by_series_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_data_by_experimental_unit_ids</p>
     * <pre>
     * given a list of ExperimentalUnitIDs returns mapping of ExperimentalUnitID to expressionDataSamples : {experimental_unit_id -> {sample_id -> expressionSampleDataStructure}}
     * </pre>
     * @param   experimentalUnitIds   instance of original type "experimental_unit_ids" (list of KBase ExperimentalUnit IDs) &rarr; list of original type "experimental_unit_id" (Kbase ExperimentalUnit ID)
     * @return   parameter "experimental_unit_expression_data_samples_mapping" of original type "experimental_unit_expression_data_samples_mapping" (mapping between experimentalUnitIDs and all Samples it contains) &rarr; mapping from original type "experimental_unit_id" (Kbase ExperimentalUnit ID) to original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.kbaseexpression.ExpressionDataSample ExpressionDataSample}
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_data_by_experimental_unit_ids")
    public Map<String,Map<String,ExpressionDataSample>> getExpressionSamplesDataByExperimentalUnitIds(List<String> experimentalUnitIds) throws Exception {
        Map<String,Map<String,ExpressionDataSample>> returnVal = null;
        //BEGIN get_expression_samples_data_by_experimental_unit_ids
        //END get_expression_samples_data_by_experimental_unit_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_experimental_unit_ids</p>
     * <pre>
     * given a list of ExperimentalUnitIDs returns a list of Sample IDs
     * </pre>
     * @param   experimentalUnitIds   instance of original type "experimental_unit_ids" (list of KBase ExperimentalUnit IDs) &rarr; list of original type "experimental_unit_id" (Kbase ExperimentalUnit ID)
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_sample_ids_by_experimental_unit_ids")
    public List<String> getExpressionSampleIdsByExperimentalUnitIds(List<String> experimentalUnitIds) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_expression_sample_ids_by_experimental_unit_ids
        //END get_expression_sample_ids_by_experimental_unit_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_data_by_experiment_meta_ids</p>
     * <pre>
     * given a list of ExperimentMetaIDs returns mapping of {experimentMetaID -> {experimentalUnitId -> {sample_id -> expressionSampleDataStructure}}}
     * </pre>
     * @param   experimentMetaIds   instance of original type "experiment_meta_ids" (list of KBase ExperimentMeta IDs) &rarr; list of original type "experiment_meta_id" (Kbase ExperimentMeta ID)
     * @return   parameter "experiment_meta_expression_data_samples_mapping" of original type "experiment_meta_expression_data_samples_mapping" (mapping between experimentMetaIDs and ExperimentalUnitExpressionDataSamplesMapping it contains) &rarr; mapping from original type "experiment_meta_id" (Kbase ExperimentMeta ID) to original type "experimental_unit_expression_data_samples_mapping" (mapping between experimentalUnitIDs and all Samples it contains) &rarr; mapping from original type "experimental_unit_id" (Kbase ExperimentalUnit ID) to original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.kbaseexpression.ExpressionDataSample ExpressionDataSample}
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_data_by_experiment_meta_ids")
    public Map<String,Map<String,Map<String,ExpressionDataSample>>> getExpressionSamplesDataByExperimentMetaIds(List<String> experimentMetaIds) throws Exception {
        Map<String,Map<String,Map<String,ExpressionDataSample>>> returnVal = null;
        //BEGIN get_expression_samples_data_by_experiment_meta_ids
        //END get_expression_samples_data_by_experiment_meta_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_experiment_meta_ids</p>
     * <pre>
     * given a list of ExperimentMetaIDs returns a list of Sample IDs
     * </pre>
     * @param   experimentMetaIds   instance of original type "experiment_meta_ids" (list of KBase ExperimentMeta IDs) &rarr; list of original type "experiment_meta_id" (Kbase ExperimentMeta ID)
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_sample_ids_by_experiment_meta_ids")
    public List<String> getExpressionSampleIdsByExperimentMetaIds(List<String> experimentMetaIds) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_expression_sample_ids_by_experiment_meta_ids
        //END get_expression_sample_ids_by_experiment_meta_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_data_by_strain_ids</p>
     * <pre>
     * given a list of Strains, and a SampleType (controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics) , it returns a StrainExpressionDataSamplesMapping,  
     * StrainId -> ExpressionSampleDataStructure {strain_id -> {sample_id -> expressionSampleDataStructure}}
     * </pre>
     * @param   strainIds   instance of original type "strain_ids" (list of KBase StrainIDs) &rarr; list of original type "strain_id" (KBase StrainID)
     * @param   sampleType   instance of original type "sample_type" (Sample type controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics)
     * @return   parameter "strain_expression_data_samples_mapping" of original type "strain_expression_data_samples_mapping" (mapping between strainIDs and all Samples it contains) &rarr; mapping from original type "strain_id" (KBase StrainID) to original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.kbaseexpression.ExpressionDataSample ExpressionDataSample}
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_data_by_strain_ids")
    public Map<String,Map<String,ExpressionDataSample>> getExpressionSamplesDataByStrainIds(List<String> strainIds, String sampleType) throws Exception {
        Map<String,Map<String,ExpressionDataSample>> returnVal = null;
        //BEGIN get_expression_samples_data_by_strain_ids
        //END get_expression_samples_data_by_strain_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_strain_ids</p>
     * <pre>
     * given a list of Strains, and a SampleType, it returns a list of Sample IDs
     * </pre>
     * @param   strainIds   instance of original type "strain_ids" (list of KBase StrainIDs) &rarr; list of original type "strain_id" (KBase StrainID)
     * @param   sampleType   instance of original type "sample_type" (Sample type controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics)
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_sample_ids_by_strain_ids")
    public List<String> getExpressionSampleIdsByStrainIds(List<String> strainIds, String sampleType) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_expression_sample_ids_by_strain_ids
        //END get_expression_sample_ids_by_strain_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_data_by_genome_ids</p>
     * <pre>
     * given a list of Genomes, a SampleType ( controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics) 
     * and a int indicating WildTypeOnly (1 = true, 0 = false) , it returns a GenomeExpressionDataSamplesMapping   ,  
     * GenomeId -> StrainId -> ExpressionDataSample.  StrainId -> ExpressionSampleDataStructure {genome_id -> {strain_id -> {sample_id -> expressionSampleDataStructure}}}
     * </pre>
     * @param   genomeIds   instance of original type "genome_ids" (list of KBase GenomeIDs) &rarr; list of original type "genome_id" (KBase GenomeID id ws KB.Genome "ws" may change to "to" in the future)
     * @param   sampleType   instance of original type "sample_type" (Sample type controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics)
     * @param   wildTypeOnly   instance of original type "wild_type_only" (Single integer 1= WildTypeonly, 0 means all strains ok)
     * @return   parameter "genome_expression_data_samples_mapping" of original type "genome_expression_data_samples_mapping" (mapping between genomeIDs and all StrainExpressionDataSamplesMapping it contains) &rarr; mapping from original type "genome_id" (KBase GenomeID id ws KB.Genome "ws" may change to "to" in the future) to original type "strain_expression_data_samples_mapping" (mapping between strainIDs and all Samples it contains) &rarr; mapping from original type "strain_id" (KBase StrainID) to original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.kbaseexpression.ExpressionDataSample ExpressionDataSample}
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_data_by_genome_ids")
    public Map<String,Map<String,Map<String,ExpressionDataSample>>> getExpressionSamplesDataByGenomeIds(List<String> genomeIds, String sampleType, Long wildTypeOnly) throws Exception {
        Map<String,Map<String,Map<String,ExpressionDataSample>>> returnVal = null;
        //BEGIN get_expression_samples_data_by_genome_ids
        //END get_expression_samples_data_by_genome_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_genome_ids</p>
     * <pre>
     * given a list of GenomeIDs, a SampleType ( controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics) 
     * and a int indicating WildType Only (1 = true, 0 = false) , it returns a list of Sample IDs
     * </pre>
     * @param   genomeIds   instance of original type "genome_ids" (list of KBase GenomeIDs) &rarr; list of original type "genome_id" (KBase GenomeID id ws KB.Genome "ws" may change to "to" in the future)
     * @param   sampleType   instance of original type "sample_type" (Sample type controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics)
     * @param   wildTypeOnly   instance of original type "wild_type_only" (Single integer 1= WildTypeonly, 0 means all strains ok)
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_sample_ids_by_genome_ids")
    public List<String> getExpressionSampleIdsByGenomeIds(List<String> genomeIds, String sampleType, Long wildTypeOnly) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_expression_sample_ids_by_genome_ids
        //END get_expression_sample_ids_by_genome_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_data_by_ontology_ids</p>
     * <pre>
     * given a list of ontologyIDs, AndOr operator (and requires sample to have all ontology IDs, or sample has to have any of the terms), GenomeId, 
     * SampleType ( controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics), wildTypeOnly returns OntologyID(concatenated if Anded) -> ExpressionDataSample
     * </pre>
     * @param   ontologyIds   instance of original type "ontology_ids" (list of Kbase Ontology IDs) &rarr; list of original type "ontology_id" (Kbase OntologyID)
     * @param   andOr   instance of String
     * @param   genomeId   instance of original type "genome_id" (KBase GenomeID id ws KB.Genome "ws" may change to "to" in the future)
     * @param   sampleType   instance of original type "sample_type" (Sample type controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics)
     * @param   wildTypeOnly   instance of original type "wild_type_only" (Single integer 1= WildTypeonly, 0 means all strains ok)
     * @return   parameter "ontology_expression_data_sample_mapping" of original type "ontology_expression_data_sample_mapping" (mapping between ontologyIDs (concatenated if searched for with the and operator) and all the Samples that match that term(s)) &rarr; mapping from original type "ontology_id" (Kbase OntologyID) to original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.kbaseexpression.ExpressionDataSample ExpressionDataSample}
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_data_by_ontology_ids")
    public Map<String,Map<String,ExpressionDataSample>> getExpressionSamplesDataByOntologyIds(List<String> ontologyIds, String andOr, String genomeId, String sampleType, Long wildTypeOnly) throws Exception {
        Map<String,Map<String,ExpressionDataSample>> returnVal = null;
        //BEGIN get_expression_samples_data_by_ontology_ids
        //END get_expression_samples_data_by_ontology_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_ontology_ids</p>
     * <pre>
     * given a list of ontologyIDs, AndOr operator (and requires sample to have all ontology IDs, or sample has to have any of the terms), GenomeId, 
     * SampleType ( controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics), wildTypeOnly returns a list of SampleIDs
     * </pre>
     * @param   ontologyIds   instance of original type "ontology_ids" (list of Kbase Ontology IDs) &rarr; list of original type "ontology_id" (Kbase OntologyID)
     * @param   andOr   instance of String
     * @param   genomeId   instance of original type "genome_id" (KBase GenomeID id ws KB.Genome "ws" may change to "to" in the future)
     * @param   sampleType   instance of original type "sample_type" (Sample type controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics)
     * @param   wildTypeOnly   instance of original type "wild_type_only" (Single integer 1= WildTypeonly, 0 means all strains ok)
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_sample_ids_by_ontology_ids")
    public List<String> getExpressionSampleIdsByOntologyIds(List<String> ontologyIds, String andOr, String genomeId, String sampleType, Long wildTypeOnly) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_expression_sample_ids_by_ontology_ids
        //END get_expression_sample_ids_by_ontology_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_data_by_feature_ids</p>
     * <pre>
     * given a list of FeatureIDs, a SampleType ( controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics) 
     * and an int indicating WildType Only (1 = true, 0 = false) returns a FeatureSampleMeasurementMapping: {featureID->{sample_id->measurement}}
     * </pre>
     * @param   featureIds   instance of original type "feature_ids" (KBase list of Feature IDs , typically CDS/PEG) &rarr; list of original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future)
     * @param   sampleType   instance of original type "sample_type" (Sample type controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics)
     * @param   wildTypeOnly   instance of original type "wild_type_only" (Single integer 1= WildTypeonly, 0 means all strains ok)
     * @return   parameter "feature_sample_measurement_mapping" of original type "feature_sample_measurement_mapping" (mapping between FeatureIds and the mappings between samples and log2level mapping) &rarr; mapping from original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future) to original type "sample_measurement_mapping" (mapping kbase sample id as the key and a single measurement (for a specified feature id, one mapping higher) as the value) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to original type "measurement" (Measurement Value (Zero median normalized within a sample) for a given feature)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_data_by_feature_ids")
    public Map<String,Map<String,Double>> getExpressionDataByFeatureIds(List<String> featureIds, String sampleType, Long wildTypeOnly) throws Exception {
        Map<String,Map<String,Double>> returnVal = null;
        //BEGIN get_expression_data_by_feature_ids
        //END get_expression_data_by_feature_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: compare_samples</p>
     * <pre>
     * Compare samples takes two data structures labelDataMapping  {sampleID or label}->{featureId or label => value}}, 
     * the first labelDataMapping is the numerator, the 2nd is the denominator in the comparison. returns a 
     * SampleComparisonMapping {numerator_sample_id(or label)->{denominator_sample_id(or label)->{feature_id(or label) -> log2Ratio}}}
     * </pre>
     * @param   numeratorsDataMapping   instance of original type "label_data_mapping" (Mapping from Label (often a sample id, but free text to identify} to DataExpressionLevelsForSample) &rarr; mapping from String to original type "data_expression_levels_for_sample" (mapping kbase feature id as the key and measurement as the value) &rarr; mapping from original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future) to original type "measurement" (Measurement Value (Zero median normalized within a sample) for a given feature)
     * @param   denominatorsDataMapping   instance of original type "label_data_mapping" (Mapping from Label (often a sample id, but free text to identify} to DataExpressionLevelsForSample) &rarr; mapping from String to original type "data_expression_levels_for_sample" (mapping kbase feature id as the key and measurement as the value) &rarr; mapping from original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future) to original type "measurement" (Measurement Value (Zero median normalized within a sample) for a given feature)
     * @return   parameter "sample_comparison_mapping" of original type "sample_comparison_mapping" (mapping Sample Id for the numerator to a DenominatorSampleComparison.  This is the comparison data structure {NumeratorSampleId->{denominatorLabel -> {feature -> log2ratio}}}) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to original type "denominator_sample_comparison" (mapping ComparisonDenominatorLabel to DataSampleComparison mapping) &rarr; mapping from original type "comparison_denominator_label" (denominator label is the label for the denominator in a comparison. This label can be a single sampleId (default or defined) or a comma separated list of sampleIds that were averaged.) to original type "data_sample_comparison" (mapping kbase feature id as the key and log2Ratio as the value) &rarr; mapping from original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future) to original type "log2_ratio" (Log2Ratio Log2Level of sample over log2Level of another sample for a given feature. Note if the Ratio is consumed by On Off Call function it will have 1(on), 0(unknown), -1(off) for its values)
     */
    @JsonServerMethod(rpc = "KBaseExpression.compare_samples")
    public Map<String,Map<String,Map<String,Double>>> compareSamples(Map<String,Map<String,Double>> numeratorsDataMapping, Map<String,Map<String,Double>> denominatorsDataMapping) throws Exception {
        Map<String,Map<String,Map<String,Double>>> returnVal = null;
        //BEGIN compare_samples
        //END compare_samples
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: compare_samples_vs_default_controls</p>
     * <pre>
     * Compares each sample vs its defined default control.  If the Default control is not specified for a sample, then nothing is returned for that sample .
     * Takes a list of sampleIDs returns SampleComparisonMapping {sample_id ->{denominator_default_control sample_id ->{feature_id -> log2Ratio}}}
     * </pre>
     * @param   numeratorSampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "sample_comparison_mapping" of original type "sample_comparison_mapping" (mapping Sample Id for the numerator to a DenominatorSampleComparison.  This is the comparison data structure {NumeratorSampleId->{denominatorLabel -> {feature -> log2ratio}}}) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to original type "denominator_sample_comparison" (mapping ComparisonDenominatorLabel to DataSampleComparison mapping) &rarr; mapping from original type "comparison_denominator_label" (denominator label is the label for the denominator in a comparison. This label can be a single sampleId (default or defined) or a comma separated list of sampleIds that were averaged.) to original type "data_sample_comparison" (mapping kbase feature id as the key and log2Ratio as the value) &rarr; mapping from original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future) to original type "log2_ratio" (Log2Ratio Log2Level of sample over log2Level of another sample for a given feature. Note if the Ratio is consumed by On Off Call function it will have 1(on), 0(unknown), -1(off) for its values)
     */
    @JsonServerMethod(rpc = "KBaseExpression.compare_samples_vs_default_controls")
    public Map<String,Map<String,Map<String,Double>>> compareSamplesVsDefaultControls(List<String> numeratorSampleIds) throws Exception {
        Map<String,Map<String,Map<String,Double>>> returnVal = null;
        //BEGIN compare_samples_vs_default_controls
        //END compare_samples_vs_default_controls
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: compare_samples_vs_the_average</p>
     * <pre>
     * Compares each numerator sample vs the average of all the denominator sampleIds.  Take a list of numerator sample IDs and a list of samples Ids to average for the denominator.
     * returns SampleComparisonMapping {numerator_sample_id->{denominator_sample_id ->{feature_id -> log2Ratio}}}
     * </pre>
     * @param   numeratorSampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @param   denominatorSampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "sample_comparison_mapping" of original type "sample_comparison_mapping" (mapping Sample Id for the numerator to a DenominatorSampleComparison.  This is the comparison data structure {NumeratorSampleId->{denominatorLabel -> {feature -> log2ratio}}}) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to original type "denominator_sample_comparison" (mapping ComparisonDenominatorLabel to DataSampleComparison mapping) &rarr; mapping from original type "comparison_denominator_label" (denominator label is the label for the denominator in a comparison. This label can be a single sampleId (default or defined) or a comma separated list of sampleIds that were averaged.) to original type "data_sample_comparison" (mapping kbase feature id as the key and log2Ratio as the value) &rarr; mapping from original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future) to original type "log2_ratio" (Log2Ratio Log2Level of sample over log2Level of another sample for a given feature. Note if the Ratio is consumed by On Off Call function it will have 1(on), 0(unknown), -1(off) for its values)
     */
    @JsonServerMethod(rpc = "KBaseExpression.compare_samples_vs_the_average")
    public Map<String,Map<String,Map<String,Double>>> compareSamplesVsTheAverage(List<String> numeratorSampleIds, List<String> denominatorSampleIds) throws Exception {
        Map<String,Map<String,Map<String,Double>>> returnVal = null;
        //BEGIN compare_samples_vs_the_average
        //END compare_samples_vs_the_average
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_on_off_calls</p>
     * <pre>
     * Takes in comparison results.  If the value is >= on_threshold it is deemed on (1), if <= off_threshold it is off(-1), meets none then 0.  Thresholds normally set to zero.
     * returns SampleComparisonMapping {numerator_sample_id(or label)->{denominator_sample_id(or label)->{feature_id(or label) -> on_off_call (possible values 0,-1,1)}}}
     * </pre>
     * @param   sampleComparisonMapping   instance of original type "sample_comparison_mapping" (mapping Sample Id for the numerator to a DenominatorSampleComparison.  This is the comparison data structure {NumeratorSampleId->{denominatorLabel -> {feature -> log2ratio}}}) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to original type "denominator_sample_comparison" (mapping ComparisonDenominatorLabel to DataSampleComparison mapping) &rarr; mapping from original type "comparison_denominator_label" (denominator label is the label for the denominator in a comparison. This label can be a single sampleId (default or defined) or a comma separated list of sampleIds that were averaged.) to original type "data_sample_comparison" (mapping kbase feature id as the key and log2Ratio as the value) &rarr; mapping from original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future) to original type "log2_ratio" (Log2Ratio Log2Level of sample over log2Level of another sample for a given feature. Note if the Ratio is consumed by On Off Call function it will have 1(on), 0(unknown), -1(off) for its values)
     * @param   offThreshold   instance of Double
     * @param   onThreshold   instance of Double
     * @return   parameter "on_off_mappings" of original type "sample_comparison_mapping" (mapping Sample Id for the numerator to a DenominatorSampleComparison.  This is the comparison data structure {NumeratorSampleId->{denominatorLabel -> {feature -> log2ratio}}}) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to original type "denominator_sample_comparison" (mapping ComparisonDenominatorLabel to DataSampleComparison mapping) &rarr; mapping from original type "comparison_denominator_label" (denominator label is the label for the denominator in a comparison. This label can be a single sampleId (default or defined) or a comma separated list of sampleIds that were averaged.) to original type "data_sample_comparison" (mapping kbase feature id as the key and log2Ratio as the value) &rarr; mapping from original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future) to original type "log2_ratio" (Log2Ratio Log2Level of sample over log2Level of another sample for a given feature. Note if the Ratio is consumed by On Off Call function it will have 1(on), 0(unknown), -1(off) for its values)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_on_off_calls")
    public Map<String,Map<String,Map<String,Double>>> getOnOffCalls(Map<String,Map<String,Map<String,Double>>> sampleComparisonMapping, Double offThreshold, Double onThreshold) throws Exception {
        Map<String,Map<String,Map<String,Double>>> returnVal = null;
        //BEGIN get_on_off_calls
        //END get_on_off_calls
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_top_changers</p>
     * <pre>
     * Takes in comparison results. Direction must equal 'up', 'down', or 'both'.  Count is the number of changers returned in each direction.
     * returns SampleComparisonMapping {numerator_sample_id(or label)->{denominator_sample_id(or label)->{feature_id(or label) -> log2Ratio (note that the features listed will be limited to the top changers)}}}
     * </pre>
     * @param   sampleComparisonMapping   instance of original type "sample_comparison_mapping" (mapping Sample Id for the numerator to a DenominatorSampleComparison.  This is the comparison data structure {NumeratorSampleId->{denominatorLabel -> {feature -> log2ratio}}}) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to original type "denominator_sample_comparison" (mapping ComparisonDenominatorLabel to DataSampleComparison mapping) &rarr; mapping from original type "comparison_denominator_label" (denominator label is the label for the denominator in a comparison. This label can be a single sampleId (default or defined) or a comma separated list of sampleIds that were averaged.) to original type "data_sample_comparison" (mapping kbase feature id as the key and log2Ratio as the value) &rarr; mapping from original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future) to original type "log2_ratio" (Log2Ratio Log2Level of sample over log2Level of another sample for a given feature. Note if the Ratio is consumed by On Off Call function it will have 1(on), 0(unknown), -1(off) for its values)
     * @param   direction   instance of String
     * @param   count   instance of Long
     * @return   parameter "top_changers_mappings" of original type "sample_comparison_mapping" (mapping Sample Id for the numerator to a DenominatorSampleComparison.  This is the comparison data structure {NumeratorSampleId->{denominatorLabel -> {feature -> log2ratio}}}) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to original type "denominator_sample_comparison" (mapping ComparisonDenominatorLabel to DataSampleComparison mapping) &rarr; mapping from original type "comparison_denominator_label" (denominator label is the label for the denominator in a comparison. This label can be a single sampleId (default or defined) or a comma separated list of sampleIds that were averaged.) to original type "data_sample_comparison" (mapping kbase feature id as the key and log2Ratio as the value) &rarr; mapping from original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future) to original type "log2_ratio" (Log2Ratio Log2Level of sample over log2Level of another sample for a given feature. Note if the Ratio is consumed by On Off Call function it will have 1(on), 0(unknown), -1(off) for its values)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_top_changers")
    public Map<String,Map<String,Map<String,Double>>> getTopChangers(Map<String,Map<String,Map<String,Double>>> sampleComparisonMapping, String direction, Long count) throws Exception {
        Map<String,Map<String,Map<String,Double>>> returnVal = null;
        //BEGIN get_top_changers
        //END get_top_changers
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_titles</p>
     * <pre>
     * given a List of SampleIDs, returns a Hash (key : SampleID, value: Title of Sample)
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "samples_titles_map" of original type "samples_string_map" (Mapping between sample id and corresponding value.   Used as return for get_expression_samples_(titles,descriptions,molecules,types,external_source_ids)) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to String
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_titles")
    public Map<String,String> getExpressionSamplesTitles(List<String> sampleIds) throws Exception {
        Map<String,String> returnVal = null;
        //BEGIN get_expression_samples_titles
        //END get_expression_samples_titles
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_descriptions</p>
     * <pre>
     * given a List of SampleIDs, returns a Hash (key : SampleID, value: Description of Sample)
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "samples_descriptions_map" of original type "samples_string_map" (Mapping between sample id and corresponding value.   Used as return for get_expression_samples_(titles,descriptions,molecules,types,external_source_ids)) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to String
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_descriptions")
    public Map<String,String> getExpressionSamplesDescriptions(List<String> sampleIds) throws Exception {
        Map<String,String> returnVal = null;
        //BEGIN get_expression_samples_descriptions
        //END get_expression_samples_descriptions
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_molecules</p>
     * <pre>
     * given a List of SampleIDs, returns a Hash (key : SampleID, value: Molecule of Sample)
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "samples_molecules_map" of original type "samples_string_map" (Mapping between sample id and corresponding value.   Used as return for get_expression_samples_(titles,descriptions,molecules,types,external_source_ids)) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to String
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_molecules")
    public Map<String,String> getExpressionSamplesMolecules(List<String> sampleIds) throws Exception {
        Map<String,String> returnVal = null;
        //BEGIN get_expression_samples_molecules
        //END get_expression_samples_molecules
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_types</p>
     * <pre>
     * given a List of SampleIDs, returns a Hash (key : SampleID, value: Type of Sample)
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "samples_types_map" of original type "samples_string_map" (Mapping between sample id and corresponding value.   Used as return for get_expression_samples_(titles,descriptions,molecules,types,external_source_ids)) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to String
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_types")
    public Map<String,String> getExpressionSamplesTypes(List<String> sampleIds) throws Exception {
        Map<String,String> returnVal = null;
        //BEGIN get_expression_samples_types
        //END get_expression_samples_types
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_external_source_ids</p>
     * <pre>
     * given a List of SampleIDs, returns a Hash (key : SampleID, value: External_Source_ID of Sample (typically GSM))
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "samples_external_source_id_map" of original type "samples_string_map" (Mapping between sample id and corresponding value.   Used as return for get_expression_samples_(titles,descriptions,molecules,types,external_source_ids)) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to String
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_external_source_ids")
    public Map<String,String> getExpressionSamplesExternalSourceIds(List<String> sampleIds) throws Exception {
        Map<String,String> returnVal = null;
        //BEGIN get_expression_samples_external_source_ids
        //END get_expression_samples_external_source_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_original_log2_medians</p>
     * <pre>
     * given a List of SampleIDs, returns a Hash (key : SampleID, value: OriginalLog2Median of Sample)
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "samples_float_map" of original type "samples_float_map" (Mapping between sample id and corresponding value.   Used as return for get_expression_samples_original_log2_median) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to Double
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_samples_original_log2_medians")
    public Map<String,Double> getExpressionSamplesOriginalLog2Medians(List<String> sampleIds) throws Exception {
        Map<String,Double> returnVal = null;
        //BEGIN get_expression_samples_original_log2_medians
        //END get_expression_samples_original_log2_medians
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_series_titles</p>
     * <pre>
     * given a List of SeriesIDs, returns a Hash (key : SeriesID, value: Title of Series)
     * </pre>
     * @param   seriesIds   instance of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @return   parameter "series_string_map" of original type "series_string_map" (Mapping between sample id and corresponding value.   Used as return for get_series_(titles,summaries,designs,external_source_ids)) &rarr; mapping from original type "series_id" (Kbase Series ID) to String
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_series_titles")
    public Map<String,String> getExpressionSeriesTitles(List<String> seriesIds) throws Exception {
        Map<String,String> returnVal = null;
        //BEGIN get_expression_series_titles
        //END get_expression_series_titles
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_series_summaries</p>
     * <pre>
     * given a List of SeriesIDs, returns a Hash (key : SeriesID, value: Summary of Series)
     * </pre>
     * @param   seriesIds   instance of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @return   parameter "series_string_map" of original type "series_string_map" (Mapping between sample id and corresponding value.   Used as return for get_series_(titles,summaries,designs,external_source_ids)) &rarr; mapping from original type "series_id" (Kbase Series ID) to String
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_series_summaries")
    public Map<String,String> getExpressionSeriesSummaries(List<String> seriesIds) throws Exception {
        Map<String,String> returnVal = null;
        //BEGIN get_expression_series_summaries
        //END get_expression_series_summaries
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_series_designs</p>
     * <pre>
     * given a List of SeriesIDs, returns a Hash (key : SeriesID, value: Design of Series)
     * </pre>
     * @param   seriesIds   instance of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @return   parameter "series_string_map" of original type "series_string_map" (Mapping between sample id and corresponding value.   Used as return for get_series_(titles,summaries,designs,external_source_ids)) &rarr; mapping from original type "series_id" (Kbase Series ID) to String
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_series_designs")
    public Map<String,String> getExpressionSeriesDesigns(List<String> seriesIds) throws Exception {
        Map<String,String> returnVal = null;
        //BEGIN get_expression_series_designs
        //END get_expression_series_designs
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_series_external_source_ids</p>
     * <pre>
     * given a List of SeriesIDs, returns a Hash (key : SeriesID, value: External_Source_ID of Series (typically GSE))
     * </pre>
     * @param   seriesIds   instance of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @return   parameter "series_string_map" of original type "series_string_map" (Mapping between sample id and corresponding value.   Used as return for get_series_(titles,summaries,designs,external_source_ids)) &rarr; mapping from original type "series_id" (Kbase Series ID) to String
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_series_external_source_ids")
    public Map<String,String> getExpressionSeriesExternalSourceIds(List<String> seriesIds) throws Exception {
        Map<String,String> returnVal = null;
        //BEGIN get_expression_series_external_source_ids
        //END get_expression_series_external_source_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_sample_external_source_ids</p>
     * <pre>
     * get sample ids by the sample's external source id : Takes a list of sample external source ids, and returns a list of sample ids
     * </pre>
     * @param   externalSourceIds   instance of original type "external_source_ids" (list of externalSourceIDs) &rarr; list of original type "external_source_id" (externalSourceId (could be for Platform, Sample or Series)(typically maps to a GPL, GSM or GSE from GEO))
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_sample_ids_by_sample_external_source_ids")
    public List<String> getExpressionSampleIdsBySampleExternalSourceIds(List<String> externalSourceIds) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_expression_sample_ids_by_sample_external_source_ids
        //END get_expression_sample_ids_by_sample_external_source_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_platform_external_source_ids</p>
     * <pre>
     * get sample ids by the platform's external source id : Takes a list of platform external source ids, and returns a list of sample ids
     * </pre>
     * @param   externalSourceIds   instance of original type "external_source_ids" (list of externalSourceIDs) &rarr; list of original type "external_source_id" (externalSourceId (could be for Platform, Sample or Series)(typically maps to a GPL, GSM or GSE from GEO))
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_sample_ids_by_platform_external_source_ids")
    public List<String> getExpressionSampleIdsByPlatformExternalSourceIds(List<String> externalSourceIds) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_expression_sample_ids_by_platform_external_source_ids
        //END get_expression_sample_ids_by_platform_external_source_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_expression_series_ids_by_series_external_source_ids</p>
     * <pre>
     * get series ids by the series's external source id : Takes a list of series external source ids, and returns a list of series ids
     * </pre>
     * @param   externalSourceIds   instance of original type "external_source_ids" (list of externalSourceIDs) &rarr; list of original type "external_source_id" (externalSourceId (could be for Platform, Sample or Series)(typically maps to a GPL, GSM or GSE from GEO))
     * @return   parameter "series_ids" of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_expression_series_ids_by_series_external_source_ids")
    public List<String> getExpressionSeriesIdsBySeriesExternalSourceIds(List<String> externalSourceIds) throws Exception {
        List<String> returnVal = null;
        //BEGIN get_expression_series_ids_by_series_external_source_ids
        //END get_expression_series_ids_by_series_external_source_ids
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_GEO_GSE</p>
     * <pre>
     * given a GEO GSE ID, it will return a complex data structure to be put int the upload tab files
     * </pre>
     * @param   gseInputId   instance of String
     * @return   parameter "gseObject" of type {@link us.kbase.kbaseexpression.GseObject GseObject}
     */
    @JsonServerMethod(rpc = "KBaseExpression.get_GEO_GSE")
    public GseObject getGEOGSE(String gseInputId) throws Exception {
        GseObject returnVal = null;
        //BEGIN get_GEO_GSE
        //END get_GEO_GSE
        return returnVal;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: <program> <server_port>");
            return;
        }
        new KBaseExpressionServer().startupServer(Integer.parseInt(args[0]));
    }
}
