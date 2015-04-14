package util;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CSVParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		CSVParser p = new CSVParser();
		p.parse("merchantMenuofTest.csv");

		assertEquals(p.getInputColumnName().get(0), "description");
		assertEquals(p.getOutputColumnName().get(0), "status");

		assertEquals(p.getInputColumnValues().get(0).get("pk"), "43541693");
		
		//eval
		assertEquals(p.getInputColumnValues().get(0).get("ts"), "eval(new Date().getTime().toString())");
		// assertEquals(p.getInputColumnValues().get(0),"status");
	}

}
