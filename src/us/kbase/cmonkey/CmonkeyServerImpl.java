package us.kbase.cmonkey;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import us.kbase.auth.AuthException;
import us.kbase.auth.AuthToken;
import us.kbase.auth.TokenFormatException;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UObject;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.expressionservices.ExpressionSample;
import us.kbase.expressionservices.ExpressionSeries;
import us.kbase.idserverapi.IDServerAPIClient;
import us.kbase.userandjobstate.InitProgress;
import us.kbase.userandjobstate.Results;
import us.kbase.userandjobstate.UserAndJobStateClient;
import us.kbase.util.GenomeExporter;
import us.kbase.util.NetworkExporter;
import us.kbase.util.WsDeluxeUtil;
import us.kbase.workspace.ObjectData;

public class CmonkeyServerImpl {
	private static boolean awe = CmonkeyServerConfig.DEPLOY_AWE;

	private static final String JOB_PATH = CmonkeyServerConfig.JOB_DIRECTORY;
	private static final String CMONKEY_DIR = CmonkeyServerConfig.CMONKEY_DIRECTORY;
	private static final String CMONKEY_COMMAND = CmonkeyServerConfig.CMONKEY_RUN_PATH;

	private static final String ID_SERVICE_URL = CmonkeyServerConfig.ID_SERVICE_URL;
	private static final String JOB_SERVICE_URL = CmonkeyServerConfig.JOB_SERVICE_URL;
	private static IDServerAPIClient _idClient = null;
	private static UserAndJobStateClient _jobClient = null;

	private static Date date = new Date();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssZ");

	protected static IDServerAPIClient idClient() throws TokenFormatException,
			UnauthorizedException, IOException {
		if (_idClient == null) {
			URL idServerUrl = new URL(ID_SERVICE_URL);
			_idClient = new IDServerAPIClient(idServerUrl);
		}
		return _idClient;
	}

	protected static UserAndJobStateClient jobClient(String token)
			throws UnauthorizedException, IOException, AuthException {
		if (_jobClient == null) {
			URL jobServiceUrl = new URL(JOB_SERVICE_URL);
			AuthToken authToken = new AuthToken(token);
			_jobClient = new UserAndJobStateClient(jobServiceUrl, authToken);
			_jobClient.setAuthAllowedForHttp(true);
		}
		return _jobClient;
	}

	
	public static void buildCmonkeyNetworkJobFromWs(String wsName,
			CmonkeyRunParameters params, String jobId, String token,
			String currentDir) throws UnauthorizedException, IOException, JsonClientException, AuthException, InterruptedException, ClassNotFoundException, SQLException {
		//Let's start!
		String desc = "Cmonkey service job. Method: buildCmonkeyNetworkJobFromWs. Input: "
				+ params.getSeriesRef() + ". Workspace: " + wsName + ".";
		if (jobId != null)
			startJob(jobId, desc, 23L, token.toString());
		//get expression data
		ExpressionSeries series = WsDeluxeUtil.getObjectFromWsByRef(params.getSeriesRef(), token).getData().asClassInstance(ExpressionSeries.class);		
		//create result object
		CmonkeyRunResult cmonkeyRunResult = new CmonkeyRunResult().withId(getKbaseId("CmonkeyRunResult"));
		//create working directory
		String jobPath = createDirs(jobId, currentDir);
		//start log file
		FileWriter writer = new FileWriter(jobPath + "serveroutput.txt");
		writer.write("log file created " + dateFormat.format(date) + "\n");
		writer.flush();
		//prepare cache files
		prepareCacheFiles(jobPath + "cache/", params, token, writer);		
		writer.write("Cache files created in " + jobPath + "cache/\n");
		writer.flush();
		// prepare input file
		createInputTable(jobPath, series.getExpressionSampleIds(), token);
		writer.write("Input file created\n");
		writer.flush();
		// generate command line
		String commandLine = generateCmonkeyCommandLine(jobPath, params);
		writer.write(commandLine + "\n");
		writer.flush();
		// cross fingers and run cmonkey-python
		if (jobId != null)
			updateJobProgress(jobId,
					"Input prepared. Starting cMonkey program...", token);
		Integer exitVal = executeCommand(commandLine, jobPath, jobId, token);
		if (exitVal != null) {
			writer.write("ExitValue: " + exitVal.toString() + "\n");
			writer.flush();
		} else {
			writer.write("ExitValue: null\n");
			writer.flush();
		}
		// parse results
		if (jobId != null)
			updateJobProgress(jobId, "cMonkey finished. Processing output...",
					token);
		String sqlFile = jobPath + "out/cmonkey_run.db";
		writer.write(sqlFile + "\n");
		writer.flush();
		parseCmonkeySql(sqlFile, cmonkeyRunResult);
		String resultId = getKbaseId("CmonkeyRunResult");
		writer.write(resultId + "\n");
		//get ID for the result
		cmonkeyRunResult.setId(resultId);
		cmonkeyRunResult.setParameters(params);
		//save result
		WsDeluxeUtil.saveObjectToWorkspace(
				UObject.transformObjectToObject(cmonkeyRunResult, UObject.class),
				"Cmonkey.CmonkeyRunResult", wsName, cmonkeyRunResult.getId(),
				token.toString());
		//close log file
		writer.close();
		// clean up (if not on AWE)
		if (awe == false) {
			File fileDelete = new File(jobPath);
			fileDelete.delete();
			deleteFile(JOB_PATH, "cmonkey-checkpoint.*");
			//Runtime.getRuntime().exec("rm -r " + jobPath);
			//Runtime.getRuntime().exec("rm " + JOB_PATH + "cmonkey-checkpoint*");
		}
		if (jobId != null)
			finishJob(jobId, wsName, cmonkeyRunResult.getId(), token.toString());
	}

