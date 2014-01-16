package us.kbase.cmonkey;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.kbasenetworks.InteractionSet;
import us.kbase.util.NetworkImporter;

public class CmonkeyImporter {

	/**
	 * @param args
	 */
	private static final String USER_NAME = "aktest";
	private static final String PASSWORD = "1475rokegi";
	private final static String workspaceName = "AKtest";
	private static AuthToken token = null;
	private static String testGenomeRef = "AKtest/Desulfovibrio_vulgaris_Hildenborough";//"AKtest/kb|genome.9";

	public static void main(String[] args) throws Exception {
		String ncbiId = "882";
		String name = "D_vulgaris_STRING";
		token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		NetworkImporter importer = new NetworkImporter(testGenomeRef, ncbiId, "/home/kbase/Documents/dvh/", workspaceName, token.toString());
		InteractionSet result = importer.ImportStringFile(name);
		
		
		System.out.println(result.getId());
		System.out.println(result.getDescription());
		System.out.println(result.getSource().getResourceUrl());
		System.out.println(result.getSource().getReference());
		System.out.println(result.getInteractions().get(0).getAdditionalProperties().get("STRING_SCORE"));

	}

}
