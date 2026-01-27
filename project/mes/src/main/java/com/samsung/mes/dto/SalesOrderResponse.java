package com.samsung.mes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.samsung.mes.entity.SalesOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrderResponse {

    private Long id;

    // 수주 정보
    private LocalDate orderDate;

    private String customerCode;
    private String customerName;   // ✅ 누락되면 안 됨

    private String itemCode;
    private String itemName;

    // 수량 / 금액
    private BigDecimal orderQty;
    private BigDecimal price;
    private BigDecimal amount;

    // ⭐ 실무에서 거의 필수
    private BigDecimal remainQty;      // 잔량
    private String deliveryStatus;     // 미납 / 부분납품 / 납품완료

    // 납품 / 비고
    private LocalDate deliveryDate;
    private String remark;

    // 관리용 (선택)
    private LocalDateTime updatedAt;

    /**
     * Entity → Response DTO 변환
     */
    public static SalesOrderResponse fromEntity(SalesOrder e) {

        BigDecimal qty = e.getOrderQty() == null
                ? BigDecimal.ZERO
                : e.getOrderQty();

        BigDecimal price = e.getPrice() == null
                ? BigDecimal.ZERO
                : e.getPrice();

        BigDecimal amount = e.getAmount() != null
                ? e.getAmount()
                : qty.multiply(price);

        // 납품 수량 (없으면 0)
        BigDecimal deliveredQty = e.getDeliveredQty() == null
                ? BigDecimal.ZERO
                : e.getDeliveredQty();

        BigDecimal remainQty = qty.subtract(deliveredQty);

        // 납품 상태 자동 계산
        String deliveryStatus;
        if (deliveredQty.compareTo(BigDecimal.ZERO) == 0) {
            deliveryStatus = "미납";
        } else if (remainQty.compareTo(BigDecimal.ZERO) > 0) {
            deliveryStatus = "부분납품";
        } else {
            deliveryStatus = "납품완료";
        }

        return SalesOrderResponse.builder()
                .id(e.getId())
                .orderDate(e.getOrderDate())

                .customerCode(e.getCustomerCode())
                .customerName(e.getCustomerName())   // ✅ 핵심

                .itemCode(e.getItemCode())
                .itemName(e.getItemName())

                .orderQty(qty)
                .price(price)
                .amount(amount)

                .remainQty(remainQty)
                .deliveryStatus(deliveryStatus)

                .deliveryDate(e.getDeliveryDate())
                .remark(e.getRemark())

                .updatedAt(e.getUpdatedAt())
                .build();
    }
}
