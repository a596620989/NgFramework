package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	public static String readContentFromGet(String url) throws IOException {

		URL getUrl = new URL(url);

		// 根据拼凑的URL，打开连接，URL.openConnection()函数会根据
		// URL的类型，返回不同的URLConnection子类的对象，在这里我们的URL是一个http，因此它实际上返回的是HttpURLConnection
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();

		// 建立与服务器的连接，并未发送数据
		connection.connect();

		// 发送数据到服务器并使用Reader读取返回的数据
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "UTF-8"));

		// System.out.println(" ============================= ");
		// System.out.println(" Contents of get request ");
		// System.out.println(" ============================= ");

		StringBuilder entityStringBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			entityStringBuilder.append(line);
		}

		reader.close();

		// 断开连接
		connection.disconnect();

		// System.out.println(" ============================= ");
		// System.out.println(" Contents of get request ends ");
		// System.out.println(" ============================= ");

		return entityStringBuilder.toString();
	}

}
