package us.kbase.cmonkey;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UObject;
import us.kbase.expressionservices.ExpressionSample;
import us.kbase.expressionservices.ExpressionSeries;
import us.kbase.meme.MastHit;
import us.kbase.auth.AuthException;
import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.userandjobstate.Results;
import us.kbase.workspace.RegisterTypespecParams;

public class CmonkeyServerImplTest {
	private static final String USER_NAME = "aktest";
	private static final String PASSWORD = "1475rokegi";
	private static final String workspaceName = "AKtest";
//	private String quickTestSeriesId = "QuickTestExpressionDataSeries";
	private String testSeriesId = "kb|series.test";
	//private final String TEST_DATABASE_PATH = "test/cmonkey_run_test.db";
	private final String TEST_DATABASE_PATH = "/home/kbase/Documents/inferelator-test/out/cmonkey_run.db";
	private ExpressionSeries series = new ExpressionSeries();
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

		
		series.setKbId(testSeriesId);
		ExpressionSample sample1 = new ExpressionSample();
		ExpressionSample sample2 = new ExpressionSample();
		// points for set 1
		Map<String, Double> dataPoints1 = new HashMap<String, Double>();
		dataPoints1.put("VNG1951G", 0.026D);
		dataPoints1.put("VNG1659G", -0.082D);
		dataPoints1.put("VNG1282G", 0.152D);
		sample1.setExpressionLevels(dataPoints1);
		sample1.setKbId("kb|sample.test1");

		Map<String, Double> dataPoints2 = new HashMap<String, Double>();
		dataPoints2.put("VNG1951G", -0.002D);
		dataPoints2.put("VNG1659G", -0.059D);
		dataPoints2.put("VNG1282G", 0.153D);
		sample1.setExpressionLevels(dataPoints2);
		sample1.setKbId("kb|sample.test2");

		// points for set2
		List<String> sampleIds = new ArrayList<String>();
		sampleIds.add(sample1.getKbId());
		sampleIds.add(sample2.getKbId());
		series.setExpressionSampleIds(sampleIds);
		// String result = KbasecmonkeyServerImp.getInputTable(collection);

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
		String collectionId = "AKtest/TestExpressionSeries";
		String genomeId = "kb|g.2723";
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(0L);
		params.setNetworksScoring(1L);
		params.setOperomeId("");
		params.setNetworkId("");
		params.setSeriesId(collectionId);
		params.setGenomeId(genomeId);
		
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

