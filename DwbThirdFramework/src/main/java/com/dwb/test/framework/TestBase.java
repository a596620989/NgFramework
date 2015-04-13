package com.dwb.test.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;

public abstract class TestBase {
	public static String host = "";
	public static String account = "";
	public static String pwd = "";
	public static String uuid = "";

	@BeforeTest
	protected void setUp() throws Exception {

		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("./config/config.properties");

		Properties p = new Properties();
		try {
			p.load(inputStream);
			String env = p.getProperty("envIs");

			if (env.equals("TEST")) {

				host = p.getProperty("testHost");
				account = p.getProperty("testAccout");
				pwd = p.getProperty("testPwd");
				uuid = p.getProperty("testuuid");
				ConfigModel.setHost(host);
				ConfigModel.setAccount(account);
				ConfigModel.setPwd(pwd);
				ConfigModel.setUuid(uuid);
				// logger.info("host is : " + host + "; acccount is :" + account
				// + ";pwd is :" + pwd
				// + "!");
				// logger.info("host is : " + host);
			} else if (env.equals("YUFA")) {
				host = p.getProperty("yufaHost");
				account = p.getProperty("yufaAccount");
				pwd = p.getProperty("yufaPwd");
				uuid = p.getProperty("yufauuid");
				ConfigModel.setHost(host);
				ConfigModel.setAccount(account);
				ConfigModel.setPwd(pwd);
				ConfigModel.setUuid(uuid);
				// logger.info("host is : " + host + "; acccount is :" + account
				// + ";pwd is :" + pwd
				// + "!");

			} else if (env.equals("LINE")) {
				host = p.getProperty("Host");
				account = p.getProperty("yufaAccount");
				pwd = p.getProperty("yufaPwd");
				uuid = p.getProperty("UUID");
				ConfigModel.setHost(host);
				ConfigModel.setAccount(account);
				ConfigModel.setPwd(pwd);
				ConfigModel.setUuid(uuid);
				// logger.info("host is : " + host + "; acccount is :" + account
				// + ";pwd is :" + pwd
				// + "!");

			} else
				throw new Exception("异常情况");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
