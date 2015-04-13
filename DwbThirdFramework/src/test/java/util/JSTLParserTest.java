package util;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JSTLParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllTag() {
		List<String> result = JSTLParser.getAllTag("util.SignUtil.sign(&ts=${ts}&menu_id=${menu_id})");
		assertEquals(result.get(0),"ts");
		assertEquals(result.get(1),"menu_id");
	}
	
	//匹配带错误的输入
	@Test
	public void testGetAllTag1() {
		List<String> result = JSTLParser.getAllTag("util.SignUtil.sign(&ts=${ts&menu_id=${menu_id})");
		assertEquals(result.get(0),"ts&menu_id=${menu_id");
	}

	//无匹配
	@Test
	public void testGetAllTag2() {
		List<String> result = JSTLParser.getAllTag("util.SignUtil.sign(&ts=lalal})");
		assertEquals(result.size(),0);
	}
	
	@Test
	public void testjstlReplacement(){
		
		String source = "util.SignUtil.sign(&ts=${ts}&menu_id=${menu_id})";
		
		Map<String,String> lookUp = new HashMap<String, String>();
		lookUp.put("ts", "112");
		lookUp.put("menu_id", "1001");
		
		String result = JSTLParser.jstlReplacement(lookUp, source);
		
		assertEquals("util.SignUtil.sign(&ts=112&menu_id=1001)",result);
	}
}
