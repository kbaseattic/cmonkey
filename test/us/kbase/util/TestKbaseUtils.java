package us.kbase.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import us.kbase.auth.AuthException;
import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.kbasegenomes.Genome;
import us.kbase.kbasenetworks.InteractionSet;

public class TestKbaseUtils {

	private static final String USER_NAME = "";
	private static final String PASSWORD = "";
	private static final String workspaceName = "";
	private static AuthToken token = null;
	private String testGenomeRef = "ENIGMA_KBASE/Halobacterium_sp_NRC-1";//"ENIGMA_KBASE/Desulfovibrio_vulgaris_Hildenborough";//

	@Before
	public void setUp() throws Exception {
		try {
			token = AuthService.login(USER_NAME, new String(PASSWORD)).getToken();
		} catch (AuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testGenomeImport() throws Exception {
		GenomeImporter reader = new GenomeImporter("Halobacterium_sp_", null, "/home/kbase/cmonkey20131126/cache/", workspaceName, token.toString());
		Genome result = reader.readGenomeWithKbIds();
		
		
		System.out.println(result.getId());
		System.out.println(result.getScientificName());
		System.out.println(result.getSource());
		System.out.println(result.getContigsetRef());
		System.out.println(result.getDomain());
		System.out.println(result.getContigIds().toString());
		System.out.println(result.getFeatures().get(0).getAliases().toString());
		System.out.println(result.getFeatures().get(0).getId());
		System.out.println(result.getFeatures().get(0).getLocation().get(0).toString());
		System.out.println(result.getFeatures().get(100).getAliases().toString());
		
		assertNotNull(result);
		assertEquals(result.getContigIds().size(), 2);
		assertEquals(result.getScientificName(), "Halobacterium sp. NRC-1");
	}

	@Test
	public void testGenomeImportExtId() throws Exception {
		//GenomeImporter reader = new GenomeImporter("Halobacterium_sp_", "VNG", "/home/kbase/cmonkey20131126/cache/", workspaceName, token.toString());
		GenomeImporter reader = new GenomeImporter("", "DVU", "/home/kbase/Documents/dvh/", workspaceName, token.toString());
		String id = "Desulfovibrio_vulgaris_Hildenborough";
		//String id = "Halobacterium_sp_NRC-1";
		Genome result = reader.readGenomeWithExternalIds(id);
		
		
		System.out.println(result.getId());
		System.out.println(result.getScientificName());
		System.out.println(result.getSource());
		System.out.println(result.getContigsetRef());
		System.out.println(result.getDomain());
		System.out.println(result.getContigIds().toString());
		System.out.println(result.getFeatures().get(0).getAliases().toString());
		System.out.println(result.getFeatures().get(0).getId());
		System.out.println(result.getFeatures().get(0).getLocation().get(0).toString());
		System.out.println(result.getFeatures().get(100).getAliases().toString());
		
		assertNotNull(result);
//		assertEquals(2, result.getContigIds().size());
//		assertEquals("Halobacterium sp. NRC-1", result.getScientificName());
	}

	@Test
	public void testSeriesImport() throws Exception {
		//To use KBase IDs for samples and series, set namePrefix to null 
		String fileName = "/home/kbase/Documents/dvh/dvu-ratios.tsv";//"test/hal-ratios.tsv";//
		String genomeId = "Desulfovibrio_vulgaris_Hildenborough";//"Halobacterium_sp_NRC-1";//
		String namePrefix = "D_vulgaris_Hildenborough_expression";//"Halobacterium_sp_expression";//
		ExpressionSeriesImporter importer = new ExpressionSeriesImporter (genomeId, fileName, workspaceName, token.toString());
		List<String> result = importer.importExpressionSeriesFile(namePrefix);
		
		
		System.out.println(result.toString());
		
		assertNotNull(result);
	}

	@Test
	public void testReadGenome() throws Exception {
		String genomeRef = "AKtest/kb|genome.9";
		Genome result = WsDeluxeUtil.getObjectFromWsByRef(genomeRef, token.toString()).getData().asClassInstance(Genome.class);
		
		/*System.out.println(result.getId());
		System.out.println(result.getScientificName());
		System.out.println(result.getSource());
		System.out.println(result.getContigsetRef());
		System.out.println(result.getDomain());
		System.out.println(result.getContigIds().toString());
		System.out.println(result.getFeatures().get(0).getAliases().toString());
		System.out.println(result.getFeatures().get(0).getId());
		System.out.println(result.getFeatures().get(0).getLocation().get(0).toString());
		System.out.println(result.getFeatures().get(100).getAliases().toString());*/
		System.out.println(result.getFeatures().get(0).toString());
		System.out.println(result.getFeatures().get(41).toString());
		
		assertNotNull(result);
		assertEquals(result.getContigIds().size(), 2);
		assertEquals(result.getScientificName(), "Halobacterium sp. NRC-1");
		
		
	}

	@Test
	public void testGenomeExport() throws Exception {
		String genomeRef = "AKtest/kb|genome.9";
		GenomeExporter.writeGenome(genomeRef, null, null, token.toString());
		
		fail("Not yet implemented");
	}

	@Test
	public void testStringImport() throws Exception {
		String ncbiId = "64091";
		String name = "Halobacterium_sp_STRING";//"D_vulgaris_STRING";
		//NetworkImporter importer = new NetworkImporter(testGenomeRef, ncbiId, "/home/kbase/Documents/dvh/", workspaceName, token.toString());
		NetworkImporter importer = new NetworkImporter(testGenomeRef, ncbiId, "/home/kbase/cmonkey20131126/cache/", workspaceName, token.toString());
		InteractionSet result = importer.ImportStringFile(name);
		
		
		System.out.println(result.getId());
		System.out.println(result.getDescription());
		System.out.println(result.getSource().getResourceUrl());
		System.out.println(result.getSource().getReference());
		System.out.println(result.getInteractions().get(0).getAdditionalProperties().get("STRING_SCORE"));
		
		assertNotNull(result);
		assertEquals(result.getName(), ncbiId);
	}

	@Test
	public void testStringExport() throws Exception {
		String networkRef = "AKtest/kb|interactionset.6";
		NetworkExporter.exportString(networkRef, null, null, token.toString());
		
		fail("Not yet implemented");
	}

	
	@Test
	public void testOperonsImport() throws Exception {
		String ncbiId = "64091";
		String name = "Halobacterium_sp_operons";//"D_vulgaris_Hildenborough_operons";//
		String dataDir = "/home/kbase/cmonkey20131126/cache/";//"/home/kbase/Documents/dvh/";//
		NetworkImporter importer = new NetworkImporter(testGenomeRef, ncbiId, dataDir, workspaceName, token.toString());
		InteractionSet result = importer.ImportOperonsFile(name);
		
		
		System.out.println(result.getId());
		System.out.println(result.getDescription());
		System.out.println(result.getSource().getResourceUrl());
		System.out.println(result.getSource().getReference());
		System.out.println(result.getInteractions().get(0).getAdditionalProperties().get("SAME_OPERON"));
		
		assertNotNull(result);
		assertEquals(result.getName(), ncbiId);
	}

	@Test
	public void testOperonsExport() throws Exception {
		String networkRef = "AKtest/kb|interactionset.3";
		NetworkExporter.exportOperons(networkRef, null, null, token.toString());
		
		fail("Not yet implemented");
	}

}
