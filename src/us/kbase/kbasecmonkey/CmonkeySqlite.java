package us.kbase.kbasecmonkey;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import us.kbase.util.IdService;


public class CmonkeySqlite {

	private Connection connection = null;
	private Statement statement = null;
	
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
	
	protected List<CmonkeyCluster> getClusterList (Integer iteration){
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
				cluster.setCmonkeyClusterId(resultSet.getString("cluster"));
				cluster.setNumGenes(resultSet.getInt("num_rows"));
				cluster.setNumConditions(resultSet.getInt("num_cols"));
				cluster.setClusterResidual(resultSet.getDouble("residual"));
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
		for(CmonkeyCluster clusterData:clusterList){
			clusterData.setClusterMotifs(this.getClusterMotifs(iteration, clusterData.getCmonkeyClusterId()));
			clusterData.setClusterGeneIds(this.getListOfGenes(iteration, clusterData.getCmonkeyClusterId()));
			clusterData.setClusterExpressionDataSetIds(this.getListOfConditions(iteration, clusterData.getCmonkeyClusterId()));
		}
		return clusterList;
	}

	protected List<MotifCmonkey> getClusterMotifs (Integer iteration, String clusterId){
		List<MotifCmonkey> motifList = new ArrayList<MotifCmonkey>();
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
				MotifCmonkey motif = new MotifCmonkey();
				motif.setCmonkeyMotifId(resultSet.getString("rowid"));
				motif.setSeqType(resultSet.getString("seqtype"));
				motif.setMotifNumber(resultSet.getInt("motif_num"));
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
        
        for (MotifCmonkey motif:motifList){
			motif.setPssm(this.getMotifPssm(motif.getCmonkeyMotifId()));
			motif.setHits(this.getMotifAnnotation(motif.getCmonkeyMotifId(), motif.getPssm().size()));
        }
		return motifList;
	}

	protected List<PssmRow> getMotifPssm (String cmonkeyMotifId){
		List<PssmRow> result = new ArrayList<PssmRow>();
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
				PssmRow pssmRow = new PssmRow();
				pssmRow.setRowNumber(resultSet.getInt("row"));
				pssmRow.setAWeight(resultSet.getDouble("a"));
				pssmRow.setCWeight(resultSet.getDouble("c"));
				pssmRow.setGWeight(resultSet.getDouble("g"));
				pssmRow.setTWeight(resultSet.getDouble("t"));
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
	

	protected List<HitMast> getMotifAnnotation (String cmonkeyMotifId, Integer motifLength){
		List<HitMast> result = new ArrayList<HitMast>();
        String sqlQuery = "SELECT * FROM row_names g JOIN motif_annotations m ON m.gene_num=g.rowid WHERE m.motif_info_id="+cmonkeyMotifId;
        ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sqlQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while (resultSet.next()){
				HitMast motifHit = new HitMast();
				motifHit.setMotifName(cmonkeyMotifId);
				motifHit.setSequenceName(resultSet.getString("name"));
				motifHit.setHitStart(resultSet.getInt("position"));
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
	
	protected String convertStrandIntoSequnce (Boolean strand){
		if (strand){
			return "+";
		} else {
			return "-";
		}
	}
	
	protected Integer getHitEnd(Integer motifLength, Integer hitStart, String strand){
		Integer result = 0;
		if (strand.equals("+")){
			result = hitStart + motifLength - 1;
		} else if (strand.equals("-")){
			result = hitStart - motifLength + 1;
		}
		return result;
	}
	
	protected List<String> getListOfGenes (Integer iteration, String clusterId){
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
	
	protected List<String> getListOfConditions (Integer iteration, String clusterId){
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
	
	public void buildCmonkeyRun (CmonkeyRun cmonkeyRun){
		cmonkeyRun.setCmonkeyRunId(IdService.getKbaseId(CmonkeyRun.class.getSimpleName()));
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
				cmonkeyRun.setCmonkeyRunStartTime(resultSet.getString("start_time"));
				cmonkeyRun.setCmonkeyRunFinishTime(resultSet.getString("finish_time"));
				cmonkeyRun.setCmonkeyRunOrganism(resultSet.getString("organism"));
				cmonkeyRun.setCmonkeyRunNumIterations(resultSet.getInt("num_iterations"));
				cmonkeyRun.setCmonkeyRunLastIteration(resultSet.getInt("last_iteration"));
				cmonkeyRun.setCmonkeyRunNumRows(resultSet.getInt("num_rows"));
				cmonkeyRun.setCmonkeyRunNumColumns(resultSet.getInt("num_columns"));
				cmonkeyRun.setCmonkeyRunNumClusters(resultSet.getInt("num_clusters"));
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
        cmonkeyRun.setCmonkeyNetwork(getCmonkeyNetwork(cmonkeyRun.getCmonkeyRunLastIteration()));
	}
	
	protected CmonkeyNetwork getCmonkeyNetwork(Integer iteration){
		CmonkeyNetwork result = new CmonkeyNetwork();
		result.setCmonkeyNetworkId(IdService.getKbaseId(CmonkeyNetwork.class.getSimpleName()));
		result.setCmonkeyClusters(this.getClusterList(iteration));
		return result;
	}

}
