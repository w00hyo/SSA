package com.samsung.mes.member.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.samsung.mes.member.entity.SalesOrder;
import com.samsung.mes.member.entity.SalesOrder.SalesOrderBuilder;

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
	private LocalDate orderDate;
	
	private String customerCode;
	private String customerName;
	
	private String itemCode;
	private String itemName;
	
	private BigDecimal orderQty;
	private BigDecimal price;
	private BigDecimal amount;
	
	private LocalDate deliveryDate;
	private String remark;
	
	public static SalesOrderResponse fromEntity(SalesOrder e) {
		BigDecimal qty = e.getOrderQty() == null ? BigDecimal.ZERO : e.getOrderQty();
		BigDecimal price = e.getPrice() == null ? BigDecimal.ZERO : e.getPrice();
		BigDecimal amount = e.getAmount() != null ? e.getAmount(): qty.multiply(price);
		
		return SalesOrderResponse.builder()
				.id(e.getId())
				.orderDate(e.getOrderDate())
				.customerCode(e.getCustomerCode())
				.itemCode(e.getItemCode())
				.itemName(e.getItemName())
				.orderQty(qty)
				.price(price)
				.amount(amount)
				.deliveryDate(e.getDeliveryDate())
				.remark(e.getRemark())
				.build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
