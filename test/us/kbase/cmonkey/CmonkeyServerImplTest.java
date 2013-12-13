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
import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.userandjobstate.Results;
//import us.kbase.util.WSRegisterType;
//import us.kbase.util.WSUtil;
import us.kbase.workspaceservice.GetObjectOutput;
import us.kbase.workspaceservice.GetObjectParams;

public class CmonkeyServerImplTest {
	private static final String USER_NAME = "aktest";
	private static final String PASSWORD = "1475rokegi";
	private static final String workspaceName = "AKtest";
//	private String quickTestSeriesId = "QuickTestExpressionDataSeries";
	private String testSeriesId = "TestExpressionDataSeries";
	private final String TEST_DATABASE_PATH = "test/cmonkey_run_test.db";
	private ExpressionDataSeries series = new ExpressionDataSeries();

	@Before
	public void setUp() throws Exception {
		series.setId(testSeriesId);
		ExpressionDataSample set1 = new ExpressionDataSample();
		ExpressionDataSample set2 = new ExpressionDataSample();
		// points for set 1
		List<ExpressionDataPoint> points1 = new ArrayList<ExpressionDataPoint>();
		ExpressionDataPoint point1 = new ExpressionDataPoint();
		point1.setGeneId("VNG1951G");
		point1.setExpressionValue(0.026D);
		ExpressionDataPoint point2 = new ExpressionDataPoint();
		point2.setGeneId("VNG1659G");
		point2.setExpressionValue(-0.082D);
		ExpressionDataPoint point3 = new ExpressionDataPoint();
		point3.setGeneId("VNG1282G");
		point3.setExpressionValue(0.152D);
		points1.add(point1);
		points1.add(point2);
		points1.add(point3);
		set1.setPoints(points1);
		set1.setId("condition1");
		// points for set2
		List<ExpressionDataPoint> points2 = new ArrayList<ExpressionDataPoint>();
		ExpressionDataPoint point4 = new ExpressionDataPoint();
		point4.setGeneId("VNG1951G");
		point4.setExpressionValue(-0.002D);
		ExpressionDataPoint point5 = new ExpressionDataPoint();
		point5.setGeneId("VNG1659G");
		point5.setExpressionValue(-0.059D);
		ExpressionDataPoint point6 = new ExpressionDataPoint();
		point6.setGeneId("VNG1282G");
		point6.setExpressionValue(0.153D);
		points2.add(point4);
		points2.add(point5);
		points2.add(point6);
		set2.setPoints(points2);
		set2.setId("condition2");
		List<ExpressionDataSample> sets = new ArrayList<ExpressionDataSample>();
		sets.add(set1);
		sets.add(set2);
		series.setSamples(sets);
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
		String collectionId = "HalobacteriumExpressionSeries";
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setNoMotifs(0L);
		params.setNoNetworks(0L);
		params.setNoOperons(0L);
		params.setNoString(0L);
		String jobId = CmonkeyServerCaller.buildCmonkeyNetworkJobFromWs(workspaceName, collectionId, params, token);
		
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

		GetObjectParams objectParams = new GetObjectParams().withType("CmonkeyRunResult").withId(resultId).withWorkspace(workspaceName).withAuth(token.toString());
		GetObjectOutput output = CmonkeyServerImpl.wsClient(token.toString()).getObject(objectParams);
		CmonkeyRunResult result = UObject.transformObjectToObject(output.getData(), CmonkeyRunResult.class);
		
		
		assertEquals(Long.valueOf("43"), result.getClustersNumber());
	}

