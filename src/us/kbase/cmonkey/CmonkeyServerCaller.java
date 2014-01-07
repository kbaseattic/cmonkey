package us.kbase.cmonkey;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import us.kbase.auth.AuthToken;
import us.kbase.auth.TokenFormatException;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UnauthorizedException;
//import us.kbase.expressionservices.ExpressionSeries;
import us.kbase.userandjobstate.UserAndJobStateClient;

public class CmonkeyServerCaller {

	private static boolean deployCluster = CmonkeyServerConfig.DEPLOY_AWE;
	private static final String JOB_SERVICE = CmonkeyServerConfig.JOB_SERVICE_URL;
	private static final String AWE_SERVICE = CmonkeyServerConfig.AWE_SERVICE_URL;

	private static Integer connectionReadTimeOut = 30 * 60 * 1000;

	/*
	 * public static CmonkeyRunResult buildCmonkeyNetwork( ExpressionSeries
	 * series, CmonkeyRunParameters params, String jobId, AuthToken authPart)
	 * throws Exception { CmonkeyRunResult returnVal =
	 * CmonkeyServerImpl.buildCmonkeyNetwork( series, params, jobId,
	 * authPart.toString(), null); return returnVal; }
	 */

	public static String buildCmonkeyNetworkJobFromWs(String wsId,
			CmonkeyRunParameters params, AuthToken authPart)
			throws TokenFormatException, UnauthorizedException, IOException,
			JsonClientException {

		String returnVal = null;
		URL jobServiceUrl = new URL(JOB_SERVICE);
		UserAndJobStateClient jobClient = new UserAndJobStateClient(
				jobServiceUrl, authPart);
		// jobClient.setAuthAllowedForHttp(true);
		returnVal = jobClient.createJob();
		jobClient = null;

		if (deployCluster == false) {
			CmonkeyServerThread cmonkeyServerThread = new CmonkeyServerThread(
					wsId, params, returnVal, authPart.toString());
			cmonkeyServerThread.start();
		} else {
			String jsonArgs = prepareJson(wsId, returnVal, params,
					authPart.toString());
			if (CmonkeyServerConfig.LOG_AWE_CALLS) {
				System.out.println(jsonArgs);
				PrintWriter out = new PrintWriter(new FileWriter(
						"/var/tmp/cmonkey/cmonkey-awe.log", true));
				out.write("Job " + returnVal + " : call to AWE\n" + jsonArgs
						+ "\n***\n");
				out.write("***");
				if (out != null) {
					out.close();
				}
			}
			String result = executePost(jsonArgs);
			if (CmonkeyServerConfig.LOG_AWE_CALLS) {
				System.out.println(result);
				PrintWriter out = new PrintWriter(new FileWriter(
						"/var/tmp/cmonkey/cmonkey-awe.log", true));
				out.write("Job " + returnVal + " : AWE response\n" + result
						+ "\n***\n");
				if (out != null) {
					out.close();
				}
			}
		}
		return returnVal;
	}

	protected static String prepareJson(String wsId, String jobId,
			CmonkeyRunParameters params, String token) {
		String returnVal = "{\"info\": {\"pipeline\": \"cmonkey-runner-pipeline\",\"name\": \"cmonkey\",\"project\": \"default\""
				+ ",\"user\": \"default\",\"clientgroups\":\"\",\"sessionId\":\""
				+ jobId + "\"},\"tasks\": [{\"cmd\": {\"args\": \"";
		returnVal += " --job " + jobId
				+ " --method build_cmonkey_network_job_from_ws --ws '" + wsId
				+ "' --series '" + params.getSeriesRef() + "' --genome '"
				+ params.getGenomeRef() + "'";

		if (params.getMotifsScoring() == 0L) {
			returnVal += " --motifs 0";
		} else {
			returnVal += " --motifs 1";
		}
		if (params.getNetworksScoring() == 0L) {
			returnVal += " --networks 0";
		} else {
			returnVal += " --networks 1";
		}
		if (params.getOperomeRef() != null) {
			returnVal += " --operons '" + params.getOperomeRef() + "'";
		} else {
			returnVal += " --operons 'null'";
		}
		if (params.getNetworkRef() != null) {
			returnVal += " --string '" + params.getNetworkRef() + "'";
		} else {
			returnVal += " --string 'null'";
		}

		returnVal += " --token '" + token + "'";
		returnVal += "\", \"description\": \"running cMonkey service\", \"name\": \"run_cmonkey\"}, \"dependsOn\": [], \"outputs\": {\""
				+ jobId
				+ ".tgz\": {\"host\": \"http://140.221.84.236:8000\"}},\"taskid\": \"0\",\"skip\": 0,\"totalwork\": 1}]}";

		return returnVal;
	}

	protected static String executePost(String jsonRequest) throws IOException {
		URL url;
		HttpURLConnection connection = null;
		PrintWriter writer = null;
		url = new URL(AWE_SERVICE);
		String boundary = Long.toHexString(System.currentTimeMillis());
		connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(10000);
		if (connectionReadTimeOut != null) {
			connection.setReadTimeout(connectionReadTimeOut);
		}
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + boundary);
		connection.setDoOutput(true);
		// connection.setDoInput(true);
		OutputStream output = connection.getOutputStream();
		writer = new PrintWriter(new OutputStreamWriter(output), true); //set true for autoFlush!
		String CRLF = "\r\n";
		writer.append("--" + boundary).append(CRLF);
		writer.append(
				"Content-Disposition: form-data; name=\"upload\"; filename=\"cmonkey.awe\"")
				.append(CRLF);
		writer.append("Content-Type: application/octet-stream").append(CRLF);
		writer.append(CRLF).flush();
		writer.append(jsonRequest).append(CRLF);
		writer.flush();
		writer.append("--" + boundary + "--").append(CRLF);
		writer.append(CRLF).flush();

		// Get Response
		InputStream is = connection.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuffer response = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			response.append(line);
			response.append('\r');
		}
		rd.close();

		if (writer != null)
			writer.close();

		if (connection != null) {
			connection.disconnect();
		}
		return response.toString();
	}
}