	protected static void prepareCacheFiles(String cachePath, CmonkeyRunParameters params, String token, FileWriter writer) throws TokenFormatException, IOException, JsonClientException {
		//get genome, contigset and export
		GenomeExporter genomeExporter = new GenomeExporter(params.getGenomeRef(), "my_favorite_pet", cachePath, token);
		genomeExporter.writeGenome();
		writer.write("Genome files created");
		//get operons and export
		if (!((params.getOperomeRef() == null)||(params.getOperomeRef().equals("")))){
			NetworkExporter.exportOperons(params.getOperomeRef(), "1", cachePath, token);
			writer.write("Operons file created");
		}
		//get string and export
		if (!((params.getNetworkRef() == null)||(params.getNetworkRef().equals("")))){
			NetworkExporter.exportString(params.getNetworkRef(), "1", cachePath, token);
			writer.write("String file created");
		}
}

	protected static String createDirs( String jobId, String currentDir){
		String jobPath = null;

		if (currentDir == null) {
			jobPath = JOB_PATH + jobId + "/";
			new File(jobPath).mkdir();
		} else {
			jobPath = currentDir + "/" + jobId + "/";
			awe = true;
		}
		new File(jobPath + "out/").mkdir();
		new File(jobPath + "cache/").mkdir();
		
		return jobPath;
	}
	
