package com.javis.iot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
	
	@GetMapping("/download")
	public void download(HttpServletResponse response) throws Exception{
		try {
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
