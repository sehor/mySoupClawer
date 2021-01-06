package clawer.extractor.website.kankan;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import clawer.helper;
import clawer.extractor.UrlExtractor;

public class KanKanUrlExtrator implements UrlExtractor{

	@Override
	public List<String> getBookUrls(String startUrl) {
		List<String> bookUrls = new ArrayList<>();
		Element body = helper.getBody(startUrl);
		Elements nav = body.select("div.navigation > ul > li:eq(7) > a");
		int maxPage = Integer.parseInt(nav.text().trim());
		for (int i = 1; i <= maxPage; i++) {
			String nextUrl = "https://www.kuaikanmanhua.com/tag/0?state=1&sort=1&page=" + String.valueOf(i);
			Elements as = helper.getBody(nextUrl).select("div.ItemSpecial").select("a");
			for (Element a : as) {
				bookUrls.add(a.attr("abs:href"));
			}
		}
		return bookUrls;
	}

	@Override
	public List<String> getChapterUrls(String startUrl) {

		Element body = helper.getBody("https://www.kuaikanmanhua.com/web/comic/272673/");
		Elements ul = body.select("div.ChapterList");
		System.out.println(body.toString());

		return null;
	}

	@Override
	public List<String> getChapterImageUrls(String startUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
