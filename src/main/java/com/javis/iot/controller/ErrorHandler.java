package com.javis.iot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandler {
	String path="/error";
	
	@GetMapping()
	public String error() {
		return "index.html";
	}
}
