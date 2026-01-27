package com.samsung.mes.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItemResponse {
    private Long id;
private String itemCode, itemName, itemGroup, spec, warehouse, location, useYn, remark;
private Integer stockQty, safetyStock;
private BigDecimal inPrice, outPrice;
private LocalDateTime updatedAt;

}
