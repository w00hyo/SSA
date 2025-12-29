package com.hbk.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbk.model.MemberVO;
import com.hbk.service.MemberService;


@Controller
@RequestMapping(value = "/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	/*@Autowired
	private MemberService memberservice;*/
	
	@Autowired
	private JavaMailSender mailSender;

	//회원가입 페이지 이동
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void loginGET() {
		
		logger.info("회원가입 페이지 진입");
		
	}
	//회원가입 메서드 추가
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinPOST(MemberVO member) throws Exception{
		
		logger.info("join 진입");
		
		/*회원가입 서비스 실행
		memberservice.memberJoin(member);*/
		
		logger.info("join Service 성공");
		
		return "redirect:/main";
		
	}
	
	
	//로그인 페이지 이동
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void joinGET() {
		
		logger.info("로그인 페이지 진입");
		
	}
	
	/*이메일 인증 mailCheckGET추가*/
	/*반환타입을 스트링으로 만들었을경우 온전하게 데이터를 전송하기 위해서는 ResponseBody어노테이션 필요*/
	@RequestMapping(value="/mailCheck", method=RequestMethod.GET)
	@ResponseBody
	public void mailCheckGET(String email)throws Exception{
		/*뷰로 넘어온 데이터 확인*/
		logger.info("이메일 데이터 전송확인");
		logger.info("인증번호: "+email);
		
		/*인증번호 난수 생성*/
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		/*random.nextInt함수를 통해서 난수를 생성합니다 checkNum변수를 선언하고 난수생성 결과값을 할당합니다*/
	    logger.info("인증번호"+checkNum);
	    
	    /*이메일 보내기*/
	    String setFrom = "anakyn@naver.com";
	    String toMail = email;
	    String title = "회원가입 인증이메일 입니다";
	    String content =
	    		"사이트를 방문해 주셔서 감사합니다" + "<br>"+
	            "인증 번호는 "+checkNum + "입니다"+
	    		"해당 인증번호를 인증번호 확인란에 기입하여 주세요";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
