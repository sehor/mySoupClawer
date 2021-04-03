package clawer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import clawer.util.Helper;
import clawer.util.Tools;

public class JustTest {

	// @Test
	public void extAndToNumber() {

		String input = "第3话 给我尽情的吃";
		long result = Tools.extAndToNumber(input);
		System.out.print(result);
	}

	// @Test
	public void selenium() {

		Element body = Helper.getBodyBySelenium("https://www.kuaikanmanhua.com/web/comic/200791/");
		System.out.println(body.select("div.comicDetails > div.imgList").toString());
		body.select("div.comicDetails > div.imgList>img").eachAttr("data-src").forEach(System.out::println);
	}

	@Test
	public void test() {

		Element page=Helper.getBody("https://www.aimimh.com/chapter/20222-131618");
		System.out.println(page);
	}


}
