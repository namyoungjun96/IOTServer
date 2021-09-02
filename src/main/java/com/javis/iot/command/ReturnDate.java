package com.javis.iot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReturnDate {
	public String GetCreateTime(){
		LocalDateTime Today=LocalDateTime.now();
		String createTime=Today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		return createTime;
	}
}
