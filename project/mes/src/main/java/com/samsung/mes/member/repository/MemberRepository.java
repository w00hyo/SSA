package com.samsung.mes.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.samsung.mes.member.entity.Member;

//이코드는 회원정보를 db에서 쉽게 꺼내기 위한 창구 입니다
//DB랑 대화하는 대화하는 전담 인터페이스 sql을 직접 안써도 메서드 이름만으로 select, insert, update, delete를 만들어줌
//이 한줄이 모든걸 의미함 결과적으로 멤버테이블을 다루는 db관리자
public interface MemberRepository extends JpaRepository<Member, Long> {
/*
extends JpaRepository<Member, Long>이한줄을 쓰는것 만으로
save(member)           // insert / update
findById(id)           // select by PK
findAll()              // select *
deleteById(id)         // delete
count()                // select count(*)
*/
	Optional<Member> findByEmail(String email);//이메일로 회원 찾기 이메일 컬럼을 기준으로 회원 한명을 찾아서 있으면 member 없으면 null
	//실수로 NullPointerException 터지는걸 방지
	
	boolean existsByEmail(String email);//이메일 존재 여부 체크 true 가입 못함, false 가입가능
	
	//소셜로그인용 조회 
	Optional<Member> findByProviderAndProviderId(String provider, String providerId);

}
/*
JpaRepository<Member, Long> => <엔티티클래스, 프라이머리키 타입>
그리하여 그리하여
이한줄로 기본 메서드들이 자동으로 생김
save() => insert 또는 update
saveAll() 여러개 한꺼번에 저장

findById(id)  => pk로 한건조회 없으면 Optinal.empty()
exitsById(id) => 해당 pk가 존재하는지만 boolean으로 확인

findAll() => 전체 조회
findAllById(ids)  => 여러 id로 한번에 조회(in쿼리느낌)

delete(member) 삭제
delateById(id) pk로 삭제
deleteAll(entities) 전달된 목록만 삭제
deleteAll() 테이블 전체삭제

count() - 개수 세기


Repository = DB담당자
Member 엔티티를 db에 저장 /조회/삭제 하는 일을 전담하는 계층
자 그리고 class가 아닌 인터페이스인 이유
1) 구현체는 우리가 직접 만들지 않음
2) Spring Data jpa가 실행ㅅ기점에 자동으로구현 클래스 만들어서 빈으로 등록
3) 이런 메서드가 필요해
*/









