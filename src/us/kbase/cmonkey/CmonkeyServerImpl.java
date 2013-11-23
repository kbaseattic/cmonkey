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
import us.kbase.userandjobstate.InitProgress;
import us.kbase.userandjobstate.Results;
import us.kbase.userandjobstate.UserAndJobStateClient;
import us.kbase.workspaceservice.GetObjectOutput;
import us.kbase.workspaceservice.GetObjectParams;
import us.kbase.workspaceservice.ObjectData;
import us.kbase.workspaceservice.SaveObjectParams;
import us.kbase.workspaceservice.WorkspaceServiceClient;


public class CmonkeyServerImpl {
	private static Integer tempFileId = 0;
	private static final String CMONKEY_PATH = ".";
	//private static final String CMONKEY_PATH = "/media/sf_Shared/cmonkey-python-master/";
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

	public static CmonkeyRunResult buildCmonkeyNetwork(ExpressionDataSeries expressionDataSeries, CmonkeyRunParameters params) throws Exception{
		CmonkeyRunResult cmonkeyRunResult = buildCmonkeyNetwork(expressionDataSeries, params, null, null);
		return cmonkeyRunResult;
	}
	
	public static CmonkeyRunResult buildCmonkeyNetwork(ExpressionDataSeries expressionDataSeries, CmonkeyRunParameters params, String jobId, String token) throws Exception{
		CmonkeyRunResult cmonkeyRunResult = new CmonkeyRunResult();
		String jobName = tempFileId.toString();
		tempFileId++;
		//prepare input
			//convert input data
		String inputTable = getInputTable(expressionDataSeries);
			//check list of genes
		String organismCode = getOrganismCode(expressionDataSeries);
			//save input file
		writeInputFile (jobName+"_input.txt", inputTable);
			//generate command line
		String commandLine = generateCmonkeyCommandLine (jobName, params, organismCode);
				
				
		System.out.println(commandLine);
		//run
		if (jobId != null) updateJobProgress (jobId, "Input prepared. Starting cMonkey program...", token);
		executeCommand (commandLine, CMONKEY_PATH + "/" + jobName+"_log.txt", jobId, token);
		//parse results

		if (jobId != null) updateJobProgress (jobId, "cMonkey finished. Processing output...", token);

		String sqlFile=CMONKEY_PATH + "/" + jobName+"_out"+"/cmonkey_run.db";
		System.out.println(sqlFile);
		parseCmonkeySql(sqlFile, cmonkeyRunResult);

		//clean up
		Runtime.getRuntime().exec("rm " + jobName + "_input.txt");
		Runtime.getRuntime().exec("rm -r " + jobName + "_out");

		return cmonkeyRunResult;
	}
	
	public static String buildCmonkeyNetworkFromWs(String wsId, String seriesId, CmonkeyRunParameters params, AuthToken token) throws Exception {

		//Read data
		GetObjectParams objectParams = new GetObjectParams().withType("ExpressionDataSeries").withId(seriesId).withWorkspace(wsId).withAuth(token.toString());   
		GetObjectOutput output = wsClient(token.toString()).getObject(objectParams);
		ExpressionDataSeries expressionDataCollection = UObject.transformObjectToObject(output.getData(), ExpressionDataSeries.class);

		//Run
		CmonkeyRunResult runResult = buildCmonkeyNetwork(expressionDataCollection, params);		
		String returnVal = runResult.getId();
		
		//Save result
		saveObjectToWorkspace (UObject.transformObjectToObject(runResult, UObject.class), runResult.getClass().getSimpleName(), wsId, returnVal, token.toString());

		return returnVal;		
	}
	
	public static String buildCmonkeyNetworkJobFromWs (String wsId, String seriesId, CmonkeyRunParameters params, AuthToken token) throws Exception {
		String jobId = jobClient(token.toString()).createJob();
		String desc = "Cmonkey service job. Method: buildCmonkeyNetworkJobFromWs. Input: " + seriesId + ". Workspace: " + wsId + ".";
		if (jobId != null) startJob (jobId, desc, 25L, token.toString());

		GetObjectParams objectParams = new GetObjectParams().withType("ExpressionDataSeries").withId(seriesId).withWorkspace(wsId).withAuth(token.toString());
		GetObjectOutput output = wsClient(token.toString()).getObject(objectParams);
		ExpressionDataSeries input = UObject.transformObjectToObject(output.getData(), ExpressionDataSeries.class);

		CmonkeyRunResult runResult = buildCmonkeyNetwork(input, params, jobId, token.toString());
		
		saveObjectToWorkspace (UObject.transformObjectToObject(runResult, UObject.class), runResult.getClass().getSimpleName(), wsId, runResult.getId(), token.toString());
		if (jobId != null) finishJob (jobId, wsId, runResult.getId(), token.toString());
		
		return jobId;
	}

	protected static String generateCmonkeyCommandLine (String jobName, CmonkeyRunParameters params, String organismCode) {

		String outputDirectory = jobName+"_out";
		String cacheDirectory = jobName+"_cache";
		String inputFile = jobName+"_input.txt";
		
		String commandLine = "cmonkey-python --organism "+ organismCode +" --ratios "+inputFile+" --out "+outputDirectory+" --cachedir "+cacheDirectory;
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


	protected static String getOrganismCode (ExpressionDataSeries series) throws Exception {
		String organismName = null;
		Microbesonline microbesonline = new Microbesonline();
		List<String> geneNames = new ArrayList<String>();
		for(ExpressionDataSample set:series.getSamples()){
			for(ExpressionDataPoint point:set.getPoints()){
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
			BufferedReader br = new BufferedReader(new FileReader(CMONKEY_PATH+"/data/KEGG_taxonomy"));
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

	protected static void executeCommand(String commandLine, String logFile) throws InterruptedException {
		executeCommand (commandLine, logFile, null, null);
	}

	
	protected static void executeCommand(String commandLine, String logFile, String jobId, String token) throws InterruptedException {
		try {
			Process p = Runtime.getRuntime().exec(commandLine, null, new File(CMONKEY_PATH));
			
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR", jobId, token);            
	            
	            // any output?
			StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream(), "OUTPUT", jobId, token);
	                
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
