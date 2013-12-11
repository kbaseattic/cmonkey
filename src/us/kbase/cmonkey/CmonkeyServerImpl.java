package us.kbase.cmonkey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import us.kbase.auth.AuthException;
import us.kbase.auth.AuthToken;
import us.kbase.auth.TokenFormatException;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.idserverapi.IDServerAPIClient;
import us.kbase.userandjobstate.InitProgress;
import us.kbase.userandjobstate.Results;
import us.kbase.userandjobstate.UserAndJobStateClient;
import us.kbase.workspaceservice.GetObjectOutput;
import us.kbase.workspaceservice.GetObjectParams;
import us.kbase.workspaceservice.ObjectData;
import us.kbase.workspaceservice.SaveObjectParams;
import us.kbase.workspaceservice.WorkspaceServiceClient;


public class CmonkeyServerImpl {
//	private static Integer tempFileId = 0;
	private static final String JOB_PATH = "";//"/var/tmp/cmonkey/";
//	private static final String CMONKEY_COMMAND = "cmonkey-python";
	private static final String CMONKEY_DIR = "/kb/runtime/cmonkey-python/";
	private static final String CMONKEY_COMMAND = "/kb/runtime/cmonkey-python/cmonkey.py";	
	private static final String DATA_PATH = "/etc/cmonkey-python/KEGG_taxonomy";
	private static final String ID_SERVICE_URL = "http://kbase.us/services/idserver";
	private static final String WS_SERVICE_URL = "http://kbase.us/services/workspace";
	private static final String JOB_SERVICE_URL = "http://140.221.84.180:7083";
	private static WorkspaceServiceClient _wsClient = null;
	private static UserAndJobStateClient _jobClient = null;
	private static Date date = new Date();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	protected static WorkspaceServiceClient wsClient(String token) throws TokenFormatException, UnauthorizedException, IOException{
		if(_wsClient == null)
		{
			URL workspaceClientUrl = new URL (WS_SERVICE_URL);
			AuthToken authToken = new AuthToken(token);
			_wsClient = new WorkspaceServiceClient(workspaceClientUrl, authToken);
			_wsClient.setAuthAllowedForHttp(true);
		}
		return _wsClient;
	} 
	
	protected static UserAndJobStateClient jobClient(String token) throws UnauthorizedException, IOException, AuthException {
		if(_jobClient == null)
		{
			URL jobServiceUrl = new URL (JOB_SERVICE_URL);
			AuthToken authToken = new AuthToken(token);
			_jobClient = new UserAndJobStateClient (jobServiceUrl, authToken);
			_jobClient.setAuthAllowedForHttp(true);
		}
		return _jobClient;
	} 

