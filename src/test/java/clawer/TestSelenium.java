package clawer;

import java.util.List;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import clawer.util.Helper;

public class TestSelenium {
 
	@Test
	public void test1() {
		
		 Element page=Helper.getBodyBySelenium("https://www.mh160.xyz/kanmanhua/12094/");
		 List<WebElement> webElements=Helper.chromeDriver.findElements(By.cssSelector("#mh-chapter-list-ol-0>li>a"));
		 System.out.println(webElements.size());
		 for(WebElement e:webElements) {
			 System.out.println(e.getAttribute("href"));
		 }
		 
	}
}
