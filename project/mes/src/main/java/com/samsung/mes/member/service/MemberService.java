package com.samsung.mes.member.service;

import java.util.List; //유 류[류시원]

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.samsung.mes.member.dto.MemberRequestDTO;
import com.samsung.mes.member.entity.Member;
import com.samsung.mes.member.repository.MemberRepository;

import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;

@Service
//@Transactional
//@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;//멤버변수2개 의존성 주입
	private final PasswordEncoder passwordEncoder;
	
	//
	@Autowired //생성자로 위에 맴버변수가 주입되어야 하는이유 
	//스프링이 자동으로 memberRepository, passwordEncoder를 넣어줌 이렇게 하면 서비스 안에서 바로 사용 가능
	public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional //중간에 실패하면 저장이 안되서 자동으로 롤백
	public Member register(MemberRequestDTO dto) {//회원가입 
		
		if(memberRepository.existsByEmail(dto.getEmail())) {
			throw new IllegalArgumentException("이미 가입된 이메일 입니다");
		}//같은 이메일이 있으면 가입 못하게 막음
		
		String encryptedPw = passwordEncoder.encode(dto.getPassword());
		//암호화된 문자열로 바꿔 저장
		
        // 3. DTO -> Entity (Builder 이용)
        Member member = Member.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(encryptedPw) // 실서비스에서는 암호화 필수
                .gender(dto.getGender())
                .companyName(dto.getCompanyName())
                .position(dto.getPosition())
                .tel(dto.getTel())
                .address(dto.getAddress())
                .detailAddress(dto.getDetailAddress())
                .build();
        return memberRepository.save(member);//db에 저장하고 저장된 객체를 리턴
    }
	
	public List<Member> getAllMembers(){//전체회원조회
		return memberRepository.findAll();
	}
	
	public Member getMemberById(Long id) {//특정회원조회
		return memberRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(""+id)
		);
	}
       
	@Transactional
	public void deleteMember(Long id) {//존재하는지 확인하고
		if(!memberRepository.existsById(id)) {
			throw new IllegalArgumentException("삭제할 회원이 없음"+id);
		}
	}

	public Member login(String email, String password) {
		Member member = memberRepository.findByEmail(email.trim())
				.orElseThrow(() -> new RuntimeException("존재하지 않는 이메일"));
		
	boolean ok = passwordEncoder.matches(password, member.getPassword());
	System.out.println(""+ok);
	
	//틀리면 예외 맞으며 회원 반환
	if(!ok) throw new RuntimeException("비밀번호 불일치");
	return member;
	}




}
/* 1. 비밀번호 일치 검사
if (!req.getPassword().equals(req.getRepeatPassword())) {
    throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
}*/

/* 2. 이메일 중복 검사
memberRepository.findByEmail(req.getEmail())
        .ifPresent(m -> {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
*/