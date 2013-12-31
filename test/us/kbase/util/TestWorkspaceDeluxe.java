package us.kbase.util;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import us.kbase.auth.AuthException;
import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.auth.TokenFormatException;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.workspace.GetModuleInfoParams;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.ModuleInfo;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.RegisterTypespecParams;
import us.kbase.workspace.SetGlobalPermissionsParams;
import us.kbase.workspace.TypeInfo;
import us.kbase.workspace.WorkspaceClient;

public class TestWorkspaceDeluxe {

	private static final String ADMIN_USER_NAME = "kazakov";
	private static final String ADMIN_PASSWORD = "1475.kafa";

	private static WorkspaceClient _wsClient = null;
	private static final String USER_NAME = "aktest";
	private static final String PASSWORD = "1475rokegi";
	private static final String workspaceName = "AKtest";
	private static final String WS_SERVICE_URL = "http://140.221.84.209:7058";

	protected static WorkspaceClient wsClient() {
		if(_wsClient == null)
		{
			URL workspaceClientUrl;
			try {
				AuthToken authToken = AuthService.login(ADMIN_USER_NAME, new String(ADMIN_PASSWORD)).getToken();
				//AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
				workspaceClientUrl = new URL (WS_SERVICE_URL);
				
				_wsClient = new WorkspaceClient(workspaceClientUrl, authToken);
				_wsClient.setAuthAllowedForHttp(true);
				_wsClient.setConnectionReadTimeOut(1000000);
			} catch (MalformedURLException e) {
				System.err.println("Bad URL? Unable to communicate with workspace service at" + WS_SERVICE_URL);
				e.printStackTrace();
			} catch (TokenFormatException e) {
				System.err.println("Unable to authenticate");
				e.printStackTrace();
			} catch (UnauthorizedException e) {
				System.err.println("Unable to authenticate in workspace service at" + WS_SERVICE_URL);
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("Unable to communicate with workspace service at" + WS_SERVICE_URL);
				e.printStackTrace();
			} catch (AuthException e) {
				System.err.println("Authorization error");
				e.printStackTrace();
			}
		}
		return _wsClient;
	} 

	@Test
	public void testRegisterModule() throws Exception {
		//AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		//WsDeluxeUtil.wsClient(authToken.toString()).requestModuleOwnership("MEME");
		fail("Nothing to test");
		
	}

	@Test
	public void testWsRegisterType() throws Exception {
		
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		RegisterTypespecParams params = new RegisterTypespecParams();
		//String specFileName = "/home/kbase/dev_container/modules/inferelator/kbase_inferelator.spec";
		String specFileName = "/home/kbase/dev_container/modules/cmonkey/kbase_cmonkey.spec";
		//String specFileName = "/home/kbase/dev_container/modules/meme/kbase_meme.spec";
		String spec = "";
		BufferedReader br = null;
		try {
			String line = null;
			br = new BufferedReader(new FileReader(specFileName));
			while ((line = br.readLine()) != null) {
				spec += line + "\n";
			}
		} catch (IOException e) {
			System.out.println(specFileName + "read error\n" + e.getLocalizedMessage());
		} finally {
			if (br != null) {
				br.close();
			}
		}
		params.setSpec(spec);

//		params.setMod("Inferelator");
//		List<String> addTypes = new ArrayList<String>();
//		types.add("ExpressionDataSeries");
//		types.add("CmonkeyRunResult");
//		types.add("CmonkeyNetwork");
//		types.add("CmonkeyCluster");
//		types.add("CmonkeyMotif");
//		types.add("InferelatorRunResult");

/*		types.add("MemeMotif");
		types.add("MemeRunResult");
		types.add("TomtomRunResult");
		types.add("MastRunResult");
		types.add("MemePSPM");
		types.add("MemePSPMCollection");
		types.add("MemeSite");
		types.add("MastHit");*/
//		params.setNewTypes(addTypes);
/*		List<String> removeTypes = new ArrayList<String>();
		removeTypes.add("CmonkeyNetwork");
		removeTypes.add("CmonkeyCluster");
		removeTypes.add("CmonkeyMotif");*/
//		removeTypes.add("MemeMotif");
//		removeTypes.add("MemePSPM");
//		removeTypes.add("MemeSite");
//		removeTypes.add("MastHit");
//		params.setRemoveTypes(removeTypes);
		
		params.setDryrun(1L);
		Map<String,String> result = WsDeluxeUtil.wsClient(authToken.toString()).registerTypespec(params);
		//Map<String,String> result = wsClient().registerTypespec(params);
		System.out.println(result.toString());
		assertNotNull(result);
		
	}

	@Test
	public void testWsModuleInfo() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		GetModuleInfoParams params = new GetModuleInfoParams(); 
		params.setMod("Cmonkey");
		ModuleInfo moduleInfo = WsDeluxeUtil.wsClient(authToken.toString()).getModuleInfo(params);
		System.out.println("Description " + moduleInfo.getDescription());
		System.out.println("Owners " + moduleInfo.getOwners().toString());
		System.out.println("Spec " + moduleInfo.getSpec());
		System.out.println("Functions " + moduleInfo.getFunctions().toString());
		System.out.println("Versions " + moduleInfo.getVer());
		//System.out.println("Types " + moduleInfo.getTypes().toString());
		
