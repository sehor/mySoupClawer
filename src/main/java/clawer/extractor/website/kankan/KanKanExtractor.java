package clawer.extractor.website.kankan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import clawer.extractor.Extractor;
import clawer.util.Helper;
import clawer.util.Tools;

public class KanKanExtractor implements Extractor {
	
	private final String rootUrl = "https://www.kuaikanmanhua.com";

	@Override
	public List<String> getBookUrls(String startUrl) {
		List<String> bookUrls = new ArrayList<>();
		int maxPage = 79;
		for (int i = 1; i <= maxPage; i++) {
			String nextUrl = "https://www.kuaikanmanhua.com/tag/0?state=1&sort=1&page=" + String.valueOf(i);
            Elements divs=Helper.getBodyBySelenium(nextUrl).select("div.ItemSpecial");
			
			for (Element div : divs) {
				String spanText=div.selectFirst("span.zanNumber.fr > span").text();
				double hot=Double.valueOf(spanText.replaceAll("万|\\+", ""));
				if(hot>=98) {
					
					Element a=div.selectFirst("a");
					bookUrls.add(rootUrl+a.attr("href"));
				}

			}
		}
		return bookUrls;
	}

	@Override
	public List<String> getChapterUrls(Element bookPage) {
		Elements aTags = bookPage.select("div.contentBox > div > div.TopicList > div > div> div.title.fl > a");
	Elements aTags1 = new Elements();
		
		//去掉有 i 元素的（锁住付费的）
		for (Element a : aTags) {
			
			if(a.select("i").isEmpty()) {
				aTags1.add(a);
			}
		}
		List<String> urls=Helper.getEachHref(aTags1, rootUrl);
		Collections.reverse(urls);
		return urls;

	}

	@Override
	public List<String> getChapterImageUrls(Element chapterPage) {

		Elements lis = chapterPage
				.select("div.contentBox > div.comicDetails > div.imgList > img");

		return lis.eachAttr("data-src");

	}

	

	@Override
	public String etrBookName(Element infoNode) {
		// TODO Auto-generated method stub
        String text=infoNode.select(" div.TopicHeader.cls > div.right.fl > h3").text();
        text=Tools.trimText(text);
		return text;
	}

	@Override
	public String etrAuthor(Element infoNode) {
		// TODO Auto-generated method stub
        String text=infoNode.select("div.TopicHeader.cls > div.right.fl > div.nickname").text();
        text=Tools.trimText(text);
		return text;
		
	}

	@Override
	public String etrBrief(Element infoNode) {
		// TODO Auto-generated method stub
		return infoNode.select("div.comicIntro > div > div > p").text();
	}

	@Override
	public String etrCoverImageUrl(Element infoNode) {
		// TODO Auto-generated method stub
		Element el=infoNode.select("div.left.fl > img.imgCover").first();
    
		return el.attr("src");
	}

	@Override
	public String etrChapterName(Element infoNode) {
		// TODO Auto-generated method stub
		List<TextNode> textNodes=infoNode.selectFirst("div.titleBox.cls > h3").textNodes();
		if(textNodes==null||textNodes.isEmpty()) {
			return "";
		}
		String text=textNodes.get(textNodes.size()-1).text();
		text=Tools.trimText(text);
		return text;
	}
	

	@Override
	public long etrChapterNum(Element infoNode) {
		// TODO Auto-generated method stub
		return Tools.extAndToNumber(etrChapterName(infoNode));
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
		return "https://www.kuaikanmanhua.com";
	}

	@Override
	public String weibsiteName() {
		// TODO Auto-generated method stub
		return "KanKan";
	}

	@Override
	public String startUrl() {
		// TODO Auto-generated method stub
		return "https://www.kuaikanmanhua.com/tag/0";
	}

	@Override
	public String bookListPageToolType() {
		// TODO Auto-generated method stub
		return "selenium";
	}

	@Override
	public String bookPageToolType() {
		// TODO Auto-generated method stub
		return "jsoup";
	}

	@Override
	public String chapterPageToolType() {
		// TODO Auto-generated method stub
		return "jsoup";
	}





}
