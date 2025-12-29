package com.sm.kr.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sm.kr.entity.Boot;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter//세팅된걸 리턴
@Setter//세팅
public class BootEditDTO {
	
	//필드선언과 검증 어노테이션
	@NonNull //not null과 같다 
	@Positive//반듯이 양수 이어야 합니다
	private Integer bootId;
	
	
	@NonNull //null이면 안됨
	@NotBlank //String에만 한정적 어노테이션 비어 있거나
	//공백만 있어도 안됩니다
	private String title;
	//제목은 필수이며 공백이 아닌 문자열 이어야 합니다
	
	@NonNull
	@Min(1000)
	private Integer price;
	
	//전달받은 boot객체의 제목과 가격만 수정합니다
	//fill(Boot boot) 기존 boot객체에 제목과 가격만 업데이트
	public Boot fill(Boot boot) {
		boot.setTitle(this.title);
		boot.setPrice(this.price);
		return boot;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
