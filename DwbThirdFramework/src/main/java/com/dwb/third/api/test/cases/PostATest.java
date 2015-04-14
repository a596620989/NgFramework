package com.dwb.third.api.test.cases;

import org.testng.annotations.Test;

import com.dwb.test.framework.ExecuteEngineTemplate;

public class PostATest extends ExecuteEngineTemplate {

	@Test(dataProvider = "csvRowProvider")
	public void execute(String descption, int i) throws Exception {
		super.execute(descption, i);
	}

	@Override
	public String getCsvFile() {
		return "loginTest.csv";
	}

	@Override
	public String getRequestType() {
		return "post";
	}

}
