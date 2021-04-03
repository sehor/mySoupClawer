package clawer.extractor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import clawer.util.BookType;
import clawer.util.Helper;

public class MtmntExtractor implements Extractor{

	@Override
	public String bookListPageToolType() {
		// TODO Auto-generated method stub
		return "jsoup";
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

	@Override
	public String rootUrl() {
		// TODO Auto-generated method stub
		return "http://www.mtmnt.com";
	}

	@Override
	public String startUrl() {
		// TODO Auto-generated method stub
		return  "http://www.mtmnt.com";
	}

	@Override
	public String weibsiteName() {
		// TODO Auto-generated method stub
		return "mtmnt";
	}

	@Override
	public String etrBookName(Element infoNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String etrAuthor(Element infoNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String etrBrief(Element infoNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String etrCoverImageUrl(Element infoNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String etrChapterName(Element infoNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long etrChapterNum(Element infoNode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String etrImageName(Element infoNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate etrImagePublishDate(Element infoNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getBookUrls(String startUrl) {
		// TODO Auto-generated method stub
		Element body=Helper.getBody(startUrl);
		Elements els1=body.select("div.nav > div > a");
		List<String> typeUrls=Helper.getEachHref(els1, this.rootUrl());
		List<String> bookUrls=new ArrayList<>();
		List<String> allPageUrls=new ArrayList<>();
		
//		Elements els2=body.select("div> li> a");
//		List<String> firstPageBookUls=Helper.getEachHref(els2, this.rootUrl());
//		
//		bookUrls.addAll(firstPageBookUls);
		
		for(int i=1;i<typeUrls.size();i++) {
			
			 Element start=Helper.getBody(typeUrls.get(i));
			 Element last=start.select("center> div > p > a").last();
			 if(last==null) {
				 continue;
			 }
			 String href=last.attr("href");
			 int begin=href.indexOf("_");
			 int end=href.indexOf(".");
			 String href1=href.substring(begin+1, end);
			 System.out.println(href1);
			 int max=Integer.valueOf(href1);
			 
			 String baseUrl=typeUrls.get(i).substring(0,typeUrls.get(i).length()-6);
			 
			 for(int k=1;k<=max;k++) {
				 allPageUrls.add(baseUrl+String.valueOf(k)+".html");
			 }
		}
		
		allPageUrls.forEach(url->{
			Element eachPage=Helper.getBody(url);
			Elements aTags=eachPage.select("#list > ul > li> a");
			List<String> bookUrlsList=Helper.getEachHref(aTags, this.rootUrl());
			bookUrls.addAll(bookUrlsList);
		});
		
		
		
		return bookUrls;
	}

	@Override
	public List<String> getChapterUrls(Element bookPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getChapterImageUrls(Element chapterPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookType bookType() {
		// TODO Auto-generated method stub
		return BookType.Image_Series;
	}

}
