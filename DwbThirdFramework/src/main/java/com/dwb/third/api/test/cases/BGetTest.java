package com.dwb.third.api.test.cases;

import org.testng.annotations.Test;

import com.dwb.test.framework.ExecuteEngine;

public class BGetTest extends ExecuteEngine {

	@Test(dataProvider = "csvRowProvider")
	public void execute(String descption, int i) throws Exception {
		super.execute(descption, i);
	}

	@Override
	public String getCsvFile() {
		return "merchantMenuofTest1.csv";
	}

	@Override
	public String getRequestType() {
		// TODO Auto-generated method stub
		return "get";
	}

}
