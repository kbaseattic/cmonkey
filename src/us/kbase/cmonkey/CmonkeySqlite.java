package us.kbase.cmonkey;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import us.kbase.idserverapi.IDServerAPIClient;

public class CmonkeySqlite {

	private Connection connection = null;
	private Statement statement = null;
	private static final String ID_SERVICE_URL = "http://kbase.us/services/idserver";
	
	public CmonkeySqlite(String filePath) {
		connect (filePath);
	}
	
	public void connect (String file) {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String connectString = "jdbc:sqlite:"+file;
		try {
			connection = DriverManager.getConnection(connectString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void disconnect() {
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected List<CmonkeyCluster> getClusterList (Long iteration) throws Exception{
		List<CmonkeyCluster> clusterList = new ArrayList<CmonkeyCluster>();
		String sqlQuery = "SELECT * FROM cluster_stats c WHERE c.iteration="+String.valueOf(iteration);
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sqlQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while (resultSet.next()){
				CmonkeyCluster cluster = new CmonkeyCluster();
				cluster.setId(getKbaseId(CmonkeyCluster.class.getSimpleName()));
				cluster.setResidual(resultSet.getDouble("residual"));
				clusterList.add(cluster);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Integer i = 1; i <= clusterList.size(); i++){
			clusterList.get(i-1).setMotifs(this.getClusterMotifs(iteration, i.toString()));
			clusterList.get(i-1).setGeneIds(this.getListOfGenes(iteration, i.toString()));
			clusterList.get(i-1).setDatasetIds(this.getListOfConditions(iteration, i.toString()));
		}
		return clusterList;
	}

	protected List<CmonkeyMotif> getClusterMotifs (Long iteration, String clusterId) throws Exception{
		List<CmonkeyMotif> motifList = new ArrayList<CmonkeyMotif>();
		String sqlQuery = "SELECT m.rowid,m.seqtype,m.motif_num,m.evalue FROM motif_infos m WHERE m.iteration="+String.valueOf(iteration)+
				" AND m.cluster="+Integer.valueOf(clusterId);
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sqlQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while (resultSet.next()){
				CmonkeyMotif motif = new CmonkeyMotif();
				motif.setId(getKbaseId(CmonkeyMotif.class.getSimpleName()));
				motif.setSeqType(resultSet.getString("seqtype"));
				motif.setEvalue(resultSet.getDouble("evalue"));
				motifList.add(motif);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        for (CmonkeyMotif motif:motifList){
			motif.setPssmRows(this.getMotifPssm(motif.getId()));
			motif.setHits(this.getMotifAnnotation(motif.getId(), motif.getPssmRows().size()));
			motif.setSites(this.getMotifSites(motif.getId()));
        }
		return motifList;
	}

	protected List<List<Double>> getMotifPssm (String cmonkeyMotifId){
		List<List<Double>> result = new ArrayList<List<Double>>();
        String sqlQuery = "SELECT * FROM motif_pssm_rows m WHERE m.motif_info_id="+cmonkeyMotifId;
        ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sqlQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while (resultSet.next()){
				List<Double> pssmRow = new ArrayList<Double>();
				pssmRow.add(resultSet.getDouble("a"));
				pssmRow.add(resultSet.getDouble("c"));
				pssmRow.add(resultSet.getDouble("g"));
				pssmRow.add(resultSet.getDouble("t"));
				result.add(pssmRow);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	

	protected List<MastHit> getMotifAnnotation (String motifId, Integer motifLength){
		List<MastHit> result = new ArrayList<MastHit>();
        String sqlQuery = "SELECT * FROM row_names g JOIN motif_annotations m ON m.gene_num=g.rowid WHERE m.motif_info_id="+motifId;
        ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sqlQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while (resultSet.next()){
				MastHit motifHit = new MastHit();
				motifHit.setPssmId(motifId);
				motifHit.setSequenceId(resultSet.getString("name"));
				motifHit.setHitStart(resultSet.getLong("position"));
				motifHit.setStrand(convertStrandIntoSequnce(resultSet.getBoolean("reverse")));
				motifHit.setHitEnd(getHitEnd(motifLength, motifHit.getHitStart(), motifHit.getStrand()));
				motifHit.setHitPvalue(resultSet.getDouble("pvalue"));
				result.add(motifHit);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	protected List<SiteMeme> getMotifSites (String motifId){
		List<SiteMeme> result = new ArrayList<SiteMeme>();
        String sqlQuery = "SELECT * FROM meme_motif_sites m WHERE m.motif_info_id="+motifId;
        ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sqlQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while (resultSet.next()){
				SiteMeme site = new SiteMeme();
				site.setSourceSequenceId(resultSet.getString("seq_name"));
				site.setPvalue(resultSet.getDouble("pvalue"));
				site.setStart(resultSet.getLong("start"));
				site.setLeftFlank(resultSet.getString("flank_left"));
				site.setSequence(resultSet.getString("seq"));
				site.setRightFlank(resultSet.getString("flank_right"));
				result.add(site);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	protected String convertStrandIntoSequnce (Boolean strand){
		if (strand){
			return "+";
		} else {
			return "-";
		}
	}
	
	protected Long getHitEnd(Integer motifLength, Long hitStart, String strand){
		Long result = 0L;
		if (strand.equals("+")){
			result = hitStart + motifLength - 1;
		} else if (strand.equals("-")){
			result = hitStart - motifLength + 1;
		}
		return result;
	}
	
	protected List<String> getListOfGenes (Long iteration, String clusterId){
		List<String> genes = new ArrayList<String>();
		String sqlQuery = "SELECT g.name FROM row_names g JOIN row_members m ON m.order_num=g.rowid WHERE m.iteration=" +
				String.valueOf(iteration)+" AND m.cluster="+clusterId;
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sqlQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while (resultSet.next()){
				genes.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genes;
	}
	
	protected List<String> getListOfConditions (Long iteration, String clusterId){
		List<String> conditions = new ArrayList<String>();
		String sqlQuery = "SELECT g.name FROM column_names g JOIN column_members m ON m.order_num=g.rowid WHERE m.iteration=" +
				String.valueOf(iteration)+" AND m.cluster="+clusterId;
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sqlQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while (resultSet.next()){
				conditions.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conditions;
	}
	
	public void buildCmonkeyRunResult (CmonkeyRunResult cmonkeyRunResult) throws Exception{
		cmonkeyRunResult.setId(getKbaseId(CmonkeyRunResult.class.getSimpleName()));
		String sqlQuery = "SELECT * FROM run_infos";
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sqlQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while (resultSet.next()){
				cmonkeyRunResult.setStartTime(resultSet.getString("start_time"));
				cmonkeyRunResult.setFinishTime(resultSet.getString("finish_time"));
				cmonkeyRunResult.setOrganism(resultSet.getString("organism"));
				cmonkeyRunResult.setIterationsNumber(resultSet.getLong("num_iterations"));
				cmonkeyRunResult.setLastIteration(resultSet.getLong("last_iteration"));
				cmonkeyRunResult.setRowsNumber(resultSet.getLong("num_rows"));
				cmonkeyRunResult.setColumnsNumber(resultSet.getLong("num_columns"));
				cmonkeyRunResult.setClustersNumber(resultSet.getLong("num_clusters"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (cmonkeyRunResult.getLastIteration() < cmonkeyRunResult.getIterationsNumber()) {
        	throw new Exception("cmonkey-python finished after iteration " + cmonkeyRunResult.getLastIteration()+ " but expected number of iterations is " + cmonkeyRunResult.getIterationsNumber());
        }
        cmonkeyRunResult.setNetwork(getCmonkeyNetwork(cmonkeyRunResult.getLastIteration()));
	}
	
	protected CmonkeyNetwork getCmonkeyNetwork(Long iteration) throws Exception{
		CmonkeyNetwork result = new CmonkeyNetwork();
		result.setId(getKbaseId(CmonkeyNetwork.class.getSimpleName()));
		result.setClusters(this.getClusterList(iteration));
		return result;
	}
	
	public static String getKbaseId(String entityType) throws Exception {
		String returnVal = null;
		URL url = new URL(ID_SERVICE_URL);
		IDServerAPIClient idClient = new IDServerAPIClient(url);
		
		if (entityType.equals("CmonkeyRun")) {
			returnVal = "kb|cmonkeyrun." + idClient.allocateIdRange("cmonkeyrun", 1L).toString();
		} else if (entityType.equals("CmonkeyNetwork")) {
			returnVal = "kb|cmonkeynetwork." + idClient.allocateIdRange("cmonkeynetwork", 1L).toString();
		} else if (entityType.equals("CmonkeyCluster")) {
			returnVal = "kb|cmonkeycluster." + idClient.allocateIdRange("cmonkeycluster", 1L).toString();
		} else if (entityType.equals("CmonkeyMotif")) {
			returnVal = "kb|cmonkeymotif." + idClient.allocateIdRange("cmonkeymotif", 1L).toString();
		} else {
		}
		return returnVal;
	}


}
