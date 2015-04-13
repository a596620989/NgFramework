package com.dwb.test.framework;

/**
 * 环境配置文件模型
 * 
 * @author changsheng.shaocs
 * 
 */
public class ConfigModel {

	public static String host;
	public static String account;
	public static String pwd;
	public static String uuid;

	public static String getUuid() {
		return uuid;
	}

	public static void setUuid(String uuid) {
		ConfigModel.uuid = uuid;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		ConfigModel.host = host;
	}

	public static String getAccount() {
		return account;
	}

	public static void setAccount(String account) {
		ConfigModel.account = account;
	}

	public static String getPwd() {
		return pwd;
	}

	public static void setPwd(String pwd) {
		ConfigModel.pwd = pwd;
	}

}
