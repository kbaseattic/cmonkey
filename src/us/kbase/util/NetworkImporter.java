package us.kbase.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import us.kbase.auth.TokenFormatException;
import us.kbase.cmonkey.CmonkeyServerConfig;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UObject;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.idserverapi.IDServerAPIClient;
import us.kbase.kbasegenomes.Feature;
import us.kbase.kbasegenomes.Genome;
import us.kbase.networks.DatasetSource;
import us.kbase.networks.Interaction;
import us.kbase.networks.InteractionSet;

public class NetworkImporter {
	
	private static final String PREFIX_OPERONS = "gnc";
	private static final String POSTFIX_OPERONS = ".named";
	private static final String ID_SERVICE_URL = CmonkeyServerConfig.ID_SERVICE_URL;

	private static IDServerAPIClient _idClient = null;

	private String ncbiId = "";
	private String workDir = "/home/kbase/cmonkey20131126/cache/";
	private String token = null;
	private String wsId = null;
	private Genome genome;
	private HashMap<String, String> aliases;

	
	public NetworkImporter (String genomeRef, String taxId, String workDir, String wsId, String token) throws Exception{
		this.ncbiId = taxId;
		if (workDir == null) {
			System.out.println("No working dir assigned, " + this.workDir + "will be used instead");
		} else {
			this.workDir = workDir;
		}
		if (token == null) {
			throw new Exception("Token not assigned");
		} else {
			this.token = token;
		}
		
		if (wsId == null) {
			throw new Exception("Workspace name not assigned");
		} else {
			this.wsId = wsId;
		}

		this.genome = WsDeluxeUtil.getObjectFromWsByRef(genomeRef, token).getData().asClassInstance(Genome.class);
		this.aliases = readFeatures(this.genome);
	}

	private HashMap<String,String> readFeatures (Genome genome) {
		HashMap<String,String> aliases = new HashMap<String, String>(); 
		for (Feature f : genome.getFeatures()){
			String id = f.getId();
			for (String a : f.getAliases()){
				//System.out.println("alias = " + a + " : id = " + id);
				aliases.put(a, id);
			}
		}
		return aliases;
	}

	protected static IDServerAPIClient idClient() {
		if (_idClient == null) {
			URL idServerUrl = null;
			try {
				idServerUrl = new URL(ID_SERVICE_URL);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_idClient = new IDServerAPIClient(idServerUrl);
		}
		return _idClient;
	}
	
	public InteractionSet ImportOperonsFile (String name) throws TokenFormatException, IOException, JsonClientException{
		List<Interaction> interactions = new ArrayList<Interaction>();
		String fileName = workDir + PREFIX_OPERONS + ncbiId + POSTFIX_OPERONS;
		System.out.println(fileName);
		BufferedReader br = null;
		List<String> fileData = new ArrayList<String>(); 
		try {
			
			br = new BufferedReader(new FileReader(fileName));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.equals("Gene1	Gene2	SysName1	SysName2	Name1	Name2	bOp	pOp	Sep	MOGScore	GOScore	COGSim	ExprSim")) {
					// do nothing
				} else if (line.equals("")){
					// do nothing
				} else {
					fileData.add(line);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
					e.printStackTrace();
				}
			}
		}
		
		Long startKbaseId = getKbaseIds("Interaction", new Long(fileData.size()));

		for (String line : fileData){
			String[] fields = line.split("\t");
			String id1 = getFeatureId(fields[2]);
			String id2 = getFeatureId(fields[3]);
			if (id1 != null && id2 != null) {
				Interaction interaction = new Interaction().withEntity1Id(id1).withEntity2Id(id2).withId("kb|interaction." + startKbaseId.toString()).withType("operon");
				startKbaseId++;
				Map<String, Double> scores = new HashMap<String, Double>();
				Double bOp = 0.0D; 
				if (fields[6].equals("TRUE")){
					bOp = 1.0D;
				}
				scores.put("SAME_OPERON", bOp);
				scores.put("SAME_OPERON_SCORE", Double.parseDouble(fields[7]));
				scores.put("GENE_DISTANCE", Double.parseDouble(fields[8]));
				scores.put("CONSERVATION_SCORE", Double.parseDouble(fields[9]));
				Double goScore = null;
				if (fields[10].equals("NA")){
					//do nothing
				} else {
					goScore = Double.parseDouble(fields[10]);
					scores.put("GO_SCORE", goScore);
				}
				Double cogSim = null;
				if (fields[11].equals("Y")){
					cogSim = 1.0D;
					scores.put("COG_SIM", cogSim);
				} else if (fields[11].equals("N")){
					cogSim = 0.0D;
					scores.put("COG_SIM", cogSim);
				}
				Double exprSim = null;
				if (fields[12].equals("NA")){
					//do nothing
				} else {
					exprSim = Double.parseDouble(fields[12]);
					scores.put("EXPR_SIM", exprSim);
				}
				interaction.setScores(scores);
				interactions.add(interaction);
			} else {
				System.out.println("Unknown feature ID in line " + line);
			}
		}
		
		DatasetSource source = new DatasetSource().withId(getKbaseId("DatasetSource")).withName(ncbiId).withReference("undefined").withDescription("Imported operons data").withResourceUrl("http://microbesonline.org/operons/gnc"+ncbiId+".html");
		InteractionSet set = new InteractionSet().withName(ncbiId).withSource(source).withInteractions(interactions).withType("operons");
		if (name == null) {
			set.setId(getKbaseId("InteractionSet"));
			WsDeluxeUtil.saveObjectToWorkspace(UObject.transformObjectToObject(set, UObject.class), "Networks.InteractionSet", wsId, set.getId(), token);
		} else {
			set.setId(name);
			WsDeluxeUtil.saveObjectToWorkspace(UObject.transformObjectToObject(set, UObject.class), "Networks.InteractionSet", wsId, name, token);
		}
				
