package legacy;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//TODO: sucks! who write this long method
public class HttpUtil {

	public String getHttpsResponseByGet(String url) {
		HttpClient hc = new HttpClient();
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		HttpMethod method = null;
		try {
			method = new GetMethod(url);
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			createSSL();

			int statuscode = hc.executeMethod(method);

			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();
			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public String getHttpsResponseByPost(String url, String body) {
		HttpClient hc = new HttpClient();
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		PostMethod method = new PostMethod(url);

		try {

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			createSSL();

			RequestEntity requestEntity = new StringRequestEntity(body,
					"application/json", "utf-8");

			method.setRequestEntity(requestEntity);

			int statuscode = hc.executeMethod(method);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();
			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("http status is:" + hc.getState());
		return s;
	}

	public String getHttpsResponseByPost(String url) {
		HttpClient hc = new HttpClient();
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		PostMethod method = new PostMethod(url);

		try {

			if (!url.contains("?")) {
				throw new UnsupportedOperationException("此url(" + url
						+ ")不支持该方法");
			}

			String paramStr = url.substring(url.indexOf("?") + 1);

			String[] params = paramStr.split("&");

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			createSSL();

			int statuscode = hc.executeMethod(method);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();
			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	//	

	public String getHttpsResponseByPost(String url, NameValuePair[] attr) {
		HttpClient hc = new HttpClient();
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		PostMethod method = new PostMethod(url);

		try {

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			createSSL();

			method.setRequestBody(attr);

			int statuscode = hc.executeMethod(method);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();
			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public String getHttpsResponseByPut(String url, String body, String username) {
		HttpClient hc = new HttpClient();
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		PutMethod method = new PutMethod(url);
		try {

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			createSSL();

			RequestEntity requestEntity = new StringRequestEntity(body,
					"application/json", "utf-8");

			method.setRequestEntity(requestEntity);

			int statuscode = hc.executeMethod(method);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();
			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public String getHttpsResponseByDelete(String url, String username) {
		HttpClient hc = new HttpClient();
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		HttpMethod method = null;
		try {
			method = new DeleteMethod(url);
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			createSSL();

			int statuscode = hc.executeMethod(method);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();
			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public Map<String, String> getHttpsResponseByHead(String url,
			String username) {
		HttpClient hc = new HttpClient();
		HeadMethod method = new HeadMethod(url);
		Map<String, String> map = new HashMap<String, String>();

		try {

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			createSSL();

			int statuscode = hc.executeMethod(method);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			for (Header header : method.getResponseHeaders()) {
				// headers.put(header.getName().toLowerCase(),
				// header.getValue());
				map.put(header.getName().toLowerCase(), header.getValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return map;
	}

	/**
	 * 解析JSON为map 解析Json建议优先用这
	 * 
	 * @param jsonString
	 * @return Map
	 */
	public Map<String, String> getMap(String jsonString) {
		JSONObject jsonObject;
		Map<String, String> map = null;
		try {
			jsonObject = new JSONObject(jsonString);
			@SuppressWarnings("unchecked")
			Iterator<String> i = jsonObject.keys();
			String key;
			String value;
			map = new HashMap<String, String>();
			while (i.hasNext()) {
				key = (String) i.next();
				value = jsonObject.get(key).toString();
				map.put(key, value);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 解析JSON为List
	 * 
	 * @param jsonString
	 * @return List
	 */
	public List<Map<String, String>> getList(String jsonString) {
		List<Map<String, String>> list = null;
		try {
			JSONArray jsonArray = new JSONArray(jsonString);
			JSONObject jsonObject;
			list = new ArrayList<Map<String, String>>();

			for (int i = 0; i < jsonArray.length(); i++) {
				jsonObject = jsonArray.getJSONObject(i);
				list.add(getMap(jsonObject.toString()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public String getHttpResponseByGet(String url) {
		HttpClient hc = new HttpClient();
		// hc.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		HttpMethod method = null;
		try {
			method = new GetMethod(url);
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			int statuscode = hc.executeMethod(method);
			System.out.println("res statuscode is : " + statuscode);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();

			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public String getHttpResponse_Cookie_ByGet(String url, String cookies) {
		HttpClient hc = new HttpClient();
		// hc.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		HttpMethod method = null;
		try {
			method = new GetMethod(url);
			method.setRequestHeader("Cookie", cookies);
			// method.setRequestHeader("Cookie",cookies2);

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			int statuscode = hc.executeMethod(method);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();

			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public String getHttpResponseByPost(String url, String body) {
		HttpClient hc = new HttpClient();
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		PostMethod method = new PostMethod(url);

		try {

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			RequestEntity requestEntity = new StringRequestEntity(body,
					"application/json", "utf-8");

			method.setRequestEntity(requestEntity);

			int statuscode = hc.executeMethod(method);
			System.out.println("http status is:" + statuscode);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();
			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return s;
	}

	/*
	 * 数据内容采用multipart/form-data方式上传
	 */
	public String getHttpResponseMultipartFormDataByPost(String url,
			String fileName, String filePath) throws ClientProtocolException,
			IOException {
		MultipartEntity multipartEntity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE, "--VANE", Charset
						.defaultCharset());
		multipartEntity.addPart("name", new StringBody("dp_data", Charset
				.forName("UTF-8")));
		multipartEntity.addPart("filename", new StringBody(fileName, Charset
				.forName("UTF-8")));
		multipartEntity.addPart("file", new FileBody(new File(filePath),
				"text/plain"));
		HttpPost request = new HttpPost(url);
		request.setEntity(multipartEntity);
		request.addHeader("Content-Type",
				"multipart/form-data; boundary=--VANE");

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);

		InputStream is = response.getEntity().getContent();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}

		return buffer.toString();

	}

	public String getHttpResponseMultipartFormDataByPost2(String url,
			String fileName[], String filePath[])
			throws ClientProtocolException, IOException {
		HttpPost httppost = new HttpPost(url);

		MultipartEntity me = new MultipartEntity();

		for (int i = 0; i < filePath.length; i++) {
			FileBody bin = new FileBody(new File(filePath[i]));
			me.addPart("dp_data", bin);
			me.addPart("filename", new StringBody(fileName[i], Charset
					.forName("UTF-8")));
		}

		httppost.setEntity(me);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(httppost);

		InputStream is = response.getEntity().getContent();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}

		return buffer.toString();

	}

	public String getHttpResponse_Cookies_ByPost(String url, String body,
			String cookies) {
		HttpClient hc = new HttpClient();
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		PostMethod method = new PostMethod(url);

		try {
			method.setRequestHeader("Cookie", cookies);

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			RequestEntity requestEntity = new StringRequestEntity(body,
					"application/json", "utf-8");

			method.setRequestEntity(requestEntity);

			int statuscode = hc.executeMethod(method);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();
			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public String getHttpResponseByPost(String url, NameValuePair[] attr) {
		HttpClient hc = new HttpClient();
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		PostMethod method = new PostMethod(url);

		try {

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			method.setRequestBody(attr);

			int statuscode = hc.executeMethod(method);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();
			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public String getHttpResponseByPost2(String url, NameValuePair[] attr) {
		HttpClient hc = new HttpClient();
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		PostMethod method = new PostMethod(url);

		try {

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			method.setRequestBody(attr);

			int statuscode = hc.executeMethod(method);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
			Cookie[] cookies = hc.getState().getCookies();
			hc.getState().addCookies(cookies);
			method.releaseConnection();
			System.out.println("cookies is " + cookies.toString());

			is = method.getResponseBodyAsStream();
			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public String getHttpResponseByPut(String url, String body, String username) {
		HttpClient hc = new HttpClient();
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		PutMethod method = new PutMethod(url);
		try {

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			RequestEntity requestEntity = new StringRequestEntity(body,
					"application/json", "utf-8");

			method.setRequestEntity(requestEntity);

			int statuscode = hc.executeMethod(method);
			System.out.println("res statuscode is :" + statuscode);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();
			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public String getHttpResponseByDelete(String url, String username) {
		HttpClient hc = new HttpClient();
		String s = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		HttpMethod method = null;
		try {
			method = new DeleteMethod(url);
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			int statuscode = hc.executeMethod(method);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			is = method.getResponseBodyAsStream();
			out = new ByteArrayOutputStream();
			int i;
			while ((i = is.read()) != -1) {
				out.write(i);
			}

			s = out.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				out.close();
				method.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public Map<String, String> getHttpResponseByHead(String url, String username) {
		HttpClient hc = new HttpClient();
		Map<String, String> map = new HashMap<String, String>();

		HeadMethod method = new HeadMethod(url);
		try {

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			int statuscode = hc.executeMethod(method);
			if (HttpStatus.SC_OK != statuscode) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Header usedCapacities = method.getResponseHeader("usage");
			// if (null != usedCapacities) {
			// s = usedCapacities.getValue();
			// }

			for (Header header : method.getResponseHeaders()) {
				// headers.put(header.getName().toLowerCase(),
				// header.getValue());
				map.put(header.getName().toLowerCase(), header.getValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return map;
	}

	/*
	 * 设置https连接的ssl连接
	 */
	@SuppressWarnings("deprecation")
	public void createSSL() {
		/*
		 * 只是https要加个步骤，MySecureProtocolSocketFactory为自定义的工厂类
		 */
		// 1
		// SecureProtocolSocketFactory mySSLSocketFactory = new
		// MySecureProtocolSocketFactory();

		// 2
		// Protocol httpsProtocol = new Protocol("https", mySSLSocketFactory,
		// 443); // 老环443端口，新环境8003端口

		// 3
		// Protocol.registerProtocol("https", httpsProtocol);

	}

	/**
	 * 将字符串转成unicode
	 * 
	 * @param str
	 *            待转字符
	 * @return unicode字符
	 */
	public static String convert(String str) {
		str = (str == null ? "" : str);
		String tmp;
		StringBuffer sb = new StringBuffer(1000);
		char c;
		int i, j;
		sb.setLength(0);
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>> 8); // 取出
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);
			j = (c & 0xFF); // 取出
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);

		}
		return (new String(sb));
	}

	/**
	 * 将unicode 字符
	 * 
	 * @param str
	 *            待转字符
	 * @return 普通字符
	 */
	public static String revert(String str) {
		str = (str == null ? "" : str);
		if (str.indexOf("\\u") == -1)// 如果不是unicode码则原样返回
			return str;

		StringBuffer sb = new StringBuffer(1000);

		for (int i = 0; i < str.length() - 6;) {
			String strTemp = str.substring(i, i + 6);
			String value = strTemp.substring(2);
			int c = 0;
			for (int j = 0; j < value.length(); j++) {
				char tempChar = value.charAt(j);
				int t = 0;
				switch (tempChar) {
				case 'a':
					t = 10;
					break;
				case 'b':
					t = 11;
					break;
				case 'c':
					t = 12;
					break;
				case 'd':
					t = 13;
					break;
				case 'e':
					t = 14;
					break;
				case 'f':
					t = 15;
					break;
				default:
					t = tempChar - 48;
					break;
				}

				c += t * ((int) Math.pow(16, (value.length() - j - 1)));
			}
			sb.append((char) c);
			i = i + 6;
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		String url = "b?str=aa";
		String paramStr = url.substring(url.indexOf("?") + 1);

		System.out.println(paramStr);

	}

}
