package com.samsung.mes.member.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandardResponse {

    private Long id;
    private String stdCode;
    private String stdName;
    private String stdGroup;
    private String unit;
    private String useYn;
    private String remark;
    private LocalDateTime updatedAt;
}
