package com.react.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.react.mvc.dto.LoginRequest;
import com.react.mvc.dto.LoginResponse;
import com.react.mvc.repository.MemberRepository;
import com.react.mvc.vo.Member;

import lombok.RequiredArgsConstructor;

//i am a goodboy
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	
  private final MemberRepository memberRepository;
  
  public String register(Member member){//같은 이메일을 가진 회원이 이미 db에 있는지 확인하는 로직
  //이메일 중복 체크
	  boolean exists = memberRepository.findByEmail(member.getEmail()).isPresent();
	  //isPresent 같은값이 있으면 ture, 아니면 false
	  if(exists) {
		  return "duplicate";
	  }
	  memberRepository.save(member);
	  return "success";
  }
  
  @Transactional(readOnly = true) //트랜잭션안에서 실행되는데 읽기 전용으로 실행
  //데이터베이스에서 작업을 하나의 묶음으로 처리하는것 다성공하면 commit 하나라도 실패하면 전부취소 롤백
  public LoginResponse login(LoginRequest req) {
	  return memberRepository.findByEmailAndPassword(req.getEmail(), req.getPassword())
			  .map(member -> new LoginResponse(//조회를 성공할때만 성공 loginresponse생성
					  "success",
					  null,
					  member.getFirstName(),
					  member.getLastName(),
					  member.getEmail()			
			   )).orElseGet(()-> new LoginResponse(//실패할 경우 null리턴
					  "fail",
					  "이메일 또는 비밀번호가 올바르지 않습니다",
					  null,
					  null,
					  null
					   ));
  }
  
  
  
}
/*
public class MemberService {
	
	private final MemberDAO memberDAO;
	
	public void register(Member member) {
		memberDAO.insertMember(member);
	}

}*/
