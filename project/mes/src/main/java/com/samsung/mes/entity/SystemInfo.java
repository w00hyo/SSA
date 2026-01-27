package com.samsung.mes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "system_info",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_system_code", columnNames = "system_code")
        },
        indexes = {
                @Index(name = "ix_system_name", columnList = "system_name"),
                @Index(name = "ix_system_group", columnList = "system_group"),
                @Index(name = "ix_system_owner", columnList = "owner"),
                @Index(name = "ix_system_status", columnList = "status"),
                @Index(name = "ix_system_useyn", columnList = "use_yn")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 시스템코드
    @Column(name = "system_code", length = 50, nullable = false)
    @NotBlank(message = "systemCode는 필수입니다")
    private String systemCode;

    // 시스템명
    @Column(name = "system_name", length = 200, nullable = false)
    @NotBlank(message = "systemName은 필수입니다")
    private String systemName;

    // 그룹
    @Column(name = "system_group", length = 100, nullable = false)
    @NotBlank(message = "systemGroup은 필수입니다")
    private String systemGroup;

    // 담당자(선택)
    @Column(name = "owner", length = 100)
    private String owner;

    // 버전(선택)
    @Column(name = "version", length = 50)
    private String version;

    // 상태(선택) ACTIVE / INACTIVE / MAINTENANCE
    @Column(name = "status", length = 20)
    @Pattern(regexp = "ACTIVE|INACTIVE|MAINTENANCE", message = "status는 ACTIVE/INACTIVE/MAINTENANCE")
    private String status;

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
        if (status == null || status.isBlank()) status = "ACTIVE";
        if (useYn == null || useYn.isBlank()) useYn = "Y";
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
