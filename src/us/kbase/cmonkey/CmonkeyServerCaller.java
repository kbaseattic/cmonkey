package us.kbase.cmonkey;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import us.kbase.auth.AuthToken;
import us.kbase.auth.TokenFormatException;
import us.kbase.common.service.JacksonTupleModule;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.ServerException;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.userandjobstate.UserAndJobStateClient;

public class CmonkeyServerCaller {

	private static final String JOB_SERVICE = "http://140.221.84.180:7083";
	private static UserAndJobStateClient _jobClient = null;
	
	private static final String CLUSTER_SERVICE = "http://kbase.us/services/cs_test/jobs/";
	private static Integer connectionReadTimeOut = 30 * 60 * 1000;
	private static boolean isAuthAllowedForHttp = false;
	private static ObjectMapper mapper = new ObjectMapper().registerModule(new JacksonTupleModule());
	private static boolean deployCluster = false;


	    public static CmonkeyRunResult buildCmonkeyNetwork(ExpressionDataSeries series, CmonkeyRunParameters params) throws Exception {
	         CmonkeyRunResult returnVal = CmonkeyServerImpl.buildCmonkeyNetwork(series, params);
	        return returnVal;
	    }

	    public static String buildCmonkeyNetworkFromWs(String wsId, String collectionId, CmonkeyRunParameters params, AuthToken authPart) throws Exception {
	        String returnVal = CmonkeyServerImpl.buildCmonkeyNetworkFromWs(wsId, collectionId, params, authPart);
	        return returnVal;
	    }

	    public static String buildCmonkeyNetworkJobFromWs(String wsId, String seriesId, CmonkeyRunParameters params, AuthToken authPart) throws Exception {
	    	
	        String returnVal = null;

	        returnVal = jobClient(authPart).createJob();

	        if (deployCluster == false) { 
	        	CmonkeyServerImpl.buildCmonkeyNetworkJobFromWs(wsId, seriesId, params, returnVal, authPart.toString());
	        } else {
	        	Map<String, String> jsonArgs = new HashMap<String, String>();
	        	jsonArgs.put("target", "cloud");
	        	jsonArgs.put("application", "meme");
	        	jsonArgs.put("method", "find_sites_with_mast_job_from_ws");
	        	jsonArgs.put("job_id", returnVal);
	        	jsonArgs.put("workspace", wsId);
	        	jsonArgs.put("series", wsId);
	        	jsonArgs.put("nomotifs", params.getNoMotifs().toString());
	        	jsonArgs.put("nonetworks", params.getNoNetworks().toString());
	        	jsonArgs.put("nooperons", params.getNoOperons().toString());
	        	jsonArgs.put("nostring", params.getNoString().toString());
	        	jsonArgs.put("token", authPart.toString());
	        
	        	String result = jsonCall(jsonArgs, authPart);
	        	System.out.println(result);
	        }
	        return returnVal;
	    }

		protected static void setAuthAllowedForHttp(boolean AllowedForHttp) {
			isAuthAllowedForHttp = AllowedForHttp;
		}
		
		protected static UserAndJobStateClient jobClient(AuthToken token) throws TokenFormatException, UnauthorizedException, IOException {
			if(_jobClient == null)
			{
				URL jobServiceUrl = new URL (JOB_SERVICE);
				_jobClient = new UserAndJobStateClient (jobServiceUrl, token);
				_jobClient.setAuthAllowedForHttp(true);
			}
			return _jobClient;
		} 

		private static HttpURLConnection setupCall(AuthToken accessToken) throws IOException, JsonClientException {
			URL clusterServiceUrl = new URL(CLUSTER_SERVICE);
			HttpURLConnection conn = (HttpURLConnection) clusterServiceUrl.openConnection();
			conn.setConnectTimeout(10000);
			if (connectionReadTimeOut != null) {
				conn.setReadTimeout(connectionReadTimeOut);
			}
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			if (accessToken != null) {
				if (!(conn instanceof HttpsURLConnection || isAuthAllowedForHttp)) {
					throw new UnauthorizedException("RPC method required authentication shouldn't " +
							"be called through unsecured http, use https instead or call " +
							"setAuthAllowedForHttp(true) for your client");
				}
				if (accessToken == null || accessToken.isExpired()) {
					throw new UnauthorizedException("This method requires authentication but token was not set");
				}
				conn.setRequestProperty("Authorization", accessToken.toString());
			}
			return conn;
		}

		protected static String jsonCall(Map<String, String> arg, AuthToken token) throws IOException, JsonClientException {
			HttpURLConnection conn = setupCall(token);
			OutputStream os = conn.getOutputStream();
			JsonGenerator g = mapper.getFactory().createGenerator(os, JsonEncoding.UTF8);

			g.writeStartObject();
			for (Map.Entry<String, String> entry : arg.entrySet())
			{
				g.writeStringField(entry.getKey(), entry.getValue());
			}
			g.writeEndObject();
			g.close();

			JsonGenerator g2 = mapper.getFactory().createGenerator(System.out, JsonEncoding.UTF8);
			g2.writeStartObject();
			for (Map.Entry<String, String> entry : arg.entrySet())
			{
				g2.writeStringField(entry.getKey(), entry.getValue());
			}
			g2.writeEndObject();
			g2.close();
			

			
			int code = conn.getResponseCode();
			conn.getResponseMessage();

			InputStream istream;
			if (code == 500) {
				istream = conn.getErrorStream();
			} else {
				istream = conn.getInputStream();
			}

			JsonNode node = mapper.readTree(new UnclosableInputStream(istream));
			System.out.println(node.toString());
			
			if (node.has("error")) {
				Map<String, String> ret_error = mapper.readValue(mapper.treeAsTokens(node.get("error")), 
						new TypeReference<Map<String, String>>(){});
				
				String data = ret_error.get("data") == null ? ret_error.get("error") : ret_error.get("data");
				throw new ServerException(ret_error.get("message"),
						new Integer(ret_error.get("code")), ret_error.get("name"),
						data);
			}
			String res = null;
			if (node.has("result"))
				res = mapper.readValue(mapper.treeAsTokens(node.get("result")), String.class);
			if (res == null)
				throw new ServerException("An unknown server error occured", 0, "Unknown", null);
			return res;
		}


		private static class UnclosableInputStream extends InputStream {
			private InputStream inner;
			private boolean isClosed = false;
			
			public UnclosableInputStream(InputStream inner) {
				this.inner = inner;
			}
			
			@Override
			public int read() throws IOException {
				if (isClosed)
					return -1;
				return inner.read();
			}
			
			@Override
			public int available() throws IOException {
				if (isClosed)
					return 0;
				return inner.available();
			}
			
			@Override
			public void close() throws IOException {
				isClosed = true;
			}
			
			@Override
			public synchronized void mark(int readlimit) {
				inner.mark(readlimit);
			}
			
			@Override
			public boolean markSupported() {
				return inner.markSupported();
			}
			
			@Override
			public int read(byte[] b) throws IOException {
				if (isClosed)
					return 0;
				return inner.read(b);
			}
			
			@Override
			public int read(byte[] b, int off, int len) throws IOException {
				if (isClosed)
					return 0;
				return inner.read(b, off, len);
			}
			
			@Override
			public synchronized void reset() throws IOException {
				if (isClosed)
					return;
				inner.reset();
			}
			
			@Override
			public long skip(long n) throws IOException {
				if (isClosed)
					return 0;
				return inner.skip(n);
			}
		}
	
}