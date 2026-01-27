package com.samsung.mes.member.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_member_id") 오라클 일때
	@GeneratedValue(strategy = GenerationType.IDENTITY)//mysql이나 mariadb일때 는 pk로
	@Column(name = "id")
	private Long id;
/*
타입	저장 가능 범위
int	약 21억(2,147,483,647)
long / Long	약 9 * 10^18 (거의 무제한 수준)
왜 Long(래퍼 클래스)인가? long과 차이점?
long	Long
기본 타입(primitive)	클래스 타입(wrapper)
null 저장 불가	null 가능 → DB에서 값 생성되기 전까지 null 형태로 존재 가능

int는 범위가 작다 → 서비스가 커지면 위험
Long은 훨씬 넓은 범위라 ID 중복 가능성이 사실상 없음
@GeneratedValue 사용 시 값이 없을 수 있으므로 null 허용되는 Long 사용이 안전
*/
@Column(name = "first_name")
private String firstName;

@Column(name = "last_name")
private String lastName;

@Column(name = "email", nullable=false, unique = true)
private String email;

@Column(name = "password", nullable=false)
private String password;

@Transient
//@Column(name = "repeat_password", nullable=false)
private String repeatPassword;


@Column(name = "gender", nullable=false)
private String gender;

@Column(name = "company_name")
private String companyName;

@Column(name = "position")
private String position;

@Column(name = "tel")
private String tel;

@Column(name = "address")
private String address;

@Column(name = "detail_address")
private String detailAddress;

@Column(name = "created_at", nullable=false)
private LocalDateTime createdAt;

@Column(name = "provider")
private String provider; // kakao, google, naver

@Column(name = "provider_id")
private String providerId; // 소셜 서비스에서 주는 고유 ID

@PrePersist
public void prePersist() {
	if(createdAt == null) {
		createdAt = LocalDateTime.now();
	}
}


}
