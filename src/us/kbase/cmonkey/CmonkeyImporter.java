package us.kbase.cmonkey;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.kbasenetworks.InteractionSet;
import us.kbase.util.NetworkImporter;

public class CmonkeyImporter {

	/**
	 * @param args
	 */
	private static final String USER_NAME = "kazakov";
	private static final String PASSWORD = "1475.kafa";
	private final static String workspaceName = "ENIGMA_KBASE";
	private static AuthToken token = null;
	private static String testGenomeRef = "ENIGMA_KBASE/Halobacterium_sp_NRC-1";//"AKtest/kb|genome.9";

	public static void main(String[] args) throws Exception {
		String ncbiId = "64091";
		String name = "Halobacterium_sp_STRING";
		token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		NetworkImporter importer = new NetworkImporter(testGenomeRef, ncbiId, "/home/kbase/cmonkey20131126/cache/", workspaceName, token.toString());
		InteractionSet result = importer.ImportStringFile(name);
		
		
		System.out.println(result.getId());
		System.out.println(result.getDescription());
		System.out.println(result.getSource().getResourceUrl());
		System.out.println(result.getSource().getReference());
		System.out.println(result.getInteractions().get(0).getAdditionalProperties().get("STRING_SCORE"));

	}

}
