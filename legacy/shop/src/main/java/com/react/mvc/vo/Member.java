package com.react.mvc.vo;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "MEMBER")
@SequenceGenerator(
        name = "member_seq",
        sequenceName = "MEMBER_SEQ",   // 오라클 시퀀스 이름
        allocationSize = 1
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    private Long id;

    // ==============================
    //      이름 정보
    // ==============================
    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    // ==============================
    //      로그인 정보
    // ==============================
    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 200)
    private String password;

    // 비밀번호 확인용 → DB 저장 X
    @Transient  // JPA가 이 필드는 무시하게 함
    private String repeatPassword;

    // ==============================
    //      추가 정보
    // ==============================
    @Column(name = "GENDER", length = 10)
    private String gender;

    @Column(name = "COMPANY_NAME", length = 100)
    private String companyName;

    @Column(name = "POSITION", length = 50)
    private String position;

    @Column(name = "TEL", length = 30)
    private String tel;

    @Column(name = "ADDRESS", length = 200)
    private String address;

    @Column(name = "DETAIL_ADDRESS", length = 200)
    private String detailAddress;
}
