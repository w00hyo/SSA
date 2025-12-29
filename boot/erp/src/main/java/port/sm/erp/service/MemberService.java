package port.sm.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import port.sm.erp.dto.MemberRequestDTO;
import port.sm.erp.entity.Member;
import port.sm.erp.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository,
                         PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }
    /**
     * ð íìê°ì
     */
    @Transactional
    public Member register(MemberRequestDTO dto) {

           if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("ì´ë¯¸ ì¬ì© ì¤ì¸ ì´ë©ì¼ìëë¤.");
        }

        // ë¹ë°ë²í¸ í´ì±
        String encryptedPw = passwordEncoder.encode(dto.getPassword());

        // DTO â Entity ë³í í ì ì¥
        Member member = Member.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(encryptedPw)
                .companyName(dto.getCompanyName())
                .position(dto.getPosition())
                .tel(dto.getTel())
                .gender(dto.getGender()) // gender ì¶ê°
                .address(dto.getAddress())
                .detailAddress(dto.getDetailAddress())
                .build();

        return memberRepository.save(member);
    }

    /**
     * ð ì ì²´ íì ì¡°í
     */
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    /**
     * ð ë¨ì¼ íì ì¡°í
     */
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("í´ë¹ íìì ì°¾ì ì ììµëë¤. id=" + id)
                );
    }

    /**
     * â  íì ì­ì 
     */
    @Transactional
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("ì­ì í  íìì´ ì¡´ì¬íì§ ììµëë¤. id=" + id);
        }
        memberRepository.deleteById(id);
    }
    
    //login
    public Member login(String email, String password) {
        Member member = memberRepository.findByEmail(email.trim())
            .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일"));

        boolean ok = passwordEncoder.matches(password, member.getPassword());
        System.out.println("✅ [SERVICE] matches=" + ok);

        if (!ok) throw new RuntimeException("비밀번호 불일치");
        return member;
    }

    
    
    
    
    
    
    
    
}
