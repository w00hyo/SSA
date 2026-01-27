package com.samsung.mes.member.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samsung.mes.member.dto.LoginRequestDTO;
import com.samsung.mes.member.dto.LoginResponse;
import com.samsung.mes.member.dto.MemberRequestDTO;
import com.samsung.mes.member.entity.Member;
import com.samsung.mes.member.service.MemberService;
import com.samsung.mes.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/*
이 콘트롤러가 하는일
/member 로 들어오는 요청을 받아서
회원목록 조회, 회원 1명 조회, 회원가입, 회원삭제, 로그인(JWT토큰 발급)
*/
@RestController//웹 API 컨트롤러 => 메서드가 리턴하는 값이 그대로 JSON/문자열 응답
@RequestMapping("/members")
@CrossOrigin(origins = "http://localhost:5173") // 리액트 개발 서버 주소
@RequiredArgsConstructor
public class MemberController {

	//필드 의존성 주입 콘트롤러는 혼자서는 db 저장 /로그인 검증 / 토큰생성을 안함
    private final MemberService memberService;//회원관련 일을 시키고
    private final JwtUtil jwtUtil;//토큰 만드는걸 시킵니다
    
    //모든회원조회 서비스에서 회원목록을 가져오고 200 ok 회원 리스트를 응답으로 보냄
    @GetMapping 
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    //특정회원조회
    @GetMapping("/{id}")//url에 들어오는 숫자를 받는 자리
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@RequestBody MemberRequestDTO dto) {
        // Service에서 저장된 Member 반환
        Member savedMember = memberService.register(dto);

        // 클라이언트에게 저장된 회원 ID와 메시지 전달
        return ResponseEntity.ok(
                String.format("회원가입 완료! ID: %d", savedMember.getId())
        );
    }
    
    /**
     * 4️⃣ 회원 삭제
     */
    @DeleteMapping("/{id}")//삭제 국룰 id를 삭제하므로써 모든 정보가 날라감
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("회원 삭제 완료");
    }
    
    @PostMapping("/login")
    //이메일로 회원을 찾고 비밀번호가 맞는지 확인 맞으면 맴버객체를 반환하고 틀리면 null 처리등을 발생
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO req) {
        //System.out.println("✅ login 진입: " + req.getEmail());

        try {
            Member member = memberService.login(req.getEmail(), req.getPassword());
            //System.out.println("✅ service login 성공: " + member.getEmail());

            String token = jwtUtil.createToken(member.getEmail()); // 여기서도 터질 수 있음
            //System.out.println("✅ token 생성 성공");
            
            //이름하나 넣는것만으로 코드량이 추가
            LoginResponse response = new LoginResponse(
"success", token, member.getFirstName(), member.getLastName()
            );
            

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            //System.out.println("❌ login 에러 발생: " + e.getClass().getName() + " / " + e.getMessage());
            e.printStackTrace(); // ⭐ 이게 핵심
            return ResponseEntity.status(500).body("login error");
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
    	//logout이라는 메서드 매개변수로 HttpServletRequest request를 받음 
    	//응답(HTTP 결과)을 직접 만들겠다는 뜻 Void = 응답 body(내용)는 따로 안 보내겠다는 뜻
HttpSession session = request.getSession(false);//있으면 가져오고 없으면 만들지 말고 null
//세션이 없어도 true를 쓰면 true를 새 세션을 만들어버려서 로그아웃인데도 세션이 생기는 이상한 현상이 발생함
    	if (session != null ) {//세션이 존재할 때만 세션을 파기 하겠다는 의미
    		session.invalidate();//세션 파기
    	}
    	return ResponseEntity.ok().build();//http상태코드 200ok 응답만 보내고 끝. 
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
