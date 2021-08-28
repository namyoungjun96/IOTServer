package com.javis.iot.command;

import java.util.*;
import java.io.*; 

class ProcessBuilderExample 
{ 
	public static void main(String[] arg) throws IOException 
	{ 
		// processs 목록을 생성
		List<String> list = new ArrayList<String>(); 
		list.add("cmd");
		//list.add("nslookup");

		// 프로세스 생성
		ProcessBuilder build = new ProcessBuilder(list); 
		// 프로세스 빌더 인스턴스에 저장된 커맨드 확인 
		System.out.println("command: " + build.start()); 
	} 
}