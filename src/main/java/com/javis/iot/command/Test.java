package com.javis.iot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
	public static void main(String[] args) {		
		LocalDateTime cal=LocalDateTime.now();
		String now=cal.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(now);
	}
}
