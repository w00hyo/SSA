package com.react.mvc.repository;

//스프링에서 네임스페이스를 부를때 빨간줄이 뜨면 대부분 di를 안한것이다
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.react.mvc.vo.Member;


public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Optional<Member> findByEmail(String email);
	Optional<Member> findByEmailAndPassword(String email, String password);

}
