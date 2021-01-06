package clawer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import clawer.domain.urlTree.DefaultTreeFactory;
import clawer.domain.urlTree.UrlTreeFactory;

@SpringBootTest
class MyJsoupClawerApplicationTests {

	// @Test
	void contextLoads() {
	}

	// @Test
	void test() throws IOException {
		String startUrl = "https://www.kuaikanmanhua.com/tag/0?state=1&sort=1&page=1";

		Document doc = Jsoup.connect(startUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64").timeout(3000)
				.get();
		Elements nav = doc.select("div.navigation > ul > li:eq(7) > a");
		int maxPage = Integer.parseInt(nav.text().trim());
		for (int i = 1; i <= 1; i++) {
			String nextUrl = "https://www.kuaikanmanhua.com/tag/0?state=1&sort=1&page=" + String.valueOf(i);
			Document doc1 = Jsoup.connect(nextUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64").timeout(3000)
					.get();
			Elements els = doc1.select("div.ItemSpecial");

			int k = 0;
			for (Element el : els) {
				String introUrl = el.select("a").attr("abs:href");
				String name = el.select("a>span.itemTitle").text();
				Document introDoc = Jsoup.connect(introUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64")
						.timeout(3000).get();
				String brief = introDoc.select("div.comicIntro > div > div > p").text();
				String author = introDoc.select("div.TopicHeader.cls > div.right.fl > h3").text();
				String enterChapterUrl = introDoc.select("a.firstBtn.btns.fl").attr("abs:href");

				Document chaptersDoc = Jsoup.connect(enterChapterUrl)
						.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64").timeout(3000).get();

				Element testElements = introDoc.select("div.TopicHeader.cls > div.right.fl > h3").first();
				String testHref = testElements.attr("href");
				System.out.println(testHref.isEmpty());

				Elements chapterEls = chaptersDoc.select(" div.listBox > ul > li");

				/*
				 * for(Element elc:chapterEls) {
				 * System.out.println("章节名: "+elc.select("a").text()); }
				 */
				// System.out.println(brief+" "+author+" "+enterChapterUrl );

				k++;
				if (k >= 1) {
					break;
				}
			}

		}

	}

	// @Test
	public void test1() {

	}

	@Test
	public void seleniumTest() throws IOException {
		
		
		  System.setProperty("webdriver.chrome.driver","E:\\chromeDriver\\chromedriver.exe"); // 第二步：初始化驱动 
		  WebDriver driver = new ChromeDriver(); // 第三步：获取目标网页
		  driver.get("https://www.kuaikanmanhua.com/web/comic/191275/"); //第四步：解析。以下就可以进行解了。使用webMagic等进行必要的解析。 
		  String sourceStr=driver.getPageSource();
		  Document doc=Jsoup.parse(sourceStr);
		  Element body=doc.body().select("ul.list").first();
		  //Files.writeString(Paths.get("e:/temp/test.txt"), body.toString(), StandardCharsets.UTF_8);
		  System.out.println("Page title is: " +body); 
		  driver.close();
		  System.out.println("sys close:"); 
	}
}
