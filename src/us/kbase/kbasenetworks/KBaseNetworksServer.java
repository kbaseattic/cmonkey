package us.kbase.kbasenetworks;

import java.util.List;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;

//BEGIN_HEADER
//END_HEADER

/**
 * <p>Original spec-file module name: KBaseNetworks</p>
 * <pre>
 * Module KBaseNetworks version 2.0
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
public class KBaseNetworksServer extends JsonServerServlet {
    private static final long serialVersionUID = 1L;

    //BEGIN_CLASS_HEADER
    //END_CLASS_HEADER

    public KBaseNetworksServer() throws Exception {
        super("KBaseNetworks");
        //BEGIN_CONSTRUCTOR
        //END_CONSTRUCTOR
    }

    /**
     * <p>Original spec-file function name: all_datasets</p>
     * <pre>
     * Returns a list of all datasets that can be used to create a network
     * </pre>
     * @return   parameter "datasets" of list of type {@link us.kbase.kbasenetworks.Dataset Dataset}
     */
    @JsonServerMethod(rpc = "KBaseNetworks.all_datasets")
    public List<Dataset> allDatasets() throws Exception {
        List<Dataset> returnVal = null;
        //BEGIN all_datasets
        //END all_datasets
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: all_dataset_sources</p>
     * <pre>
     * Returns a list of all dataset sources available in  via Networks API
     * </pre>
     * @return   parameter "datasetSources" of list of type {@link us.kbase.kbasenetworks.DatasetSource DatasetSource}
     */
    @JsonServerMethod(rpc = "KBaseNetworks.all_dataset_sources")
    public List<DatasetSource> allDatasetSources() throws Exception {
        List<DatasetSource> returnVal = null;
        //BEGIN all_dataset_sources
        //END all_dataset_sources
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: all_network_types</p>
     * <pre>
     * Returns a list of all types of networks that can be created
     * </pre>
     * @return   parameter "networkTypes" of list of original type "network_type" (Type of network that can be created from a dataset)
     */
    @JsonServerMethod(rpc = "KBaseNetworks.all_network_types")
    public List<String> allNetworkTypes() throws Exception {
        List<String> returnVal = null;
        //BEGIN all_network_types
        //END all_network_types
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: dataset_source2datasets</p>
     * <pre>
     * Returns a list of all datasets from a given dataset source                   
     *            dataset_source_ref datasetSourceRef - A reference to a dataset source
     * </pre>
     * @param   sourceRef   instance of original type "dataset_source_ref" (The name of a dataset that can be accessed as a source for creating a network)
     * @return   parameter "datasets" of list of type {@link us.kbase.kbasenetworks.Dataset Dataset}
     */
    @JsonServerMethod(rpc = "KBaseNetworks.dataset_source2datasets")
    public List<Dataset> datasetSource2datasets(String sourceRef) throws Exception {
        List<Dataset> returnVal = null;
        //BEGIN dataset_source2datasets
        //END dataset_source2datasets
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: taxon2datasets</p>
     * <pre>
     * Returns a list of all datasets that can be used to build a network for a particular genome represented by NCBI taxonomy id. 
     *        taxon taxon - NCBI taxonomy id
     * </pre>
     * @param   taxid   instance of original type "taxon" (NCBI taxonomy id)
     * @return   parameter "datasets" of list of type {@link us.kbase.kbasenetworks.Dataset Dataset}
     */
    @JsonServerMethod(rpc = "KBaseNetworks.taxon2datasets")
    public List<Dataset> taxon2datasets(String taxid) throws Exception {
        List<Dataset> returnVal = null;
        //BEGIN taxon2datasets
        //END taxon2datasets
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: network_type2datasets</p>
     * <pre>
     * Returns a list of all datasets that can be used to build a network of a given type.
     *           network_type networkType - The type of network
     * </pre>
     * @param   netType   instance of original type "network_type" (Type of network that can be created from a dataset)
     * @return   parameter "datasets" of list of type {@link us.kbase.kbasenetworks.Dataset Dataset}
     */
    @JsonServerMethod(rpc = "KBaseNetworks.network_type2datasets")
    public List<Dataset> networkType2datasets(String netType) throws Exception {
        List<Dataset> returnVal = null;
        //BEGIN network_type2datasets
        //END network_type2datasets
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: entity2datasets</p>
     * <pre>
     * Returns a list of all datasets that have at least one interaction for a given  entity
     * </pre>
     * @param   entityId   instance of String
     * @return   parameter "datasets" of list of type {@link us.kbase.kbasenetworks.Dataset Dataset}
     */
    @JsonServerMethod(rpc = "KBaseNetworks.entity2datasets")
    public List<Dataset> entity2datasets(String entityId) throws Exception {
        List<Dataset> returnVal = null;
        //BEGIN entity2datasets
        //END entity2datasets
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: build_first_neighbor_network</p>
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
     * @return   parameter "network" of type {@link us.kbase.kbasenetworks.Network Network}
     */
    @JsonServerMethod(rpc = "KBaseNetworks.build_first_neighbor_network")
    public Network buildFirstNeighborNetwork(List<String> datasetIds, List<String> entityIds, List<String> edgeTypes) throws Exception {
        Network returnVal = null;
        //BEGIN build_first_neighbor_network
        //END build_first_neighbor_network
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: build_first_neighbor_network_limted_by_strength</p>
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
     * @return   parameter "network" of type {@link us.kbase.kbasenetworks.Network Network}
     */
    @JsonServerMethod(rpc = "KBaseNetworks.build_first_neighbor_network_limted_by_strength")
    public Network buildFirstNeighborNetworkLimtedByStrength(List<String> datasetIds, List<String> entityIds, List<String> edgeTypes, Double cutOff) throws Exception {
        Network returnVal = null;
        //BEGIN build_first_neighbor_network_limted_by_strength
        //END build_first_neighbor_network_limted_by_strength
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: build_internal_network</p>
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
     * @return   parameter "network" of type {@link us.kbase.kbasenetworks.Network Network}
     */
    @JsonServerMethod(rpc = "KBaseNetworks.build_internal_network")
    public Network buildInternalNetwork(List<String> datasetIds, List<String> geneIds, List<String> edgeTypes) throws Exception {
        Network returnVal = null;
        //BEGIN build_internal_network
        //END build_internal_network
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: build_internal_network_limited_by_strength</p>
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
     * @return   parameter "network" of type {@link us.kbase.kbasenetworks.Network Network}
     */
    @JsonServerMethod(rpc = "KBaseNetworks.build_internal_network_limited_by_strength")
    public Network buildInternalNetworkLimitedByStrength(List<String> datasetIds, List<String> geneIds, List<String> edgeTypes, Double cutOff) throws Exception {
        Network returnVal = null;
        //BEGIN build_internal_network_limited_by_strength
        //END build_internal_network_limited_by_strength
        return returnVal;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: <program> <server_port>");
            return;
        }
        new KBaseNetworksServer().startupServer(Integer.parseInt(args[0]));
    }
}
