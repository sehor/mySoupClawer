package clawer.extractor.website.kankan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import clawer.extractor.UrlExtractor;
import clawer.util.Helper;

public class KanKanUrlExtractor implements UrlExtractor {

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

	
}
