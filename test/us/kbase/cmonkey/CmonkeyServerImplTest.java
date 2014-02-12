package us.kbase.cmonkey;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UObject;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.kbaseexpression.ExpressionSeries;
import us.kbase.meme.MastHit;
import us.kbase.auth.AuthException;
import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.userandjobstate.Results;
import us.kbase.userandjobstate.UserAndJobStateClient;
import us.kbase.util.WsDeluxeUtil;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.WorkspaceClient;

public class CmonkeyServerImplTest {

	private static final String WS_SERVICE_URL = "https://kbase.us/services/ws";
	private static final String UJS_SERVICE_URL = "https://kbase.us/services/userandjobstate";

	private static final String USER_NAME = "";
	private static final String PASSWORD = "";
	private static final String workspaceName = "AKtest";
	private String testSeriesRef = "AKtest/Halobacterium_sp_NRC-1";
	private String testGenomeRef = "AKtest/kb|genome.9";
	private String testStringNetworkRef = "AKtest/Halobacterium_sp_STRING";
	private String testOperonNetworkRef = "AKtest/Halobacterium_sp_operons";
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

		URL jobServiceUrl = new URL(UJS_SERVICE_URL);
		String jobId = new UserAndJobStateClient(jobServiceUrl, token).createJob();
		//String jobId = CmonkeyServerCaller.buildCmonkeyNetworkJobFromWs(workspaceName, params, token);
		CmonkeyServerImpl.buildCmonkeyNetworkJobFromWs(workspaceName, params, jobId, token.toString(), null);
		
		System.out.println("Job ID = " + jobId);
		assertNotNull(jobId);
		String resultId = "";

		try {
			Results res = new UserAndJobStateClient(jobServiceUrl, token).getResults(jobId);			
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
		WorkspaceClient wsClient = new WorkspaceClient(new URL (WS_SERVICE_URL), new AuthToken(token.toString()));
		wsClient.setAuthAllowedForHttp(true);
		
		List<ObjectIdentity> objectsIdentity = new ArrayList<ObjectIdentity>();
		ObjectIdentity objectIdentity = new ObjectIdentity().withRef(resultId);
		objectsIdentity.add(objectIdentity);
		CmonkeyRunResult result = wsClient.getObjects(objectsIdentity).get(0).getData().asClassInstance(CmonkeyRunResult.class);
		
		assertEquals(Long.valueOf("43"), result.getNetwork().getClustersNumber());
	}

