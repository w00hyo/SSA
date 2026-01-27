package com.samsung.mes.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.samsung.mes.dto.SalesOrderRequest;
import com.samsung.mes.dto.SalesOrderResponse;
import com.samsung.mes.entity.ProductionOrder;
import com.samsung.mes.entity.SalesOrder;
import com.samsung.mes.repository.ProductionOrderRepository;
import com.samsung.mes.repository.SalesOrderRepository;
import com.samsung.mes.security.RequestValidator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SalesOrderService {

    private final SalesOrderRepository repo;
    private final ProductionOrderRepository productionOrderRepository;

    // ✅ 등록
    public SalesOrderResponse create(SalesOrderRequest req) {
        RequestValidator.validate(req);

        BigDecimal qty = safe(req.getOrderQty());
        BigDecimal price = safe(req.getPrice());
        BigDecimal amount = qty.multiply(price);

        SalesOrder saved = repo.save(
                SalesOrder.builder()
                        .orderDate(req.getOrderDate())
                        .customerCode(req.getCustomerCode())
                        .customerName(req.getCustomerName())
                        .itemCode(req.getItemCode())
                        .itemName(req.getItemName())
                        .orderQty(qty)
                        .price(price)
                        .amount(amount)
                        .deliveryDate(req.getDeliveryDate())
                        .remark(req.getRemark())
                        // deliveredQty 엔티티에 있다면 기본값 0 세팅(안전)
                        .deliveredQty(BigDecimal.ZERO)
                        .build()
        );

        // ✅ 생산지시 생성(원하면 조건 걸 수 있음)
        // 예: 특정 품목만 생성, 또는 req에 createWorkOrderYn 같은 플래그로 제어
        String workOrderNo = "WO-" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
                + "-" + System.currentTimeMillis();

        ProductionOrder po = ProductionOrder.builder()
                .workOrderNo(workOrderNo)
                .orderDate(LocalDate.now())
                .itemCode(saved.getItemCode())
                .itemName(saved.getItemName())
                .planQty(saved.getOrderQty() != null ? saved.getOrderQty().intValue() : 0)
                .status("대기")
                .build();

        productionOrderRepository.save(po);

        return SalesOrderResponse.fromEntity(saved);
    }

    // ✅ 페이징 목록조회 (+ from/to 조건)
    @Transactional
    public Page<SalesOrderResponse> list(LocalDate from, LocalDate to, int page, int size) {

        int safePage = Math.max(0, page);
        int safeSize = Math.max(1, Math.min(size, 200)); // 너무 큰 size 제한

        Pageable pageable = PageRequest.of(
                safePage,
                safeSize,
                Sort.by(Sort.Direction.DESC, "orderDate").and(Sort.by(Sort.Direction.DESC, "id"))
        );

        Page<SalesOrder> result;

        // ✅ from/to 둘 다 들어오면 between
        if (from != null && to != null) {
            if (from.isAfter(to)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "from은 to보다 클 수 없습니다.");
            }
            result = repo.findByOrderDateBetween(from, to, pageable);
        }
        // ✅ 둘 중 하나만 들어오면 기본값 보정(실무형)
        else if (from != null) {
            result = repo.findByOrderDateGreaterThanEqual(from, pageable);
        } else if (to != null) {
            result = repo.findByOrderDateLessThanEqual(to, pageable);
        } else {
            result = repo.findAll(pageable);
        }

        return result.map(SalesOrderResponse::fromEntity);
    }

    // ✅ 단건조회
    @Transactional
    public SalesOrderResponse get(Long id) {
        SalesOrder so = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수주가 없습니다. id=" + id));
        return SalesOrderResponse.fromEntity(so);
    }

    // ✅ 수정 (프론트에서 PUT /api/sales/orders/{id} 하면 여기 필요)
    public SalesOrderResponse update(Long id, SalesOrderRequest req) {
        RequestValidator.validate(req);

        SalesOrder so = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수주가 없습니다. id=" + id));

        BigDecimal qty = safe(req.getOrderQty());
        BigDecimal price = safe(req.getPrice());
        BigDecimal amount = qty.multiply(price);

        so.setOrderDate(req.getOrderDate());
        so.setCustomerCode(req.getCustomerCode());
        so.setCustomerName(req.getCustomerName());
        so.setItemCode(req.getItemCode());
        so.setItemName(req.getItemName());
        so.setOrderQty(qty);
        so.setPrice(price);
        so.setAmount(amount);
        so.setDeliveryDate(req.getDeliveryDate());
        so.setRemark(req.getRemark());

        // JPA dirty checking으로 자동 업데이트
        return SalesOrderResponse.fromEntity(so);
    }

    // ✅ 삭제
    public void delete(Long id) {
        SalesOrder so = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수주가 없습니다. id=" + id));
        repo.delete(so);
    }

    // ✅ BigDecimal null 방어
    private BigDecimal safe(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }
}
