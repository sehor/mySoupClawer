package clawer;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class helper {
	private final static int TIME_OUT = 5000;
	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64";

	public static Document getDoc(String url) {
		Connection conn = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIME_OUT).maxBodySize(0);
		try {
			return conn.get();
		} catch (UnknownHostException netErr) {
			System.out.println("网址错误！");
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static String getByHttp(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpGet request = new HttpGet(url);
		try {
			// 3.执行get请求，相当于在输入地址栏后敲回车键
			response = httpClient.execute(request);

			// 4.判断响应状态为200，进行处理
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 5.获取响应内容
				HttpEntity httpEntity = response.getEntity();
				String html = EntityUtils.toString(httpEntity, "utf-8");
				return html;
			} else {
				// 如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
				System.out.println("返回状态不是200");
				System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
				return "error";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "error";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		} finally {
			// 6.关闭
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(httpClient);
		}
	}

	public static Element getBody(String url) {
		Document doc = getDoc(url);
		if (doc == null)
			return null;
		return doc.body();
	}

	public static boolean elsIsEmpty(Elements els) {
		return els.size() < 1;
	}
}
