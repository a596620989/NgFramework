package com.dwb.test.framework;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import util.CSVParser;
import util.HttpUtil;
import util.JSTLParser;
import util.JavaInterpreter;

public class Get extends Http {

	private Logger logger = Logger.getLogger(Post.class);

	public void processRow(CSVParser parser,
			Map<String, String> currentCaseInput,
			Map<String, String> currentCaseOutput) throws IOException {

		String hosts = ConfigModel.host;
		String url = hosts + parser.getAction();

		// 根据参数拼凑请求
		for (int i = 0; i < parser.getInputColumnName().size(); i++) {

			String inputColumn = parser.getInputColumnName().get(i);

			//忽略description
			if (inputColumn.equals("description")) {
				continue;
			}
			
			String value = currentCaseInput.get(inputColumn);

			//忽略当前行中值为null的列
			if("null".equalsIgnoreCase(value)){
				continue;
			}
			
			url += "&";
			url += inputColumn;
			url += "=";
			
			//eval, 不用patter match,而先用前缀判断, 为了提高性能.
			if(value.startsWith("eval")){
				
				//拦截并解析jstl
				value = JSTLParser.jstlReplacement(currentCaseInput, value);
				
				String eval = JavaInterpreter.evalRecursively(value);
				if(eval!=null){
					value = eval;
					//用eval的值覆盖已有的eval表达式
					currentCaseInput.put(inputColumn, value);
				}
			}
			
			//TODO:sign, try beanShell, if not work, use reflect.
			
			url += value;

		}

		url = url.replaceFirst("&", "?");

		logger.info("调用接口" + url);

		// TODO: 扩展的部分, 如sign

		String jsonResult = HttpUtil.readContentFromGet(url);

		logger.info("接口调用返回的json为" + jsonResult);

		assertResult(currentCaseOutput, jsonResult);
	}

}
