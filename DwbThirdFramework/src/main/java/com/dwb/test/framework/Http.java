package com.dwb.test.framework;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;

import util.CSVParser;

import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.JsonPath;

public abstract class Http {
	abstract void processRow(CSVParser parser,
			Map<String, String> currentCaseInput,
			Map<String, String> currentCaseOutput) throws IOException;

	protected void assertResult(Map<String, String> currentCaseOutput,
			String jsonResult) {
		// assert 返回值和csv中的预期结果
		for (Entry<String, String> e : currentCaseOutput.entrySet()) {
			String jsonValue = null;
			try {
				jsonValue = JsonPath.read(jsonResult, e.getKey()).toString();
			} catch (InvalidPathException exception) {
				// 更友好的提示
				jsonValue = "路径不存在:" + e.getKey();
			}
			Assert.assertEquals(e.getValue().toString(), jsonValue);
		}
	}

}