		assertNotNull(moduleInfo);
	}

	@Test
	public void testWsTypeInfo() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		String type = "Cmonkey.CmonkeyMotif"; 
		TypeInfo typeInfo = WsDeluxeUtil.wsClient(authToken.toString()).getTypeInfo(type);
		System.out.println("Description " + typeInfo.getDescription());
		System.out.println("Type definition from spec " + typeInfo.getSpecDef());
		System.out.println("Type definition " + typeInfo.getTypeDef());
		System.out.println("Module versions " + typeInfo.getModuleVers().toString());
		System.out.println("Type versions " + typeInfo.getTypeVers().toString());
		System.out.println("Used types " + typeInfo.getUsedTypeDefs().toString());
		System.out.println("Using functions " + typeInfo.getUsingFuncDefs().toString());
		System.out.println("Using types " + typeInfo.getUsingTypeDefs().toString());
		
		assertNotNull(typeInfo);
	}	
	
	@Test
	public void testWsReleaseModule() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		//String module = "MEME"; 
		String module = "Cmonkey";
		List<String> result = WsDeluxeUtil.wsClient(authToken.toString()).releaseModule(module);
		System.out.println(result.toString());
		
		assertNotNull(result);
	}


	@Test
	public void testWsInfo() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		//String module = "MEME"; 
		SetGlobalPermissionsParams params = new SetGlobalPermissionsParams().withWorkspace("AKtest").withNewPermission("r");
		WsDeluxeUtil.wsClient(authToken.toString()).setGlobalPermission(params);
	}

	
	@Test
	public void testWsListObjects() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		ListObjectsParams params = new ListObjectsParams();
		//String type = "ExpressionServices.ExpressionSeries-1.0";
		List<String> workspaces = new ArrayList<String>();
		workspaces.add("AKtest");
		//workspaces.add("networks_typed_objects_examples");
		//params.setType(type);
		params.setWorkspaces(workspaces);
		List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> typeInfo = WsDeluxeUtil.wsClient(authToken.toString()).listObjects(params);
		for (Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> object: typeInfo){
			System.out.println(object.getE3() + " : " + object.getE2());
		}
		/*System.out.println(typeInfo.get(0).getE1());
		System.out.println(typeInfo.get(0).getE2());
		System.out.println(typeInfo.get(0).getE3());
		System.out.println(typeInfo.get(0).getE4());
		System.out.println(typeInfo.get(0).getE5());
		System.out.println(typeInfo.get(0).getE6());
		System.out.println(typeInfo.get(0).getE7());
		System.out.println(typeInfo.get(0).getE8());
		System.out.println(typeInfo.get(0).getE9());
		System.out.println(typeInfo.get(0).getE10());
		System.out.println(typeInfo.get(0).getE11());*/
		
		assertNotNull(typeInfo);
	}	

	@Test
	public void testWsGetObjectInfo() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		String name = "kb|interactionset.5";
		List<ObjectIdentity> objectsIdentity = new ArrayList<ObjectIdentity>();
		ObjectIdentity objectIdentity = new ObjectIdentity().withName(name)
				.withWorkspace(workspaceName);
		objectsIdentity.add(objectIdentity);

		List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> objInfo = WsDeluxeUtil.wsClient(authToken.toString()).getObjectInfo(objectsIdentity, null);
		for (Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> object: objInfo){
			System.out.println(object.getE1());
			System.out.println(object.getE2());
			System.out.println(object.getE3());
			System.out.println(object.getE4());
			System.out.println(object.getE5());
			System.out.println(object.getE6());
			System.out.println(object.getE7());
			System.out.println(object.getE8());
			System.out.println(object.getE9());
			System.out.println(object.getE10());
			System.out.println(object.getE11());
			System.out.println("");

		}
		/*System.out.println(typeInfo.get(0).getE1());
		System.out.println(typeInfo.get(0).getE2());
		System.out.println(typeInfo.get(0).getE3());
		System.out.println(typeInfo.get(0).getE4());
		System.out.println(typeInfo.get(0).getE5());
		System.out.println(typeInfo.get(0).getE6());
		System.out.println(typeInfo.get(0).getE7());
		System.out.println(typeInfo.get(0).getE8());
		System.out.println(typeInfo.get(0).getE9());
		System.out.println(typeInfo.get(0).getE10());
		System.out.println(typeInfo.get(0).getE11());*/
		
		assertNotNull(objInfo);
	}	

	
	@Test
	public void testWsReadObject() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		String name = "test_Halobacterium__series";
		//String exampleWs = "networks_typed_objects_examples";
		
		ObjectData output = WsDeluxeUtil.getObjectFromWorkspace(workspaceName, name, authToken.toString());
		BufferedWriter writer = new BufferedWriter(new FileWriter("string.txt"));
		writer.write(output.getData().toString());
		writer.close();

		//System.out.println(output.getData().toString());
		assertNotNull(output);

	}	
	

	@Test
	public void testWsDeleteObject() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		List<ObjectIdentity> objectsIdentity = new ArrayList<ObjectIdentity>();
		
		String[] names = {
					"kb|cmonkeyrunresult.112",
					"kb|cmonkeyrunresult.107",
					"kb|cmonkeyrunresult.106",
					"kb|cmonkeyrunresult.105",
					"kb|interactionset.6",
					"kb|interactionset.5"
					}; 

		for (String name : names){
			String ref = workspaceName + "/" + name;
			ObjectIdentity objectIdentity = new ObjectIdentity().withRef(ref);
			objectsIdentity.add(objectIdentity);
		}
		WsDeluxeUtil.wsClient(authToken.toString()).deleteObjects(objectsIdentity);

	}	

}
