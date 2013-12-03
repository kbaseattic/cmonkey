package us.kbase.cmonkey;

public class CmonkeyServerThread extends Thread {
	
	String wsId;
	String seriesId;
	CmonkeyRunParameters params;
	String jobId;
	String token;
	
	CmonkeyServerThread (String wsId, String seriesId, CmonkeyRunParameters params, String jobId, String token ){
		this.wsId = wsId;
		this.seriesId = seriesId;
		this.params = params;
		this.jobId = jobId;
		this.token = token;
	}
	
	public void run (){
		try {
			CmonkeyServerImpl.buildCmonkeyNetworkJobFromWs(wsId, seriesId, params, jobId, token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
