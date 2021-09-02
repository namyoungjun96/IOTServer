package com.javis.iot.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javis.iot.command.*;

@RestController
public class RequestController {
	
	@GetMapping("/request_behavior")
	public void request_behavior(HttpServletResponse response) {
		ReturnDate createTime=new ReturnDate();
		//DownloadBehavior downBehavior = new DownloadBehavior();
		CameraProcess camera=new CameraProcess();
		
		camera.start(createTime.GetCreateTime());
		/*try {
			downBehavior.Download(createTime.GetCreateTime(), response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
