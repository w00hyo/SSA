package com.react.mvc.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.react.mvc.dto.LoginRequest;
import com.react.mvc.dto.LoginResponse;
import com.react.mvc.service.MemberService;
import com.react.mvc.vo.Member;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@CrossOrigin(origins="http://localhost:5173")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping("/register")
	public String register(@RequestBody Member member) {
		try {
			memberService.register(member);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		//@RequestBody LoginRequest request 프론트가 보낸 json데이터를 LoginRequest객체로 변환하여 받음
		return memberService.login(request);
	}
	
	
	
	
	
	
}
