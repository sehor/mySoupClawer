package clawer.website.kankan;

import java.util.ArrayList;
import java.util.List;

import clawer.domain.book.Book;
import clawer.domain.book.BookFactory;
import clawer.domain.chapter.Chapter;
import clawer.domain.image.Image;
import clawer.domain.urlTree.UrlTree;

public class KanKanBookFactory implements BookFactory {

	private UrlTree urlTree;
	
	public  KanKanBookFactory(UrlTree urlTree) {
		// TODO Auto-generated constructor stub
		this.urlTree=urlTree;
	}
	
	@Override
	public String etrName(String entyUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String etrAuthor(String entyUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String etrBrief(String entyUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String etrChapterName(String entyUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String etrImageName(String entyUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> books() {
		// TODO Auto-generated method stub
		List<Book> books=new ArrayList<>();
		
		//book
		for(String bookUrl:this.urlTree.getBookUrls()) {
			
			 Book book=new Book();
			 book.setAuthor(etrAuthor(bookUrl));
			 book.setBrief(etrBrief(bookUrl));
			 book.setName(etrName(bookUrl));
			 
			 //chapter
			 for(String chapterUrl:this.urlTree.getChapterUrls().get(bookUrl)) {
				 
				   Chapter chapter=new Chapter();
				   
				   chapter.setTitle(etrChapterName(chapterUrl));
				   
				   //image
				   for(String imageUrl:this.urlTree.getImageUrls().get(chapterUrl)) {
					   
					       Image image=new Image();
					       image.setName(etrImageName(imageUrl));
					    
				   }
			 }
			
		}
		
		
		return null;
	}


}