	protected static String generateCmonkeyCommandLine(String jobPath,
			CmonkeyRunParameters params) {

		String outputDirectory = jobPath + "out";
		String cacheDirectory = jobPath + "cache";
		String inputFile = jobPath + "input.txt";

		String commandLine = CMONKEY_COMMAND + " --rsat_organism my_favorite_pet --rsat_dir " + cacheDirectory 
				+ " --ratios " + inputFile + " --cache " + cacheDirectory + " --out " + outputDirectory + " --config /etc/cmonkey-python/config.ini ";
		// Set options
		if (params.getMotifsScoring() == 0L) {
			commandLine += " --nomotifs";
		}
		if (!((params.getOperomeRef() == null)||(params.getOperomeRef().equals("")))){
			commandLine += " --operons " + cacheDirectory + "/gnc1.named";
		} else {
			commandLine += " --nooperons";
		}
		if (!((params.getNetworkRef() == null)||(params.getNetworkRef().equals("")))){
			commandLine += " --string " + cacheDirectory + "/1.gz";
			if (params.getNetworksScoring() == 0L) {
				commandLine += " --nonetworks";
			}
		} else {
			commandLine += " --nostring --nonetworks";
		}
		return commandLine;
	}

	protected static void startJob(String jobId, String desc, Long tasks,
			String token) throws UnauthorizedException, IOException, JsonClientException, AuthException {

		String status = "cmonkey service job started. Preparing input...";
		InitProgress initProgress = new InitProgress();
		initProgress.setPtype("task");
		initProgress.setMax(tasks);
		date.setTime(date.getTime() + 108000000L);

//		try {
			// System.out.println(dateFormat.format(date));
			jobClient(token).startJob(jobId, token, status, desc, initProgress,
					dateFormat.format(date));
/*		} catch (JsonClientException e) {
			System.err.println("Unable to start job "+ jobId + " (" + desc + "): JSON Client Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Unable to start job "+ jobId + " (" + desc + "): IO Exception");
			e.printStackTrace();
		} catch (AuthException e) {
			System.err.println("Unable to start job "+ jobId + " (" + desc + "): Authentication Exception");
			e.printStackTrace();
		}
*/
	}

	protected static void updateJobProgress(String jobId, String status,
			String token) throws UnauthorizedException, IOException, JsonClientException, AuthException {
//		try {
			date.setTime(date.getTime() + 1000000L);
			jobClient(token).updateJobProgress(jobId, token, status, 1L,
					dateFormat.format(date));
/*		} catch (JsonClientException e) {
			System.err.println("Unable to update job "+ jobId + " (" + status + "): JSON Client Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Unable to update job "+ jobId + " (" + status + "): IO Exception");
			e.printStackTrace();
		} catch (AuthException e) {
			System.err.println("Unable to update job "+ jobId + " (" + status + "): Authentication Exception");
			e.printStackTrace();
		}
*/
	}

	protected static void finishJob(String jobId, String wsId, String objectId,
			String token) throws UnauthorizedException, IOException, JsonClientException, AuthException {
//		try {
			String status = "Finished";
			String error = null;
			Results res = new Results();
			List<String> workspaceIds = new ArrayList<String>();
			workspaceIds.add(wsId + "/" + objectId);
			res.setWorkspaceids(workspaceIds);
			jobClient(token).completeJob(jobId, token, status, error, res);
/*		} catch (JsonClientException e) {
			System.err.println("Unable to finish job "+ jobId + " (result object: " + objectId + " in workspace " + wsId + "): JSON Client Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Unable to finish job "+ jobId + " (result object: " + objectId + " in workspace " + wsId + "): IO Exception");
			e.printStackTrace();
		} catch (AuthException e) {
			System.err.println("Unable to finish job "+ jobId + " (result object: " + objectId + " in workspace " + wsId + "): Authentication Exception");
			e.printStackTrace();
		}
*/
	}

