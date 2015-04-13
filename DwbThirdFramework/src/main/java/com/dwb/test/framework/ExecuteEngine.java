package com.dwb.test.framework;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.CSVParser;

/**
 * TODO: 使用注解来保存文件名, get/post, host等信息.
 * TODO: 优化testNg的调用显示. 使得eval可以被动态替换成相应的值
 * @author Administrator
 *
 */
public abstract class ExecuteEngine extends TestBase {

	CSVParser parser = new CSVParser();

	private Logger logger = Logger.getLogger(ExecuteEngine.class);

	@Test(dataProvider = "csvRowProvider")
	public void execute(String descption, int i) throws Exception {

		Map<String, String> currentCaseInput = parser.getInputColumnValues()
				.get(i);
		Map<String, String> currentCaseOutput = parser.getOutputColumnValues()
				.get(i);

		logger.debug("请求的类型为:" + getRequestType());
		HttpFactory.getInstance(getRequestType()).processRow(parser,
				currentCaseInput, currentCaseOutput);

	}

	public abstract String getCsvFile();

	public abstract String getRequestType();

	@DataProvider(name = "csvRowProvider")
	public Object[][] createData() {

		try {
			parser.parse("src/test/resources/" + getCsvFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int caseNumber = parser.getTestCaseNumber();

		// 构建一个case行数, 列数为2的数组. 作为迭代执行的数据源. 其中第一列为description
		Object[][] result = new Object[caseNumber][2];

		for (int i = 0; i < caseNumber; i++) {
			//显示在testNg上的描述.
			String value = virsual(parser.getInputColumnValues().get(i),parser.getOutputColumnValues().get(i));
			result[i] = new Object[] {value, i };
		}

		return result;
	}
	
	private String virsual(HashMap<String, String> inputColumnValues, HashMap<String, String> outputColumnValues){
		
		StringBuffer sb = new StringBuffer();
		
		for(Entry<String,String> e : inputColumnValues.entrySet()){
			
			//不显示eval表达式,eval表达式是动态生成的, 用户不需要关心.
			if(e.getValue().startsWith("eval(")){
				continue;
			}
			
			sb.append(e.getKey());
			sb.append("=");
			sb.append(e.getValue());
			sb.append(",");
		}
		
		for(Entry<String,String> e : outputColumnValues.entrySet()){
			sb.append("#" + e.getKey());
			sb.append("=");
			sb.append(e.getValue());
			sb.append(",");
		}
		
		return sb.toString();
	}

}
