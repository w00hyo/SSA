package port.sm.erp.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import port.sm.erp.dto.MemberRequestDTO;
import port.sm.erp.entity.Member;
import port.sm.erp.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /*@Autowired
    public MemberService(MemberRepository memberRepository,
                         PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }*/
    /**
     * ð íìê°ì
     */
    @Transactional
    public Member register(MemberRequestDTO dto) {

           if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        /*
        String encryptedPw = passwordEncoder.encode(dto.getPassword());*/

        // DTO â Entity ë³í í ì ì¥
        Member member = Member.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .username(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .companyName(dto.getCompanyName())
                .position(dto.getPosition())
                .tel(dto.getTel())
                .gender(dto.getGender()) // gender
                .address(dto.getAddress())
                .detailAddress(dto.getDetailAddress())
                .build();

        return memberRepository.save(member);
    }

    /**
     전체회원조회
     */
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    /**
    단일회원조회
     */
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("회원이 존재 하지 않습니다. id=" + id)
                );
    }

    /**
    회원삭제
     */
    @Transactional
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제할 회원이 존재하지 않습니다. id=" + id);
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