	protected static String getKbaseId(String entityType) throws TokenFormatException, UnauthorizedException, IOException, JsonClientException {
		String returnVal = null;
//		try {
			if (entityType.equals("CmonkeyRunResult")) {
				returnVal = "kb|cmonkeyrunresult."
						+ idClient().allocateIdRange("kb|cmonkeyrunresult", 1L)
								.toString();
			} else if (entityType.equals("CmonkeyNetwork")) {
				returnVal = "kb|cmonkeynetwork."
						+ idClient().allocateIdRange("kb|cmonkeynetwork", 1L)
								.toString();
			} else if (entityType.equals("CmonkeyCluster")) {
				returnVal = "kb|cmonkeycluster."
						+ idClient().allocateIdRange("kb|cmonkeycluster", 1L)
								.toString();
			} else if (entityType.equals("CmonkeyMotif")) {
				returnVal = "kb|cmonkeymotif."
						+ idClient().allocateIdRange("kb|cmonkeymotif", 1L)
								.toString();
			} else if (entityType.equals("MastHit")) {
				returnVal = "kb|masthit."
						+ idClient().allocateIdRange("kb|masthit", 1L)
								.toString();
			} else if (entityType.equals("ExpressionSeries")) {
				returnVal = "kb|series."
						+ idClient().allocateIdRange("kb|series", 1L)
								.toString();
			} else if (entityType.equals("ExpressionSample")) {
				returnVal = "kb|sample."
						+ idClient().allocateIdRange("kb|sample", 1L)
								.toString();
			} else {
				System.out.println("ID requested for unknown type "
						+ entityType);
			}
/*		} catch (TokenFormatException e) {
			System.err.println("Unable to get KBase ID for " + entityType + " from " + ID_SERVICE_URL + ": Token Format Exception");
			e.printStackTrace();
		} catch (UnauthorizedException e) {
			System.err.println("Unable to get KBase ID for " + entityType + " from " + ID_SERVICE_URL + ": Unauthorized Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Unable to get KBase ID for " + entityType + " from " + ID_SERVICE_URL + ": IO Exception");
			e.printStackTrace();
		} catch (JsonClientException e) {
			System.err.println("Unable to get KBase ID for " + entityType + " from " + ID_SERVICE_URL + ": Json error");
			e.printStackTrace();
		}
*/
		return returnVal;
	}

	protected static void createInputTable(String jobPath, List<String> sampleRefs, String token) throws TokenFormatException, IOException, JsonClientException {
		
		List<ObjectData> objects = WsDeluxeUtil.getObjectsFromWsByRef(sampleRefs, token);
		List<ExpressionSample> samples = new ArrayList<ExpressionSample>();
		for (ObjectData o : objects){
			ExpressionSample s = o.getData().asClassInstance(ExpressionSample.class);
			samples.add(s);
		}

		BufferedWriter writer = null;
//		try {
			writer = new BufferedWriter(new FileWriter(jobPath + "input.txt"));
			writer.write("GENE");
			
			List<Map<String, Double>> dataCollection = new ArrayList<Map<String, Double>>();
			// make list of conditions
			for (ExpressionSample sample : samples) {
				writer.write("\t" + sample.getKbId());
				Map<String, Double> dataSet = sample.getExpressionLevels();
				dataCollection.add(dataSet);
			}
			// make list of genes
			List<String> geneNames = new ArrayList<String>();
			for (ExpressionSample sample : samples) {
				Map<String, Double> values = sample.getExpressionLevels();
				for (String gene : values.keySet()){
					geneNames.add(gene);
				}
			}
			List<String> uniqueGeneNames = new ArrayList<String>(
					new HashSet<String>(geneNames));
			for (String geneName : uniqueGeneNames) {
				writer.write("\n" + geneName);
				DecimalFormat df = new DecimalFormat("0.000");
				for (Map<String, Double> dataSetMap : dataCollection) {
					if (dataSetMap.containsKey(geneName)) {
						if (dataSetMap.get(geneName).toString().matches("-.*")) {
							writer.write("\t" + df.format(dataSetMap.get(geneName)));
						} else {
							writer.write("\t " + df.format(dataSetMap.get(geneName)));
						}
					} else {
						writer.write("\tNA");
					}
				}
			}
			writer.write("\n");
			
/*		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
*/
				if (writer != null)
					writer.close();
/*			} catch (IOException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
*/
	}

