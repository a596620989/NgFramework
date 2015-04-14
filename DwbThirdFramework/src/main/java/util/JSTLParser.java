package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.dwb.test.framework.ExecuteEngineTemplate;

/**
 * 解析并替换${}中的内容.
 * ${columnName}代表当前行中,列明为cloumn的值.
 * @author Administrator
 *
 */
public class JSTLParser {
	
	//非贪婪模式匹配
	private static final String PATTERN = "\\$\\{(.*?)\\}";
	private static Logger logger = Logger.getLogger(ExecuteEngineTemplate.class);

	/**
	 * 获取所有需要被解析的参数列表.
	 * 如,util.SignUtil.sign(&ts=${ts}&menu_id=${menu_id}), 返回[ts,menu_id]
	 * @return
	 */
	public static List<String> getAllTag(String source){
		
		List<String> result = new ArrayList<String>();
		
		Pattern p = Pattern.compile(PATTERN);
		Matcher m = p.matcher(source);

		while (m.find()) {
			result.add( m.group(1) );
			logger.debug(result);
		}
		
		return result;
	}
	
	/**
	 * 根据map中的key-value对, 对source中的jstl求值并替换.
	 * for example:
	 *     source = util.SignUtil.sign(&ts=${ts}&menu_id=${menu_id})
	 *     lookUp = <ts,1481021110>,<menu_id,111>
	 *     return = util.SignUtil.sign(&ts=1481021110&menu_id=111)
	 * @param lookUp 
	 * @param source 源串
	 * @return
	 */
	public static String jstlReplacement(Map<String,String> lookUp,String source){
		
		List<String> allTags = getAllTag(source);
		
		for(String tag : allTags){
			//\\$,\\{代码转义
			source = source.replaceAll("\\$\\{"+tag+"\\}", lookUp.get(tag));
		}
		
		return source;
	}
}
