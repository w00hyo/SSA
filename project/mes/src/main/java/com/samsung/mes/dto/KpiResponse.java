package com.samsung.mes.member.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KpiResponse {

    private Long id;
    private String kpiName;
    private String kpiGroup;
    private String owner;

    private String periodType;
    private String periodValue;

    private BigDecimal targetValue;
    private BigDecimal actualValue;
    private String unit;

    private String status;
    private String useYn;
    private String remark;

private LocalDateTime updatedAt;

}
