package us.kbase.cmonkey;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple7;
import us.kbase.userandjobstate.Results;
import us.kbase.util.WsDeluxeUtil;

public class CmonkeyClientTest {

	private static final String USER_NAME = "";
	private static final String PASSWORD = "";
	private static final String workspaceName = "AKtest";//"ENIGMA_KBASE";//
	private String serverUrl = "http://140.221.85.173:7078";
//	private String serverUrl = "http://localhost:7049";
	private String quickTestSeriesRef = "AKtest/test_Halobacterium_sp_expression_series";
	private String testSeriesRef = "ENIGMA_KBASE/Halobacterium_sp_expression_series";//"ENIGMA_KBASE/D_vulgaris_Hildenborough_expression_series";//"AKtest/Halobacterium_sp_expression_series";//
	private String genomeRef = "ENIGMA_KBASE/Halobacterium_sp_NRC-1";//"ENIGMA_KBASE/Desulfovibrio_vulgaris_Hildenborough";//"AKtest/Halobacterium_sp_NRC-1";//
	private String testStringNetworkRef = "ENIGMA_KBASE/Halobacterium_sp_STRING";//"AKtest/Halobacterium_sp_STRING";//"ENIGMA_KBASE/D_vulgaris_STRING";//"ENIGMA_KBASE/Halobacterium_sp_NRC-1_string";
	private String testOperonNetworkRef = "ENIGMA_KBASE/Halobacterium_sp_operons";//"AKtest/Halobacterium_sp_operons";//"AKtest/D_vulgaris_Hildenborough_operons";//"ENIGMA_KBASE/Halobacterium_sp_NRC-1_operons";
	
	@Test
	public final void testQuickBuildCmonkeyNetworkJobFromWs() throws Exception {
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
//		System.out.println(token.toString());
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(0L);
		params.setNetworksScoring(0L);
		params.setGenomeRef(genomeRef);
		params.setSeriesRef(quickTestSeriesRef);
		URL url = new URL(serverUrl);
		CmonkeyClient client = new CmonkeyClient(url, token);
		client.setAuthAllowedForHttp(true);
		String jobId = client.runCmonkey(workspaceName, params);
		
		System.out.println("Job ID = " + jobId);
		assertNotNull(jobId);
		String resultId = "";

		String status = "";
		Integer waitingTime = 2;
		while (!status.equalsIgnoreCase("finished")){
			
			try {
			    Thread.sleep(120000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			try {
				Tuple7<String,String,String,Long,String,Long,Long> t = CmonkeyServerImpl.jobClient(token.toString()).getJobStatus(jobId); 
				//System.out.println(t.getE1());
				//System.out.println(t.getE2());
				status = t.getE3();
				//System.out.println(t.getE3());//Status
				//System.out.println(t.getE4());
				//System.out.println(t.getE5());
				//System.out.println(t.getE6());
				//System.out.println(t.getE7());
				System.out.println("Waiting time: "+ waitingTime.toString() + " minutes");
				waitingTime += 2;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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
		
		CmonkeyRunResult result = WsDeluxeUtil.getObjectFromWorkspace(workspaceName, resultId, token.toString()).getData().asClassInstance(CmonkeyRunResult.class);
		
		assertEquals(Long.valueOf("39"), result.getNetwork().getClustersNumber());
		assertEquals(Long.valueOf("2001"), result.getLastIteration());

	}

	@Test
	public final void testBuildCmonkeyNetworkJobFromWs() throws Exception {
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		System.out.println(token.toString());
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(1L);
		params.setNetworksScoring(1L);
		params.setOperomeRef(testOperonNetworkRef);
		params.setNetworkRef(testStringNetworkRef);
		params.setSeriesRef(testSeriesRef);
		params.setGenomeRef(genomeRef);

		URL url = new URL(serverUrl);
		CmonkeyClient client = new CmonkeyClient(url, token);
		client.setAuthAllowedForHttp(true);
		String jobId = client.runCmonkey(workspaceName, params);
		
		System.out.println("Job ID = " + jobId);
		assertNotNull(jobId);
		String resultId = "";

		String status = "";
		Integer waitingTime = 2;
		while (!status.equalsIgnoreCase("finished")){
			
			try {
			    Thread.sleep(120000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			try {
				Tuple7<String,String,String,Long,String,Long,Long> t = CmonkeyServerImpl.jobClient(token.toString()).getJobStatus(jobId); 
				System.out.println(t.getE1());
				System.out.println(t.getE2());
				status = t.getE3();
				System.out.println(t.getE3());//Status
				System.out.println(t.getE4());
				System.out.println(t.getE5());
				System.out.println(t.getE6());
				System.out.println(t.getE7());
				System.out.println("Waiting time: "+ waitingTime.toString() + " minutes");
				waitingTime += 2;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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

		CmonkeyRunResult result = WsDeluxeUtil.getObjectFromWsByRef(workspaceName+"/"+resultId, token.toString()).getData().asClassInstance(CmonkeyRunResult.class);

		assertEquals(Long.valueOf("194"), result.getNetwork().getClustersNumber());
		assertEquals(Long.valueOf("2001"), result.getLastIteration());

	}

	@Test
	public void testURL() throws Exception {

	    try {
	        URL url = new URL(serverUrl);
	        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
	        urlConn.connect();
	        System.out.println(urlConn.getResponseMessage());
	        System.out.println(urlConn.getContent().toString());

	        assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
	    } catch (IOException e) {
	        System.err.println("Error creating HTTP connection");
	        e.printStackTrace();
	        throw e;
	    }
	}
}
