package com.javis.iot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReturnDate {
	public String GetCreateTime(){
		LocalDateTime Today=LocalDateTime.now();
		// 년-월-일 시간:분:초로 되있는 정보를 가져온다
		
		String createTime=Today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		// 한번 더 포매팅 ( sql datetime에 맞게하기위해 )
		
		return createTime;
	}
}
