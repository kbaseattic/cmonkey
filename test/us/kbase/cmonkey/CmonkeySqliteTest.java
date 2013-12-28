package us.kbase.cmonkey;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import us.kbase.meme.MastHit;
import us.kbase.meme.MemeSite;

public class CmonkeySqliteTest {
	public final String TEST_DATABASE_PATH = "test/cmonkey_run_test.db";
	//public final String TEST_DATABASE_PATH = "/home/kbase/Documents/inferelator-test/out/cmonkey_run.db";
	private CmonkeySqlite database;
	Long iterationNumber = 2001L;


	@Before
	public void setUp() throws Exception {
		database = new CmonkeySqlite(TEST_DATABASE_PATH);
	}

	@After
	public void tearDown() throws Exception {
		database.disconnect();
	}

	@Test
	public final void testCmonkeySqlite() {
		assertNotNull(database);
	}

	@Test
	public final void testConnect() {
		assertNotNull(database);
	}

	@Test
	public final void testGetClusterList() throws Exception {
		List<CmonkeyCluster> clusterList = database.getClusterList(iterationNumber);
		assertEquals(Long.valueOf("1"), clusterList.get(0).getMotifs().get(0).getPssmId());
		assertEquals("0.028017213920899894", clusterList.get(0).getResidual().toString());
	}

	@Test
	public final void testGetClusterMotifs() throws Exception {
		String clusterNumber = "1";
		List<CmonkeyMotif> motifList = database.getClusterMotifs(iterationNumber,clusterNumber);
		assertEquals("1", motifList.get(0).getPssmId().toString());
		assertEquals(2, motifList.size());
	}

	@Test
	public final void testGetMotifPssm() throws SQLException {
		String motifInfoId = "1807";
		List<List<Double>> result = database.getMotifPssm(motifInfoId);
		assertEquals(Double.valueOf("0.714286"), result.get(0).get(2));
	}

	@Test
	public final void testGetMotifAnnotation() throws SQLException {
		String motifInfoId = "1807";
		Integer motifLength = 18;
		List<MastHit> result = database.getMotifAnnotation(motifInfoId, motifLength);
		assertEquals("1807", result.get(0).getPspmId());
	}

	@Test
	public final void testGetMotifSites() throws SQLException {
		String motifInfoId = "1540";
		List<MemeSite> result = database.getMotifSites(motifInfoId);
		assertEquals("TCTCGGACCACCACGCCGACAGCG", result.get(0).getSequence());
	}

	@Test
	public final void testGetListOfGenes() throws SQLException {
		String clusterId = "43";
		List<String> genes = database.getListOfGenes (iterationNumber, clusterId);
		assertEquals("VNG1085H", genes.get(0));
	}

	@Test
	public final void testGetListOfConditions() throws SQLException {
		String clusterId = "43";
		List<String> conditions = database.getListOfConditions(iterationNumber, clusterId);
		assertEquals("Cond3", conditions.get(0));
	}

	@Test
	public final void testGetCmonkeyRun() throws Exception {
		CmonkeyRunResult result = new CmonkeyRunResult(); 
		database.buildCmonkeyRunResult(result);
		String id = "testCmonkeyResult";
		result.setId(id);
		
		String collectionId = "AKtest/TestExpressionSeries";
		String genomeId = "kb|genome.test";
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(1L);
		params.setNetworksScoring(1L);
		params.setOperomeRef("undefined");
		params.setNetworkRef("undefined");
		params.setSeriesRef(collectionId);
		params.setGenomeRef(genomeId);

		result.setParameters(params);
		CmonkeyServerImplTest.showCmonkeyRun(result);
		//AuthToken token = AuthService.login("aktest", new String("1475rokegi")).getToken();
		//CmonkeyServerImpl.saveObjectToWorkspace(UObject.transformObjectToObject(result, UObject.class), "Cmonkey.CmonkeyRunResult-5.0", "networks_typed_objects_examples", id, token.toString());
		assertEquals(Long.valueOf("2001"),result.getLastIteration());
	}


}
