package com.samsung.mes.dto;

import jakarta.validation.constraints.*;
import lombok.*;


import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KpiRequest {

    @NotBlank(message = "kpiName은 필수입니다")
    private String kpiName;
    private String kpiGroup;
    private String owner;

    @NotBlank(message = "periodType는 필수입니다")
    private String periodType;

    @NotBlank(message = "periodValue는 필수입니다")
    private String periodValue;

    @DecimalMin(value="0.0", inclusive = true, message = "targetValue는 0 이상")
    private BigDecimal targetValue;

    @DecimalMin(value="0.0", inclusive = true, message = "actualValue는 0 이상")
    private BigDecimal actualValue;

    private String unit;
    private String status;

    @Pattern(regexp ="Y|N", message = "useYn은 Y또는 N")
    private String useYn;
    private String remark;



}
