package clawer;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import clawer.util.Helper;

public class JustTest {

	//@Test

	public void extAndToNumber() {
		
		String input="- 四万五千零三百;二  十五 _章";
		long result=Helper.extAndToNumber(input);
		System.out.print(result);
	}

  @Test
  public void selenium() {
	  
	  Element body=Helper.getBodyBySelenium("https://www.kuaikanmanhua.com/web/comic/200791/");
	  System.out.println(body.select("div.comicDetails > div.imgList").toString());
	  body.select("div.comicDetails > div.imgList>img").eachAttr("data-src").forEach(System.out::println);
  }
	 
}
