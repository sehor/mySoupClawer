package clawer.extractor.FengZhiDongMan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import clawer.extractor.Extractor;
import clawer.util.Helper;
import clawer.util.Tools;

public class FengZhiDongManExtractor implements Extractor {

	private final String rootUrl = "https://manhua.fzdm.com/";

	@Override
	public List<String> getBookUrls(String startUrl) {
		List<String> bookUrls = new ArrayList<>();
		Elements as = Helper.getBody(startUrl).select("#mhmain > ul > div> li:nth-child(1) > a");
		bookUrls = Helper.getEachHref(as, this.rootUrl());
		return bookUrls;
	}

	@Override
	public List<String> getChapterUrls(Element bookPage) {

		Elements aTags = bookPage.select("#content > li > a");

		List<String> urls = new ArrayList<>();
		for (Element e : aTags) {
			urls.add(e.attr("abs:href"));
		}
		Collections.reverse(urls);
		return urls;

	}

	@Override
	public List<String> getChapterImageUrls(Element chapterPage) {
		List<String> imageUrls = new ArrayList<>();

		// System.out.println(chapterPage.toString());

		Element nextPage = chapterPage;

 
		while (true) {
			Elements script = nextPage.select("script[type=text/javascript]");
			String str = extImageUrl(script.toString());
			imageUrls.add(str);
			Element a_NextPage = nextPage.select("a.pure-button-primary").last();
			//System.out.println(str);

			if (a_NextPage.text().trim().equals("下一话吧")) {
				System.out.println("no, 最后一页了！");
				break;
			}
			String nextUrl = a_NextPage.attr("abs:href");

			try {

				nextPage = Helper.getBody(nextUrl);

				if (nextPage == null) {
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error occurr");
				break;
			}

			
		}
		return imageUrls;

	}

	@Override
	public String etrBookName(Element infoNode) {
		// TODO Auto-generated method stub
		String text = infoNode.select("#content > h2").text();
		text = Tools.trimText(text);
		text=text.split("\\s+")[0];
		return text;
	}

	@Override
	public String etrAuthor(Element infoNode) {
		// TODO Auto-generated method stub

		return "";

	}

	@Override
	public String etrBrief(Element infoNode) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String etrCoverImageUrl(Element infoNode) {
		// TODO Auto-generated method stub
		Element el = infoNode.select("div.left.fl > img.imgCover").first();

		return el.attr("src");
	}

	@Override
	public String etrChapterName(Element infoNode) {
		// TODO Auto-generated method stub
		String text = infoNode.select("#pjax-container > h1").first().text();
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
		return "FengZhiDongMan";
	}

	@Override
	public String startUrl() {
		// TODO Auto-generated method stub
		return "https://manhua.fzdm.com/";
	}

	private String extImageUrl(String input) {
		String result;
		int positon = input.indexOf(".jpg");
		result = input.substring(positon - 23, positon + 4);
		result = result.replaceAll("[\\s+=\";]", "");
		result=result.replaceAll("varmhurl", "");
		return result;
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
}
