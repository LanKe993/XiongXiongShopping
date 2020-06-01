package com.taotao.httpClient;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2020年2月26日 下午5:01:34 类说明
 */

public class HttpClentTest {

	/*@Test
	public void doGet() throws Exception {
		// 创建一个httpClinet对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建一个get对象
		HttpGet httpGet = new HttpGet("http://www.sogou.com");

		// 执行请求
		CloseableHttpResponse response = httpClient.execute(httpGet);

		// 取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		System.out.println();

		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		System.out.println();

		// 关闭httpClient
		httpClient.close();
		response.close();
	}

	@Test
	public void doGetWithParam() throws Exception {
		// 创建一个httpClinet对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建一个uri对象
		URIBuilder uriBuilder = new URIBuilder("http://www.sogou.com/web");
		uriBuilder.addParameter("query", "华强北");
		// 创建一个get对象
		HttpGet httpGet = new HttpGet(uriBuilder.build());

		// 执行请求
		CloseableHttpResponse response = httpClient.execute(httpGet);

		// 取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		System.out.println();

		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		System.out.println();

		// 关闭httpClient
		httpClient.close();
		response.close();
	}
	@Test
	public void doPost() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个post对象
		HttpPost post  = new HttpPost("http://localhost:8082/httpclient/post.html");
		// 执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		httpClient.close();
	}*/
}
