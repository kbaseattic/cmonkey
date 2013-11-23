package us.kbase.cmonkey;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MicrobesonlineTest {
	Microbesonline microbesonline = null;

	@Before
	public void setUp() throws Exception {
		microbesonline = new Microbesonline();
	}

	@After
	public void tearDown() throws Exception {
		microbesonline = null;
	}

	@Test
	public final void testMicrobesonline() {
		assertNotNull(microbesonline);
	}

	@Test
	public final void testGetGenomeForGene() throws Exception {
		String result = microbesonline.getGenomeForGene("VNG1951G");
		assertEquals("Halobacterium sp. NRC-1", result);
	}

	@Test
	public final void testGetTaxidForGenome() throws Exception {
		String result = microbesonline.getTaxidForGenome("Halobacterium sp. NRC-1");
		assertEquals("64091", result);
	}


}
