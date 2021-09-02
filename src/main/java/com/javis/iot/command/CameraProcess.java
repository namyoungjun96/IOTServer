package com.javis.iot.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CameraProcess { 
	
	public void start(String createTime) {
		List<String> list = new ArrayList<String>(); 
		list.add("raspivid -o cam.h264");
		list.add("MP4Box -add cam.h264 "+createTime+".mp4");
		
		ProcessBuilder build = new ProcessBuilder(list); 
		
		try {
			System.out.println("command: " + build.start());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} 
}