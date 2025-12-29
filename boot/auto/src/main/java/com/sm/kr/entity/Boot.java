package com.sm.kr.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //이클래스가 jpa 엔티티임을 나타냅니다
@Data //롬복어노테이션으로 getter, setter, toString, equals, hashCode
@Builder //롬복어노테이션으로 객체 생성시에 빌더패턴을 사용할수 있음
@NoArgsConstructor
@AllArgsConstructor
public class Boot {
	
	
	@Id//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bootId;
	//mysql AUTO_INCREMENT same same
	
	@Column(length = 200)
	private String title;
	private Integer price;
	
	@CreationTimestamp
	private LocalDateTime insertDateTime;
	
	
	
	

}
