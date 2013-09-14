package us.kbase.kbasecmonkey;

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

import us.kbase.UObject;
import us.kbase.util.WSRegisterType;
import us.kbase.util.WSUtil;
import us.kbase.workspaceservice.GetObjectOutput;
import us.kbase.workspaceservice.GetObjectParams;

public class KBaseCmonkeyServerImpTest {
	private final String TEST_DATABASE_PATH = "test/cmonkey_run_test.db";
	private ExpressionDataCollection collection = new ExpressionDataCollection();

	@Before
	public void setUp() throws Exception {
		collection.setExpressionDataCollectionId("testExpressionCollection");
		ExpressionDataSet set1 = new ExpressionDataSet();
		ExpressionDataSet set2 = new ExpressionDataSet();
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
		set1.setExpressionDataPoints(points1);
		set1.setExpressionDataSetId("condition1");
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
		set2.setExpressionDataPoints(points2);
		set2.setExpressionDataSetId("condition2");
		List<ExpressionDataSet> sets = new ArrayList<ExpressionDataSet>();
		sets.add(set1);
		sets.add(set2);
		collection.setExpressionDataSets(sets);
		// String result = KbasecmonkeyServerImp.getInputTable(collection);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testBuildCmonkeyNetwork() throws Exception {
		String testFile = "test/halo_ratios5.tsv";
		ExpressionDataCollection testCollection = readCollectionFromFile(testFile);
		System.out.println(testCollection.getExpressionDataCollectionId());
		CmonkeyRun result = KbasecmonkeyServerImp
				.buildCmonkeyNetwork(testCollection);
		showCmonkeyRun(result);
		assertEquals(Integer.valueOf("43"), result.getCmonkeyRunNumClusters());
	}

	@Test
	public final void testBuildCmonkeyNetworkFromWs() throws Exception {
		String collectionId = "HalobacteriumExpressionCollection";
		CmonkeyRun result = KbasecmonkeyServerImp
				.buildCmonkeyNetworkFromWS(collectionId);
		assertEquals(Integer.valueOf("43"), result.getCmonkeyRunNumClusters());
	}
	
	
	@Test
	public final void testParseCmonkeySql() {
		CmonkeyRun cmonkeyRun = new CmonkeyRun();
//		String database = "/media/sf_Shared/cmonkey-python-master/0_out/cmonkey_run.db";
		KbasecmonkeyServerImp.parseCmonkeySql(TEST_DATABASE_PATH, cmonkeyRun);
//		KbasecmonkeyServerImp.parseCmonkeySql(database, cmonkeyRun);
		showCmonkeyRun(cmonkeyRun);
		assertNotNull(cmonkeyRun);
		assertEquals(Integer.valueOf("43"), cmonkeyRun.getCmonkeyRunNumClusters());
		assertEquals(2, cmonkeyRun.getCmonkeyNetwork().getCmonkeyClusters().get(0).getClusterMotifs().size());

	}

	@Test
	public final void testGetInputTable() {
		String result = KbasecmonkeyServerImp.getInputTable(collection);
		System.out.println(result);
		assertEquals(
				"GENE\tcondition1\tcondition2\nVNG1659G\t-0.082\t-0.059\nVNG1282G\t0.152\t0.153\nVNG1951G\t0.026\t-0.002\n",
				result);
	}

	@Test
	public final void testWriteInputFile() {
		String testFile = "test/halo_ratios5.tsv";
		ExpressionDataCollection testCollection = readCollectionFromFile(testFile);
		String result = KbasecmonkeyServerImp.getInputTable(testCollection);
		KbasecmonkeyServerImp.writeInputFile("test/halo_testinput.txt", result);
		assertEquals(5, testCollection.getExpressionDataSets().size());
		// TODO: check file content
	}

