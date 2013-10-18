package us.kbase.kbasecmonkey;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CmonkeySqliteTest {
	public final String TEST_DATABASE_PATH = "test/cmonkey_run_test.db";
	private CmonkeySqlite database;
	Integer iterationNumber = 2001;


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
		assertEquals("1", clusterList.get(0).getCmonkeyClusterId());
		assertEquals("43", clusterList.get(42).getCmonkeyClusterId());
		assertEquals("23", clusterList.get(0).getNumGenes().toString());
	}

	@Test
	public final void testGetClusterMotifs() {
		String clusterNumber = "1";
		List<MotifCmonkey> motifList = database.getClusterMotifs(iterationNumber,clusterNumber);
		assertEquals("1807", motifList.get(0).getCmonkeyMotifId().toString());
		assertEquals(2, motifList.size());
	}

	@Test
	public final void testGetMotifPssm() {
		String motifInfoId = "1807";
		List<PssmRow> result = database.getMotifPssm(motifInfoId);
		assertEquals(Double.valueOf("0.222222"), result.get(0).getAWeight());
	}

	@Test
	public final void testGetMotifAnnotation() {
		String motifInfoId = "1807";
		Integer motifLength = 18;
		List<HitMast> result = database.getMotifAnnotation(motifInfoId, motifLength);
		assertEquals("1807", result.get(0).getMotifName());
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
	public final void testGetCmonkeyRun() {
		CmonkeyRun result = new CmonkeyRun(); 
		database.buildCmonkeyRun(result);
		assertEquals(Integer.valueOf("2001"),result.getCmonkeyRunLastIteration());
	}

	@Test
	public final void testGetCmonkeyNetwork() {
		CmonkeyNetwork network = database.getCmonkeyNetwork(iterationNumber);
		assertEquals("1", network.getCmonkeyClusters().get(0).getCmonkeyClusterId());
	}

}
