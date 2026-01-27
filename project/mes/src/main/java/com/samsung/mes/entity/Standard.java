package com.samsung.mes.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "standard",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_standard_code", columnNames = "std_code")
        },
        indexes = {
                @Index(name = "ix_standard_name", columnList = "std_name"),
                @Index(name = "ix_standard_group", columnList = "std_group"),
                @Index(name = "ix_standard_useyn", columnList = "use_yn")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Standard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 기준코드
    @Column(name = "std_code", length = 50, nullable = false)
    @NotBlank(message = "stdCode는 필수입니다")
    private String stdCode;

    // 기준명
    @Column(name = "std_name", length = 200, nullable = false)
    @NotBlank(message = "stdName은 필수입니다")
    private String stdName;

    // 그룹(프론트에서는 stdGroup 필수처럼 쓰고 있음)
    @Column(name = "std_group", length = 100, nullable = false)
    @NotBlank(message = "stdGroup은 필수입니다")
    private String stdGroup;

    // 단위 (optional)
    @Column(name = "unit", length = 30)
    private String unit;

    // 사용여부 Y/N
    @Column(name = "use_yn", length = 1, nullable = false)
    @Pattern(regexp = "Y|N", message = "useYn은 Y 또는 N")
    private String useYn;

    // 비고
    @Column(name = "remark", length = 500)
    private String remark;

    // 수정일(등록/수정 시 자동 갱신)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (useYn == null || useYn.isBlank()) useYn = "Y";
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