	protected static void cleanUpOnStart () {
		try {
			Runtime.getRuntime().exec("rm -r "+JOB_PATH+"*");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static CmonkeyRunResult buildCmonkeyNetwork(ExpressionDataSeries expressionDataSeries, CmonkeyRunParameters params, String jobId, String token) throws Exception{
		CmonkeyRunResult cmonkeyRunResult = new CmonkeyRunResult();
		cmonkeyRunResult.setId(getKbaseId("CmonkeyRunResult"));
		String jobPath = JOB_PATH + jobId + "/";
//		tempFileId++;
		Runtime.getRuntime().exec("mkdir " + jobPath);

		//prepare input

		String inputTable = getInputTable(expressionDataSeries);
		FileWriter writer = new FileWriter(jobPath+"serveroutput.txt");
		writer.write(inputTable);
		writer.flush();
		
		//check list of genes
		String organismName = getOrganismName(expressionDataSeries);
		writer.write("Organism name = " + organismName + "\n");
		writer.flush();
		String organismCode = getKeggCode(organismName);
		writer.write("Organism code = " + organismCode + "\n");
		writer.flush();

			//save input file
		writeInputFile (jobPath+"input.txt", inputTable);
			//generate command line
		String commandLine = generateCmonkeyCommandLine (jobPath, params, organismCode);				
		writer.write(commandLine + "\n");
		writer.flush();
		//run
		if (jobId != null) updateJobProgress (jobId, "Input prepared. Starting cMonkey program...", token);
		Integer exitVal = executeCommand (commandLine, jobPath, jobId, token);
		if (exitVal != null) { 
			writer.write("ExitValue: " + exitVal.toString() + "\n");
			writer.flush();
		} else {
			writer.write("ExitValue: null\n");
			writer.flush();
		}
		
		//parse results

		if (jobId != null) updateJobProgress (jobId, "cMonkey finished. Processing output...", token);

		String sqlFile=jobPath+"out/cmonkey_run.db";
		writer.write(sqlFile + "\n");
		writer.flush();
		parseCmonkeySql(sqlFile, cmonkeyRunResult);
		String resultId = getKbaseId("CmonkeyRunResult");
		writer.write(resultId + "\n");
		cmonkeyRunResult.setId(resultId);

		writer.close();
		//clean up
		Runtime.getRuntime().exec("rm -r " + jobPath);
		Runtime.getRuntime().exec("rm " + JOB_PATH + "cmonkey-checkpoint*");

		return cmonkeyRunResult;
	}
	
	public static void buildCmonkeyNetworkJobFromWs (String wsId, String seriesId, CmonkeyRunParameters params, String jobId, String token) throws Exception {
		String desc = "Cmonkey service job. Method: buildCmonkeyNetworkJobFromWs. Input: " + seriesId + ". Workspace: " + wsId + ".";
		if (jobId != null) startJob (jobId, desc, 23L, token.toString());

		GetObjectParams objectParams = new GetObjectParams().withType("ExpressionDataSeries").withId(seriesId).withWorkspace(wsId).withAuth(token.toString());
		GetObjectOutput output = wsClient(token.toString()).getObject(objectParams);
		ExpressionDataSeries input = UObject.transformObjectToObject(output.getData(), ExpressionDataSeries.class);

		CmonkeyRunResult runResult = buildCmonkeyNetwork(input, params, jobId, token);
		
		saveObjectToWorkspace (UObject.transformObjectToObject(runResult, UObject.class), runResult.getClass().getSimpleName(), wsId, runResult.getId(), token.toString());
		if (jobId != null) finishJob (jobId, wsId, runResult.getId(), token.toString());
		
	}

	protected static String generateCmonkeyCommandLine (String jobPath, CmonkeyRunParameters params, String organismCode) {

		String outputDirectory = jobPath+"out";
		String cacheDirectory = jobPath+"cache";
		String inputFile = jobPath+"input.txt";
		
		String commandLine = CMONKEY_COMMAND + " --organism "+ organismCode +" --ratios "+inputFile+" --out "+outputDirectory+" --cachedir "+cacheDirectory;// + " --config " + CONFIG_PATH;
		//Set options
		if (params.getNoMotifs() == 1L) {
			commandLine += " --nomotifs";
		}
		if (params.getNoNetworks() == 1L) {
			commandLine += " --nonetworks";
		}
		if (params.getNoOperons() == 1L) {
			commandLine += " --nooperons";
		}
		if (params.getNoString() == 1L) {
			commandLine += " --nostring";
		}
				
		return commandLine;
	}
	
	protected static void startJob (String jobId, String desc, Long tasks, String token) {
		
		String status = "cmonkey service job started. Preparing input...";
		InitProgress initProgress = new InitProgress();
		initProgress.setPtype("task");
		initProgress.setMax(tasks);
		date.setTime(date.getTime()+108000000L);
	
		try {
			//System.out.println(dateFormat.format(date));
			jobClient(token).startJob(jobId, token, status, desc, initProgress, dateFormat.format(date));
		} catch (JsonClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static void updateJobProgress (String jobId, String status, String token){
		try {
			date.setTime(date.getTime()+1000000L);
			jobClient(token).updateJobProgress(jobId, token, status, 1L, dateFormat.format(date));
		} catch (JsonClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	protected static void finishJob (String jobId, String wsId, String objectId, String token){
		try {
			String status = "Finished";
			String error = null;
			
			Results res = new Results();
			List<String> workspaceIds = new ArrayList<String>();
			workspaceIds.add(wsId + "/" + objectId);
			res.setWorkspaceids(workspaceIds);
			jobClient(token).completeJob(jobId, token, status, error, res); 
		} catch (JsonClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected static String getKbaseId(String entityType) throws Exception {
		String returnVal = null;
		URL idServerUrl = new URL(ID_SERVICE_URL);
		IDServerAPIClient idClient = new IDServerAPIClient(idServerUrl);
		
		if (entityType.equals("CmonkeyRunResult")) {
			returnVal = "kb|cmonkeyrunresult." + idClient.allocateIdRange("cmonkeyrunresult", 1L).toString();
		} else if (entityType.equals("CmonkeyNetwork")) {
			returnVal = "kb|cmonkeynetwork." + idClient.allocateIdRange("cmonkeynetwork", 1L).toString();
		} else if (entityType.equals("CmonkeyCluster")) {
			returnVal = "kb|cmonkeycluster." + idClient.allocateIdRange("cmonkeycluster", 1L).toString();
		} else if (entityType.equals("CmonkeyMotif")) {
			returnVal = "kb|cmonkeymotif." + idClient.allocateIdRange("cmonkeymotif", 1L).toString();
		} else if (entityType.equals("MastHit")) {
			returnVal = "kb|masthit." + idClient.allocateIdRange("masthit", 1L).toString();
		} else if (entityType.equals("ExpressionDataSeries")) {
			returnVal = "kb|expressiondataseries." + idClient.allocateIdRange("expressiondataseries", 1L).toString();
		} else {
			System.out.println("ID requested for unknown type " + entityType);
		}
		return returnVal;
	}


	protected static String getOrganismName (ExpressionDataSeries series) throws Exception {
		String organismName = null;
		Microbesonline microbesonline = new Microbesonline();
		List<String> geneNames = new ArrayList<String>();
		for(ExpressionDataSample set:series.getSamples()){
			for(ExpressionDataPoint point:set.getPoints()){
				geneNames.add(point.getGeneId());
			}
		}
		geneNames = new ArrayList<String>(new HashSet<String>(geneNames));
		for (int i = 0; i < geneNames.size(); i++){
			try {
				organismName = microbesonline.getGenomeForGene(geneNames.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(geneNames.get(i));
				e.printStackTrace();
			}
			if (organismName != null) {
				break;
			}
		}

		if (organismName == null) {
			throw new Exception("Organism name cannot be identified");
		}

		for (String geneName:geneNames){
			if ((microbesonline.getGenomeForGene(geneName) != null) && (!microbesonline.getGenomeForGene(geneName).equals(organismName))){
				throw new Exception("Genes in input data series belong to different organisms");
			}
		}
		return organismName;
	}

	protected static String getKeggCode(String organismName) {
		String result = null;
		
		try {
			String line = null;
			BufferedReader br = new BufferedReader(new FileReader(DATA_PATH));
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

	protected static String getInputTable(ExpressionDataSeries series){
		String result = "GENE";
		List<HashMap<String, Double>> dataCollection = new ArrayList<HashMap<String, Double>>();
		//make list of conditions
		for(ExpressionDataSample sample:series.getSamples()){
			result+="\t"+sample.getId();
			HashMap<String, Double> dataSet= new HashMap<String, Double>();
			for (ExpressionDataPoint point:sample.getPoints()){
				dataSet.put(point.getGeneId(), point.getExpressionValue());
			}
			dataCollection.add(dataSet);
		}
		//make list of genes
		List<String> geneNames=new ArrayList<String>();
		for(ExpressionDataSample sample:series.getSamples()){
			for(ExpressionDataPoint point:sample.getPoints()){
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

	protected static Integer executeCommand(String commandLine, String jobPath) throws InterruptedException {
		Integer exitVal = executeCommand (commandLine, jobPath, null, null);
		return exitVal;
	}

	
	protected static Integer executeCommand(String commandLine, String jobPath, String jobId, String token) throws InterruptedException {
		Integer exitVal = null;
		try {
			Process p = Runtime.getRuntime().exec(commandLine, null, new File(CMONKEY_DIR));
			
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR", jobId, token, jobPath+"errorlog.txt");            
	            
	            // any output?
			StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream(), "OUTPUT", jobId, token, jobPath+"out.txt");
	                
	            // kick them off
			errorGobbler.start();
			outputGobbler.start();
	                                    
	            // any error???
			exitVal = p.waitFor();
			System.out.println("ExitValue: " + exitVal);      
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
		}
		return exitVal;

		
	}
	
	protected static void parseCmonkeySql(String sqlFile, CmonkeyRunResult cmonkeyRunResult) throws Exception{
		CmonkeySqlite database = new CmonkeySqlite(sqlFile);
		database.buildCmonkeyRunResult(cmonkeyRunResult);
		database.disconnect();
	}
	
	protected static void saveObjectToWorkspace (UObject object, String type, String workspaceName, String id, String token) throws Exception {

		ObjectData objectData = UObject.transformObjectToObject(object, ObjectData.class);
		SaveObjectParams params = new SaveObjectParams();
		params.setAuth(token);
		params.setCompressed(0L);
		params.setData(objectData);
		params.setId(id); 
		params.setJson(0L); 
		params.setType(type);
		
		Map<String, String> metadata = new HashMap<String, String>();
		params.setMetadata(metadata);
		
		params.setWorkspace(workspaceName);
		Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> ret = wsClient(token).saveObject(params);
		
		System.out.println("Saving object:");
		System.out.println(ret.getE1());
/*		System.out.println(ret.getE2());
		System.out.println(ret.getE3());
		System.out.println(ret.getE4());
		System.out.println(ret.getE5());
		System.out.println(ret.getE6());
		System.out.println(ret.getE7());
		System.out.println(ret.getE8());
		System.out.println(ret.getE9());
		System.out.println(ret.getE10());
		System.out.println(ret.getE11());
*/
	}

}
