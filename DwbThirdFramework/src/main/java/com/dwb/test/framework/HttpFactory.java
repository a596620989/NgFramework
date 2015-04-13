package com.dwb.test.framework;

public class HttpFactory {

	public static Http getInstance(String requestType) {

		if ("get".equalsIgnoreCase(requestType)) {
			return new Get();
		} else {
			return new Post();
		}

	}

}
