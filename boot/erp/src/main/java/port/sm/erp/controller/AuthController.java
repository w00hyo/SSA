package port.sm.erp.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import port.sm.erp.dto.MemberRequestDTO;
import port.sm.erp.entity.Member;
import port.sm.erp.security.JwtUtil;
import port.sm.erp.service.MemberService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {//프론트에서 로그인 요청을 받아 jwt를 생성해주는 Controller가 필요

	private final MemberService memberService;
	private final JwtUtil jwtUtil;
	
	public AuthController(MemberService memberService, JwtUtil jwtUtil) {
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String>body){
		String email = body.get("email");
		String password = body.get("password");
		
		Member m = memberService.login(email, password);
		
		String token = jwtUtil.generateToken(m.getId(), m.getEmail());
		
		return ResponseEntity.ok(Map.of("token", token, "id", m.getId(), "email", m.getEmail()));
		
	}
	
	
	@PostMapping("/register")
    public ResponseEntity<Member> register(@RequestBody MemberRequestDTO dto) {
        Member m = memberService.register(dto);
        return ResponseEntity.ok(m);
    }
	
	
	
}
