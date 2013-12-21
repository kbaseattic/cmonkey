package us.kbase.expressionservices;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UnauthorizedException;

/**
 * <p>Original spec-file module name: ExpressionServices</p>
 * <pre>
 * Service for all different sorts of Expression data (microarray, RNA_seq, proteomics, qPCR
 * </pre>
 */
public class ExpressionServicesClient {
    private JsonClientCaller caller;

    public ExpressionServicesClient(URL url) {
        caller = new JsonClientCaller(url);
    }

    public ExpressionServicesClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, token);
    }

    public ExpressionServicesClient(URL url, String user, String password) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password);
    }

	public void setConnectionReadTimeOut(Integer milliseconds) {
		this.caller.setConnectionReadTimeOut(milliseconds);
	}

    public boolean isAuthAllowedForHttp() {
        return caller.isAuthAllowedForHttp();
    }

    public void setAuthAllowedForHttp(boolean isAuthAllowedForHttp) {
        caller.setAuthAllowedForHttp(isAuthAllowedForHttp);
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_data</p>
     * <pre>
     * core function used by many others.  Given a list of KBase SampleIds returns mapping of SampleId to expressionSampleDataStructure (essentially the core Expression Sample Object) : 
     * {sample_id -> expressionSampleDataStructure}
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "expression_data_samples_map" of original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.expressionservices.ExpressionDataSample ExpressionDataSample}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,ExpressionDataSample> getExpressionSamplesData(List<String> sampleIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(sampleIds);
        TypeReference<List<Map<String,ExpressionDataSample>>> retType = new TypeReference<List<Map<String,ExpressionDataSample>>>() {};
        List<Map<String,ExpressionDataSample>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_data", args, retType, true, false);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,Double>> getExpressionDataBySamplesAndFeatures(List<String> sampleIds, List<String> featureIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(sampleIds);
        args.add(featureIds);
        TypeReference<List<Map<String,Map<String,Double>>>> retType = new TypeReference<List<Map<String,Map<String,Double>>>>() {};
        List<Map<String,Map<String,Double>>> res = caller.jsonrpcCall("ExpressionServices.get_expression_data_by_samples_and_features", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_data_by_series_ids</p>
     * <pre>
     * given a list of SeriesIDs returns mapping of SeriesID to expressionDataSamples : {series_id -> {sample_id -> expressionSampleDataStructure}}
     * </pre>
     * @param   seriesIds   instance of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @return   parameter "series_expression_data_samples_mapping" of original type "series_expression_data_samples_mapping" (mapping between seriesIDs and all Samples it contains) &rarr; mapping from original type "series_id" (Kbase Series ID) to original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.expressionservices.ExpressionDataSample ExpressionDataSample}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,ExpressionDataSample>> getExpressionSamplesDataBySeriesIds(List<String> seriesIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(seriesIds);
        TypeReference<List<Map<String,Map<String,ExpressionDataSample>>>> retType = new TypeReference<List<Map<String,Map<String,ExpressionDataSample>>>>() {};
        List<Map<String,Map<String,ExpressionDataSample>>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_data_by_series_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_series_ids</p>
     * <pre>
     * given a list of SeriesIDs returns a list of Sample IDs
     * </pre>
     * @param   seriesIds   instance of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getExpressionSampleIdsBySeriesIds(List<String> seriesIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(seriesIds);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_sample_ids_by_series_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_data_by_experimental_unit_ids</p>
     * <pre>
     * given a list of ExperimentalUnitIDs returns mapping of ExperimentalUnitID to expressionDataSamples : {experimental_unit_id -> {sample_id -> expressionSampleDataStructure}}
     * </pre>
     * @param   experimentalUnitIds   instance of original type "experimental_unit_ids" (list of KBase ExperimentalUnit IDs) &rarr; list of original type "experimental_unit_id" (Kbase ExperimentalUnit ID)
     * @return   parameter "experimental_unit_expression_data_samples_mapping" of original type "experimental_unit_expression_data_samples_mapping" (mapping between experimentalUnitIDs and all Samples it contains) &rarr; mapping from original type "experimental_unit_id" (Kbase ExperimentalUnit ID) to original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.expressionservices.ExpressionDataSample ExpressionDataSample}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,ExpressionDataSample>> getExpressionSamplesDataByExperimentalUnitIds(List<String> experimentalUnitIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(experimentalUnitIds);
        TypeReference<List<Map<String,Map<String,ExpressionDataSample>>>> retType = new TypeReference<List<Map<String,Map<String,ExpressionDataSample>>>>() {};
        List<Map<String,Map<String,ExpressionDataSample>>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_data_by_experimental_unit_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_experimental_unit_ids</p>
     * <pre>
     * given a list of ExperimentalUnitIDs returns a list of Sample IDs
     * </pre>
     * @param   experimentalUnitIds   instance of original type "experimental_unit_ids" (list of KBase ExperimentalUnit IDs) &rarr; list of original type "experimental_unit_id" (Kbase ExperimentalUnit ID)
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getExpressionSampleIdsByExperimentalUnitIds(List<String> experimentalUnitIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(experimentalUnitIds);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_sample_ids_by_experimental_unit_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_data_by_experiment_meta_ids</p>
     * <pre>
     * given a list of ExperimentMetaIDs returns mapping of {experimentMetaID -> {experimentalUnitId -> {sample_id -> expressionSampleDataStructure}}}
     * </pre>
     * @param   experimentMetaIds   instance of original type "experiment_meta_ids" (list of KBase ExperimentMeta IDs) &rarr; list of original type "experiment_meta_id" (Kbase ExperimentMeta ID)
     * @return   parameter "experiment_meta_expression_data_samples_mapping" of original type "experiment_meta_expression_data_samples_mapping" (mapping between experimentMetaIDs and ExperimentalUnitExpressionDataSamplesMapping it contains) &rarr; mapping from original type "experiment_meta_id" (Kbase ExperimentMeta ID) to original type "experimental_unit_expression_data_samples_mapping" (mapping between experimentalUnitIDs and all Samples it contains) &rarr; mapping from original type "experimental_unit_id" (Kbase ExperimentalUnit ID) to original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.expressionservices.ExpressionDataSample ExpressionDataSample}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,Map<String,ExpressionDataSample>>> getExpressionSamplesDataByExperimentMetaIds(List<String> experimentMetaIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(experimentMetaIds);
        TypeReference<List<Map<String,Map<String,Map<String,ExpressionDataSample>>>>> retType = new TypeReference<List<Map<String,Map<String,Map<String,ExpressionDataSample>>>>>() {};
        List<Map<String,Map<String,Map<String,ExpressionDataSample>>>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_data_by_experiment_meta_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_experiment_meta_ids</p>
     * <pre>
     * given a list of ExperimentMetaIDs returns a list of Sample IDs
     * </pre>
     * @param   experimentMetaIds   instance of original type "experiment_meta_ids" (list of KBase ExperimentMeta IDs) &rarr; list of original type "experiment_meta_id" (Kbase ExperimentMeta ID)
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getExpressionSampleIdsByExperimentMetaIds(List<String> experimentMetaIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(experimentMetaIds);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_sample_ids_by_experiment_meta_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_data_by_strain_ids</p>
     * <pre>
     * given a list of Strains, and a SampleType (controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics) , it returns a StrainExpressionDataSamplesMapping,  
     * StrainId -> ExpressionSampleDataStructure {strain_id -> {sample_id -> expressionSampleDataStructure}}
     * </pre>
     * @param   strainIds   instance of original type "strain_ids" (list of KBase StrainIDs) &rarr; list of original type "strain_id" (KBase StrainID)
     * @param   sampleType   instance of original type "sample_type" (Sample type controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics)
     * @return   parameter "strain_expression_data_samples_mapping" of original type "strain_expression_data_samples_mapping" (mapping between strainIDs and all Samples it contains) &rarr; mapping from original type "strain_id" (KBase StrainID) to original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.expressionservices.ExpressionDataSample ExpressionDataSample}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,ExpressionDataSample>> getExpressionSamplesDataByStrainIds(List<String> strainIds, String sampleType) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(strainIds);
        args.add(sampleType);
        TypeReference<List<Map<String,Map<String,ExpressionDataSample>>>> retType = new TypeReference<List<Map<String,Map<String,ExpressionDataSample>>>>() {};
        List<Map<String,Map<String,ExpressionDataSample>>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_data_by_strain_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_strain_ids</p>
     * <pre>
     * given a list of Strains, and a SampleType, it returns a list of Sample IDs
     * </pre>
     * @param   strainIds   instance of original type "strain_ids" (list of KBase StrainIDs) &rarr; list of original type "strain_id" (KBase StrainID)
     * @param   sampleType   instance of original type "sample_type" (Sample type controlled vocabulary : microarray, RNA-Seq, qPCR, or proteomics)
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getExpressionSampleIdsByStrainIds(List<String> strainIds, String sampleType) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(strainIds);
        args.add(sampleType);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_sample_ids_by_strain_ids", args, retType, true, false);
        return res.get(0);
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
     * @return   parameter "genome_expression_data_samples_mapping" of original type "genome_expression_data_samples_mapping" (mapping between genomeIDs and all StrainExpressionDataSamplesMapping it contains) &rarr; mapping from original type "genome_id" (KBase GenomeID id ws KB.Genome "ws" may change to "to" in the future) to original type "strain_expression_data_samples_mapping" (mapping between strainIDs and all Samples it contains) &rarr; mapping from original type "strain_id" (KBase StrainID) to original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.expressionservices.ExpressionDataSample ExpressionDataSample}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,Map<String,ExpressionDataSample>>> getExpressionSamplesDataByGenomeIds(List<String> genomeIds, String sampleType, Long wildTypeOnly) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(genomeIds);
        args.add(sampleType);
        args.add(wildTypeOnly);
        TypeReference<List<Map<String,Map<String,Map<String,ExpressionDataSample>>>>> retType = new TypeReference<List<Map<String,Map<String,Map<String,ExpressionDataSample>>>>>() {};
        List<Map<String,Map<String,Map<String,ExpressionDataSample>>>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_data_by_genome_ids", args, retType, true, false);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getExpressionSampleIdsByGenomeIds(List<String> genomeIds, String sampleType, Long wildTypeOnly) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(genomeIds);
        args.add(sampleType);
        args.add(wildTypeOnly);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_sample_ids_by_genome_ids", args, retType, true, false);
        return res.get(0);
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
     * @return   parameter "ontology_expression_data_sample_mapping" of original type "ontology_expression_data_sample_mapping" (mapping between ontologyIDs (concatenated if searched for with the and operator) and all the Samples that match that term(s)) &rarr; mapping from original type "ontology_id" (Kbase OntologyID) to original type "expression_data_samples_map" (Mapping between sampleID and ExpressionDataSample) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to type {@link us.kbase.expressionservices.ExpressionDataSample ExpressionDataSample}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,ExpressionDataSample>> getExpressionSamplesDataByOntologyIds(List<String> ontologyIds, String andOr, String genomeId, String sampleType, Long wildTypeOnly) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ontologyIds);
        args.add(andOr);
        args.add(genomeId);
        args.add(sampleType);
        args.add(wildTypeOnly);
        TypeReference<List<Map<String,Map<String,ExpressionDataSample>>>> retType = new TypeReference<List<Map<String,Map<String,ExpressionDataSample>>>>() {};
        List<Map<String,Map<String,ExpressionDataSample>>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_data_by_ontology_ids", args, retType, true, false);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getExpressionSampleIdsByOntologyIds(List<String> ontologyIds, String andOr, String genomeId, String sampleType, Long wildTypeOnly) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ontologyIds);
        args.add(andOr);
        args.add(genomeId);
        args.add(sampleType);
        args.add(wildTypeOnly);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_sample_ids_by_ontology_ids", args, retType, true, false);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,Double>> getExpressionDataByFeatureIds(List<String> featureIds, String sampleType, Long wildTypeOnly) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(featureIds);
        args.add(sampleType);
        args.add(wildTypeOnly);
        TypeReference<List<Map<String,Map<String,Double>>>> retType = new TypeReference<List<Map<String,Map<String,Double>>>>() {};
        List<Map<String,Map<String,Double>>> res = caller.jsonrpcCall("ExpressionServices.get_expression_data_by_feature_ids", args, retType, true, false);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,Map<String,Double>>> compareSamples(Map<String,Map<String,Double>> numeratorsDataMapping, Map<String,Map<String,Double>> denominatorsDataMapping) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(numeratorsDataMapping);
        args.add(denominatorsDataMapping);
        TypeReference<List<Map<String,Map<String,Map<String,Double>>>>> retType = new TypeReference<List<Map<String,Map<String,Map<String,Double>>>>>() {};
        List<Map<String,Map<String,Map<String,Double>>>> res = caller.jsonrpcCall("ExpressionServices.compare_samples", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: compare_samples_vs_default_controls</p>
     * <pre>
     * Compares each sample vs its defined default control.  If the Default control is not specified for a sample, then nothing is returned for that sample .
     * Takes a list of sampleIDs returns SampleComparisonMapping {sample_id ->{denominator_default_control sample_id ->{feature_id -> log2Ratio}}}
     * </pre>
     * @param   numeratorSampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "sample_comparison_mapping" of original type "sample_comparison_mapping" (mapping Sample Id for the numerator to a DenominatorSampleComparison.  This is the comparison data structure {NumeratorSampleId->{denominatorLabel -> {feature -> log2ratio}}}) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to original type "denominator_sample_comparison" (mapping ComparisonDenominatorLabel to DataSampleComparison mapping) &rarr; mapping from original type "comparison_denominator_label" (denominator label is the label for the denominator in a comparison. This label can be a single sampleId (default or defined) or a comma separated list of sampleIds that were averaged.) to original type "data_sample_comparison" (mapping kbase feature id as the key and log2Ratio as the value) &rarr; mapping from original type "feature_id" (KBase Feature ID for a feature, typically CDS/PEG id ws KB.Feature "ws" may change to "to" in the future) to original type "log2_ratio" (Log2Ratio Log2Level of sample over log2Level of another sample for a given feature. Note if the Ratio is consumed by On Off Call function it will have 1(on), 0(unknown), -1(off) for its values)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,Map<String,Double>>> compareSamplesVsDefaultControls(List<String> numeratorSampleIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(numeratorSampleIds);
        TypeReference<List<Map<String,Map<String,Map<String,Double>>>>> retType = new TypeReference<List<Map<String,Map<String,Map<String,Double>>>>>() {};
        List<Map<String,Map<String,Map<String,Double>>>> res = caller.jsonrpcCall("ExpressionServices.compare_samples_vs_default_controls", args, retType, true, false);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,Map<String,Double>>> compareSamplesVsTheAverage(List<String> numeratorSampleIds, List<String> denominatorSampleIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(numeratorSampleIds);
        args.add(denominatorSampleIds);
        TypeReference<List<Map<String,Map<String,Map<String,Double>>>>> retType = new TypeReference<List<Map<String,Map<String,Map<String,Double>>>>>() {};
        List<Map<String,Map<String,Map<String,Double>>>> res = caller.jsonrpcCall("ExpressionServices.compare_samples_vs_the_average", args, retType, true, false);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,Map<String,Double>>> getOnOffCalls(Map<String,Map<String,Map<String,Double>>> sampleComparisonMapping, Double offThreshold, Double onThreshold) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(sampleComparisonMapping);
        args.add(offThreshold);
        args.add(onThreshold);
        TypeReference<List<Map<String,Map<String,Map<String,Double>>>>> retType = new TypeReference<List<Map<String,Map<String,Map<String,Double>>>>>() {};
        List<Map<String,Map<String,Map<String,Double>>>> res = caller.jsonrpcCall("ExpressionServices.get_on_off_calls", args, retType, true, false);
        return res.get(0);
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
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,Map<String,Double>>> getTopChangers(Map<String,Map<String,Map<String,Double>>> sampleComparisonMapping, String direction, Long count) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(sampleComparisonMapping);
        args.add(direction);
        args.add(count);
        TypeReference<List<Map<String,Map<String,Map<String,Double>>>>> retType = new TypeReference<List<Map<String,Map<String,Map<String,Double>>>>>() {};
        List<Map<String,Map<String,Map<String,Double>>>> res = caller.jsonrpcCall("ExpressionServices.get_top_changers", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_titles</p>
     * <pre>
     * given a List of SampleIDs, returns a Hash (key : SampleID, value: Title of Sample)
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "samples_titles_map" of original type "samples_string_map" (Mapping between sample id and corresponding value.   Used as return for get_expression_samples_(titles,descriptions,molecules,types,external_source_ids)) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getExpressionSamplesTitles(List<String> sampleIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(sampleIds);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_titles", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_descriptions</p>
     * <pre>
     * given a List of SampleIDs, returns a Hash (key : SampleID, value: Description of Sample)
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "samples_descriptions_map" of original type "samples_string_map" (Mapping between sample id and corresponding value.   Used as return for get_expression_samples_(titles,descriptions,molecules,types,external_source_ids)) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getExpressionSamplesDescriptions(List<String> sampleIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(sampleIds);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_descriptions", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_molecules</p>
     * <pre>
     * given a List of SampleIDs, returns a Hash (key : SampleID, value: Molecule of Sample)
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "samples_molecules_map" of original type "samples_string_map" (Mapping between sample id and corresponding value.   Used as return for get_expression_samples_(titles,descriptions,molecules,types,external_source_ids)) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getExpressionSamplesMolecules(List<String> sampleIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(sampleIds);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_molecules", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_types</p>
     * <pre>
     * given a List of SampleIDs, returns a Hash (key : SampleID, value: Type of Sample)
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "samples_types_map" of original type "samples_string_map" (Mapping between sample id and corresponding value.   Used as return for get_expression_samples_(titles,descriptions,molecules,types,external_source_ids)) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getExpressionSamplesTypes(List<String> sampleIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(sampleIds);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_types", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_external_source_ids</p>
     * <pre>
     * given a List of SampleIDs, returns a Hash (key : SampleID, value: External_Source_ID of Sample (typically GSM))
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "samples_external_source_id_map" of original type "samples_string_map" (Mapping between sample id and corresponding value.   Used as return for get_expression_samples_(titles,descriptions,molecules,types,external_source_ids)) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getExpressionSamplesExternalSourceIds(List<String> sampleIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(sampleIds);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_external_source_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_samples_original_log2_medians</p>
     * <pre>
     * given a List of SampleIDs, returns a Hash (key : SampleID, value: OriginalLog2Median of Sample)
     * </pre>
     * @param   sampleIds   instance of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @return   parameter "samples_float_map" of original type "samples_float_map" (Mapping between sample id and corresponding value.   Used as return for get_expression_samples_original_log2_median) &rarr; mapping from original type "sample_id" (KBase Sample ID for the sample) to Double
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Double> getExpressionSamplesOriginalLog2Medians(List<String> sampleIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(sampleIds);
        TypeReference<List<Map<String,Double>>> retType = new TypeReference<List<Map<String,Double>>>() {};
        List<Map<String,Double>> res = caller.jsonrpcCall("ExpressionServices.get_expression_samples_original_log2_medians", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_series_titles</p>
     * <pre>
     * given a List of SeriesIDs, returns a Hash (key : SeriesID, value: Title of Series)
     * </pre>
     * @param   seriesIds   instance of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @return   parameter "series_string_map" of original type "series_string_map" (Mapping between sample id and corresponding value.   Used as return for get_series_(titles,summaries,designs,external_source_ids)) &rarr; mapping from original type "series_id" (Kbase Series ID) to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getExpressionSeriesTitles(List<String> seriesIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(seriesIds);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_series_titles", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_series_summaries</p>
     * <pre>
     * given a List of SeriesIDs, returns a Hash (key : SeriesID, value: Summary of Series)
     * </pre>
     * @param   seriesIds   instance of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @return   parameter "series_string_map" of original type "series_string_map" (Mapping between sample id and corresponding value.   Used as return for get_series_(titles,summaries,designs,external_source_ids)) &rarr; mapping from original type "series_id" (Kbase Series ID) to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getExpressionSeriesSummaries(List<String> seriesIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(seriesIds);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_series_summaries", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_series_designs</p>
     * <pre>
     * given a List of SeriesIDs, returns a Hash (key : SeriesID, value: Design of Series)
     * </pre>
     * @param   seriesIds   instance of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @return   parameter "series_string_map" of original type "series_string_map" (Mapping between sample id and corresponding value.   Used as return for get_series_(titles,summaries,designs,external_source_ids)) &rarr; mapping from original type "series_id" (Kbase Series ID) to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getExpressionSeriesDesigns(List<String> seriesIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(seriesIds);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_series_designs", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_series_external_source_ids</p>
     * <pre>
     * given a List of SeriesIDs, returns a Hash (key : SeriesID, value: External_Source_ID of Series (typically GSE))
     * </pre>
     * @param   seriesIds   instance of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @return   parameter "series_string_map" of original type "series_string_map" (Mapping between sample id and corresponding value.   Used as return for get_series_(titles,summaries,designs,external_source_ids)) &rarr; mapping from original type "series_id" (Kbase Series ID) to String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getExpressionSeriesExternalSourceIds(List<String> seriesIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(seriesIds);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_series_external_source_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_sample_external_source_ids</p>
     * <pre>
     * get sample ids by the sample's external source id : Takes a list of sample external source ids, and returns a list of sample ids
     * </pre>
     * @param   externalSourceIds   instance of original type "external_source_ids" (list of externalSourceIDs) &rarr; list of original type "external_source_id" (externalSourceId (could be for Platform, Sample or Series)(typically maps to a GPL, GSM or GSE from GEO))
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getExpressionSampleIdsBySampleExternalSourceIds(List<String> externalSourceIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(externalSourceIds);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_sample_ids_by_sample_external_source_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_sample_ids_by_platform_external_source_ids</p>
     * <pre>
     * get sample ids by the platform's external source id : Takes a list of platform external source ids, and returns a list of sample ids
     * </pre>
     * @param   externalSourceIds   instance of original type "external_source_ids" (list of externalSourceIDs) &rarr; list of original type "external_source_id" (externalSourceId (could be for Platform, Sample or Series)(typically maps to a GPL, GSM or GSE from GEO))
     * @return   parameter "sample_ids" of original type "sample_ids" (List of KBase Sample IDs) &rarr; list of original type "sample_id" (KBase Sample ID for the sample)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getExpressionSampleIdsByPlatformExternalSourceIds(List<String> externalSourceIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(externalSourceIds);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_sample_ids_by_platform_external_source_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_series_ids_by_series_external_source_ids</p>
     * <pre>
     * get series ids by the series's external source id : Takes a list of series external source ids, and returns a list of series ids
     * </pre>
     * @param   externalSourceIds   instance of original type "external_source_ids" (list of externalSourceIDs) &rarr; list of original type "external_source_id" (externalSourceId (could be for Platform, Sample or Series)(typically maps to a GPL, GSM or GSE from GEO))
     * @return   parameter "series_ids" of original type "series_ids" (list of KBase Series IDs) &rarr; list of original type "series_id" (Kbase Series ID)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getExpressionSeriesIdsBySeriesExternalSourceIds(List<String> externalSourceIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(externalSourceIds);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_expression_series_ids_by_series_external_source_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_GEO_GSE</p>
     * <pre>
     * given a GEO GSE ID, it will return a complex data structure to be put int the upload tab files
     * </pre>
     * @param   gseInputId   instance of String
     * @return   parameter "gseObject" of type {@link us.kbase.expressionservices.GseObject GseObject}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GseObject getGEOGSE(String gseInputId) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(gseInputId);
        TypeReference<List<GseObject>> retType = new TypeReference<List<GseObject>>() {};
        List<GseObject> res = caller.jsonrpcCall("ExpressionServices.get_GEO_GSE", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_platform</p>
     * <pre>
     * Given a KBase Platfrom ID (kb_id) returns an ExpressionPlatform typed object
     * </pre>
     * @param   kbPlatformId   instance of String
     * @return   parameter "expression_platform" of type {@link us.kbase.expressionservices.ExpressionPlatform ExpressionPlatform}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExpressionPlatform getExpressionPlatform(String kbPlatformId) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(kbPlatformId);
        TypeReference<List<ExpressionPlatform>> retType = new TypeReference<List<ExpressionPlatform>>() {};
        List<ExpressionPlatform> res = caller.jsonrpcCall("ExpressionServices.get_expression_platform", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_sample</p>
     * <pre>
     * Given a KBase Sample ID (kb_id) returns an ExpressionSample typed object
     * </pre>
     * @param   kbSampleId   instance of String
     * @return   parameter "expression_sample" of type {@link us.kbase.expressionservices.ExpressionSample ExpressionSample}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExpressionSample getExpressionSample(String kbSampleId) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(kbSampleId);
        TypeReference<List<ExpressionSample>> retType = new TypeReference<List<ExpressionSample>>() {};
        List<ExpressionSample> res = caller.jsonrpcCall("ExpressionServices.get_expression_sample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_expression_series</p>
     * <pre>
     * Given a KBase Series ID (kb_id) returns an ExpressionSeries typed object
     * </pre>
     * @param   kbSeriesId   instance of String
     * @return   parameter "expression_series" of type {@link us.kbase.expressionservices.ExpressionSeries ExpressionSeries}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExpressionSeries getExpressionSeries(String kbSeriesId) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(kbSeriesId);
        TypeReference<List<ExpressionSeries>> retType = new TypeReference<List<ExpressionSeries>>() {};
        List<ExpressionSeries> res = caller.jsonrpcCall("ExpressionServices.get_expression_series", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: ws_import_of_expression_typed_object</p>
     * <pre>
     * Given a 1) hash ref(that contain ws_name or ws_id key and value), 
     *         2) typed_object type (must be single quoted string of 
     *                 Must be one of the following : 'ExpressionOntology', 'ExpressionPlatform', 'ExpressionSample' or 'ExpressionSeries' 
     *         3) an array ref with a list of kb_ids to be imported.
     * </pre>
     * @param   workspaceIdentifyingMap   instance of original type "workspace_identifying_map" (Mapping between ws_name or ws_id and their values.  This is a way to identify which workspace to use. Must contain either 'ws_name' or 'ws_id' key.) &rarr; mapping from String to String
     * @param   expressionTypedObject   instance of String
     * @param   arg3   instance of original type "expression_kbase_ids" (A list of kbase_ids used to fetch the workspace expression typed objects (ExpressionOntology, ExpressionPlatform, ExpressionSample, ExpressionSeries)) &rarr; list of String
     * @return   parameter "expression_kbase_ids" of original type "expression_kbase_ids" (A list of kbase_ids used to fetch the workspace expression typed objects (ExpressionOntology, ExpressionPlatform, ExpressionSample, ExpressionSeries)) &rarr; list of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> wsImportOfExpressionTypedObject(Map<String,String> workspaceIdentifyingMap, String expressionTypedObject, List<String> arg3) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(workspaceIdentifyingMap);
        args.add(expressionTypedObject);
        args.add(arg3);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.ws_import_of_expression_typed_object", args, retType, true, true);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_all_ontology_ids</p>
     * <pre>
     * get all the expression ontology ids
     * </pre>
     * @return   parameter "expression_kbase_ids" of original type "expression_kbase_ids" (A list of kbase_ids used to fetch the workspace expression typed objects (ExpressionOntology, ExpressionPlatform, ExpressionSample, ExpressionSeries)) &rarr; list of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getAllOntologyIds() throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_all_ontology_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_all_platform_ids</p>
     * <pre>
     * get all the expression platform ids
     * </pre>
     * @return   parameter "expression_kbase_ids" of original type "expression_kbase_ids" (A list of kbase_ids used to fetch the workspace expression typed objects (ExpressionOntology, ExpressionPlatform, ExpressionSample, ExpressionSeries)) &rarr; list of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getAllPlatformIds() throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_all_platform_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_all_sample_ids</p>
     * <pre>
     * get all the expression sample ids
     * </pre>
     * @return   parameter "expression_kbase_ids" of original type "expression_kbase_ids" (A list of kbase_ids used to fetch the workspace expression typed objects (ExpressionOntology, ExpressionPlatform, ExpressionSample, ExpressionSeries)) &rarr; list of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getAllSampleIds() throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_all_sample_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_all_series_ids</p>
     * <pre>
     * get all the expression series ids
     * </pre>
     * @return   parameter "expression_kbase_ids" of original type "expression_kbase_ids" (A list of kbase_ids used to fetch the workspace expression typed objects (ExpressionOntology, ExpressionPlatform, ExpressionSample, ExpressionSeries)) &rarr; list of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getAllSeriesIds() throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("ExpressionServices.get_all_series_ids", args, retType, true, false);
        return res.get(0);
    }
}
