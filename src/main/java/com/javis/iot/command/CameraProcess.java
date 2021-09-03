package com.javis.iot.command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CameraProcess { 
	
	public String start(String createTime) {
		String test;
		
		List<String> list = new ArrayList<String>(); 
		list.add("raspivid -o cam.h264");
		list.add("MP4Box -add cam.h264 "+createTime+".mp4");
		
		ProcessBuilder build = new ProcessBuilder(list);
		build.directory(new File("/home/pi/Desktop/IOTServer")); 
		
		try {
			System.out.println("command: " + build.start());
			test="성공";
			return test;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test="실패";
			return test;
		} 
	} 
}