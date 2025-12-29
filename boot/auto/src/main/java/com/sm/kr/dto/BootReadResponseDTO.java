package com.sm.kr.dto;

import java.time.LocalDateTime;

import com.sm.kr.entity.Boot;//엔티티 jpa클래스

import lombok.Getter;
import lombok.NoArgsConstructor;

/*
엔티티 클래스 Boot에서 필요한 데이터만 추출해서 외부(주로 프론트엔드 나 외부시스템)에서 전달할때 사용하는
DTO객체 입니다

*/
@NoArgsConstructor //매개변수가 없는 생성자
/*
JavaBean 규약 때문에 
java에서 프레임워크(Spring, Hibernate)는 객체를 생성할때 기본 생성자를 사용해야 한다
JPA(Entity객체) JSON 직렬화 / 역직렬화(jackson)
Spring 의 @RequestBody, @ModelAttribute 
DTO매핑등
이럴때 기본 생성자가 없으면 예외가 발생합니다

Lombok과 함께 사용하는 대부분의 경우

public BootReadResponseDTO(){
//아무것도 하지 않음

}
*/
@Getter //기존에 있던 값을 [setter로 세팅했던 값을]리턴하기에 getter를 사용
public class BootReadResponseDTO {
	private Integer bootId;
	private String title;
	private Integer price;
	private LocalDateTime insertDateTime;
	
	//생성자 자바에서 클래스를 뺀 클래스 명칭과 동일하게 사용
	//fromBoot 메서드 
	public BootReadResponseDTO fromBoot(Boot boot) {//엔티티클래스 boot를 매개변수로 사용하는 함수
		this.bootId = boot.getBootId(); //글에 고유 id
		this.title = boot.getTitle(); //글에 제목
		this.price = boot.getPrice(); //글에 가격
		this.insertDateTime = boot.getInsertDateTime(); //시간
		
		return this;
		/*
		이 메서드는 Boot객체를 받아서 현재 BootReadResponseDTO 객체에 필드에 데이터를 복사합니다
		자기자신(this)을 반환하기 때문에 메서드 체이닝이 가능합니다
		
		*/
	}
	
	public static BootReadResponseDTO BootFactory(Boot boot) {//팩토리메서드 패턴
		BootReadResponseDTO bootReadResponseDTO = new BootReadResponseDTO();
		bootReadResponseDTO.fromBoot(boot);
		return bootReadResponseDTO;
	}
	/*
	인스턴스 메서드 클래스의 객체(인스턴스)를 생성한후에 호출할수 있는 메서드
	- 클래스 자체가 아닌 클래스에서 만들어진 객체를 통해 호출
	- 그렇게 하지 않으면 컴파일 오류가 발생
	public class Person{
	private String name;
	
	public void sayHello(){
	System.out.println("나는 " +name)
	}
	
	Person p = new Person();
	p.sayHello()
	
	Person.sayHello() = x
	}
	
	정적(static) 메서드 
	- 객체를 생성하지 않아도 클래스 이름만으로 호출할수 있는 메서드
	
	public class MathUtils{
	public static int add(int a, int b){
	return a + b;
	}
	
	int result = MathUtils.add(3,4)
	}
	
	
	*/
	
	
	
}
