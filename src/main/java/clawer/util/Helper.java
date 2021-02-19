package clawer.util;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class Helper {

	public static final String Base_Save_Path = "D:/cartoon/images/origin";
	private final static int interval = 1000;
	private final static int TIME_OUT = 20000;
	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64";
	public static ChromeOptions options;
	public static WebDriver chromeDriver;

	static {
		System.out.println("start init driver:..........");
		System.setProperty("webdriver.chrome.driver", "E:\\chromeDriver\\chromedriver.exe");
		options = new ChromeOptions();

		// 新版Chromedriver设置去除浏览器上的爬虫信息
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.javascript", 2); // 2就是代表禁止加载的意思
		// 禁止加载css
		prefs.put("profile.default_content_settings.images", 2); // 2就是代表禁止加载的意思
		options.setExperimentalOption("prefs", prefs);

		options.addArguments("disable-infobars"); // 禁止提示
		options.addArguments("--headless"); // 禁止打开窗口
		options.addArguments("disable-popup-blocking");// 禁止弹窗
		options.addArguments("disable-extensions");// 禁止插件
		options.addArguments("--start-maximized");// 窗口最大化

		options.addArguments("disable-application-cache"); // to disable cache
		options.addArguments("safebrowsing-disable-download-protection");
		options.addArguments("ignore-certificate-errors");
		options.addArguments("disable-gpu");
		options.addArguments("incognito");

		// options.setPageLoadStrategy(PageLoadStrategy.NONE); 这种策略和用jsoup差不多的效果

		chromeDriver = new ChromeDriver(options);
		chromeDriver.manage().timeouts();

		System.out.println("end init driver");

	}

	public static WebDriver initWebDriver() {
		return new ChromeDriver(options);

	}

	public static Document getDoc(String url) {
		interval();
		int k = 1;
		Document document = null;
		Connection conn = null;
		boolean flag = true;
		while (flag && k <= 5) {
			try {
				conn = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIME_OUT);
				document = conn.get();
				flag = false;
			} catch (UnknownHostException netErr) {
				System.out.println("网址错误！");
				return null;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = true; // 重试
				k++;
			}
		}
		return document;
	}

	public static String getByHttp(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		interval();
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

	public static Element getBodyBySelenium(String url) {
		// System.out.println("start get form web:");
		// WebDriver myChromeDriver = initWebDriver();
		chromeDriver.get(url);
		interval();
		String soruceStr = chromeDriver.getPageSource();
		// System.out.println("end get form web");
		Element body = Jsoup.parse(soruceStr).body();
		// System.out.println("end Jsoup parese ");
		// chromeDriver.close();
		return body;
	}

	public static Element getBodyBySeleniumScroll(String url, int steps) {
		WebDriver myChromeDriver = initWebDriver();

		myChromeDriver.get(url);
		scrollWeb(myChromeDriver, steps);
		String soruceStr = myChromeDriver.getPageSource();
		Element body = Jsoup.parse(soruceStr).body();
		// myChromeDriver.close();
		// System.out.println("driver close!");
		return body;
	}

	public static void interval() {
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void interval(int milns) {
		try {
			Thread.sleep(milns);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<String> getEachHref(Elements elements, String baseUrl) {
		List<String> hrefs = new ArrayList<>();
		List<String> rHrefs = elements.eachAttr("href");
		rHrefs.forEach(e -> hrefs.add(baseUrl + e));

		return hrefs;
	}

	public static void scrollWeb(WebDriver driver, int steps) {
		// String jsScroll = "window.scrollTo({top: document.body.scrollHeight,
		// behavior: \"smooth\"})";
		JavascriptExecutor exDriver = (JavascriptExecutor) driver;
		long height = (long) exDriver.executeScript("return document.body.scrollHeight");
		int times = (int) (height / steps);
		for (int i = 0; i <= times; i++) {
			int pass = steps * i;
			exDriver.executeScript("scroll(0," + pass + ")");
			interval(500);
		}

	}

	public static Element getBody(String url, String toolType) {
		if (toolType == null || toolType.contains("jsoup")) {
			return getBody(url);
		} else if (toolType.contains("selenium")) {
			return getBodyBySelenium(url);
		} else if (toolType.contains("selenium_scroll")) {
			return getBodyBySeleniumScroll(url, 50);
		}

		return null;

	}
}
