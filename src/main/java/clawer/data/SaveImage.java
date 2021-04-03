package clawer.data;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import clawer.domain.image.Image;
import clawer.util.Helper;
import clawer.util.Tools;

@Service
public class SaveImage {
	public static void saveImageToFile(Image image) {
		

		String fullPath = Tools.Base_Save_Path + "/" + image.getSavePath();
		String dirPath = fullPath.substring(0, fullPath.length() - image.getName().length());

		// 建一个文件夹
		File fold = new File(dirPath);
		if (!fold.exists()) {
			fold.mkdirs();
		}

		File file = new File(fullPath);
		if (file.exists()) {
			System.out.println(image.getName() + " already exists! ");
			return;
		}

		try {
			String encondUrl=encode(image.getUrl(), "utf-8");
			URL url = new URL(encondUrl);

			// 如果网站图片用了referer防盗链，设置requestProperty
			/*
			 * URLConnection urlConnection=url.openConnection();
			 * urlConnection.setRequestProperty("referer","xxxurl"); DataInputStream
			 * dataInputStream = new DataInputStream(urlConnection.getInputStream());
			 */

			DataInputStream input = new DataInputStream(url.openStream());
			FileOutputStream output = new FileOutputStream(file);
			byte[] buffer = new byte[1024 * 100];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			System.out.println("saving " + image.getSavePath() + " is done!");

			output.close();
			input.close();
			image.setCompleted(true);
		} catch (MalformedURLException mex) {
			mex.printStackTrace();
			System.out.println("download image error!");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("download image error!");
		}
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	 private static String encode(String str, String charset) throws UnsupportedEncodingException {
		String zhPattern="[\\u4e00-\\u9fa5？，：；（）——“”、]";
	    Pattern p = Pattern.compile(zhPattern);
	    Matcher m = p.matcher(str);
	    StringBuffer b = new StringBuffer();
	    while (m.find()) {
	        m.appendReplacement(b, URLEncoder.encode(m.group(0), charset));
	    }
	    m.appendTail(b);
	    return b.toString();
	}

}
