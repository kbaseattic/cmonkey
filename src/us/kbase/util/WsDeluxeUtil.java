package us.kbase.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.kbase.auth.AuthToken;
import us.kbase.auth.TokenFormatException;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.WorkspaceClient;

public class WsDeluxeUtil {
	private static WorkspaceClient _wsClient = null;
	
	public static final String userName = "aktest";
	public static final String password = "1475rokegi";
	public static final String workspaceName = "AKtest";
	private static final String WS_SERVICE_URL = "http://140.221.84.209:7058";

	protected static WorkspaceClient wsClient(String token) {
		if(_wsClient == null)
		{
			URL workspaceClientUrl;
			try {
				workspaceClientUrl = new URL (WS_SERVICE_URL);
				AuthToken authToken = new AuthToken(token);
				_wsClient = new WorkspaceClient(workspaceClientUrl, authToken);
				_wsClient.setAuthAllowedForHttp(true);
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
			}
		}
		return _wsClient;
	} 
	
	protected static List<UObject> getObjectsFromWorkspace(String workspaceName,
			List<String> names, String token) {
		try {
			List<ObjectIdentity> objectsIdentity = new ArrayList<ObjectIdentity>();
			for (String name : names){
				System.out.println(name);
			ObjectIdentity objectIdentity = new ObjectIdentity().withWorkspace(workspaceName).withName(name);
			objectsIdentity.add(objectIdentity);
			}
			List<ObjectData> output = wsClient(token.toString()).getObjects(
					objectsIdentity);

			List<UObject> returnVal = new ArrayList<UObject>();
			
			for (ObjectData data : output) {
				returnVal.add(data.getData());
			}
			return returnVal;
		} catch (IOException e) {
			System.err.println("Unable to get objects " + names.toString()
					+ " from workspace " + workspaceName + ": IO Exception");
			e.printStackTrace();
		} catch (JsonClientException e) {
			System.err.println("Unable to get objects " + names.toString()
					+ " from workspace " + workspaceName + ": JSON Client Exception");
			e.printStackTrace();
		}
		return null;
	}

	protected static List<UObject> getObjectsFromWsByRef(
			List<String> refs, String token) {
		try {
			List<ObjectIdentity> objectsIdentity = new ArrayList<ObjectIdentity>();
			for (String ref : refs){
				System.out.println(ref);
			ObjectIdentity objectIdentity = new ObjectIdentity().withRef(ref);
			objectsIdentity.add(objectIdentity);
			}
			List<ObjectData> output = wsClient(token.toString()).getObjects(
					objectsIdentity);

			List<UObject> returnVal = new ArrayList<UObject>();
			
			for (ObjectData data : output) {
				returnVal.add(data.getData());
			}
			return returnVal;
		} catch (IOException e) {
			System.err.println("Unable to get objects " + refs.toString() + " : IO Exception");
			e.printStackTrace();
		} catch (JsonClientException e) {
			System.err.println("Unable to get objects " + refs.toString() + " : JSON Client Exception");
			e.printStackTrace();
		}
		return null;
	}

	
	protected static ObjectData getObjectFromWorkspace(String workspaceName,
			String name, String token) {
		try {
			List<ObjectIdentity> objectsIdentity = new ArrayList<ObjectIdentity>();
			ObjectIdentity objectIdentity = new ObjectIdentity().withName(name)
					.withWorkspace(workspaceName);
			objectsIdentity.add(objectIdentity);
			List<ObjectData> output = wsClient(token.toString()).getObjects(
					objectsIdentity);

			return output.get(0);
		} catch (IOException e) {
			System.err.println("Unable to get object " + name
					+ " from workspace " + workspaceName + ": IO Exception");
			e.printStackTrace();
		} catch (JsonClientException e) {
			System.err.println("Unable to get object " + name
					+ " from workspace " + workspaceName + ": JSON Client Exception");
			e.printStackTrace();
		}
		return null;
	}

	protected static ObjectData getObjectFromWsByRef(String ref, String token) {
		try {
			List<ObjectIdentity> objectsIdentity = new ArrayList<ObjectIdentity>();
			ObjectIdentity objectIdentity = new ObjectIdentity().withRef(ref);
			objectsIdentity.add(objectIdentity);
			List<ObjectData> output = wsClient(token.toString()).getObjects(
					objectsIdentity);

			return output.get(0);
		} catch (IOException e) {
			System.err.println("Unable to get object " + ref + ": IO Exeption");
			e.printStackTrace();
		} catch (JsonClientException e) {
			System.err.println("Unable to get object " + ref + ": JSON Client Exception");
			e.printStackTrace();
		}
		return null;
	}

	protected static void saveObjectToWorkspace(UObject object, String type,
			String workspaceName, String name, String token) {

		SaveObjectsParams params = new SaveObjectsParams();
		params.setWorkspace(workspaceName);

		ObjectSaveData objectToSave = new ObjectSaveData();
		objectToSave.setData(object);
		objectToSave.setName(name);
		objectToSave.setType(type);
		Map<String, String> metadata = new HashMap<String, String>();
		objectToSave.setMeta(metadata);
		List<ObjectSaveData> objectsData = new ArrayList<ObjectSaveData>();
		objectsData.add(objectToSave);
		params.setObjects(objectsData);

		List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>>> ret = null;
		try {
			ret = wsClient(token).saveObjects(params);
		} catch (IOException e) {
			System.err.println("Unable to write object to workspace at " + WS_SERVICE_URL + " : IO Exception");
			e.printStackTrace();
		} catch (JsonClientException e) {
			System.err.println("Unable to write object to workspace at " + WS_SERVICE_URL + " : JSON Client Exception");
			e.printStackTrace();
		}

		System.out.println("Saving object:");
		System.out.println(ret.get(0).getE1());
		System.out.println(ret.get(0).getE2());
		System.out.println(ret.get(0).getE3());
		System.out.println(ret.get(0).getE4());
		System.out.println(ret.get(0).getE5());
		System.out.println(ret.get(0).getE6());
		System.out.println(ret.get(0).getE7());
		System.out.println(ret.get(0).getE8());
		System.out.println(ret.get(0).getE9());
		System.out.println(ret.get(0).getE10());
		System.out.println(ret.get(0).getE11());

	}

}
