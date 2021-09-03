package com.javis.iot.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javis.iot.command.*;

@RestController
public class RequestController {
	
	@GetMapping("/request_behavior")
	public String request_behavior(HttpServletResponse response, Model model) {
		ReturnDate createTime=new ReturnDate();
		//DownloadBehavior downBehavior = new DownloadBehavior();
		CameraProcess camera=new CameraProcess();
		
		String test=camera.start(createTime.GetCreateTime());
		/*try {
			downBehavior.Download(createTime.GetCreateTime(), response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		model.addAttribute("test", test);
		return "test";
	}
}
