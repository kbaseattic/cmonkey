package us.kbase.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import us.kbase.common.service.Tuple11;
import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.workspaceservice.AddTypeParams;
import us.kbase.workspaceservice.ObjectData;
import us.kbase.workspaceservice.SaveObjectParams;
import us.kbase.workspaceservice.WorkspaceServiceClient;

public class WSUtil {
	
	public static final String WORKSPACE_URL = "http://kbase.us/services/workspace/";
	public static final String userName = "aktest";
	public static final String password = "1475rokegi";
	public static final String workspaceName = "AKtest";
	
	private static WorkspaceServiceClient _wsClient = null;
	private static AuthToken _token = null;
	
	
	public static WorkspaceServiceClient wsClient() throws MalformedURLException{
		if(_wsClient == null)
		{
			URL workspaceUrl = new URL(WORKSPACE_URL);
			_wsClient = new WorkspaceServiceClient(workspaceUrl);
		}
		return _wsClient;
	}
	
	public static AuthToken authToken() throws Exception{
		if(_token == null){
//			_token = JsonClientCaller.requestTokenFromKBase(userName, password.toCharArray());
			_token = AuthService.login(userName, new String(password)).getToken();
		}
		return _token; 	  
	}
	
	
	public static Long registerDataType(String dataType) throws Exception{
		AddTypeParams typeParams = new AddTypeParams();
		typeParams.setType(dataType);
		typeParams.setAuth( authToken().toString() );
		
		return wsClient().addType(typeParams);
	}

	public static Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> saveObject(String id, ObjectData data, boolean registerType) throws Exception {
		String dataType = data.getClass().getSimpleName();
		
		if(registerType){
			System.out.print("Registering data type " + dataType + ": ");
			try{
				Long ret = registerDataType(dataType);
				System.out.println(ret);
			}
			catch(Exception e){
				System.out.println(" false...");
			}
		}
		
		SaveObjectParams params = new SaveObjectParams();
		params.setAuth(authToken().toString());
		params.setCompressed(0L);
		params.setData(data);
		params.setId(id); 
		params.setJson(0L); 
		params.setType(dataType);
		
		Map<String, String> metadata = new HashMap<String, String>();
		params.setMetadata(metadata);
		
		params.setWorkspace(workspaceName);
		Tuple11<String, String, String, Long, String, String, String, String, String, String, Map<String,String>> ret = wsClient().saveObject(params);
		
		System.out.println("Saving object:");
		System.out.println(ret.getE1());
		System.out.println(ret.getE2());
		System.out.println(ret.getE3());
		System.out.println(ret.getE4());
		System.out.println(ret.getE5());
		System.out.println(ret.getE6());
		System.out.println(ret.getE7());
		System.out.println(ret.getE8());
		System.out.println(ret.getE9());
		System.out.println(ret.getE10());
		System.out.println(ret.getE11());
				
		
		return ret;		
	}
}
