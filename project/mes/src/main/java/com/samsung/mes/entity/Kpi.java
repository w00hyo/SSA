package com.samsung.mes.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
name = "kpi",
uniqueConstraints = {
    @UniqueConstraint(
    name = "uk_kpi_name_period",
    columnNames = {"kpi_name", "period_type", "period_value"}
    )
},indexes = {
        @Index(name = "ix_kpi_name", columnList = "kpi_name"),
        @Index(name = "ix_kpi_owner", columnList = "owner"),
}
)
@Getter
@Setter
@NoArgsConstructor// 기본 생성자
@AllArgsConstructor// 모든 필드 생성자
@Builder //객체를 편하게 생성
public class Kpi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kpi_name", length=200, nullable = false)
    @NotBlank
    private String kpiName;

    @Column(name = "kpi_group", length = 100)
    private String kpiGroup;

    @Column(name ="owner", length=100)
    private String owner;

    @Column(name = "period_type", length=10, nullable = false)
    @NotBlank
    @Pattern(regexp = "MONTH|QUARTER|YEAR")
    private String periodType;

    @Column(name = "period_value", length=20, nullable = false)
    @NotBlank
    private String periodValue;

    @Column(name = "target_value", precision = 18, scale = 2)
    @DecimalMin("0.0")
    private BigDecimal targetValue;

    @Column(name = "actual_value", precision = 18, scale = 2)
    @DecimalMin("0.0")
    private BigDecimal actualValue;

    @Column(name ="unit", length = 30)
    private String unit;

    @Column(name = "status", length = 20, nullable = false)
    @NotBlank
    @Pattern(regexp = "ON_TRACK|RISK|OFF_TRACK")
    private String status;

    @Column(name = "use_yn", length = 1, nullable = false)
    @Pattern(regexp = "Y|N")
    private String useYn;

    @Column(name = "remark", length = 500)
    private String remark;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    public void prePersist(){
//이건 JPA 엔티티가 저장/수정될 때 자동으로 처리해야 할 공통 규칙을 한 곳에 모아두기 위한 장치
// ✔ insert 되기 직전에 자동 실행
if(targetValue == null) targetValue = BigDecimal.ZERO;
if(actualValue == null) actualValue = BigDecimal.ZERO;
if(status == null || status.isBlank()) status = "ON_TRACK";
if(useYn == null || useYn.isBlank()) useYn = "Y";
/*
targetValue, actualValue 없으면 → 0으로
status 없으면 → "ON_TRACK"
useYn 없으면 → "Y"
updatedAt → 현재 시간 자동 세팅
왜 필요?
프론트 / 서비스에서 값 빼먹어도 에러 안 나게
DB에 null 값 들어가는 거 방지
기본값 로직을 엔티티 한 곳에서 통일 관리
👉 결과적으로
“등록 로직이 단순해지고, 데이터가 항상 안전해짐
* */

updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
/*
✔ update 되기 직전에 자동 실행
하는 일 요약
수정될 때마다 updatedAt 자동 갱신
*/



}
