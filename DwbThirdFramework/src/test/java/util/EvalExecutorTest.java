package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EvalExecutorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEval() {

		assertEquals(EvalExecutor.eval("eval(1+4)"), "5");
		// 务必注意在js中使用toString将Number转为字符串类型, js只有Number类型(64位), 跟java不兼容
		assertTrue(Math.abs(Long.valueOf(EvalExecutor
				.eval("eval(new Date().getTime().toString())"))
				- new Date().getTime()) < 100);

		assertEquals(EvalExecutor.eval("eval(\"11\"==\"11\")"), "true");
	}

	@Test
	public void testEvalMatch() {
		assertEquals(EvalExecutor.match("eval(11.toString())"), "11.toString()");

		assertEquals(EvalExecutor.match("eval(new String())"), "new String()");

		assertEquals(EvalExecutor.match("eval(new Date().getTime())"),
				"new Date().getTime()");

		assertEquals(EvalExecutor.match("eval()"), "");

		assertEquals(EvalExecutor.match("eval(hahaha)"), "hahaha");

		assertEquals(EvalExecutor.match("eval("), null);

		assertEquals(EvalExecutor.match("evalhaha()"), null);

		// 前缀
		assertEquals(EvalExecutor.match("1eval()"), null);
		// 后缀
		assertEquals(EvalExecutor.match("eval()1"), null);
	}

}
