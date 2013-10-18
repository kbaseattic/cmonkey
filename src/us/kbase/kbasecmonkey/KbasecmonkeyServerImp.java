package us.kbase.kbasecmonkey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import us.kbase.UObject;
import us.kbase.util.WSUtil;
import us.kbase.workspaceservice.GetObjectOutput;
import us.kbase.workspaceservice.GetObjectParams;


public class KbasecmonkeyServerImp {
	private static Integer jobId = 0;
	private static final String CMONKEY_PATH = "/media/sf_Shared/cmonkey-python-master/";


	public static CmonkeyRun buildCmonkeyNetwork(ExpressionDataCollection expressionDataCollection) throws Exception{
		CmonkeyRun cmonkeyRun = new CmonkeyRun();
		String jobName = jobId.toString();
		jobId++;
		//prepare input
			//convert input data
		String inputTable = getInputTable(expressionDataCollection);
			//check list of genes
		String organismCode = getOrganismCode(expressionDataCollection);
			//save input file
		String inputFile = jobName+"_input.txt";
		writeInputFile (inputFile, inputTable);
			//generate command line
		String outputDirectory = jobName+"_out";
		String cacheDirectory = jobName+"_cache";
		String commandLine = "./run_cmonkey.sh --organism "+ organismCode +" --ratios "+inputFile+" --out "+outputDirectory+" --cachedir "+cacheDirectory;
		System.out.println(commandLine);
		//run
		executeCommand (commandLine, CMONKEY_PATH +jobName+"_log.txt");
		//parse results
		String sqlFile=CMONKEY_PATH+outputDirectory+"/cmonkey_run.db";
		System.out.println(sqlFile);
		parseCmonkeySql(sqlFile, cmonkeyRun);
		//save results to workspace
		//clean up
		return cmonkeyRun;
	}
	
	public static CmonkeyRun buildCmonkeyNetworkFromWS(String expressionDataCollectionId) throws Exception {
		CmonkeyRun returnVal = null;

		GetObjectParams params = new GetObjectParams().withType("ExpressionDataCollection").withId(expressionDataCollectionId).withWorkspace(WSUtil.workspaceName).withAuth(WSUtil.authToken().toString());   
		GetObjectOutput output = WSUtil.wsClient().getObject(params);
		ExpressionDataCollection expressionDataCollection = UObject.transform(output.getData(), ExpressionDataCollection.class);
		returnVal = buildCmonkeyNetwork(expressionDataCollection);
		return returnVal;		
	}

	
	protected static String getOrganismCode (ExpressionDataCollection collection) throws Exception {
		String organismName = null;
		Microbesonline microbesonline = new Microbesonline();
		List<String> geneNames = new ArrayList<String>();
		for(ExpressionDataSet set:collection.getExpressionDataSets()){
			for(ExpressionDataPoint point:set.getExpressionDataPoints()){
				geneNames.add(point.getGeneId());
			}
		}
		geneNames = new ArrayList<String>(new HashSet<String>(geneNames));
		try {
			organismName = microbesonline.getGenomeForGene(geneNames.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(geneNames.get(0));
			e.printStackTrace();
		}
		System.out.println(organismName);
		for (String geneName:geneNames){
			if ((microbesonline.getGenomeForGene(geneName) != null) && (!microbesonline.getGenomeForGene(geneName).equals(organismName))){
				throw new Exception();
			}
		}
		String result = getKeggCode(organismName);
		return result;
	}

	protected static String getKeggCode(String organismName) {
		String result = null;
		
		try {
			String line = null;
			BufferedReader br = new BufferedReader(new FileReader(CMONKEY_PATH+"testdata/KEGG_taxonomy"));
			while ((line = br.readLine()) != null) {
				if ((line.equals("")) || (line.matches("#.*"))){
					//do nothing
				} else{
					String[] fields = line.split("\t");
					if (fields[3].equals(organismName)){
						result = fields[1];
					}
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return result;
	}

	protected static String getInputTable(ExpressionDataCollection collection){
		String result = "GENE";
		List<HashMap<String, Double>> dataCollection = new ArrayList<HashMap<String, Double>>();
		//make list of conditions
		for(ExpressionDataSet set:collection.getExpressionDataSets()){
			result+="\t"+set.getExpressionDataSetId();
			HashMap<String, Double> dataSet= new HashMap<String, Double>();
			for (ExpressionDataPoint point:set.getExpressionDataPoints()){
				dataSet.put(point.getGeneId(), point.getExpressionValue());
			}
			dataCollection.add(dataSet);
		}
		//make list of genes
		List<String> geneNames=new ArrayList<String>();
		for(ExpressionDataSet set:collection.getExpressionDataSets()){
			for(ExpressionDataPoint point:set.getExpressionDataPoints()){
				geneNames.add(point.getGeneId());
			}
		}
		List<String> uniqueGeneNames = new ArrayList<String>(new HashSet<String>(geneNames));
		for(String geneName:uniqueGeneNames){
			result+="\n"+geneName;
			DecimalFormat df = new DecimalFormat("0.000");
			for (HashMap<String, Double> dataSetHashmap: dataCollection){
				if (dataSetHashmap.containsKey(geneName)){
					if (dataSetHashmap.get(geneName).toString().matches("-.*")){
						result+="\t"+ df.format(dataSetHashmap.get(geneName));
					} else {
						result+="\t "+ df.format(dataSetHashmap.get(geneName));
					}
				} else {
					result+="\tNA";
				}
			}			
		}
		result+="\n";
		return result;
	}

	protected static void writeInputFile (String inputFileName, String input){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(inputFileName));
			writer.write(input);
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}

	}
	
	protected static void executeCommand(String commandLine, String logFile) throws InterruptedException {
		try {
			Process p = Runtime.getRuntime()
					.exec(commandLine, null, new File(CMONKEY_PATH));
			
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
	
	protected static void parseCmonkeySql(String sqlFile, CmonkeyRun cmonkeyRun){
		CmonkeySqlite database = new CmonkeySqlite(sqlFile);
		database.buildCmonkeyRun(cmonkeyRun);
		database.disconnect();
	}
	
}
