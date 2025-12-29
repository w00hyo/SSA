package com.sm.kr.dto;
//Data Transfer Object 계층간에 데이터전달을 위한 객체
import java.time.LocalDateTime;

import com.sm.kr.entity.Boot;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //기본생성자 파라미터 없는 생성자
@Getter //getBootId(), getTitle()등을 자동생성
public class BootEditResponseDTO{
	
	private Integer bootId;
	private String title;
	private Integer price;
	private LocalDateTime insertDateTime;
	
	//수정하는 메소드
	public BootEditResponseDTO fromBoot(Boot boot) {
		
	this.bootId = boot.getBootId();//수정하기 때문에 똑같은 순번이 나오고
	this.title = boot.getTitle();//타이틀을 수정할수도 있고 안할수도 있지만 불러옵니다
	this.price = boot.getPrice();//수정할수도 있고 안할수도 있지만 불러옵니다
	this.insertDateTime = boot.getInsertDateTime();
	return this;
		/*
		Boot객체를 받아서 해당 객체의 필드값을 현재 DTO객체의 필드에 복사
	매서드 마지막에서 this를 반환하므로 메서드 체이닝도 가능합니다	
		*/
		
	}
	
	//정적팩토리 메서드
	public static BootEditResponseDTO BootFactory(Boot boot) {
		BootEditResponseDTO bootReadResponseDTO = new BootEditResponseDTO();
//bootReadResponseDTO는 	BootEditResponseDTO()로 객체를 생성하는데 나중에 	Boot객체의 데이터를 기반으로 초기화 됩니다
bootReadResponseDTO.fromBoot(boot);
// 객체로 만든 bootReadResponseDTO는 fromBoot(boot)매서드를 호출하여 bootReadResponseDTO객체에 boot객체의 데이터를 채운다

		return bootReadResponseDTO;
		//최종적으로 생성된 DTO객체를 리턴
	}
	
	
	
	
	
	
	
	
	
	
}
