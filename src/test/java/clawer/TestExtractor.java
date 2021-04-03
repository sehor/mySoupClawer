package clawer;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clawer.extractor.Extractor;
import clawer.extractor.MtmntExtractor;
import clawer.extractor.FengZhiDongMan.FengZhiDongManExtractor;
import clawer.extractor.manhua160.ManHua160Extractor;
import clawer.util.Helper;

public class TestExtractor {
	
	//Extractor extractor=new FengZhiDongManExtractor();
	Extractor extractor=new MtmntExtractor();
	Element bookPage=Helper.getBody("http://www.mtmnt.com/meinv/image/20210328/97426.html");
	Element chapterPage=Helper.getBody("http://www.mtmnt.com/meinv/image/20210328/97426.html");
	
	//@BeforeEach
	public void setUp() {

	}

	// @Test
	public void etrBookName() {
		 
		 System.out.println("bookname: "+this.extractor.etrBookName(bookPage));
 
	}

	//@Test
	public void etrAuthor() {
		 System.out.println("author: "+this.extractor.etrAuthor(bookPage));
	}

   // @Test
	public void etrBrief() {
    	System.out.println("brief: "+this.extractor.etrBrief(bookPage));
	}

	//@Test
	public void etrCoverImageUrl() {
		System.out.println("coverImageUrl: "+this.extractor.etrCoverImageUrl(bookPage));
	}

	//@Test
	public void etrChapterName() {
		//System.out.println("chapterName: "+this.extractor.etrChapterName(chapterPage));
		
	}

	// @Test
	public void etrChapterNum() {

	}

	// @Test
	public void etrImageName() {

	}

	@Test
	public void getBookUrls() {

		extractor.getBookUrls(extractor.startUrl()).forEach(e->System.out.println(e));
	}

	//@Test
	public void getChapterUrls() {
        extractor.getChapterUrls(this.bookPage).forEach(e->System.out.println("chapterUrls: "+e));
	}

	//@Test
	public void getChapterImageUrls() {
		Element chapterPage=Helper.getBodyBySelenium("https://www.mh160.xyz/kanmanhua/12094/43350001.html");
		//System.out.println(chapterPage.toString());
		//System.out.println(chapterPage);
		//System.out.println(chapterPage.select("a.pure-button-primary").last().text());
		this.extractor.getChapterImageUrls(chapterPage);

	}
	
	//@Test
	public void extImageUrl() {
		String input="<script type=\"text/javascript\">var mhurl1=\"2018/10/12062707625806.jpg\";mhpicurl=mhss+\"/\"+mhurl1;document.write";
		String result;
		int positon=input.indexOf(".jpg");
		System.out.println(positon);
		result=input.substring(positon-23,positon+4);
		System.out.println(result);

		
	}
}
