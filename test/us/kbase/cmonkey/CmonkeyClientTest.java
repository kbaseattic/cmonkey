package us.kbase.cmonkey;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UObject;
import us.kbase.userandjobstate.Results;
import us.kbase.workspaceservice.GetObjectOutput;
import us.kbase.workspaceservice.GetObjectParams;

public class CmonkeyClientTest {

	private static final String USER_NAME = "aktest";
	private static final String PASSWORD = "1475rokegi";
	private static final String workspaceName = "AKtest";
	private String serverUrl = "http://140.221.84.195:7049";
//	private String serverUrl = "http://localhost:7108";

	
	@Test
	public final void testBuildCmonkeyNetworkJobFromWs() throws Exception {
		String collectionId = "HalobacteriumExpressionSeries";
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setNoMotifs(1L);
		params.setNoNetworks(1L);
		params.setNoOperons(1L);
		params.setNoString(1L);
		URL url = new URL(serverUrl);
		CmonkeyClient client = new CmonkeyClient(url, token);
		client.setAuthAllowedForHttp(true);
		String jobId = client.buildCmonkeyNetworkJobFromWs(workspaceName, collectionId, params);
		
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
	public final void testBuildCmonkeyNetworkFromWs() throws Exception {
		String collectionId = "HalobacteriumExpressionSeries";
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setNoMotifs(1L);
		params.setNoNetworks(1L);
		params.setNoOperons(1L);
		params.setNoString(1L);
		URL url = new URL(serverUrl);
		CmonkeyClient client = new CmonkeyClient(url, token);
		client.setAuthAllowedForHttp(true);
		String resultId = client.buildCmonkeyNetworkFromWs(workspaceName, collectionId, params);
		
		GetObjectParams objectParams = new GetObjectParams().withType("CmonkeyRunResult").withId(resultId).withWorkspace(workspaceName).withAuth(token.toString());
		GetObjectOutput output = CmonkeyServerImpl.wsClient(token.toString()).getObject(objectParams);
		CmonkeyRunResult result = UObject.transformObjectToObject(output.getData(), CmonkeyRunResult.class);
		
		assertEquals(Long.valueOf("43"), result.getClustersNumber());

	}

}
