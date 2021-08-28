package com.javis.iot.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileTestController {
	@Autowired
	private ResourceLoader resourceLoader;

	@GetMapping("/request_behavior")
	public ResponseEntity<Resource> fileDownload() throws IOException {
		Resource resource = resourceLoader.getResource("D:\\Spring\\Video");
		File file = resource.getFile();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment;filename=\"" + resource.getFilename() + "\"")
				.header(HttpHeaders.CONTENT_TYPE, "multipart/formed-data")
				.header(HttpHeaders.CONTENT_LENGTH, file.length() + "")
				.body(resource);
	}
}
