package com.sm.kr.dto;

import lombok.Getter;//장문에 글이 ..간소화
import lombok.NonNull;
import lombok.Setter;

//int 원시값 원시값에 객체형을 래퍼클래스 래퍼클래스를 사용이유 자바에 컬렉션은 list등이 있는데
//List, Map은 객체만 저장함 int불가능 integer가능
//int 지태 = null //0 Integer 탈락지태 = null;
@Getter
@Setter //롬복에 getter setter를 어노테이션 선언하면 아주 심플해짐 not null 값이 필수로 들어감
public class BootCreateDTO {
	
	@NonNull
	private String title;
	
	@NonNull
	private Integer price;

}
/*
Data Transfer Object : 값을 담는 컨테이너 객체
우리가 쓰기 page를 사용할때 제목하고 가격(title, price)를 입력 했을때
서버로 전달할 값을 담아주는 역활을 합니다

DAO : 데이터 엑세스 오브젝트 
*/
