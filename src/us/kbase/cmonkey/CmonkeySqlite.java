package us.kbase.cmonkey;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import us.kbase.common.service.JsonClientException;
import us.kbase.idserverapi.IDServerAPIClient;
import us.kbase.meme.MastHit;
import us.kbase.meme.MemeSite;

public class CmonkeySqlite {

	private Connection connection = null;
	private Statement statement = null;
	private static final String ID_SERVICE_URL = "http://kbase.us/services/idserver";

	public CmonkeySqlite(String filePath) throws ClassNotFoundException,
			SQLException {
		connect(filePath);
	}

	public void connect(String file) throws ClassNotFoundException,
			SQLException {
		Class.forName("org.sqlite.JDBC");
		String connectString = "jdbc:sqlite:" + file;
		connection = DriverManager.getConnection(connectString);
		statement = connection.createStatement();
	}

	public void disconnect() throws SQLException {
		statement.close();
		connection.close();
	}

	protected List<CmonkeyCluster> getClusterList(Long iteration)
			throws SQLException, IOException, JsonClientException {
		List<CmonkeyCluster> clusterList = new ArrayList<CmonkeyCluster>();
		String sqlQuery = "SELECT * FROM cluster_stats c WHERE c.iteration="
				+ String.valueOf(iteration);
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		while (resultSet.next()) {
			CmonkeyCluster cluster = new CmonkeyCluster();
			cluster.setId(getKbaseId(CmonkeyCluster.class.getSimpleName()));
			cluster.setResidual(resultSet.getDouble("residual"));
			clusterList.add(cluster);
		}
		resultSet.close();
		for (Integer i = 1; i <= clusterList.size(); i++) {
			clusterList.get(i - 1).setMotifs(
					this.getClusterMotifs(iteration, i.toString()));
			clusterList.get(i - 1).setGeneIds(
					this.getListOfGenes(iteration, i.toString()));
			clusterList.get(i - 1).setSampleWsIds(
					this.getListOfConditions(iteration, i.toString()));
		}
		return clusterList;
	}

	protected List<CmonkeyMotif> getClusterMotifs(Long iteration,
			String clusterId) throws SQLException, IOException,
			JsonClientException {
		List<CmonkeyMotif> motifList = new ArrayList<CmonkeyMotif>();
		String sqlQuery = "SELECT m.rowid,m.seqtype,m.motif_num,m.evalue FROM motif_infos m WHERE m.iteration="
				+ String.valueOf(iteration)
				+ " AND m.cluster="
				+ Integer.valueOf(clusterId);
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		while (resultSet.next()) {
			CmonkeyMotif motif = new CmonkeyMotif();
			motif.setId(getKbaseId(CmonkeyMotif.class.getSimpleName()));
			motif.setPssmId(resultSet.getLong("motif_num"));
			motif.setSeqType(resultSet.getString("seqtype"));
			motif.setEvalue(resultSet.getDouble("evalue"));
			motifList.add(motif);
		}
		resultSet.close();

		for (CmonkeyMotif motif : motifList) {
			motif.setPssmRows(this.getMotifPssm(motif.getPssmId().toString()));
			motif.setHits(this.getMotifAnnotation(motif.getPssmId().toString(),
					motif.getPssmRows().size()));
			motif.setSites(this.getMotifSites(motif.getPssmId().toString()));
		}
		return motifList;
	}

	protected List<List<Double>> getMotifPssm(String cmonkeyMotifId)
			throws SQLException {
		List<List<Double>> result = new ArrayList<List<Double>>();
		String sqlQuery = "SELECT * FROM motif_pssm_rows m WHERE m.motif_info_id="
				+ cmonkeyMotifId;
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		while (resultSet.next()) {
			List<Double> pssmRow = new ArrayList<Double>();
			pssmRow.add(resultSet.getDouble("a"));
			pssmRow.add(resultSet.getDouble("c"));
			pssmRow.add(resultSet.getDouble("g"));
			pssmRow.add(resultSet.getDouble("t"));
			result.add(pssmRow);
		}
		resultSet.close();
		return result;
	}

	protected List<MastHit> getMotifAnnotation(String motifId,
			Integer motifLength) throws SQLException {
		List<MastHit> result = new ArrayList<MastHit>();
		String sqlQuery = "SELECT * FROM row_names g JOIN motif_annotations m ON m.gene_num=g.rowid WHERE m.motif_info_id="
				+ motifId;
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		while (resultSet.next()) {
			MastHit motifHit = new MastHit();
			motifHit.setPspmId(motifId);
			motifHit.setSeqId(resultSet.getString("name"));
			motifHit.setHitStart(resultSet.getLong("position"));
			motifHit.setStrand(convertStrandIntoSequnce(resultSet
					.getBoolean("reverse")));
			motifHit.setHitEnd(getHitEnd(motifLength, motifHit.getHitStart(),
					motifHit.getStrand()));
			motifHit.setHitPvalue(resultSet.getDouble("pvalue"));
			motifHit.setScore(0.0D);// added for compatibility
			result.add(motifHit);
		}
		resultSet.close();
		return result;
	}

