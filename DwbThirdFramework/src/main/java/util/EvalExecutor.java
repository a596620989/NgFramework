package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 使用js的eval执行引擎
 * @deprecated use {@link util.JavaInterpreter} instead.
 * @author Administrator
 *
 */
public class EvalExecutor {

	private static final String SCRIPT_LANGUAGE = "js";
	private static final String PATTERN = "^eval\\((.*)\\)$";

	/**
	 * 执行一段js代码, 并以String返回执行结果
	 * 
	 * @param expression
	 * @return null if pattern not match.
	 */
	public static String eval(String expression) {
		expression = match(expression);
		return eval0(expression);
	}

	protected static String eval0(String expression) {

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName(SCRIPT_LANGUAGE);
		Object o;
		try {
			o = engine.eval(expression);
		} catch (ScriptException e) {
			e.printStackTrace();
			return null;
		}

		return String.valueOf(o);

	}

	protected static String match(String source) {

		String result = null;
		Pattern p = Pattern.compile(PATTERN);
		Matcher m = p.matcher(source);

		if (m.find()) {
			result = m.group(1);
			System.out.println(result);
		}

		return result;

	}

}
