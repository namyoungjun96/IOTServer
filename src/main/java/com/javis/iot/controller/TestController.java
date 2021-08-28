package com.javis.iot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@Autowired
	ResourceLoader resourceLoader;
	
	@Value("${file.path}")
	private String file_Path;
	
	@RequestMapping("/TEST")
	public ResponseEntity<InputStreamResource> request_behavior(
			@RequestParam(defaultValue = "test") String fName) throws IOException {
			// HTTP에서 request를 요청한 값을 받는다.
		System.out.println("fName = " + fName);
		String path = file_Path + File.separator + fName;
		// 

		// 파일 경로
		File file = new File(path);
		boolean fExist = file.exists();
		// 파일 존재 유무
		if(fExist) {
			// 파일이 존재 할 경우
			String fileName = file.getName();
			// 파일 이름을 받아온다
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			// 파일 확장자
			HttpHeaders header = new HttpHeaders();
			// Http헤더 값을 header에 할당한다.
			Path fPath = Paths.get(file.getAbsolutePath());
			// 파일의 경로를 다 긁어온다.
			
			
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
			header.add("Cache-Control", "no-cache, no-store, must-revalidate");
			header.add("Pragma", "no-cache");
			header.add("Expires", "0");
			
			InputStreamResource resource3 = new InputStreamResource(new FileInputStream(file));
			
			return ResponseEntity.ok()
					.headers(header)
					.contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/octet-stream"))
					.body(resource3);
		}
		
		return null;
	}
}