	protected List<MemeSite> getMotifSites(String motifId) throws SQLException {
		List<MemeSite> result = new ArrayList<MemeSite>();
		String sqlQuery = "SELECT * FROM meme_motif_sites m WHERE m.motif_info_id="
				+ motifId;
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		while (resultSet.next()) {
			MemeSite site = new MemeSite();
			site.setSourceSequenceId(resultSet.getString("seq_name"));
			site.setPvalue(resultSet.getDouble("pvalue"));
			site.setStart(resultSet.getLong("start"));
			site.setLeftFlank(resultSet.getString("flank_left"));
			site.setSequence(resultSet.getString("seq"));
			site.setRightFlank(resultSet.getString("flank_right"));
			result.add(site);
		}
		resultSet.close();
		return result;
	}

	protected String convertStrandIntoSequnce(Boolean strand) {
		if (strand) {
			return "+";
		} else {
			return "-";
		}
	}

	protected Long getHitEnd(Integer motifLength, Long hitStart, String strand) {
		Long result = 0L;
		if (strand.equals("+")) {
			result = hitStart + motifLength - 1;
		} else if (strand.equals("-")) {
			result = hitStart - motifLength + 1;
		}
		return result;
	}

	protected List<String> getListOfGenes(Long iteration, String clusterId)
			throws SQLException {
		List<String> genes = new ArrayList<String>();
		String sqlQuery = "SELECT g.name FROM row_names g JOIN row_members m ON m.order_num=g.rowid WHERE m.iteration="
				+ String.valueOf(iteration) + " AND m.cluster=" + clusterId;
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		while (resultSet.next()) {
			genes.add(resultSet.getString("name"));
		}
		resultSet.close();
		return genes;
	}

	protected List<String> getListOfConditions(Long iteration, String clusterId)
			throws SQLException {
		List<String> conditions = new ArrayList<String>();
		String sqlQuery = "SELECT g.name FROM column_names g JOIN column_members m ON m.order_num=g.rowid WHERE m.iteration="
				+ String.valueOf(iteration) + " AND m.cluster=" + clusterId;
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		while (resultSet.next()) {
			conditions.add(resultSet.getString("name"));
		}
		resultSet.close();
		return conditions;
	}

	public void buildCmonkeyRunResult(CmonkeyRunResult cmonkeyRunResult)
			throws IOException, JsonClientException, SQLException {
		cmonkeyRunResult.setId(getKbaseId(CmonkeyRunResult.class
				.getSimpleName()));
		CmonkeyNetwork network = new CmonkeyNetwork();
		String sqlQuery = "SELECT * FROM run_infos";
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		while (resultSet.next()) {
			cmonkeyRunResult.setStartTime(resultSet.getString("start_time"));
			cmonkeyRunResult.setFinishTime(resultSet.getString("finish_time"));
			cmonkeyRunResult.setIterationsNumber(resultSet
					.getLong("num_iterations"));
			cmonkeyRunResult.setLastIteration(resultSet
					.getLong("last_iteration"));
			network.setGenomeName(resultSet.getString("organism"));
			network.setRowsNumber(resultSet.getLong("num_rows"));
			network.setColumnsNumber(resultSet.getLong("num_columns"));
			network.setClustersNumber(resultSet.getLong("num_clusters"));
			network.setIteration(resultSet.getLong("last_iteration"));
		}
		resultSet.close();
		if (cmonkeyRunResult.getLastIteration() < cmonkeyRunResult
				.getIterationsNumber()) {
			throw new RuntimeException(
					"cmonkey-python finished after iteration "
							+ cmonkeyRunResult.getLastIteration()
							+ " but expected number of iterations is "
							+ cmonkeyRunResult.getIterationsNumber());
		}
		network.setId(getKbaseId(CmonkeyNetwork.class.getSimpleName()));
		network.setClusters(this.getClusterList(cmonkeyRunResult
				.getLastIteration()));
		cmonkeyRunResult.setNetwork(network);

	}

	public static String getKbaseId(String entityType) throws IOException,
			JsonClientException {
		String returnVal = null;
		URL url = new URL(ID_SERVICE_URL);
		IDServerAPIClient idClient = new IDServerAPIClient(url);

		if (entityType.equals("CmonkeyRun")) {
			returnVal = "kb|cmonkeyrun."
					+ idClient.allocateIdRange("cmonkeyrun", 1L).toString();
		} else if (entityType.equals("CmonkeyNetwork")) {
			returnVal = "kb|cmonkeynetwork."
					+ idClient.allocateIdRange("cmonkeynetwork", 1L).toString();
		} else if (entityType.equals("CmonkeyCluster")) {
			returnVal = "kb|cmonkeycluster."
					+ idClient.allocateIdRange("cmonkeycluster", 1L).toString();
		} else if (entityType.equals("CmonkeyMotif")) {
			returnVal = "kb|cmonkeymotif."
					+ idClient.allocateIdRange("cmonkeymotif", 1L).toString();
		} else {
		}
		return returnVal;
	}

}