		CmonkeyRunResult result = CmonkeyServerImpl.getObjectFromWorkspace(workspaceName, resultId, token.toString()).getData().asClassInstance(CmonkeyRunResult.class);
		
		
		assertEquals(Long.valueOf("43"), result.getNetwork().getClustersNumber());
	}

	@Test
	public final void testEnvironment() throws Exception {
		System.out.println(CmonkeyServerImpl.executeCommand("which R", "."));
	}
	

	
	@Test
	public final void testFastBuildCmonkeyNetworkJobFromWs() throws Exception {
		String seriesRef = "AKtest/QuickTestExpressionSeries";
		String genomeId = "kb|g.2723";
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(1L);
		params.setNetworksScoring(0L);
		params.setSeriesId(seriesRef);
		params.setGenomeId(genomeId);
		
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
		CmonkeyRunResult result = CmonkeyServerImpl.getObjectFromWorkspace(workspaceName, resultId, token.toString()).getData().asClassInstance(CmonkeyRunResult.class);;
		
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
	public final void testGetInputTable() {

		String result = CmonkeyServerImpl.getInputTable("AKtest", series.getExpressionSampleIds(), token.toString());
		System.out.println(result);
		assertEquals(
				"GENE\tcondition1\tcondition2\nVNG1659G\t-0.082\t-0.059\nVNG1282G\t0.152\t0.153\nVNG1951G\t0.026\t-0.002\n",
				result);
	}

	@Test
	public final void testWriteInputFile() {
		String testFile = "test/halo_ratios5.tsv";

		List<String> testSeriesIds = readCollectionFromFile(testFile);
		ExpressionSeries testCollection = UObject.transformObjectToObject(CmonkeyServerImpl.getObjectFromWorkspace("AKtest", testSeriesIds.get(0), token.toString()), ExpressionSeries.class);

		String result = CmonkeyServerImpl.getInputTable("AKtest", testCollection.getExpressionSampleIds(), token.toString());
		CmonkeyServerImpl.writeInputFile("test/halo_testinput.txt", result);
		assertEquals(5, testCollection.getExpressionSampleIds().size());
		// TODO: check file content
	}

/*	@Test
	public final void testGetOrganismCode() {

		String result = null;
		try {
			result = CmonkeyServerImpl.getOrganismName("AKtest", series, token.toString());
			assertEquals("Halobacterium sp. NRC-1", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

/*	@Test
	public final void testGetOrganismCodeWrongData() {
		ExpressionSeries testSeries = new ExpressionSeries();
		ExpressionSample sample1 = new ExpressionSample();
		ExpressionSample sample2 = new ExpressionSample();
		// points for set 1
		Map<String, Double> dataPoints1 = new HashMap<String, Double>();
		dataPoints1.put("VNG1951G", 0.026D);
		dataPoints1.put("VNG1659G", -0.082D);
		dataPoints1.put("VNG1282G", 0.152D);
		sample1.setExpressionLevels(dataPoints1);
		sample1.setKbId("kb|sample.test1");

		Map<String, Double> dataPoints2 = new HashMap<String, Double>();
		dataPoints2.put("VNG1951G", -0.002D);
		dataPoints2.put("VNG1659G", -0.059D);
		dataPoints2.put("DVU0745", 0.153D);
		sample1.setExpressionLevels(dataPoints2);
		sample1.setKbId("kb|sample.test2");

		// points for set2
		List<String> sampleIds = new ArrayList<String>();
		sampleIds.add(sample1.getKbId());
		sampleIds.add(sample2.getKbId());
		testSeries.setExpressionSampleIds(sampleIds);

		String result = null;
		try {
			result = CmonkeyServerImpl.getOrganismName(testSeries);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertNull(result);
			e.printStackTrace();
		}
	}*/

	@Test
	public final void testGetKeggCode() {
		String organismName = "Halobacterium sp. NRC-1";
		String result = CmonkeyServerImpl.getKeggCode(organismName);
		assertEquals("hal", result);
	}

	@Test
	public final void testReadCollectionFromFile() {
		String testFile = "test/halo_ratios5.tsv";

		List<String> testSeriesIds = readCollectionFromFile(testFile);
		ExpressionSeries testCollection = UObject.transformObjectToObject(CmonkeyServerImpl.getObjectFromWorkspace("AKtest", testSeriesIds.get(0), token.toString()), ExpressionSeries.class);
		assertEquals(5, testCollection.getExpressionSampleIds().size());
	}

	@Test
	public final void testRunCmonkey() throws IOException, InterruptedException {
		runCmonkey();
		fail("No assertions");
	}
	
	@Test
	public void testWsRead() throws Exception {
		String name = "";

		ExpressionSeries result = UObject.transformObjectToObject(CmonkeyServerImpl.getObjectFromWorkspace(workspaceName, name, token.toString()), ExpressionSeries.class);

		assertEquals(series.getKbId(),result.getKbId());
	}


	private List<String> readCollectionFromFile(String fileName) {
		List<String> result = new ArrayList<String>();
		ExpressionSeries series = new ExpressionSeries();
		try {
			series.setKbId(CmonkeyServerImpl.getKbaseId("ExpressionSeries"));//("TestExpressionSeries");//
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		series.setSourceId("unknown");
		series.setExternalSourceDate("unknown");
		result.add(series.getKbId());

		

		List<String> sampleRefs = new ArrayList<String>();
		List<String> conditions = new ArrayList<String>();
		List<HashMap<String, Double>> dataValues = new ArrayList<HashMap<String, Double>>();
		
		Integer samplesNumber = 0;
		try {
			String line = null;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				if (line.equals("")) {
					// do nothing
				} else if (line.matches("GENE\t.*")) {
					line = line.substring(5);
					String[] fields = line.split("\t");
					for (String field : fields) {
						conditions.add(field);
						 //System.out.println(field);
						samplesNumber++;
					}
					for (Integer i = 0; i < samplesNumber; i++) {
						HashMap<String, Double> dataValue = new HashMap<String, Double>();
						dataValues.add(dataValue);
					}

				} else {
					String[] fields = line.split("\t");
					Integer j = 0;
					while (j < samplesNumber) {
						dataValues.get(j).put(fields[0],
								Double.valueOf(fields[j + 1]));
						 //System.out.println(fields[0]+" "+fields[j+1]);
						j++;
					}
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}

		 System.out.println(conditions.toString());
		for (Integer i = 0; i < samplesNumber; i++) {
			ExpressionSample sample = new ExpressionSample();
			//Integer sampleNo = i+1;
			sample.setKbId(CmonkeyServerImpl.getKbaseId("ExpressionSample"));//("TestExpressionSample"+sampleNo.toString());//
			sample.setSourceId(conditions.get(i));
			sample.setType("microarray");
			sample.setNumericalInterpretation("undefined");
			sample.setExternalSourceDate("undefined");
			sample.setGenomeId("kb|genome.1");
			sample.setExpressionLevels(dataValues.get(i));
			sampleRefs.add(workspaceName + "/" + sample.getKbId());
			CmonkeyServerImpl.saveObjectToWorkspace(UObject.transformObjectToObject(sample, UObject.class), "ExpressionServices.ExpressionSample-1.0", workspaceName, sample.getKbId(), token.toString());
			result.add(sample.getKbId());
						
		}
		series.setExpressionSampleIds(sampleRefs);
		CmonkeyServerImpl.saveObjectToWorkspace(UObject.transformObjectToObject(series, UObject.class), "ExpressionServices.ExpressionSeries-1.0", workspaceName, series.getKbId(), token.toString());
		return result;
	}

	private static void runCmonkey() throws IOException, InterruptedException {
		String commandLine = "cmonkey-python --organism hal --ratios 0_input.txt --out 0_out";
		File cmonkeyDir = new File("/media/sf_Shared/cmonkey-python-master/");
		try {
			Process p = Runtime.getRuntime()
					.exec(commandLine, null, cmonkeyDir);
			
			StreamGobbler errorGobbler = new 
	                StreamGobbler(p.getErrorStream(), "ERROR", null);            
	            
	            // any output?
	            StreamGobbler outputGobbler = new 
	                StreamGobbler(p.getInputStream(), "OUTPUT", null);
	                
	            // kick them off
	            errorGobbler.start();
	            outputGobbler.start();
	                                    
	            // any error???
	            int exitVal = p.waitFor();
	            System.out.println("ExitValue: " + exitVal);      
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
		}

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
/*	@Test
	public void testWsWriteRead() throws Exception {
		String id = "QuickTestExpressionDataSeries12345";
		String testFile = "/home/kbase/cmonkey-test/mini-halo_ratios5.tsv";

		List<String> testSeriesIds = readCollectionFromFile(testFile);
		System.out.println(testSeriesIds.toString());
		
		ExpressionSeries testCollection = CmonkeyServerImpl.getObjectFromWorkspace(workspaceName, testSeriesIds.get(0), token.toString()).getData().asClassInstance(ExpressionSeries.class);
	    testCollection.setKbId(id);
	    
	    CmonkeyServerImpl.saveObjectToWorkspace(UObject.transformObjectToObject(testCollection, UObject.class), "ExpressionServices.ExpressionSeries-1.0", workspaceName, testCollection.getKbId(), token.toString());
		ExpressionSeries result = CmonkeyServerImpl.getObjectFromWorkspace(workspaceName, id, token.toString()).getData().asClassInstance(ExpressionSeries.class);

		assertEquals(testCollection.getKbId(),result.getKbId());		
	}*/

	@Test
	public final void testGetKbaseId() throws Exception {
		String id = CmonkeyServerImpl.getKbaseId("CmonkeyRunResult");
		System.out.println(id);
		assertNotNull(id);
		
	}
	
	
	@Test
	public void testToken() throws Exception {
		String token = null;
		AuthToken auth = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		token = auth.toString();
		System.out.println(token);
		assertNotNull(token);
		
	}

	@Test
	public void testCleanupArgument() throws Exception {
		String argument = "'un=aktest|tokenid=378448aa-642b-11e3-884e-12313d2d6e7f|expiry=1418498252|client_id=aktest|token_type=Bearer|SigningSubject=https://nexus.api.globusonline.org/goauth/keys/38973b4e-642b-11e3-884e-12313d2d6e7f|sig=a61bfe37fd8d3a882ea62e6a8f5c35f546cdf7870e797ec3a6588a53409a28ece1775b596411d3d82c907e17dbd10bb467fc06e43633f9c33c6151e1e19a1aabaa4cb58fac78d7e904adb154a3043df4301f747b61d75586b93046ece55c14564afba32290b1fbb405cf9ce1d060336f46285ab5b4225c95bef12fe99086ce8b'";
		argument = CmonkeyInvoker.cleanUpArgument(argument);
		System.out.println(argument);
		assertEquals(argument, "un=aktest|tokenid=378448aa-642b-11e3-884e-12313d2d6e7f|expiry=1418498252|client_id=aktest|token_type=Bearer|SigningSubject=https://nexus.api.globusonline.org/goauth/keys/38973b4e-642b-11e3-884e-12313d2d6e7f|sig=a61bfe37fd8d3a882ea62e6a8f5c35f546cdf7870e797ec3a6588a53409a28ece1775b596411d3d82c907e17dbd10bb467fc06e43633f9c33c6151e1e19a1aabaa4cb58fac78d7e904adb154a3043df4301f747b61d75586b93046ece55c14564afba32290b1fbb405cf9ce1d060336f46285ab5b4225c95bef12fe99086ce8b");
		
	}


	@Test
	public void testWsRegisterType() throws Exception {
		//Now CompileTypespec
		
		RegisterTypespecParams params = new RegisterTypespecParams();
		String specFileName = "/home/kbase/dev_container/modules/cmonkey/kbase_cmonkey.spec";
		String spec = "";
		BufferedReader br = null;
		try {
			String line = null;
			br = new BufferedReader(new FileReader(specFileName));
			while ((line = br.readLine()) != null) {
				spec += line + "\n";
			}
		} catch (IOException e) {
			System.out.println(specFileName + "read error\n" + e.getLocalizedMessage());
		} finally {
			if (br != null) {
				br.close();
			}
		}
		params.setSpec(spec);

//		params.setMod("Cmonkey");
		List<String> types = new ArrayList<String>();
		types.add("CmonkeyRunResult");
//		types.add("CmonkeyNetwork");
//		types.add("CmonkeyCluster");
//		types.add("CmonkeyMotif");
		params.setNewTypes(types);
		Map<String,String> result = CmonkeyServerImpl.wsClient(token.toString()).registerTypespec(params);
		System.out.println(result.toString());
		assertNotNull(result);
		
	}

	
}

