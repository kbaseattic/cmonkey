package us.kbase.cmonkey;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientException;
import us.kbase.userandjobstate.Results;
import us.kbase.util.WsDeluxeUtil;

public class CmonkeyInvokerTest {

	private static final String USER_NAME = "aktest";
	private static final String PASSWORD = "1475rokegi";
	private static final String workspaceName = "AKtest";
	private String quickTestSeriesId = "QuickTestExpressionDataSeries";
	private String testSeriesId = "TestExpressionDataSeries";
	private String genomeId = "kb|genome.test";
	
	@Test
	public void testCleanupArgument() throws Exception {
		String argument = "'un=aktest|tokenid=378448aa-642b-11e3-884e-12313d2d6e7f|expiry=1418498252|client_id=aktest|token_type=Bearer|SigningSubject=https://nexus.api.globusonline.org/goauth/keys/38973b4e-642b-11e3-884e-12313d2d6e7f|sig=a61bfe37fd8d3a882ea62e6a8f5c35f546cdf7870e797ec3a6588a53409a28ece1775b596411d3d82c907e17dbd10bb467fc06e43633f9c33c6151e1e19a1aabaa4cb58fac78d7e904adb154a3043df4301f747b61d75586b93046ece55c14564afba32290b1fbb405cf9ce1d060336f46285ab5b4225c95bef12fe99086ce8b'";
		argument = CmonkeyInvoker.cleanUpArgument(argument);
		System.out.println(argument);
		assertEquals(argument, "un=aktest|tokenid=378448aa-642b-11e3-884e-12313d2d6e7f|expiry=1418498252|client_id=aktest|token_type=Bearer|SigningSubject=https://nexus.api.globusonline.org/goauth/keys/38973b4e-642b-11e3-884e-12313d2d6e7f|sig=a61bfe37fd8d3a882ea62e6a8f5c35f546cdf7870e797ec3a6588a53409a28ece1775b596411d3d82c907e17dbd10bb467fc06e43633f9c33c6151e1e19a1aabaa4cb58fac78d7e904adb154a3043df4301f747b61d75586b93046ece55c14564afba32290b1fbb405cf9ce1d060336f46285ab5b4225c95bef12fe99086ce8b");
		
	}

	@Test
	public final void testCmonkeyInvokerQuick() throws Exception {
		
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(0L);
		params.setNetworksScoring(0L);
		params.setSeriesRef(quickTestSeriesId);
		params.setGenomeRef(genomeId);

		String jobId = CmonkeyServerCaller.buildCmonkeyNetworkJobFromWs(workspaceName, params, token);
		
		System.out.println("Job ID = " + jobId);
		assertNotNull(jobId);
		String commandLine = "java jar /kb/deployment/cmonkey/cmonkey_cluster.jar --method build_cmonkey_network_job_from_ws --ws" + workspaceName + " --job " + jobId + " --series " + quickTestSeriesId + " --nomotifs 1 --nooperons 1 --nonetworks 1 --nostring 1 --token " + token.toString(); 
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
		
		CmonkeyRunResult result = WsDeluxeUtil.getObjectFromWsByRef(resultId, token.toString()).getData().asClassInstance(CmonkeyRunResult.class);
		
		assertEquals(Long.valueOf("3"), result.getNetwork().getClustersNumber());
	}

	public final void testCmonkeyInvokerLong() throws Exception {
		
		AuthToken token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		CmonkeyRunParameters params = new CmonkeyRunParameters();
		params.setMotifsScoring(1L);
		params.setNetworksScoring(1L);
		params.setOperomeRef("");
		params.setNetworkRef("");
		params.setSeriesRef(testSeriesId);
		params.setGenomeRef(genomeId);

		String jobId = CmonkeyServerCaller.buildCmonkeyNetworkJobFromWs(workspaceName, params, token);
		
		System.out.println("Job ID = " + jobId);
		assertNotNull(jobId);
		String commandLine = "java jar /kb/deployment/cmonkey/cmonkey_cluster.jar --method build_cmonkey_network_job_from_ws --ws" + workspaceName + " --job " + jobId + " --series " + testSeriesId + " --nomotifs 1 --nooperons 1 --nonetworks 1 --nostring 1 --token " + token.toString(); 
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
		
		CmonkeyRunResult result = WsDeluxeUtil.getObjectFromWsByRef(resultId, token.toString()).getData().asClassInstance(CmonkeyRunResult.class);
		
		assertEquals(Long.valueOf("43"), result.getNetwork().getClustersNumber());
		assertEquals(Long.valueOf("2001"), result.getLastIteration());
	}

}