	@Test
	public final void testGetOrganismCode() {
		String result;
		try {
			result = KbasecmonkeyServerImp.getOrganismCode(collection);
			assertEquals("Halobacterium sp. NRC-1", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public final void testGetOrganismCodeWrongData() {
		ExpressionDataCollection testCollection = new ExpressionDataCollection();
		ExpressionDataSet set1 = new ExpressionDataSet();
		ExpressionDataSet set2 = new ExpressionDataSet();
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
		set1.setExpressionDataPoints(points1);
		set1.setExpressionDataSetId("condition1");
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
		set2.setExpressionDataPoints(points2);
		set2.setExpressionDataSetId("condition2");
		List<ExpressionDataSet> sets = new ArrayList<ExpressionDataSet>();
		sets.add(set1);
		sets.add(set2);
		testCollection.setExpressionDataSets(sets);
		String result = null;
		try {
			result = KbasecmonkeyServerImp.getOrganismCode(testCollection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertNull(result);
			e.printStackTrace();
		}
	}

	@Test
	public final void testGetKeggCode() {
		String organismName = "Halobacterium sp. NRC-1";
		String result = KbasecmonkeyServerImp.getKeggCode(organismName);
		assertEquals("hal", result);
	}

	@Test
	public final void testReadCollectionFromFile() {
		String testFile = "test/halo_ratios5.tsv";
		ExpressionDataCollection testCollection = readCollectionFromFile(testFile);
		assertEquals(5, testCollection.getExpressionDataSets().size());
		assertEquals(2400, testCollection.getExpressionDataSets().get(0)
				.getExpressionDataPoints().size());
		assertEquals("VNG0245G", testCollection.getExpressionDataSets().get(0)
				.getExpressionDataPoints().get(0).getGeneId());
	}

	@Test
	public final void testRunCmonkey() throws IOException, InterruptedException {
		runCmonkey();
		fail("No assertions");
	}
	
	@Test
	public void testWsRead() throws Exception {
		String id = "testExpressionCollection";
//		WSUtil.saveObject(objectId, object, false);
		GetObjectParams params = new GetObjectParams().withType("ExpressionDataCollection").withId(id).withWorkspace(WSUtil.workspaceName).withAuth(WSUtil.authToken().toString());
		GetObjectOutput output = WSUtil.wsClient().getObject(params);
		ExpressionDataCollection result = UObject.transform(output.getData(), ExpressionDataCollection.class);
		assertEquals(collection.getExpressionDataCollectionId(),result.getExpressionDataCollectionId());
	}


	private ExpressionDataCollection readCollectionFromFile(String fileName) {
		ExpressionDataCollection collection = new ExpressionDataCollection();
		collection.setExpressionDataCollectionId("testcollection");
		List<ExpressionDataSet> dataSets = new ArrayList<ExpressionDataSet>();
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
			ExpressionDataSet dataSet = new ExpressionDataSet();
			dataSet.setExpressionDataSetId(conditions.get(i));
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
			dataSet.setExpressionDataPoints(pointsList);
			dataSets.add(dataSet);
		}
		collection.setExpressionDataSets(dataSets);
		return collection;
	}

	private static void runCmonkey() throws IOException, InterruptedException {
		String commandLine = "/media/sf_Shared/cmonkey-python-master/run_cmonkey.sh --organism hal --ratios 0_input.txt --out 0_out";
		File cmonkeyDir = new File("/media/sf_Shared/cmonkey-python-master/");
		try {
			Process p = Runtime.getRuntime()
					.exec(commandLine, null, cmonkeyDir);
			
			StreamGobbler errorGobbler = new 
	                StreamGobbler(p.getErrorStream(), "ERROR");            
	            
	            // any output?
	            StreamGobbler outputGobbler = new 
	                StreamGobbler(p.getInputStream(), "OUTPUT");
	                
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
	
	public static void showCmonkeyRun (CmonkeyRun run){
		DecimalFormat df = new DecimalFormat("0.000");
		System.out.println("CMONKEY RUN PARAMETERS:");
		System.out.println("\tstart time = "+run.getCmonkeyRunStartTime());
		System.out.println("\tfinish time = "+run.getCmonkeyRunFinishTime());
		System.out.println("\torganism = "+run.getCmonkeyRunOrganism());
		System.out.println("\titerations = "+run.getCmonkeyRunNumIterations());
		System.out.println("\tlast iteration = "+run.getCmonkeyRunLastIteration());
		System.out.println("\trows = "+run.getCmonkeyRunNumRows());
		System.out.println("\tcolumns = "+run.getCmonkeyRunNumColumns());
		System.out.println("\tclusters = "+run.getCmonkeyRunNumClusters()+"\n");

		System.out.println("NETWORK\n");
		System.out.println("\tNetwork ID = "+run.getCmonkeyNetwork().getCmonkeyNetworkId()+"\n");
		for(CmonkeyCluster cluster:run.getCmonkeyNetwork().getCmonkeyClusters()){
			System.out.println("\tCLUSTER:");
			System.out.println("\t\tCluster ID = "+cluster.getCmonkeyClusterId());
			System.out.println("\t\tNumber of genes = "+cluster.getNumGenes());
			System.out.println("\t\tNumber of conditions = "+cluster.getNumConditions());
			System.out.println("\t\tResidual = "+cluster.getClusterResidual());
			System.out.println("\t\tGENES:");
			for (String gene:cluster.getClusterGeneIds()){
				System.out.println("\t\t\t"+gene);
			}
			System.out.println("\t\tCONDITIONS:");
			for (String condition:cluster.getClusterExpressionDataSetIds()){
				System.out.println("\t\t\t"+condition);
			}
			System.out.println("\t\tMOTIFS:");
			for (MotifCmonkey motif: cluster.getClusterMotifs()){
				System.out.println("\t\t\tMOTIF "+motif.getCmonkeyMotifId());
				System.out.println("\t\t\t\tSeqtype = "+motif.getSeqType());
				System.out.println("\t\t\t\tMotif num = "+motif.getMotifNumber());
				System.out.println("\t\t\t\tevalue = "+motif.getEvalue());
				System.out.println("\t\t\t\tPSSM:");
				for (PssmRow row:motif.getPssm()){
					System.out.println("\t\t\t\t\t"+row.getRowNumber()+"\t"+df.format(row.getAWeight())+"\t"+
							df.format(row.getCWeight())+"\t"+df.format(row.getGWeight())+"\t"+df.format(row.getTWeight()));
				}
				System.out.println("\t\t\t\tMOTIF HITS:");
				for (HitMast hit:motif.getHits()){
					System.out.println("\t\t\t\t\t"+hit.getSequenceName()+"\t"+hit.getHitStart()+"\t"+hit.getHitEnd()+"\t"+
							hit.getStrand()+"\t"+hit.getHitPvalue());
				}
			}
			System.out.println();
		}
	}
	@Test
	public void testWsWriteRead() throws Exception {
		String id = "HalobacteriumExpressionCollection";
		String testFile = "test/halo_ratios5.tsv";
		ExpressionDataCollection testCollection = readCollectionFromFile(testFile);
	    testCollection.setExpressionDataCollectionId(id);
		WSUtil.saveObject(testCollection.getExpressionDataCollectionId(), testCollection, false);
		
		GetObjectParams params = new GetObjectParams().withType("ExpressionDataCollection").withId(id).withWorkspace(WSUtil.workspaceName).withAuth(WSUtil.authToken().toString());
		GetObjectOutput output = WSUtil.wsClient().getObject(params);
		ExpressionDataCollection result = UObject.transform(output.getData(), ExpressionDataCollection.class);
		assertEquals(testCollection.getExpressionDataCollectionId(),result.getExpressionDataCollectionId());		
	}


}