	@Test
	public final void testFastBuildCmonkeyNetworkJobFromWs() throws Exception {
		String collectionId = "HalobacteriumExpressionSeries";
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setNoMotifs(1L);
		params.setNoNetworks(1L);
		params.setNoOperons(1L);
		params.setNoString(1L);
		String jobId = CmonkeyServerCaller.buildCmonkeyNetworkJobFromWs(workspaceName, collectionId, params, token);
		
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

		GetObjectParams objectParams = new GetObjectParams().withType("CmonkeyRunResult").withId(resultId).withWorkspace(workspaceName).withAuth(token.toString());
		GetObjectOutput output = CmonkeyServerImpl.wsClient(token.toString()).getObject(objectParams);
		CmonkeyRunResult result = UObject.transformObjectToObject(output.getData(), CmonkeyRunResult.class);
		
		
		assertEquals(Long.valueOf("43"), result.getClustersNumber());
	}

	@Test
	public final void testParseCmonkeySql() throws Exception {
		CmonkeyRunResult cmonkeyRun = new CmonkeyRunResult();
		CmonkeyServerImpl.parseCmonkeySql(TEST_DATABASE_PATH, cmonkeyRun);
		showCmonkeyRun(cmonkeyRun);
		assertNotNull(cmonkeyRun);
		assertEquals(Long.valueOf("43"), cmonkeyRun.getClustersNumber());
		assertEquals(2, cmonkeyRun.getNetwork().getClusters().get(0).getMotifs().size());

	}

	@Test
	public final void testGetInputTable() {
		String result = CmonkeyServerImpl.getInputTable(series);
		System.out.println(result);
		assertEquals(
				"GENE\tcondition1\tcondition2\nVNG1659G\t-0.082\t-0.059\nVNG1282G\t0.152\t0.153\nVNG1951G\t0.026\t-0.002\n",
				result);
	}

	@Test
	public final void testWriteInputFile() {
		String testFile = "test/halo_ratios5.tsv";
		ExpressionDataSeries testCollection = readCollectionFromFile(testFile);
		String result = CmonkeyServerImpl.getInputTable(testCollection);
		CmonkeyServerImpl.writeInputFile("test/halo_testinput.txt", result);
		assertEquals(5, testCollection.getSamples().size());
		// TODO: check file content
	}

