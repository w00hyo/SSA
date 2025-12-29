package port.sm.erp.controller;

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

import lombok.RequiredArgsConstructor;
import port.sm.erp.dto.LoginRequestDTO;
import port.sm.erp.dto.LoginResponse;
import port.sm.erp.dto.MemberRequestDTO;
import port.sm.erp.entity.Member;
import port.sm.erp.security.JwtUtil;
import port.sm.erp.service.MemberService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    /**
     * 1️⃣ 모든 회원 조회
     */
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    /**
     * 2️⃣ 특정 회원 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        return ResponseEntity.ok(member);
    }

    /**
     * 3️⃣ 회원가입 (React 폼에서 호출)
     */
    @PostMapping
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
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("회원 삭제 완료");
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO req) {
        //System.out.println("✅ login 진입: " + req.getEmail());

        try {
            Member member = memberService.login(req.getEmail(), req.getPassword());
            //System.out.println("✅ service login 성공: " + member.getEmail());

            String token = jwtUtil.createToken(member.getEmail()); // 여기서도 터질 수 있음
            //System.out.println("✅ token 생성 성공");

            return ResponseEntity.ok(new LoginResponse("success", token));
        } catch (Exception e) {
            //System.out.println("❌ login 에러 발생: " + e.getClass().getName() + " / " + e.getMessage());
            e.printStackTrace(); // ⭐ 이게 핵심
            return ResponseEntity.status(500).body("login error");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
