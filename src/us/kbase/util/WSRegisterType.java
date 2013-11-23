package us.kbase.util;

import java.net.MalformedURLException;
import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;

import us.kbase.common.service.JsonClientCaller;
import us.kbase.auth.AuthToken;
//	import us.kbase.workspace.CompileTypespecParams;
//	import us.kbase.workspace.WorkspaceClient;
import us.kbase.workspaceservice.AddTypeParams;
import us.kbase.workspaceservice.WorkspaceServiceClient;

public class WSRegisterType {
	
/*	Examples:
    WSRegisterType registerMotifMeme = new WSRegisterType("MotifMeme");
	WSRegisterType registerSiteMeme = new WSRegisterType("SiteMeme");
	WSRegisterType registerMotifCollectionMeme = new WSRegisterType("MotifCollectionMeme");
	WSRegisterType registerSequence = new WSRegisterType("Sequence");
	WSRegisterType registerSequenceSet = new WSRegisterType("SequenceSet");
	WSRegisterType registerHitMast = new WSRegisterType("HitMast");
	WSRegisterType registerHitTomtom = new WSRegisterType("HitTomtom");
    WSRegisterType registerExpressionDataCollection = new WSRegisterType("ExpressionDataCollection");
    WSRegisterType registerExpressionDataPoint = new WSRegisterType("ExpressionDataPoint");
    WSRegisterType registerExpressionDataSet = new WSRegisterType("ExpressionDataSet");
    WSRegisterType registerCmonkeyRun = new WSRegisterType("CmonkeyRun");
    WSRegisterType registerCmonkeyNetwork = new WSRegisterType("CmonkeyNetwork");
    WSRegisterType registerCmonkeyCluster = new WSRegisterType("CmonkeyCluster");
    WSRegisterType registerMotifCmonkey = new WSRegisterType("MotifCmonkey");

*/		

	public static final String WS_SERVICE_URL = "http://kbase.us/services/workspace/";
	public static final String userName = "aktest";
	public static final String password = "1475rokegi";
	public static final String workspaceName = "AKtest";
	
	private static WorkspaceServiceClient _wsClient = null;
	private static AuthToken _token = null;
	
	public WSRegisterType (String dataType) throws Exception{
		Long version = registerDataType(dataType);
		System.out.println(version);
	}
	
	public static WorkspaceServiceClient wsClient() throws MalformedURLException{
		if(_wsClient == null)
		{
			URL url = new URL(WS_SERVICE_URL);
			_wsClient = new WorkspaceServiceClient(url);
		}
		return _wsClient;
	}
	
	public static AuthToken authToken() throws Exception{
		if(_token == null){
			_token = JsonClientCaller.requestTokenFromKBase(userName, password.toCharArray()); 
		}
		return _token; 	  
	}
	
	public static Long registerDataType(String dataType) throws Exception{
		AddTypeParams typeParams = new AddTypeParams();
		System.out.println("Registering data type: "+dataType);
		typeParams.setType(dataType);
		typeParams.setAuth( authToken().toString() );
		
		return wsClient().addType(typeParams);
	}

}
