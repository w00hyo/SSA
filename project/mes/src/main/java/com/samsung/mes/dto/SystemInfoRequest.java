package com.samsung.mes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SystemInfoRequest {

    @NotBlank(message = "systemCode는 필수입니다")
    private String systemCode;

    @NotBlank(message = "systemName은 필수입니다")
    private String systemName;

    @NotBlank(message = "systemGroup은 필수입니다")
    private String systemGroup;

    private String owner;
    private String version;

    @Pattern(regexp = "ACTIVE|INACTIVE|MAINTENANCE", message = "status는 ACTIVE/INACTIVE/MAINTENANCE")
    private String status; // null이면 엔티티 prePersist가 ACTIVE

    @Pattern(regexp = "Y|N", message = "useYn은 Y 또는 N")
    private String useYn;  // null이면 엔티티 prePersist가 Y

    private String remark;
}
