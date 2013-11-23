package us.kbase.idserverapi;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple2;

/**
 * <p>Original spec-file module name: IDServerAPI</p>
 * <pre>
 * The KBase ID server provides access to the mappings between KBase identifiers and
 * external identifiers (the original identifiers for data that was migrated from
 * other databases into KBase).
 * </pre>
 */
public class IDServerAPIClient {
    private JsonClientCaller caller;

    public IDServerAPIClient(URL url) {
        caller = new JsonClientCaller(url);
    }

	public void setConnectionReadTimeOut(Integer milliseconds) {
		this.caller.setConnectionReadTimeOut(milliseconds);
	}

    /**
     * <p>Original spec-file function name: kbase_ids_to_external_ids</p>
     * <pre>
     * Given a set of KBase identifiers, look up the associated external identifiers.
     * If no external ID is associated with the KBase id, no entry will be present in the return.
     * </pre>
     * @param   ids   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|". KBase IDs are typed. The types are designated using a short string. For instance, "g" denotes a genome, "fp" denotes a feature representing a protein-encoding gene, etc. KBase IDs may be hierarchical. If a KBase genome identifier is "kb|g.1234", a protein within that genome may be represented as "kb|g.1234.fp.771".)
     * @return   instance of mapping from original type "kbase_id" (A KBase ID is a string starting with the characters "kb|". KBase IDs are typed. The types are designated using a short string. For instance, "g" denotes a genome, "fp" denotes a feature representing a protein-encoding gene, etc. KBase IDs may be hierarchical. If a KBase genome identifier is "kb|g.1234", a protein within that genome may be represented as "kb|g.1234.fp.771".) to tuple of size 2: original type "external_db" (Each external database is represented using a short string. Microbes Online is "MOL", the SEED is "SEED", etc.), original type "external_id" (External database identifiers are strings. They are the precise identifier used by that database. It is important to note that if a database uses the same identifier space for more than one data type (for instance, if integers are used for identifying both genomes and genes, and if the same number is valid for both a genome and a gene) then the distinction must be made by using separate exgternal database strings for the different types; e.g. DBNAME-GENE and DBNAME-GENOME for a database DBNAME that has overlapping namespace for genes and genomes).)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Tuple2<String, String>> kbaseIdsToExternalIds(List<String> ids) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        TypeReference<List<Map<String,Tuple2<String, String>>>> retType = new TypeReference<List<Map<String,Tuple2<String, String>>>>() {};
        List<Map<String,Tuple2<String, String>>> res = caller.jsonrpcCall("IDServerAPI.kbase_ids_to_external_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: external_ids_to_kbase_ids</p>
     * <pre>
     * Given a set of external identifiers, look up the associated KBase identifiers.
     * If no KBase ID is associated with the external id, no entry will be present in the return.
     * </pre>
     * @param   arg1   instance of original type "external_db" (Each external database is represented using a short string. Microbes Online is "MOL", the SEED is "SEED", etc.)
     * @param   extIds   instance of list of original type "external_id" (External database identifiers are strings. They are the precise identifier used by that database. It is important to note that if a database uses the same identifier space for more than one data type (for instance, if integers are used for identifying both genomes and genes, and if the same number is valid for both a genome and a gene) then the distinction must be made by using separate exgternal database strings for the different types; e.g. DBNAME-GENE and DBNAME-GENOME for a database DBNAME that has overlapping namespace for genes and genomes).)
     * @return   instance of mapping from original type "external_id" (External database identifiers are strings. They are the precise identifier used by that database. It is important to note that if a database uses the same identifier space for more than one data type (for instance, if integers are used for identifying both genomes and genes, and if the same number is valid for both a genome and a gene) then the distinction must be made by using separate exgternal database strings for the different types; e.g. DBNAME-GENE and DBNAME-GENOME for a database DBNAME that has overlapping namespace for genes and genomes).) to original type "kbase_id" (A KBase ID is a string starting with the characters "kb|". KBase IDs are typed. The types are designated using a short string. For instance, "g" denotes a genome, "fp" denotes a feature representing a protein-encoding gene, etc. KBase IDs may be hierarchical. If a KBase genome identifier is "kb|g.1234", a protein within that genome may be represented as "kb|g.1234.fp.771".)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> externalIdsToKbaseIds(String arg1, List<String> extIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        args.add(extIds);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("IDServerAPI.external_ids_to_kbase_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: register_ids</p>
     * <pre>
     * Register a set of identifiers. All will be assigned identifiers with the given
     * prefix.
     * If an external ID has already been registered, the existing registration will be returned instead 
     * of a new ID being allocated.
     * </pre>
     * @param   prefix   instance of original type "kbase_id_prefix" (A KBase identifier prefix. This is a string that starts with "kb|" and includes either a single type designator (e.g. "kb|g") or is a prefix for a hierarchical identifier (e.g. "kb|g.1234.fp").)
     * @param   dbName   instance of original type "external_db" (Each external database is represented using a short string. Microbes Online is "MOL", the SEED is "SEED", etc.)
     * @param   ids   instance of list of original type "external_id" (External database identifiers are strings. They are the precise identifier used by that database. It is important to note that if a database uses the same identifier space for more than one data type (for instance, if integers are used for identifying both genomes and genes, and if the same number is valid for both a genome and a gene) then the distinction must be made by using separate exgternal database strings for the different types; e.g. DBNAME-GENE and DBNAME-GENOME for a database DBNAME that has overlapping namespace for genes and genomes).)
     * @return   instance of mapping from original type "external_id" (External database identifiers are strings. They are the precise identifier used by that database. It is important to note that if a database uses the same identifier space for more than one data type (for instance, if integers are used for identifying both genomes and genes, and if the same number is valid for both a genome and a gene) then the distinction must be made by using separate exgternal database strings for the different types; e.g. DBNAME-GENE and DBNAME-GENOME for a database DBNAME that has overlapping namespace for genes and genomes).) to original type "kbase_id" (A KBase ID is a string starting with the characters "kb|". KBase IDs are typed. The types are designated using a short string. For instance, "g" denotes a genome, "fp" denotes a feature representing a protein-encoding gene, etc. KBase IDs may be hierarchical. If a KBase genome identifier is "kb|g.1234", a protein within that genome may be represented as "kb|g.1234.fp.771".)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> registerIds(String prefix, String dbName, List<String> ids) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(prefix);
        args.add(dbName);
        args.add(ids);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("IDServerAPI.register_ids", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: allocate_id_range</p>
     * <pre>
     * Allocate a set of identifiers. This allows efficient registration of a large
     * number of identifiers (e.g. several thousand features in a genome).
     * The return is the first identifier allocated.
     * </pre>
     * @param   arg1   instance of original type "kbase_id_prefix" (A KBase identifier prefix. This is a string that starts with "kb|" and includes either a single type designator (e.g. "kb|g") or is a prefix for a hierarchical identifier (e.g. "kb|g.1234.fp").)
     * @param   count   instance of Long
     * @return   parameter "starting_value" of Long
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Long allocateIdRange(String arg1, Long count) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        args.add(count);
        TypeReference<List<Long>> retType = new TypeReference<List<Long>>() {};
        List<Long> res = caller.jsonrpcCall("IDServerAPI.allocate_id_range", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: register_allocated_ids</p>
     * <pre>
     * Register the mappings for a set of external identifiers. The
     * KBase identifiers used here were previously allocated using allocate_id_range.
     * Does not return a value.
     * </pre>
     * @param   prefix   instance of original type "kbase_id_prefix" (A KBase identifier prefix. This is a string that starts with "kb|" and includes either a single type designator (e.g. "kb|g") or is a prefix for a hierarchical identifier (e.g. "kb|g.1234.fp").)
     * @param   dbName   instance of original type "external_db" (Each external database is represented using a short string. Microbes Online is "MOL", the SEED is "SEED", etc.)
     * @param   assignments   instance of mapping from original type "external_id" (External database identifiers are strings. They are the precise identifier used by that database. It is important to note that if a database uses the same identifier space for more than one data type (for instance, if integers are used for identifying both genomes and genes, and if the same number is valid for both a genome and a gene) then the distinction must be made by using separate exgternal database strings for the different types; e.g. DBNAME-GENE and DBNAME-GENOME for a database DBNAME that has overlapping namespace for genes and genomes).) to Long
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public void registerAllocatedIds(String prefix, String dbName, Map<String,Long> assignments) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(prefix);
        args.add(dbName);
        args.add(assignments);
        TypeReference<Object> retType = new TypeReference<Object>() {};
        caller.jsonrpcCall("IDServerAPI.register_allocated_ids", args, retType, false, false);
    }
}