	protected static Integer executeCommand(String commandLine, String jobPath,
			String jobId, String token) throws InterruptedException, IOException {
		Integer exitVal = null;
//		try {
			Process p = Runtime.getRuntime().exec(commandLine, null,
					new File(CMONKEY_DIR));

			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(),
					"ERROR", jobId, token, jobPath + "errorlog.txt");

			// any output?
			StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream(),
					"OUTPUT", jobId, token, jobPath + "out.txt");

			// kick them off
			errorGobbler.start();
			outputGobbler.start();

			// any error???
			exitVal = p.waitFor();
			System.out.println("ExitValue: " + exitVal);
/*		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
		}
*/
		return exitVal;

	}

	protected static void parseCmonkeySql(String sqlFile,
			CmonkeyRunResult cmonkeyRunResult) throws ClassNotFoundException, SQLException, IOException, JsonClientException {
		CmonkeySqlite database = new CmonkeySqlite(sqlFile);
		database.buildCmonkeyRunResult(cmonkeyRunResult);
		database.disconnect();
	}
	
	protected static void deleteFile(String folder, final String pattern){
		File dir = new File(folder);
		File fileDelete;

		for (String file : dir.list(new FilenameFilter(){public boolean accept( File dir, String name ) {return name.matches(pattern);}})){
			String temp = new StringBuffer(folder).append(File.separator).append(file).toString();
			fileDelete = new File(temp);
			boolean isdeleted = fileDelete.delete();
			System.out.println("file : " + temp + " is deleted : " + isdeleted);
		}
	}
	
	/*	public static CmonkeyRunResult buildCmonkeyNetwork(ExpressionSeries series,
	CmonkeyRunParameters params, String jobId, String token,
	String currentDir) throws Exception {
CmonkeyRunResult cmonkeyRunResult = new CmonkeyRunResult();
cmonkeyRunResult.setId(getKbaseId("CmonkeyRunResult"));
String jobPath = null;
if (currentDir == null) {
	jobPath = JOB_PATH + jobId + "/";
	new File(jobPath).mkdir();
} else {
	jobPath = currentDir + "/" + jobId + "/";
	awe = true;
}

// prepare input

String inputTable = getInputTable(series);
FileWriter writer = new FileWriter(jobPath + "serveroutput.txt");
writer.write(inputTable);
writer.flush();

// check list of genes
String organismName = getOrganismName(wsName, series.getKbId(), token);
writer.write("Organism name = " + organismName + "\n");
writer.flush();
String organismCode = getKeggCode(organismName);
writer.write("Organism code = " + organismCode + "\n");
writer.flush();

// save input file
writeInputFile(jobPath + "input.txt", inputTable);
// generate command line
String commandLine = generateCmonkeyCommandLine(jobPath, params,
		organismCode);
writer.write(commandLine + "\n");
writer.flush();
// run
if (jobId != null)
	updateJobProgress(jobId,
			"Input prepared. Starting cMonkey program...", token);
Integer exitVal = executeCommand(commandLine, jobPath, jobId, token);
if (exitVal != null) {
	writer.write("ExitValue: " + exitVal.toString() + "\n");
	writer.flush();
} else {
	writer.write("ExitValue: null\n");
	writer.flush();
}

// parse results

if (jobId != null)
	updateJobProgress(jobId, "cMonkey finished. Processing output...",
			token);

String sqlFile = jobPath + "out/cmonkey_run.db";
writer.write(sqlFile + "\n");
writer.flush();
parseCmonkeySql(sqlFile, cmonkeyRunResult);
String resultId = getKbaseId("CmonkeyRunResult");
writer.write(resultId + "\n");
cmonkeyRunResult.setId(resultId);

writer.close();
// clean up
if (awe == false) {
	Runtime.getRuntime().exec("rm -r " + jobPath);
	Runtime.getRuntime().exec("rm " + JOB_PATH + "cmonkey-checkpoint*");
}

return cmonkeyRunResult;
}*/

}
