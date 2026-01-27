package com.samsung.mes.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(
        name = "sales_order",
        indexes = {
                @Index(name = "idx_sales_order_order_date", columnList = "order_date"),
                @Index(name = "idx_sales_order_customer_code", columnList = "customer_code"),
                @Index(name = "idx_sales_order_item_code", columnList = "item_code")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 수주일자
    @Column(name = "order_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    // 거래처 코드/명
    @Column(name = "customer_code", nullable = false, length = 30)
    private String customerCode;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    // 품목 코드/명
    @Column(name = "item_code", nullable = false, length = 30)
    private String itemCode;

    @Column(name = "item_name", nullable = false, length = 200)
    private String itemName;

    // 수주수량
    @Column(name = "order_qty", nullable = false, precision = 18, scale = 3)
    private BigDecimal orderQty;

    // 납품수량(누적) - 잔량/납품상태 계산용
    @Column(name = "delivered_qty", nullable = false, precision = 18, scale = 3)
    @Builder.Default
    private BigDecimal deliveredQty = BigDecimal.ZERO;

    // 단가
    @Column(name = "price", nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

    // 금액(보통 qty*price)
    @Column(name = "amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    // 납품 예정일
    @Column(name = "delivery_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    // 비고
    @Column(name = "remark", length = 1000)
    private String remark;

    // 생성/수정 시간 (자동)
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ✅ 저장/수정 시 amount 자동 계산
    @PrePersist
    @PreUpdate
    private void calcAmount() {
        BigDecimal qty = (orderQty == null) ? BigDecimal.ZERO : orderQty;
        BigDecimal pr = (price == null) ? BigDecimal.ZERO : price;

        // amount가 null이거나, 정책상 항상 재계산하고 싶으면 무조건 재계산
        this.amount = qty.multiply(pr);

        // deliveredQty null 방어
        if (this.deliveredQty == null) this.deliveredQty = BigDecimal.ZERO;
    }
}
