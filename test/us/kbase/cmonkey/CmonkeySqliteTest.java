package us.kbase.cmonkey;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CmonkeySqliteTest {
	public final String TEST_DATABASE_PATH = "test/cmonkey_run_test.db";
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
	public final void testGetClusterList() {
		List<CmonkeyCluster> clusterList = database.getClusterList(iterationNumber);
		assertEquals("1", clusterList.get(0).getId());
		assertEquals("43", clusterList.get(42).getId());
		assertEquals("23", clusterList.get(0).getResidual().toString());
	}

	@Test
	public final void testGetClusterMotifs() {
		String clusterNumber = "1";
		List<CmonkeyMotif> motifList = database.getClusterMotifs(iterationNumber,clusterNumber);
		assertEquals("1807", motifList.get(0).getId().toString());
		assertEquals(2, motifList.size());
	}

	@Test
	public final void testGetMotifPssm() {
		String motifInfoId = "1807";
		List<List<Double>> result = database.getMotifPssm(motifInfoId);
		assertEquals(Double.valueOf("0.222222"), result.get(0).get(0));
	}

	@Test
	public final void testGetMotifAnnotation() {
		String motifInfoId = "1807";
		Integer motifLength = 18;
		List<MastHit> result = database.getMotifAnnotation(motifInfoId, motifLength);
		assertEquals("1807", result.get(0).getPssmId());
	}

	@Test
	public final void testGetListOfGenes() {
		String clusterId = "43";
		List<String> genes = database.getListOfGenes (iterationNumber, clusterId);
		assertEquals("VNG0158G", genes.get(0));
	}

	@Test
	public final void testGetListOfConditions() {
		String clusterId = "43";
		List<String> conditions = database.getListOfConditions(iterationNumber, clusterId);
		assertEquals("Cond0", conditions.get(0));
	}

	@Test
	public final void testGetCmonkeyRun() throws Exception {
		CmonkeyRunResult result = new CmonkeyRunResult(); 
		database.buildCmonkeyRunResult(result);
		assertEquals(Integer.valueOf("2001"),result.getLastIteration());
	}

	@Test
	public final void testGetCmonkeyNetwork() throws Exception {
		CmonkeyNetwork network = database.getCmonkeyNetwork(iterationNumber);
		assertEquals("1", network.getClusters().get(0).getId());
	}

}
