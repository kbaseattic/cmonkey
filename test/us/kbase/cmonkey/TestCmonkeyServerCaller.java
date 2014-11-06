package us.kbase.cmonkey;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple7;
import us.kbase.userandjobstate.Results;
import us.kbase.userandjobstate.UserAndJobStateClient;
import us.kbase.util.WsDeluxeUtil;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.WorkspaceClient;

public class TestCmonkeyServerCaller {

	private static final String WS_SERVICE_URL = "https://kbase.us/services/ws";
	private static final String UJS_SERVICE_URL = "https://kbase.us/services/userandjobstate";

	private static final String USER_NAME = "";
	private static final String PASSWORD = "";
	private static final String workspaceName = "AKtest";
//	private String quickTestSeriesRef = "AKtest/test_Halobacterium__series";
	private String testSeriesRef = "AKtest/Halobacterium_sp_NRC-1_series_250";
	private String genomeRef = "AKtest/Halobacterium_sp_NRC-1";
	private String testStringNetworkRef = "AKtest/Halobacterium_sp_NRC-1_string";
	private String testOperonNetworkRef = "AKtest/Halobacterium_sp_NRC-1_operons";

	@Test
	public final void testBuildCmonkeyNetworkJobFromWs() throws Exception {
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		//System.out.println(token.toString());
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(1L);
		params.setNetworksScoring(1L);
		params.setOperomeRef(testOperonNetworkRef);
		params.setNetworkRef(testStringNetworkRef);
		params.setSeriesRef(testSeriesRef);
		params.setGenomeRef(genomeRef);

		String jobId = CmonkeyServerCaller.buildCmonkeyNetworkJobFromWs(workspaceName, params, token);
		
		System.out.println("Job ID = " + jobId);
		assertNotNull(jobId);
		String resultId = "";

		String status = "";
		Integer waitingTime = 2;
		URL jobServiceUrl = new URL(UJS_SERVICE_URL);
		UserAndJobStateClient jobClient = new UserAndJobStateClient(jobServiceUrl, token);

		while (!status.equalsIgnoreCase("finished")){
			
			try {
			    Thread.sleep(120000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			try {
				Tuple7<String,String,String,Long,String,Long,Long> t = jobClient.getJobStatus(jobId); 
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
			Results res = jobClient.getResults(jobId);			
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

		WorkspaceClient wsClient = new WorkspaceClient(new URL (WS_SERVICE_URL), new AuthToken(token.toString()));
		wsClient.setAuthAllowedForHttp(true);
		
		List<ObjectIdentity> objectsIdentity = new ArrayList<ObjectIdentity>();
		ObjectIdentity objectIdentity = new ObjectIdentity().withRef(resultId);
		objectsIdentity.add(objectIdentity);
		CmonkeyRunResult result = wsClient.getObjects(objectsIdentity).get(0).getData().asClassInstance(CmonkeyRunResult.class);

		assertEquals(Long.valueOf("194"), result.getNetwork().getClustersNumber());
		assertEquals(Long.valueOf("2001"), result.getLastIteration());

	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	

}
