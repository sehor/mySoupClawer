package clawer.data;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Service;

import clawer.domain.image.Image;

@Service
public class SaveImage {
	public void saveImageToFile(Image image,String imgUrl) {
		// TODO Auto-generated method stub
		final String baseDir = "E:/clawerImages/";

		String fileName = image.getName();
		if (imgUrl == null) {
			return;
		}

		File file = new File(baseDir + fileName);

		if (file.exists()) {
			System.out.println(fileName + " already exists! ");
			return;
		}

		try {
			URL url = new URL(imgUrl);

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
			System.out.println("download " + fileName + " done!");
			output.close();
			input.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
