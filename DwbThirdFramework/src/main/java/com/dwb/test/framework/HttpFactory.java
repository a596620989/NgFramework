package com.dwb.test.framework;

/**
 * 工厂, 用于生成http实例.
 * @author Administrator
 *
 */
public class HttpFactory {

	public static Http getInstance(String requestType) {

		if ("get".equalsIgnoreCase(requestType)) {
			return new Get();
		} else {
			return new Post();
		}

	}

}
