package us.kbase.cmonkey;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UObject;
import us.kbase.meme.MastHit;
import us.kbase.auth.AuthException;
import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.userandjobstate.Results;
import us.kbase.util.WsDeluxeUtil;

public class CmonkeyServerImplTest {
	private static final String USER_NAME = "aktest";
	private static final String PASSWORD = "1475rokegi";
	private static final String workspaceName = "AKtest";
	private String testSeriesRef = "AKtest/kb|series.269";
	private String testGenomeRef = "AKtest/kb|genome.8";
	private String testStringNetworkRef = "AKtest/kb|interactionset.5";
	private String testOperonNetworkRef = "AKtest/kb|interactionset.6";
	private final String TEST_DATABASE_PATH = "test/cmonkey_run_test.db";
	//private final String TEST_DATABASE_PATH = "/home/kbase/Documents/inferelator-test/out/cmonkey_run.db";
	private static AuthToken token = null;

	@Before
	public void setUp() throws Exception {
		try {
			token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		} catch (AuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

/*	@Test
	public final void testBuildCmonkeyNetwork() throws Exception {
		String testFile = "test/halo_ratios5.tsv";
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setNoMotifs(1L);
		params.setNoNetworks(1L);
		params.setNoOperons(1L);
		params.setNoString(1L);
		ExpressionDataSeries testCollection = readCollectionFromFile(testFile);
		System.out.println(testCollection.getId());
		CmonkeyRunResult result = CmonkeyServerImpl.buildCmonkeyNetwork(testCollection, params);
		showCmonkeyRun(result);
		assertEquals(Long.valueOf("43"), result.getClustersNumber());
	}

	@Test
	public final void testBuildCmonkeyNetworkFromWs() throws Exception {
		String collectionId = "TestExpressionDataSeries";
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setNoMotifs(0L);
		params.setNoNetworks(0L);
		params.setNoOperons(0L);
		params.setNoString(0L);
		String resultId = CmonkeyServerImpl.buildCmonkeyNetworkFromWs(workspaceName, collectionId, params, token);
		GetObjectParams objectParams = new GetObjectParams().withType("CmonkeyRunResult").withId(resultId).withWorkspace(workspaceName).withAuth(token.toString());
		GetObjectOutput output = CmonkeyServerImpl.wsClient(token.toString()).getObject(objectParams);
		CmonkeyRunResult result = UObject.transformObjectToObject(output.getData(), CmonkeyRunResult.class);
		
		
		assertEquals(Long.valueOf("43"), result.getClustersNumber());
	}
	
	@Test
	public final void testQuickBuildCmonkeyNetworkFromWs() throws Exception {
		String collectionId = "QuickTestExpressionDataSeries";
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setNoMotifs(1L);
		params.setNoNetworks(1L);
		params.setNoOperons(1L);
		params.setNoString(1L);
		String resultId = CmonkeyServerImpl.buildCmonkeyNetworkFromWs(workspaceName, collectionId, params, token);
		GetObjectParams objectParams = new GetObjectParams().withType("CmonkeyRunResult").withId(resultId).withWorkspace(workspaceName).withAuth(token.toString());
		GetObjectOutput output = CmonkeyServerImpl.wsClient(token.toString()).getObject(objectParams);
		CmonkeyRunResult result = UObject.transformObjectToObject(output.getData(), CmonkeyRunResult.class);
		
		
		assertEquals(Long.valueOf("3"), result.getClustersNumber());
	}*/

	@Test
	public final void testBuildCmonkeyNetworkJobFromWs() throws Exception {
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(1L);
		params.setNetworksScoring(1L);
		params.setSeriesRef(testSeriesRef);
		params.setGenomeRef(testGenomeRef);
		params.setNetworkRef(testStringNetworkRef);
		params.setOperomeRef(testOperonNetworkRef);
		
		String jobId = CmonkeyServerImpl.jobClient(token.toString()).createJob();
		//String jobId = CmonkeyServerCaller.buildCmonkeyNetworkJobFromWs(workspaceName, params, token);
		CmonkeyServerImpl.buildCmonkeyNetworkJobFromWs(workspaceName, params, jobId, token.toString(), null);
		
		System.out.println("Job ID = " + jobId);
		assertNotNull(jobId);
		String resultId = "";

		try {
			Results res = CmonkeyServerImpl.jobClient(token.toString()).getResults(jobId);			
			resultId = res.getWorkspaceids().get(0);
			System.out.println("Result ID = " + resultId);
			assertNotNull(resultId);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CmonkeyRunResult result = WsDeluxeUtil.getObjectFromWsByRef(resultId, token.toString()).getData().asClassInstance(CmonkeyRunResult.class);
		
		
		assertEquals(Long.valueOf("43"), result.getNetwork().getClustersNumber());
	}

	@Test
	public final void testFastBuildCmonkeyNetworkJobFromWs() throws Exception {
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(0L);
		params.setNetworksScoring(0L);
		params.setSeriesRef(testSeriesRef);
		params.setGenomeRef(testGenomeRef);
		
		String jobId = CmonkeyServerImpl.jobClient(token.toString()).createJob();
		//String jobId = CmonkeyServerCaller.buildCmonkeyNetworkJobFromWs(workspaceName, params, token);
		CmonkeyServerImpl.buildCmonkeyNetworkJobFromWs(workspaceName, params, jobId, token.toString(), null);
		
		System.out.println("Job ID = " + jobId);
		assertNotNull(jobId);
		String resultId = "";

		try {
			Results res = CmonkeyServerImpl.jobClient(token.toString()).getResults(jobId);			
			resultId = res.getWorkspaceids().get(0);
			System.out.println("Result ID = " + resultId);
			assertNotNull(resultId);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] resultIdParts = resultId.split("/");
		resultId = resultIdParts[1];
		CmonkeyRunResult result = WsDeluxeUtil.getObjectFromWsByRef(resultId, token.toString()).getData().asClassInstance(CmonkeyRunResult.class);;
		
		assertEquals(Long.valueOf("3"), result.getNetwork().getClustersNumber());
	}

	@Test
	public final void testParseCmonkeySql() throws Exception {
		CmonkeyRunResult cmonkeyRun = new CmonkeyRunResult();
		CmonkeyServerImpl.parseCmonkeySql(TEST_DATABASE_PATH, cmonkeyRun);
		//cmonkeyRun.setId(CmonkeyServerImpl.getKbaseId("CmonkeyRunResult"));
		showCmonkeyRun(cmonkeyRun);
		assertNotNull(cmonkeyRun);
		assertEquals(Long.valueOf("43"), cmonkeyRun.getNetwork().getClustersNumber());
		assertEquals(2, cmonkeyRun.getNetwork().getClusters().get(0).getMotifs().size());

	}

	@Test
	public final void testWriteCmonkeyRunResult() throws Exception {
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(0L);
		params.setNetworksScoring(0L);
		params.setSeriesRef(testSeriesRef);
		params.setGenomeRef(testGenomeRef);

		CmonkeyRunResult cmonkeyRun = new CmonkeyRunResult();
		CmonkeyServerImpl.parseCmonkeySql(TEST_DATABASE_PATH, cmonkeyRun);
		cmonkeyRun.setId(CmonkeyServerImpl.getKbaseId("CmonkeyRunResult"));
		cmonkeyRun.setParameters(params);
		//showCmonkeyRun(cmonkeyRun);
		WsDeluxeUtil.saveObjectToWorkspace(
				UObject.transformObjectToObject(cmonkeyRun, UObject.class),
				"Cmonkey.CmonkeyRunResult", workspaceName, cmonkeyRun.getId(),
				token.toString());

		
		assertNotNull(cmonkeyRun);
		assertEquals(Long.valueOf("39"), cmonkeyRun.getNetwork().getClustersNumber());
		assertEquals(2, cmonkeyRun.getNetwork().getClusters().get(0).getMotifs().size());

	}

	public static void showCmonkeyRun (CmonkeyRunResult runResult){
		DecimalFormat df = new DecimalFormat("0.000");
		System.out.println("CMONKEY RUN PARAMETERS:");
		System.out.println("\tstart time = "+runResult.getStartTime());
		System.out.println("\tfinish time = "+runResult.getFinishTime());
		System.out.println("\torganism = "+runResult.getNetwork().getGenomeName());
		System.out.println("\titerations = "+runResult.getIterationsNumber());
		System.out.println("\tlast iteration = "+runResult.getLastIteration());
		System.out.println("\trows = "+runResult.getNetwork().getRowsNumber());
		System.out.println("\tcolumns = "+runResult.getNetwork().getColumnsNumber());
		System.out.println("\tclusters = "+runResult.getNetwork().getClustersNumber()+"\n");

		System.out.println("NETWORK\n");
		System.out.println("\tNetwork ID = "+runResult.getNetwork().getId()+"\n");
		for(CmonkeyCluster cluster:runResult.getNetwork().getClusters()){
			System.out.println("\tCLUSTER:");
			System.out.println("\t\tCluster ID = "+cluster.getId());
			System.out.println("\t\tNumber of genes = "+cluster.getGeneIds().size());
			System.out.println("\t\tNumber of conditions = "+cluster.getSampleWsIds().size());
			System.out.println("\t\tResidual = "+cluster.getResidual());
			System.out.println("\t\tGENES:");
			for (String gene:cluster.getGeneIds()){
				System.out.println("\t\t\t"+gene);
			}
			System.out.println("\t\tCONDITIONS:");
			for (String condition:cluster.getSampleWsIds()){
				System.out.println("\t\t\t"+condition);
			}
			System.out.println("\t\tMOTIFS:");
			for (CmonkeyMotif motif: cluster.getMotifs()){
				System.out.println("\t\t\tMOTIF "+motif.getId());
				System.out.println("\t\t\t\tSeqtype = "+motif.getSeqType());
				System.out.println("\t\t\t\tMotif num = "+motif.getPssmId());
				System.out.println("\t\t\t\tevalue = "+motif.getEvalue());
				System.out.println("\t\t\t\tPSSM:");
				for (List<Double> row:motif.getPssmRows()){
					System.out.println("\t\t\t\t\t"+df.format(row.get(0))+"\t"+
							df.format(row.get(1))+"\t"+df.format(row.get(2))+"\t"+df.format(row.get(3)));
				}
				System.out.println("\t\t\t\tMOTIF HITS:");
				for (MastHit hit:motif.getHits()){
					System.out.println("\t\t\t\t\t"+hit.getSeqId()+"\t"+hit.getHitStart()+"\t"+hit.getHitEnd()+"\t"+
							hit.getStrand()+"\t"+hit.getHitPvalue());
				}
			}
			System.out.println();
		}
	}


}

