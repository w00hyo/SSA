package com.samsung.mes.member.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItemRequest {

    @NotBlank
    private String itemCode;

    @NotBlank
    private String itemName, itemGroup, spec, warehouse, location;

    @Min(0)
    private Integer stockQty, safetyStock;

    @DecimalMin("0.0")
    private BigDecimal inPrice, outPrice;

    @Pattern(regexp ="Y|N", message ="useYn은 Y또는 N")
    private String useYn, remark;

}