	@Test
	public final void testGetOrganismCode() {
		String result;
		try {
			result = CmonkeyServerImpl.getOrganismName(series);
			assertEquals("Halobacterium sp. NRC-1", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public final void testGetOrganismCodeWrongData() {
		ExpressionDataSeries testCollection = new ExpressionDataSeries();
		ExpressionDataSample set1 = new ExpressionDataSample();
		ExpressionDataSample set2 = new ExpressionDataSample();
		// points for set 1
		List<ExpressionDataPoint> points1 = new ArrayList<ExpressionDataPoint>();
		ExpressionDataPoint point1 = new ExpressionDataPoint();
		point1.setGeneId("VNG1951G");
		point1.setExpressionValue(0.026D);
		ExpressionDataPoint point2 = new ExpressionDataPoint();
		point2.setGeneId("VNG1659G");
		point2.setExpressionValue(-0.082D);
		ExpressionDataPoint point3 = new ExpressionDataPoint();
		point3.setGeneId("VNG1282G");
		point3.setExpressionValue(0.152D);
		points1.add(point1);
		points1.add(point2);
		points1.add(point3);
		set1.setPoints(points1);
		set1.setId("condition1");
		// points for set2
		List<ExpressionDataPoint> points2 = new ArrayList<ExpressionDataPoint>();
		ExpressionDataPoint point4 = new ExpressionDataPoint();
		point4.setGeneId("VNG1951G");
		point4.setExpressionValue(-0.002D);
		ExpressionDataPoint point5 = new ExpressionDataPoint();
		point5.setGeneId("VNG1659G");
		point5.setExpressionValue(-0.059D);
		ExpressionDataPoint point6 = new ExpressionDataPoint();
		point6.setGeneId("DVU0745");
		point6.setExpressionValue(0.153D);
		points2.add(point4);
		points2.add(point5);
		points2.add(point6);
		set2.setPoints(points2);
		set2.setId("condition2");
		List<ExpressionDataSample> sets = new ArrayList<ExpressionDataSample>();
		sets.add(set1);
		sets.add(set2);
		testCollection.setSamples(sets);
		String result = null;
		try {
			result = CmonkeyServerImpl.getOrganismName(testCollection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertNull(result);
			e.printStackTrace();
		}
	}

	@Test
	public final void testGetKeggCode() {
		String organismName = "Halobacterium sp. NRC-1";
		String result = CmonkeyServerImpl.getKeggCode(organismName);
		assertEquals("hal", result);
	}

	@Test
	public final void testReadCollectionFromFile() {
		String testFile = "test/halo_ratios5.tsv";
		ExpressionDataSeries testCollection = readCollectionFromFile(testFile);
		assertEquals(5, testCollection.getSamples().size());
		assertEquals(2400, testCollection.getSamples().get(0)
				.getPoints().size());
		assertEquals("VNG0245G", testCollection.getSamples().get(0)
				.getPoints().get(0).getGeneId());
	}

	@Test
	public final void testRunCmonkey() throws IOException, InterruptedException {
		runCmonkey();
		fail("No assertions");
	}
	
	@Test
	public void testWsRead() throws Exception {
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		GetObjectParams params = new GetObjectParams().withType("ExpressionDataCollection").withId(testSeriesId).withWorkspace(workspaceName).withAuth(token.toString());
		GetObjectOutput output = CmonkeyServerImpl.wsClient(token.toString()).getObject(params);
		ExpressionDataSeries result = us.kbase.common.service.UObject.transformObjectToObject(output.getData(), ExpressionDataSeries.class);
		assertEquals(series.getId(),result.getId());
	}


	private ExpressionDataSeries readCollectionFromFile(String fileName) {
		ExpressionDataSeries collection = new ExpressionDataSeries();
		collection.setId("testcollection");
		List<ExpressionDataSample> dataSets = new ArrayList<ExpressionDataSample>();
		List<String> conditions = new ArrayList<String>();
		List<HashMap<String, Double>> data = new ArrayList<HashMap<String, Double>>();
		Integer conditionsNumber = 0;
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
						// System.out.println(field);
						conditionsNumber++;
					}
					for (Integer i = 0; i < conditionsNumber; i++) {
						HashMap<String, Double> series = new HashMap<String, Double>();
						data.add(series);
					}

				} else {
					String[] fields = line.split("\t");
					Integer j = 0;
					while (j < conditionsNumber) {
						data.get(j).put(fields[0],
								Double.valueOf(fields[j + 1]));
						// System.out.println(fields[0]+" "+fields[j+1]);
						j++;
					}
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}

		// System.out.println(conditionsNumber.toString());
		for (Integer i = 0; i < conditionsNumber; i++) {
			ExpressionDataSample dataSet = new ExpressionDataSample();
			dataSet.setId(conditions.get(i));
			// System.out.println("DataSetId = "+dataSet.getExpressionDataSetId());
			List<ExpressionDataPoint> pointsList = new ArrayList<ExpressionDataPoint>();
			for (Map.Entry<String, Double> entry : data.get(i).entrySet()) {
				ExpressionDataPoint dataPoint = new ExpressionDataPoint();
				dataPoint.setGeneId(entry.getKey());
				dataPoint.setExpressionValue(entry.getValue());
				// System.out.println("\t\tDataPoint: gene = " + entry.getKey()
				// + "expression = "+entry.getValue());
				pointsList.add(dataPoint);
			}
			dataSet.setPoints(pointsList);
			dataSets.add(dataSet);
		}
		collection.setSamples(dataSets);
		return collection;
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
		System.out.println("\torganism = "+runResult.getOrganism());
		System.out.println("\titerations = "+runResult.getIterationsNumber());
		System.out.println("\tlast iteration = "+runResult.getLastIteration());
		System.out.println("\trows = "+runResult.getRowsNumber());
		System.out.println("\tcolumns = "+runResult.getColumnsNumber());
		System.out.println("\tclusters = "+runResult.getClustersNumber()+"\n");

		System.out.println("NETWORK\n");
		System.out.println("\tNetwork ID = "+runResult.getNetwork().getId()+"\n");
		for(CmonkeyCluster cluster:runResult.getNetwork().getClusters()){
			System.out.println("\tCLUSTER:");
			System.out.println("\t\tCluster ID = "+cluster.getId());
			System.out.println("\t\tNumber of genes = "+cluster.getGeneIds().size());
			System.out.println("\t\tNumber of conditions = "+cluster.getDatasetIds().size());
			System.out.println("\t\tResidual = "+cluster.getResidual());
			System.out.println("\t\tGENES:");
			for (String gene:cluster.getGeneIds()){
				System.out.println("\t\t\t"+gene);
			}
			System.out.println("\t\tCONDITIONS:");
			for (String condition:cluster.getDatasetIds()){
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
					System.out.println("\t\t\t\t\t"+hit.getSequenceId()+"\t"+hit.getHitStart()+"\t"+hit.getHitEnd()+"\t"+
							hit.getStrand()+"\t"+hit.getHitPvalue());
				}
			}
			System.out.println();
		}
	}
	@Test
	public void testWsWriteRead() throws Exception {
//		WSRegisterType registerType = new WSRegisterType("MastHit");
//		WSRegisterType registerType2 = new WSRegisterType("CmonkeyRunResult");
		String id = "QuickTestExpressionDataSeries";
		String testFile = "/home/kbase/cmonkey-test/mini-halo_ratios5.tsv";
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();

		ExpressionDataSeries testCollection = readCollectionFromFile(testFile);
	    testCollection.setId(id);
	    CmonkeyServerImpl.saveObjectToWorkspace(UObject.transformObjectToObject(testCollection, UObject.class), testCollection.getClass().getSimpleName(), workspaceName, testCollection.getId(), token.toString());
		
		GetObjectParams params = new GetObjectParams().withType("ExpressionDataSeries").withId(id).withWorkspace(workspaceName).withAuth(token.toString());
		GetObjectOutput output = CmonkeyServerImpl.wsClient(token.toString()).getObject(params);
		ExpressionDataSeries result = UObject.transformObjectToObject(output.getData(), ExpressionDataSeries.class);
		assertEquals(testCollection.getId(),result.getId());		
	}

	@Test
	public final void testGetKbaseId() throws Exception {
		String id = CmonkeyServerImpl.getKbaseId("CmonkeyRunResult");
		System.out.println(id);
		assertNotNull(id);
		
	}
	
	@Test
	public final void testCmonkeyJsonExport() throws Exception {
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		String id = "TestCmonkeyRunResult";
		GetObjectParams objectParams = new GetObjectParams().withType("CmonkeyRunResult").withId(id).withWorkspace(workspaceName).withAuth(token.toString());
		GetObjectOutput output = CmonkeyServerImpl.wsClient(token.toString()).getObject(objectParams);
		CmonkeyRunResult result = UObject.transformObjectToObject(output.getData(), CmonkeyRunResult.class);

		String resultJson = "[";
		
		for (CmonkeyCluster cluster: result.getNetwork().getClusters()){
			resultJson += "{";
			resultJson += "\"nrows\": "+cluster.getGeneIds().size()+",";
			resultJson += "\"ncols\": "+cluster.getDatasetIds().size()+",";
			resultJson += "\"rows\": [";
			for (String geneId : cluster.getGeneIds()){
				resultJson += "\""+geneId+"\",";
			}
			resultJson += "]'";
			resultJson += "\"cols\": [";
			for (String condition : cluster.getDatasetIds()){
				resultJson += "\""+condition+"\",";
			}
			resultJson += "]'";
			resultJson += "\"k\": "+cluster.getId()+",";
			resultJson += "\"resid\": "+cluster.getResidual()+"},";
		}
		resultJson = resultJson.substring(0, resultJson.length() - 1);
		resultJson += "]";
		
		System.out.println(resultJson);
		assertNotNull(result);
		
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

}

