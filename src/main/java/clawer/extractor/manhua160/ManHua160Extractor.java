package clawer.extractor.manhua160;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import clawer.extractor.Extractor;
import clawer.util.BookType;
import clawer.util.Helper;
import clawer.util.Tools;

public class ManHua160Extractor implements Extractor {

	private final String rootUrl = "https://www.mh160.xyz";

	@Override
	public List<String> getBookUrls(String startUrl) {
		List<String> bookUrls = new ArrayList<>();
		Elements as = Helper.getBody(this.startUrl())
				.select("div.mh-search-result > ul > li> div.mh-worksbox > div > div > a");
		for (Element a : as) {
			bookUrls.add(a.attr("abs:href"));
		}
		return bookUrls;
	}

	@Override
	public List<String> getChapterUrls(Element bookPage) {

		Elements aTags = bookPage.select("#mh-chapter-list-ol-0 > li> a");

		List<String> urls = new ArrayList<>();
		for (Element e : aTags) {
			urls.add(this.rootUrl+e.attr("href"));
		}
		Collections.reverse(urls);

//		List<WebElement> aTags=Helper.chromeDriver.findElements(By.cssSelector("#mh-chapter-list-ol-0 > li > a"));
//		for(WebElement e:aTags) {
//			urls.add(e.getAttribute("href"));
//		}
		return urls;

	}

	@Override
	public List<String> getChapterImageUrls(Element chapterPage) {
		List<String> imageUrls = new ArrayList<>();
		Element image = chapterPage.selectFirst("#qTcms_Pic_middle > tbody > tr > td > img");
		String imageUrl = image.attr("src");
		String baseImageUrl = imageUrl.substring(0, imageUrl.length() - "0001.jpg".length());

		Element span = chapterPage.selectFirst("#k_total");
		int max = Integer.valueOf(span.text().replaceAll("\"", ""));

		for (int i = 1; i <= max; i++) {
			String numStr = String.valueOf(i);
			String replace = "0000".substring(0, 4 - numStr.length()) + numStr;
			imageUrls.add(baseImageUrl + replace + ".jpg");
		}

//		WebElement pageA = Helper.chromeDriver.findElement(By.xpath("//*[@id=\"pager\"]/a[3]"));
//		String baseUrl = pageA.getAttribute("href");
//		baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
//
//		
//		for(int i=1;i<=max;i++) {
//			String nextUrl=baseUrl+String.valueOf(i);
//			Element nextPage=Helper.getBodyBySelenium(nextUrl);
//			
//			Element img = nextPage.selectFirst("#qTcms_Pic_middle > tbody > tr > td > img");
//			imageUrls.add(img.attr("src"));
//		}

		return imageUrls;

	}

	@Override
	public String etrBookName(Element infoNode) {
		// TODO Auto-generated method stub
		String text = infoNode.selectFirst("div.mh-date-info-name > h4 > a").text();

		return text;
	}

	@Override
	public String etrAuthor(Element infoNode) {
		// TODO Auto-generated method stub

		return infoNode.selectFirst("p.mh-pdt30.works-info-tc > span > em > a").text();

	}

	@Override
	public String etrBrief(Element infoNode) {

		return infoNode.selectFirst("#workint > p").text().trim();
	}

	@Override
	public String etrCoverImageUrl(Element infoNode) {
		// TODO Auto-generated method stub
		Element el = infoNode.select("body > div:nth-child(7) > div.mh-works-date.fl > div > div > div > a > img")
				.first();

		return el.attr("src");
	}

	@Override
	public String etrChapterName(Element infoNode) {
		// TODO Auto-generated method stub
		String text = infoNode.select("body > div.w996.title.pr > h2").first().text();
		text = Tools.trimText(text);
		return text;
	}

	@Override
	public long etrChapterNum(Element infoNode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String etrImageName(Element infoNode) {
		// TODO Auto-generated method stub
		return "noname";
	}

	@Override
	public LocalDate etrImagePublishDate(Element infoNode) {
		// TODO Auto-generated method stub
		return LocalDate.of(2020, 1, 1);
	}

	@Override
	public String rootUrl() {
		// TODO Auto-generated method stub
		return rootUrl;
	}

	@Override
	public String weibsiteName() {
		// TODO Auto-generated method stub
		return "ManHua160";
	}

	@Override
	public String startUrl() {
		// TODO Auto-generated method stub
		return "https://www.mh160.xyz/kanmanhua/allhit/";
	}

	@Override
	public String bookListPageToolType() {
		// TODO Auto-generated method stub
		return "selenium";
	}

	@Override
	public String bookPageToolType() {
		// TODO Auto-generated method stub
		return "selenium";
	}

	@Override
	public String chapterPageToolType() {
		// TODO Auto-generated method stub
		return "selenium";
	}
	
	@Override
	public BookType bookType() {
		// TODO Auto-generated method stub
		return BookType.Cartoon;
	}
}
