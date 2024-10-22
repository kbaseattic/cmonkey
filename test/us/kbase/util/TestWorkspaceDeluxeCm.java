package us.kbase.util;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.Tuple8;
import us.kbase.kbasegenomes.ContigSet;
import us.kbase.kbasegenomes.Genome;
import us.kbase.workspace.GetModuleInfoParams;
import us.kbase.workspace.ListObjectsParams;
import us.kbase.workspace.ModuleInfo;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.RegisterTypespecParams;
import us.kbase.workspace.SetPermissionsParams;
import us.kbase.workspace.TypeInfo;
import us.kbase.workspace.WorkspaceIdentity;

public class TestWorkspaceDeluxeCm {

	private static final String USER_NAME = "";
	private static final String PASSWORD = "";
	private static final String workspaceName = "nwportal:nwportal_data";//"ENIGMA_KBASE";

	@Test
	public void testRegisterModule() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		WsDeluxeUtil.wsClient(authToken.toString()).requestModuleOwnership("Inferelator");
		fail("Nothing to test");
	}

	@Test
	public void testWsRegisterType() throws Exception {
		
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		RegisterTypespecParams params = new RegisterTypespecParams();
		String specFileName = "/home/kbase/dev_container/modules/inferelator/Inferelator.spec";
		//String specFileName = "/home/kbase/dev_container/modules/cmonkey/Cmonkey.spec";
		//String specFileName = "/home/kbase/dev_container/modules/meme/MEME.spec";
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

//		params.setMod("MEME");
		List<String> addTypes = new ArrayList<String>();
//		addTypes.add("CmonkeyRunResult");
		addTypes.add("GeneList");
		addTypes.add("InferelatorRunResult");

//		addTypes.add("MemePSPM");
//		addTypes.add("MemeSite");
//		addTypes.add("MastHit");
//		addTypes.add("MemeRunResult");
//		addTypes.add("TomtomRunResult");
//		addTypes.add("MastRunResult");
//		addTypes.add("MemePSPMCollection");
		params.setNewTypes(addTypes);
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
		params.setMod("MEME");
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
		//String module = "Cmonkey"; 
		String module = "Inferelator";
		List<String> result = WsDeluxeUtil.wsClient(authToken.toString()).releaseModule(module);
		System.out.println(result.toString());
		
		assertNotNull(result);
	}


	@Test
	public void testSetWsPermission() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		//String module = "MEME"; 
		List<String> users = new ArrayList<String>();
		users.add("aktest");
		//SetGlobalPermissionsParams params = new SetGlobalPermissionsParams().withWorkspace("AKtest").withNewPermission("r");
		SetPermissionsParams params = new SetPermissionsParams().withWorkspace("AKtest").withNewPermission("w").withUsers(users);
		WsDeluxeUtil.wsClient(authToken.toString()).setPermissions(params);
	}

	@Test
	public void testWsInfo() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		WorkspaceIdentity wsI = new WorkspaceIdentity().withWorkspace("AKtest");
		Tuple8<Long, String, String, String, Long, String, String, String> res = WsDeluxeUtil.wsClient(authToken.toString()).getWorkspaceInfo(wsI);
		System.out.println(res.getE1());
		System.out.println(res.getE2());
		System.out.println(res.getE3());
		System.out.println(res.getE4());
		System.out.println(res.getE5());
		System.out.println(res.getE6());
		System.out.println(res.getE7());
		System.out.println(res.getE8());
		assertNotNull(res);
	}
	
	@Test
	public void testWsListObjects() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		ListObjectsParams params = new ListObjectsParams();
		//String type = "ExpressionServices.ExpressionSeries-1.0";
		List<String> workspaces = new ArrayList<String>();
		workspaces.add("AKtest");//'(workspaceName);
		//workspaces.add("networks_typed_objects_examples");
		//params.setType(type);
		params.setWorkspaces(workspaces);
		List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> typeInfo = WsDeluxeUtil.wsClient(authToken.toString()).listObjects(params);

/*		for (Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> object: typeInfo){
			System.out.println(object.getE3() + " : " + object.getE2());
		}
*/
		BufferedWriter writer = new BufferedWriter(new FileWriter("list.txt"));
		for (Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> object: typeInfo){
			writer.write(object.getE3() + " : " + object.getE2() + "\n");
			System.out.println(object.getE3() + " : " + object.getE2());
		}
		writer.close();

		
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
		String name = "kb|sequenceset.9";
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
		String name = "eco.genome";
		//String exampleWs = "networks_typed_objects_examples";
		
		ObjectData output = WsDeluxeUtil.getObjectFromWorkspace("AKtest", name, authToken.toString());
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("string.txt"));
		writer.write(output.getData().toString());
		writer.close();

		//System.out.println(output.getData().toString());
		assertNotNull(output);
	}	

	@Test
	public void testWsReadObjectByRef() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		String ref = "2672/13/1";
		//String exampleWs = "networks_typed_objects_examples";
		
		ObjectData output = WsDeluxeUtil.getObjectFromWsByRef(ref, authToken.toString());
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("string.txt"));
		writer.write(output.getData().toString());
		writer.close();

		//System.out.println(output.getData().toString());
		Genome genome = output.getData().asClassInstance(Genome.class);
		System.out.println(genome.getScientificName());
		//System.out.println(genome.getContigs().get(0).getName());
		
		assertNotNull(output);
	}	

	@Test
	public void testWsDeleteObject() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		List<ObjectIdentity> objectsIdentity = new ArrayList<ObjectIdentity>();
		
		String[] names = {
				"kb|tomtomrunresult.64"
				}; 

		for (String name : names){
			String ref = workspaceName + "/" + name;
			ObjectIdentity objectIdentity = new ObjectIdentity().withRef(ref);
			objectsIdentity.add(objectIdentity);
		}
		WsDeluxeUtil.wsClient(authToken.toString()).deleteObjects(objectsIdentity);
	}
	
	@Test
	public void testWsCopyObject() throws Exception {
		AuthToken authToken = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		String name = "eco.genome";
		
		ObjectData output = WsDeluxeUtil.getObjectFromWorkspace("AKtest", name, authToken.toString());
		
		
	}	

}
