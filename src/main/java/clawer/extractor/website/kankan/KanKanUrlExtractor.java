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
		int maxPage = 15;
		for (int i = 1; i <= maxPage; i++) {
			String nextUrl = "https://www.kuaikanmanhua.com/tag/0?state=1&sort=1&page=" + String.valueOf(i);

			Elements as = Helper.getBody(nextUrl).select("div.ItemSpecial").select("a");

			for (Element a : as) {
				bookUrls.add(a.attr("abs:href"));
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
