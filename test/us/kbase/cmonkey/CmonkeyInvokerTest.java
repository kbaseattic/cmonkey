package us.kbase.cmonkey;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UObject;
import us.kbase.userandjobstate.Results;
import us.kbase.workspaceservice.GetObjectOutput;
import us.kbase.workspaceservice.GetObjectParams;

public class CmonkeyInvokerTest {

	private static final String USER_NAME = "aktest";
	private static final String PASSWORD = "1475rokegi";
	private static final String workspaceName = "AKtest";
	
	@Test
	public final void testCmonkeyInvoker() throws Exception {
		String collectionId = "HalobacteriumExpressionSeries";
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setNoMotifs(0L);
		params.setNoNetworks(0L);
		params.setNoOperons(0L);
		params.setNoString(0L);
		String jobId = CmonkeyServerCaller.buildCmonkeyNetworkJobFromWs(workspaceName, collectionId, params, token);
		
		System.out.println("Job ID = " + jobId);
		assertNotNull(jobId);
		String commandLine = "java jar /kb/deployment/cmonkey/cmonkey_cluster.jar --method build_cmonkey_network_job_from_ws --ws" + workspaceName + " --job " + jobId + " --series " + collectionId + " --nomotifs 1 --nooperons 1 --nonetworks 1 --nostring 1 --token " + token.toString(); 
		String resultId = "";
		
		try {
			Process p = Runtime.getRuntime().exec(commandLine);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();

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


}
