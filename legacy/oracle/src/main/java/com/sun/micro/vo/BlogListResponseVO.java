package com.sun.micro.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BlogListResponseVO {

	private int blgContSeq;/* 여기를 통틀어서 맴버변수[클래스안에 선언된 변수 를 말합니다 클래스의 속성을 표현] 합니다*/
	private String title, contBdy;
	private LocalDateTime insertDt;
	
	//세팅된 값을 리턴 getter
	public int getBlgContSeq() {return blgContSeq;}
	public String getTitle() {return title;}
	public String getContBdy() {return contBdy;}
	public LocalDateTime getInsertDt() {return insertDt;}
	
	//세팅하는 값 세터
    public void setBlgContSeq(int blgContSeq) {this.blgContSeq = blgContSeq;}
	public void setTitle(String title) {this.title = title;}
	public void setContBdy(String contBdy) {this.contBdy = contBdy;}
	public void setInsertDt(LocalDateTime insertDt) {this.insertDt = insertDt;}
	
	   //함수생성
	   public String getInsertDtFormat() {
	      return this.insertDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	   }
	
	
	
	
}
