package com.javis.iot.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class DownloadBehavior {

	public void Download(String createTime, HttpServletResponse response) throws Exception {
		try {
			//String path = "D:\\Spring\\Video\\"+createTime+".mp4";
			String path = "D:\\Spring\\Video\\samplevideo.mp4";

			File file = new File(path);
			response.setHeader("Content-Disposition", "attachment; filename="+file.getName());

			FileInputStream fileInputStream = new FileInputStream(path);
			OutputStream out = response.getOutputStream();

			int read = 0;
			byte[] buffer = new byte[1024];
			while((read = fileInputStream.read(buffer)) != -1 ) {
				out.write(buffer, 0, read);
			}

			fileInputStream.close();
		} catch (Exception e) {
			throw new Exception("download error");
		}
	}
}