		return set;
	}

	private String getFeatureId(String featureAlias) {
		String featureId = this.aliases.get(featureAlias);
		return featureId;
	}

	public InteractionSet ImportStringFile (String name) throws TokenFormatException, IOException, JsonClientException{
		List<Interaction> interactions = new ArrayList<Interaction>();
		String fileName = workDir + ncbiId + ".gz";
		System.out.println(fileName);
		BufferedReader br = null;
		List<String> fileData = new ArrayList<String>(); 
		try {
			
			br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(fileName))));
			String line = null;
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				if (line.equals("")) {
					// do nothing
				} else {
					fileData.add(line);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
					e.printStackTrace();
				}
			}
		}
		
		Long startKbaseId = getKbaseIds("Interaction", new Long(fileData.size()));
		
		for (String line : fileData){
			String[] fields = line.split("\t", -1);
			String id1 = getFeatureId(fields[0]);
			String id2 = getFeatureId(fields[1]);
			if (id1 != null && id2 != null) {
				Map<String, Double> scores = new HashMap<String, Double>();
				scores.put("STRING_SCORE", Double.parseDouble(fields[2]));
				interactions.add(new Interaction().withEntity1Id(id1).withEntity2Id(id2).withId("kb|interaction." + startKbaseId.toString()).withType("string").withScores(scores));
				startKbaseId++;
			} else {
				System.out.println("Unknown feature ID in line " + line);
			}
		}
		fileData = null;

/*		BufferedWriter writer = new BufferedWriter(new FileWriter("string.txt"));
		writer.write(interactions.toString());
		writer.close();
*/
		gc();
		DatasetSource source = new DatasetSource().withId(getKbaseId("DatasetSource")).withName(ncbiId).withReference("undefined").withDescription("Imported STRING data").withResourceUrl("http://networks.systemsbiology.net/string9/"+ncbiId+".gz");
		InteractionSet set = new InteractionSet().withName(ncbiId).withSource(source).withInteractions(interactions).withType("string");
		interactions = null;
		gc();
		
		if (name == null) {
			set.setId(getKbaseId("InteractionSet"));
			WsDeluxeUtil.saveObjectToWorkspace(UObject.transformObjectToObject(set, UObject.class), "Networks.InteractionSet", wsId, set.getId(), token);
		} else {
			set.setId(name);
			WsDeluxeUtil.saveObjectToWorkspace(UObject.transformObjectToObject(set, UObject.class), "Networks.InteractionSet", wsId, name, token);
		}
		return set;
	}
	
	protected static String getKbaseId(String entityType) {
		String returnVal = null;

		try {
			if (entityType.equals("Interaction")) {
				returnVal = "kb|interaction."
						+ idClient().allocateIdRange("kb|interaction", 1L)
								.toString();
			} else if (entityType.equals("InteractionSet")) {
				returnVal = "kb|interactionset."
						+ idClient().allocateIdRange("kb|interactionset", 1L)
								.toString();
			} else if (entityType.equals("DatasetSource")) {
				returnVal = "kb|datasetsource."
						+ idClient().allocateIdRange("kb|datasetsource", 1L)
								.toString();
			} else {
				System.err.println("ID requested for unknown type "
						+ entityType);
				return null;
			}
		} catch (UnauthorizedException e) {
			System.err.println("Unable to get KBase ID for " + entityType + " from " + ID_SERVICE_URL + ": Unauthorized Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Unable to get KBase ID for " + entityType + " from " + ID_SERVICE_URL + ": IO Exception");
			e.printStackTrace();
		} catch (JsonClientException e) {
			System.err.println("Unable to get KBase ID for " + entityType + " from " + ID_SERVICE_URL + ": Json error");
			e.printStackTrace();
		}
		return returnVal;
	}

	protected static Long getKbaseIds (String entityType, Long count) {

		Long returnVal = null;
		try {
			if (entityType.equals("Interaction")) {
				returnVal = idClient().allocateIdRange("kb|interaction", 1L);
			} else if (entityType.equals("InteractionSet")) {
				returnVal = idClient().allocateIdRange("kb|interactionset", 1L);
			} else if (entityType.equals("DatasetSource")) {
				returnVal = idClient().allocateIdRange("kb|datasetsource", 1L);
			} else {
				System.err.println("ID requested for unknown type "
						+ entityType);
				return null;
			}
		} catch (UnauthorizedException e) {
			System.err.println("Unable to get KBase ID for " + entityType + " from " + ID_SERVICE_URL + ": Unauthorized Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Unable to get KBase ID for " + entityType + " from " + ID_SERVICE_URL + ": IO Exception");
			e.printStackTrace();
		} catch (JsonClientException e) {
			System.err.println("Unable to get KBase ID for " + entityType + " from " + ID_SERVICE_URL + ": Json error");
			e.printStackTrace();
		}
		return returnVal;
	}
	
	public static void gc() {
		Object obj = new Object();
		@SuppressWarnings("rawtypes")
		java.lang.ref.WeakReference ref = new java.lang.ref.WeakReference<Object>(
				obj);
		obj = null;
		while (ref.get() != null) {
			System.out.println("garbage collector");
			System.gc();
		}
	}


}