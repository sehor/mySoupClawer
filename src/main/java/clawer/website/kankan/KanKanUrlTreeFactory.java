package clawer.website.kankan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import clawer.helper;
import clawer.domain.urlTree.UrlTree;
import clawer.domain.urlTree.UrlTreeFactory;

public class KanKanUrlTreeFactory implements UrlTreeFactory {

	private String startUrl;

	public KanKanUrlTreeFactory(String startUrl) {
		this.startUrl = startUrl;
	}

	@Override
	public List<String> getBookUrls() {
		List<String> bookUrls = new ArrayList<>();
		Element body = helper.getBody(this.startUrl);
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
	public List<String> getChapterUrls() {
		// TODO Auto-generated method stub

		Element body = helper.getBody("https://www.kuaikanmanhua.com/web/comic/272673/");
		Elements ul = body.select("div.ChapterList");
		System.out.println(body.toString());

		return null;
	}

	@Override
	public List<String> getChapterImageUrls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UrlTree getUrlTree() {
		// TODO Auto-generated method stub
		return null;
	}

}