	@Test
	public final void testFastBuildCmonkeyNetworkJobFromWs() throws Exception {
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(0L);
		params.setNetworksScoring(0L);
		params.setSeriesRef(testSeriesRef);
		params.setGenomeRef(testGenomeRef);
		URL jobServiceUrl = new URL(UJS_SERVICE_URL);
		
		String jobId = new UserAndJobStateClient(jobServiceUrl, token).createJob();
		//String jobId = CmonkeyServerCaller.buildCmonkeyNetworkJobFromWs(workspaceName, params, token);
		CmonkeyServerImpl.buildCmonkeyNetworkJobFromWs(workspaceName, params, jobId, token.toString(), null);
		
		System.out.println("Job ID = " + jobId);
		assertNotNull(jobId);
		String resultId = "";

		try {
			Results res = new UserAndJobStateClient(jobServiceUrl, token).getResults(jobId);			
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
		WorkspaceClient wsClient = new WorkspaceClient(new URL (WS_SERVICE_URL), new AuthToken(token.toString()));
		wsClient.setAuthAllowedForHttp(true);
		
		List<ObjectIdentity> objectsIdentity = new ArrayList<ObjectIdentity>();
		ObjectIdentity objectIdentity = new ObjectIdentity().withRef(resultId);
		objectsIdentity.add(objectIdentity);
		CmonkeyRunResult result = wsClient.getObjects(objectsIdentity).get(0).getData().asClassInstance(CmonkeyRunResult.class);
		
		assertEquals(Long.valueOf("3"), result.getNetwork().getClustersNumber());
	}

	@Test
	public final void testParseCmonkeySql() throws Exception {
		CmonkeyRunResult cmonkeyRun = new CmonkeyRunResult();
		CmonkeyServerImpl.parseCmonkeySql(TEST_DATABASE_PATH, cmonkeyRun, null);
		//cmonkeyRun.setId(CmonkeyServerImpl.getKbaseId("CmonkeyRunResult"));
		showCmonkeyRun(cmonkeyRun);
		assertNotNull(cmonkeyRun);
		assertEquals(Long.valueOf("43"), cmonkeyRun.getNetwork().getClustersNumber());
		assertEquals(2, cmonkeyRun.getNetwork().getClusters().get(0).getMotifs().size());

	}

	@Test
	public final void testWriteCmonkeyRunResult() throws Exception {
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(1L);
		params.setNetworksScoring(1L);
		params.setSeriesRef(testSeriesRef);
		params.setGenomeRef(testGenomeRef);
		params.setOperomeRef(testOperonNetworkRef);
		params.setNetworkRef(testStringNetworkRef);
		

		CmonkeyRunResult cmonkeyRun = new CmonkeyRunResult();
		CmonkeyServerImpl.parseCmonkeySql(TEST_DATABASE_PATH, cmonkeyRun, null);
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
	
	@Test
	public final void testDeleteDirectory() throws Exception {
		String directoryName = "/var/tmp/cmonkey/52be6cd9e4b0565cd80fb7dd";
		File directory = new File(directoryName) ;
		CmonkeyServerImpl.deleteDirectoryRecursively(directory);
		assertFalse(directory.exists());

	}

	@Test
	public final void testDeleteFiles() throws Exception {
		String directoryName = "/var/tmp/cmonkey/";
		String pattern = "cmonkey-checkpoint.*";
		CmonkeyServerImpl.deleteFilesByPattern(directoryName, pattern);
		assertFalse(new File(directoryName+"cmonkey-checkpoint-20131227.100").exists());

	}

	@Test
	public final void testCreateSeriesFile() throws Exception {
		String dirName = "/home/kbase/dev_container/modules/cmonkey/test/";
		
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		ExpressionSeries input = WsDeluxeUtil.getObjectFromWsByRef(testSeriesRef, token.toString()).getData().asClassInstance(ExpressionSeries.class);
		List<String> sampleIdsList = input.getGenomeExpressionSampleIdsMap().get(testGenomeRef);
		CmonkeyServerImpl.createInputTable(dirName, sampleIdsList, token.toString());
		assertTrue(new File(dirName + "input.txt").exists());

	}

	@Test
	public final void testExtractGenomeId() throws Exception {
	String genomeRef = testGenomeRef;
	String[] g = genomeRef.split("/");
	String genomeId = g[g.length - 1];
	System.out.println (genomeId);
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

	@Test
	public void testDeleteJob() throws AuthException, IOException, UnauthorizedException, JsonClientException {
		String jobId = "52e70eb7e4b0ef83573320eb";

//		AuthToken token = AuthService.login(JOB_ACCOUNT, new String(JOB_PASSWORD)).getToken();
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		AuthToken serviceToken = AuthService.login(CmonkeyServerConfig.SERVICE_LOGIN, new String(CmonkeyServerConfig.SERVICE_PASSWORD)).getToken();

		URL jobServiceUrl = new URL(UJS_SERVICE_URL);
		UserAndJobStateClient jobClient = new UserAndJobStateClient(jobServiceUrl, token);
		jobClient.forceDeleteJob(serviceToken.toString(), jobId);
	}

/*	@Test
	public void testUJS() throws Exception, IOException{
        AuthToken token = AuthService.login(USER_NAME, PASSWORD).getToken();
        AuthToken serviceToken = AuthService.login(CmonkeyServerConfig.SERVICE_LOGIN, new String(CmonkeyServerConfig.SERVICE_PASSWORD)).getToken();
        
        UserAndJobStateClient jobService = new UserAndJobStateClient(new URL ("https://kbase.us/services/userandjobstate"), token ); 
        jobService.setAuthAllowedForHttp(true);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        dateFormat.setLenient(false);
        Date date = new Date();
        jobService.createAndStartJob(serviceToken.toString(), "java job", "desc5",
                new InitProgress().withPtype("none"), dateFormat.format(date));	
	}
*/

	@Test
	public void test() throws Exception  {

		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();

	}

}

