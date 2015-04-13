package util;

import org.apache.log4j.Logger;

import bsh.EvalError;
import bsh.Interpreter;

/**
 * 运行时java解释器, 用于在运行时解析并执行java代码.
 * 此解释器支持递归嵌套(任意层), 如eval(eval(..)..)
 * @author Administrator
 *
 */
public class JavaInterpreter {

	private static Logger logger = Logger.getLogger(JavaInterpreter.class);

	// java的正则表达式不支持递归的写法,故无法解析balanced bracket, 自己写过简单的解析器
	// private static final String PATTERN = "eval\\((.*)\\)$";

	/**
	 * 执行一段java代码, 并以String返回执行结果 TODO: eval recursion
	 * 不支持递归语法,但更简洁. 如需使用, 请把注释打开.
	 * @param expression
	 * @return null if pattern not match.
	 */
//	public static String eval(String expression) {
//
//		logger.info("eval prarmeter: " + expression);
//
//		expression = match(expression);
//		return eval0(expression);
//	}

	public static String evalRecursively(String expression)  {

		logger.info("初始表达式:" + expression);
		// 总表达式, 每次迭代都会被规约一次eval
		String result = expression;
		Interpreter interpreter = new Interpreter();

		// 递归执行内部的eval(如果有)
		while (match(result) != null) {

			String temp = result;
			String foundMatch;
			// 最后一次匹配到的eval表达式
			String lastNotNull = null;
			// 贪心匹配,匹配最外层的eval
			while ((foundMatch = match(temp)) != null) {
				temp = foundMatch;
				lastNotNull = foundMatch;
			}
			
			logger.info("规约:" + lastNotNull);
			try {
				result = result.replace("eval(" + lastNotNull + ")",
						String.valueOf(interpreter.eval(lastNotNull)));
			} catch (EvalError e) {
				logger.info("规约失败");
				e.printStackTrace();
			}
			
		}

		// 执行最外层的eval
		return result;
	}

	protected static String eval0(String expression) {

		Interpreter interpreter = new Interpreter();

		Object o;
		try {
			o = interpreter.eval(expression);
		} catch (EvalError e) {
			e.printStackTrace();
			return null;
		}

		return String.valueOf(o);
	}

	//使用正则的匹配算法, 无法匹配递归
	// protected static String match(String source) {
	//
	// String result = null;
	// Pattern p = Pattern.compile(PATTERN);
	// Matcher m = p.matcher(source);
	//
	// if (m.find()) {
	// result = m.group(1);
	// System.out.println(result);
	// }
	//
	// return result;
	//
	// }

	/**
	 * 抽取表达式中最外层的eval(.*)内容, 此算法允许eval()嵌套, 如eval(eval(..)..)
	 * @param source
	 * @return
	 */
	protected static String match(String source) {

		int balance = 0;

		StringBuffer result = new StringBuffer();
		
		int index = source.indexOf("eval(");
		
		if(index == -1){
			return null;
		}
		
		String tmp = source.substring(index+"eval(".length()-1);

		boolean isFound = false;
		
		for (char c : tmp.toCharArray()) {
			result.append(c);
			if (c == '(') {
				balance++;
			} else if (c == ')') {
				balance--;
				//此时平衡度为0, 表明已匹配完最外面的eval();
				if(balance == 0){
					//删除多添加的)字符和(字符
					result.deleteCharAt(0);
					result.deleteCharAt(result.length()-1);
					isFound = true;
					break;
				}
			}
		}
		
		if(isFound){
			return result.toString();
		}else{
			return null;
		}

	}

}
