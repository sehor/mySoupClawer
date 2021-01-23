package clawer.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SomeTest {

	@RequestMapping("/text")
	public String testRegx(@PathParam("value=input") String input) {

		return extSuffix(input);
	}
	
	@RequestMapping("/number")
	public String testExtNumberRegx(@PathParam("value=input") String input) {

		return extAndToNumber(input);
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

	private String extSuffix(String input) {
		if (input.contains(".jpg") || input.contains(".JPG")) {
			return ".jpg";
		} else if (input.contains(".gif") || input.contains(".GIF")) {
			return ".gif";
		} else if (input.contains(".jpeg") || input.contains(".JPEG")) {
			return ".jpeg";
		}

		return ".不明图画格式";
	}
}
