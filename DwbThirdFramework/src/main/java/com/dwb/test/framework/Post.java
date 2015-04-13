package com.dwb.test.framework;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import util.CSVParser;

/**
 * TODO:post是否也需要加入eval?
 * @author Administrator
 *
 */
public class Post extends Http {

	private Logger logger = Logger.getLogger(Post.class);
	static legacy.HttpUtil aUtil = new legacy.HttpUtil();

	public void processRow(CSVParser parser,
			Map<String, String> currentCaseInput,
			Map<String, String> currentCaseOutput) throws IOException {

		String hosts = ConfigModel.host;
		String url = hosts + parser.getAction();

		// 获取post body, 只有一列.
		String inputColumn = parser.getInputColumnName().get(0);
		// 跳过description
		if (inputColumn.equalsIgnoreCase("description")) {
			inputColumn = parser.getInputColumnName().get(1);
		}

		logger.info("调用接口" + url);

		String body = currentCaseInput.get(inputColumn);

		logger.info("http body" + body);

		String jsonResult = aUtil.getHttpResponseByPost(url, body);

		logger.info("接口调用返回的json为" + jsonResult);

		assertResult(currentCaseOutput, jsonResult);

	}

}
