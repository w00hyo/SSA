package com.samsung.mes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class StandardRequest {

    @NotBlank(message = "stdCode는 필수입니다")
    private String stdCode;

    @NotBlank(message = "stdName은 필수입니다")
    private String stdName;

    @NotBlank(message = "stdGroup은 필수입니다")
    private String stdGroup;

    private String unit;

    @Pattern(regexp = "Y|N", message = "useYn은 Y 또는 N")
    private String useYn;   // null이면 엔티티 prePersist에서 Y로 들어감

    private String remark;
}

