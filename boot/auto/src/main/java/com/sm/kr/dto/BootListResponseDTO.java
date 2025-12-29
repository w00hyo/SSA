package com.sm.kr.dto;

import lombok.Getter;

@Getter	//목록은 어떤 개발에서도 기존에 작성한 글들에 집합이 보이므로 가지고 있는 값만 리턴합니다
public class BootListResponseDTO {
	private Integer bootId;//글에 순번이 들어있는 필드
	private String title;//제목을 저장하는 필드
	
	//이생성자는 객체를 초기화 할때 사용합니다
	public BootListResponseDTO(Integer bootId, String title) {
		this.bootId = bootId;
		this.title = title;
	}
	

	
}
