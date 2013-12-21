package us.kbase.cmonkey;

public class CmonkeyServerThread extends Thread {
	
	String wsId;
	CmonkeyRunParameters params;
	String jobId;
	String token;
	
	CmonkeyServerThread (String wsId, CmonkeyRunParameters params, String jobId, String token ){
		this.wsId = wsId;
		this.params = params;
		this.jobId = jobId;
		this.token = token;
	}
	
	public void run (){
		try {
			CmonkeyServerImpl.buildCmonkeyNetworkJobFromWs(wsId, params, jobId, token, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
