package com.samsung.mes.member.controller;

import com.samsung.mes.member.dto.InventoryItemRequest;
import com.samsung.mes.member.dto.InventoryItemResponse;
import com.samsung.mes.member.service.InventoryItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory/items")
@CrossOrigin(origins = "http://localhost:5173") // 필요하면 수정
@RequiredArgsConstructor
public class InventoryItemController {

    private final InventoryItemService service;

    // 목록 (페이징)
    @GetMapping
    public Page<InventoryItemResponse> list(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return service.list(pageable);
    }

    // 상세
    @GetMapping("/{id}")
    public InventoryItemResponse getOne(@PathVariable Long id) {
        return service.getOne(id);
    }

    // 등록
    @PostMapping
    public InventoryItemResponse create(@Valid @RequestBody InventoryItemRequest req) {
        return service.create(req);
    }

    // 수정
    @PutMapping("/{id}")
    public InventoryItemResponse update(@PathVariable Long id,
                                        @Valid @RequestBody InventoryItemRequest req) {
        return service.update(id, req);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
