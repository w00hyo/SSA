package com.samsung.mes.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samsung.mes.dto.SalesOrderRequest;
import com.samsung.mes.dto.SalesOrderResponse;
import com.samsung.mes.service.SalesOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sales/orders")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class SalesOrderController {

    private final SalesOrderService service;

    // ✅ 수주 등록
    @PostMapping
    public SalesOrderResponse create(@RequestBody SalesOrderRequest req) {
        return service.create(req);
    }

    // ✅ 수주 목록 조회 (기간 + 페이징)
    // 예: /api/sales/orders?from=2026-01-01&to=2026-01-31&page=0&size=10
    @GetMapping
    public Page<SalesOrderResponse> list(
            @RequestParam(name = "from", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,

            @RequestParam(name = "to", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,

            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return service.list(from, to, page, size);
    }

    // ✅ 수주 상세
    @GetMapping("/{id}")
    public SalesOrderResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    // ✅ 수주 수정 (프론트 상세 모달 "수정 저장" 이거 필요)
    @PutMapping("/{id}")
    public SalesOrderResponse update(@PathVariable Long id, @RequestBody SalesOrderRequest req) {
        return service.update(id, req);
    }

    // ✅ 수주 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
