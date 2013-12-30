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
import us.kbase.networks.InteractionSet;

public class TestKbaseUtils {

	private static final String USER_NAME = "aktest";
	private static final String PASSWORD = "1475rokegi";
	private static final String workspaceName = "AKtest";
	private static AuthToken token = null;
	private String testGenomeRef = "AKtest/kb|genome.8";

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
		GenomeImporter reader = new GenomeImporter("Halobacterium_sp", "/home/kbase/cmonkey20131126/cache/", workspaceName, token.toString());
		Genome result = reader.readGenome();
		
		
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
	public void testSeriesImport() throws Exception {
		String fileName = "test/hal-ratios.tsv";
		String genomeRef = "AKtest/kb|genome.8";
		String namePrefix = "Halobacterium_sp_NRC1";
		ExpressionSeriesImporter importer = new ExpressionSeriesImporter (genomeRef, fileName, workspaceName, token.toString());
		List<String> result = importer.importExpressionSeriesFile(namePrefix);
		
		
		System.out.println(result.toString());
		
		assertNotNull(result);
	}

	@Test
	public void testReadGenome() throws Exception {
		String genomeRef = "AKtest/kb|genome.8";
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
		String genomeRef = "AKtest/kb|genome.8";
		GenomeExporter.writeGenome(genomeRef, null, null, token.toString());
		
		fail("Not yet implemented");
	}

	@Test
	public void testStringImport() throws Exception {
		String ncbiId = "64091";
		String name = null;
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
		String name = null;
		NetworkImporter importer = new NetworkImporter(testGenomeRef, ncbiId, "/home/kbase/cmonkey20131126/cache/", workspaceName, token.toString());
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
