package clawer.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clawer.data.DataUtils;
import clawer.domain.chapter.Chapter;
import clawer.exception.ClawerException;
import clawer.exception.ClawerExceptionFactory;
import clawer.exception.ClawerExceptionType;

@RestController
@RequestMapping("/test")
public class SomeTest {
    @Autowired
    DataUtils dataUtils;

	@RequestMapping("/number")
	public String testExtNumberRegx(@PathParam("value=input") String input) {

		return extAndToNumber(input);
	}
	
	@RequestMapping("/enum")
	public List<ClawerExceptionType> getEnum() {
		
		return List.of(ClawerExceptionType.BookName_No_Founded,ClawerExceptionType.BookUrls_No_Founded);
	}
		
	@RequestMapping("/exception")
	public List<ClawerException> getException() {
		
		return List.of(ClawerExceptionFactory.ChapterNameNoFound(new Chapter()));
	}
	
	private String extAndToNumber(String input) {
		
         String regx="第*([一二三四五六七八九十百千]{1,5})/s*章";
         Pattern p=Pattern.compile(regx);
         Matcher matcher=p.matcher(input);
         if(matcher.matches()) {
        	 return matcher.group(1);
         }
		 
		 return "未找到章节数";
		 				 
	}

 @RequestMapping("/warnDeleteDB")
	  
 public String deleteAll() {
	  dataUtils.deleletAll();
	  return "done";
 }
}
