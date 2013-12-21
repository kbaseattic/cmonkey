package us.kbase.networks;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;

/**
 * <p>Original spec-file module name: Networks</p>
 * <pre>
 * Module Networks version 2.0
 * This module provides access to various types of network-related datasets across all domains of  in a unified format.
 * Networks are composed of Nodes and Edges. Nodes represent entities from the datasets (e.g., genes, proteins,
 * biclusters, subystems, etc.), and edges represent relationships (e.g., protein-protein interactions,
 * gene-subsystem membership, etc.). Networks can contain Nodes and Edges from multiple datasets.
 * All methods in this module can be classified into two types: 
 * i. getting general information about datasets and network types currently available via Networks API
 *    For example: all_DatasetSources(), allnetwork_types(), datasetSource2Datasets()
 * ii. building various types of Network objects
 *    For example: buildFirstNeighborNetwork(), buildInternalNetwork()
 * </pre>
 */
public class NetworksClient {
    private JsonClientCaller caller;

    public NetworksClient(URL url) {
        caller = new JsonClientCaller(url);
    }

	public void setConnectionReadTimeOut(Integer milliseconds) {
		this.caller.setConnectionReadTimeOut(milliseconds);
	}

    /**
     * <p>Original spec-file function name: all_Datasets</p>
     * <pre>
     * Returns a list of all datasets that can be used to create a network
     * </pre>
     * @return   parameter "datasets" of list of type {@link us.kbase.networks.Dataset Dataset}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Dataset> allDatasets() throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<List<Dataset>>> retType = new TypeReference<List<List<Dataset>>>() {};
        List<List<Dataset>> res = caller.jsonrpcCall("Networks.all_Datasets", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_DatasetSources</p>
     * <pre>
     * Returns a list of all dataset sources available in  via Networks API
     * </pre>
     * @return   parameter "datasetSources" of list of type {@link us.kbase.networks.DatasetSource DatasetSource}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<DatasetSource> allDatasetSources() throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<List<DatasetSource>>> retType = new TypeReference<List<List<DatasetSource>>>() {};
        List<List<DatasetSource>> res = caller.jsonrpcCall("Networks.all_DatasetSources", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: allnetwork_types</p>
     * <pre>
     * Returns a list of all types of networks that can be created
     * </pre>
     * @return   parameter "networkTypes" of list of original type "network_type" (Type of network that can be created from a dataset)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> allnetworkTypes() throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("Networks.allnetwork_types", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_DatasetSource2Datasets</p>
     * <pre>
     * Returns a list of all datasets from a given dataset source                   
     *            dataset_source_ref datasetSourceRef - A reference to a dataset source
     * </pre>
     * @param   sourceRef   instance of original type "dataset_source_ref" (The name of a dataset that can be accessed as a source for creating a network)
     * @return   parameter "datasets" of list of type {@link us.kbase.networks.Dataset Dataset}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Dataset> getDatasetSource2Datasets(String sourceRef) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(sourceRef);
        TypeReference<List<List<Dataset>>> retType = new TypeReference<List<List<Dataset>>>() {};
        List<List<Dataset>> res = caller.jsonrpcCall("Networks.get_DatasetSource2Datasets", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_taxon2Datasets</p>
     * <pre>
     * Returns a list of all datasets that can be used to build a network for a particular genome represented by NCBI taxonomy id. 
     *        taxon taxon - NCBI taxonomy id
     * </pre>
     * @param   taxid   instance of original type "taxon" (NCBI taxonomy id)
     * @return   parameter "datasets" of list of type {@link us.kbase.networks.Dataset Dataset}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Dataset> getTaxon2Datasets(String taxid) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(taxid);
        TypeReference<List<List<Dataset>>> retType = new TypeReference<List<List<Dataset>>>() {};
        List<List<Dataset>> res = caller.jsonrpcCall("Networks.get_taxon2Datasets", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_network_type2Datasets</p>
     * <pre>
     * Returns a list of all datasets that can be used to build a network of a given type.
     *           network_type networkType - The type of network
     * </pre>
     * @param   netType   instance of original type "network_type" (Type of network that can be created from a dataset)
     * @return   parameter "datasets" of list of type {@link us.kbase.networks.Dataset Dataset}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Dataset> getNetworkType2Datasets(String netType) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(netType);
        TypeReference<List<List<Dataset>>> retType = new TypeReference<List<List<Dataset>>>() {};
        List<List<Dataset>> res = caller.jsonrpcCall("Networks.get_network_type2Datasets", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity2Datasets</p>
     * <pre>
     * Returns a list of all datasets that have at least one interaction for a given  entity
     * </pre>
     * @param   entityId   instance of String
     * @return   parameter "datasets" of list of type {@link us.kbase.networks.Dataset Dataset}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Dataset> getEntity2Datasets(String entityId) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(entityId);
        TypeReference<List<List<Dataset>>> retType = new TypeReference<List<List<Dataset>>>() {};
        List<List<Dataset>> res = caller.jsonrpcCall("Networks.get_entity2Datasets", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: build_first_neighbor_Network</p>
     * <pre>
     * Returns a "first-neighbor" network constructed based on a given list of datasets. A first-neighbor network contains 
     * "source" nodes and all other nodes that have at least one interaction with the "source" nodes. 
     * Only interactions of given types are considered.    
     *           list<string> dataset_ids - List of dataset identifiers to be used for building a network
     *        list<string> entity_ids - List of entity identifiers to be used as source nodes
     *           list<edge_type> edge_types - List of possible edge types to be considered for building a network
     * </pre>
     * @param   datasetIds   instance of list of String
     * @param   entityIds   instance of list of String
     * @param   edgeTypes   instance of list of original type "edge_type" (Type of edge in a network)
     * @return   parameter "network" of type {@link us.kbase.networks.Network Network}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Network buildFirstNeighborNetwork(List<String> datasetIds, List<String> entityIds, List<String> edgeTypes) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(datasetIds);
        args.add(entityIds);
        args.add(edgeTypes);
        TypeReference<List<Network>> retType = new TypeReference<List<Network>>() {};
        List<Network> res = caller.jsonrpcCall("Networks.build_first_neighbor_Network", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: build_first_neighbor_Network_limted_by_strength</p>
     * <pre>
     * Returns a "first-neighbor" network constructed basing on a given list of datasets. First-neighbor network contains 
     * "source" nodes and all other nodes that have at least one interaction with the "source" nodes. 
     * Only interactions of given types are considered. Additional cutOff parameter allows setting a threshold
     * on the strength of edges to be considered.   
     *           list<string> dataset_ids - List of dataset identifiers to be used for building a network
     *        list<string> entity_ids - List of entity identifiers to be used as source nodes
     *           list<edge_type> edge_types - List of possible edge types to be considered for building a network
     *           float cutOff - The threshold on the strength of edges to be considered for building a network
     * </pre>
     * @param   datasetIds   instance of list of String
     * @param   entityIds   instance of list of String
     * @param   edgeTypes   instance of list of original type "edge_type" (Type of edge in a network)
     * @param   cutOff   instance of Double
     * @return   parameter "network" of type {@link us.kbase.networks.Network Network}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Network buildFirstNeighborNetworkLimtedByStrength(List<String> datasetIds, List<String> entityIds, List<String> edgeTypes, Double cutOff) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(datasetIds);
        args.add(entityIds);
        args.add(edgeTypes);
        args.add(cutOff);
        TypeReference<List<Network>> retType = new TypeReference<List<Network>>() {};
        List<Network> res = caller.jsonrpcCall("Networks.build_first_neighbor_Network_limted_by_strength", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: build_internal_Network</p>
     * <pre>
     * Returns an "internal" network constructed based on a given list of datasets. 
     * Internal network contains only the nodes defined by the gene_ids parameter, 
     * and edges representing interactions between these nodes.  Only interactions of given types are considered.    
     *           list<string> dataset_ids - List of dataset identifiers to be used for building a network
     *        list<string> gene_ids - Identifiers of genes of interest for building a network         
     *           list<edge_type> edge_types - List of possible edge types to be considered for building a network
     * </pre>
     * @param   datasetIds   instance of list of String
     * @param   geneIds   instance of list of String
     * @param   edgeTypes   instance of list of original type "edge_type" (Type of edge in a network)
     * @return   parameter "network" of type {@link us.kbase.networks.Network Network}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Network buildInternalNetwork(List<String> datasetIds, List<String> geneIds, List<String> edgeTypes) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(datasetIds);
        args.add(geneIds);
        args.add(edgeTypes);
        TypeReference<List<Network>> retType = new TypeReference<List<Network>>() {};
        List<Network> res = caller.jsonrpcCall("Networks.build_internal_Network", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: build_internal_Network_limited_by_strength</p>
     * <pre>
     * Returns an "internal" network constructed based on a given list of datasets. 
     * Internal network contains the only nodes defined by the gene_ids parameter, 
     * and edges representing interactions between these nodes.  Only interactions of given types are considered. 
     * Additional cutOff parameter allows to set a threshold on the strength of edges to be considered.     
     *           list<string> dataset_ids - List of dataset identifiers to be used for building a network
     *        list<string> gene_ids - Identifiers of genes of interest for building a network         
     *           list<edge_type> edge_types - List of possible edge types to be considered for building a network
     *          float cutOff - The threshold on the strength of edges to be considered for building a network
     * </pre>
     * @param   datasetIds   instance of list of String
     * @param   geneIds   instance of list of String
     * @param   edgeTypes   instance of list of original type "edge_type" (Type of edge in a network)
     * @param   cutOff   instance of Double
     * @return   parameter "network" of type {@link us.kbase.networks.Network Network}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Network buildInternalNetworkLimitedByStrength(List<String> datasetIds, List<String> geneIds, List<String> edgeTypes, Double cutOff) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(datasetIds);
        args.add(geneIds);
        args.add(edgeTypes);
        args.add(cutOff);
        TypeReference<List<Network>> retType = new TypeReference<List<Network>>() {};
        List<Network> res = caller.jsonrpcCall("Networks.build_internal_Network_limited_by_strength", args, retType, true, false);
        return res.get(0);
    }
}
