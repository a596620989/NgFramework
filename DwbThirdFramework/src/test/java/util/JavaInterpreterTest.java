package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JavaInterpreterTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEval() {

		assertEquals(JavaInterpreter.evalRecursively("eval(1+4)"), "5");
		assertTrue(Math.abs(Long.valueOf(JavaInterpreter
				.evalRecursively("eval(new Date().getTime().toString())"))
				- new Date().getTime()) < 100);
		
		assertTrue(Math.abs(Long.valueOf(JavaInterpreter
				.evalRecursively("eval(System.currentTimeMillis())"))
				- new Date().getTime()) < 100);

		assertEquals(JavaInterpreter.evalRecursively("eval(\"11\"==\"11\")"), "true");
		
		//内建数据类型
		JavaInterpreter.evalRecursively("eval(new String())");
		//用户类型
		JavaInterpreter.evalRecursively("eval(new util.JsonParser())");
		
		assertEquals("4124BC0A9335C27F086F24BA207A4912",JavaInterpreter.evalRecursively("eval(util.SignUtil.sign(\"aa\"))"));
		assertEquals("D064F8858FE4E69115ACD386E33D0209",JavaInterpreter.evalRecursively("eval(util.SignUtil.sign(\"&menu_id=101002\"))"));
		//时间戳每次都会变, 故无法assert
		assertNotSame("nimei",JavaInterpreter.evalRecursively("eval(util.SignUtil.sign(\"&ts=eval(new Date().getTime().toString())&menu_id=101002\"))"));
		
		assertEquals("6",JavaInterpreter.evalRecursively("eval(1+eval(2+3))"));
		
		assertEquals("5",JavaInterpreter.evalRecursively("eval(1+eval(2+eval(1+1)))"));
		
		assertEquals("11",JavaInterpreter.evalRecursively("eval(1+eval(2+eval(1+1)+eval(2*3)))"));
		
		
	}

	@Test
	public void testEvalMatch() {
		assertEquals(JavaInterpreter.match("eval(11.toString())"), "11.toString()");

		assertEquals(JavaInterpreter.match("eval(new String())"), "new String()");

		assertEquals(JavaInterpreter.match("eval(new Date().getTime())"),
				"new Date().getTime()");

		assertEquals(JavaInterpreter.match("eval()"), "");

		assertEquals(JavaInterpreter.match("eval(hahaha)"), "hahaha");

		assertEquals(JavaInterpreter.match("evalhaha()"), null);
		assertEquals(JavaInterpreter.match("eval("), null);

		// 前缀
		assertEquals(JavaInterpreter.match("1+eval()"), "");
		// 后缀
		assertEquals(JavaInterpreter.match("eval()+1"), "");
		
		//Sign格式调用, 其中$ts=等是字符串, ${}代表解析当前行对应的值.
		assertEquals(JavaInterpreter.match("eval(util.SignUtil.sign(&ts=${ts}&menu_id=${menu_id}))"), "util.SignUtil.sign(&ts=${ts}&menu_id=${menu_id})");
	
		//balanced bracket!嵌套嵌套
		assertEquals("1+1",JavaInterpreter.match("2+eval(1+1)+eval(2*3)"));
	}

}
