package com.samsung.mes.member.controller;

import com.samsung.mes.member.dto.StandardRequest;
import com.samsung.mes.member.dto.StandardResponse;
import com.samsung.mes.member.service.StandardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/standards")
public class StandardController {

    private final StandardService service;

    // ✅ 목록: /api/standards?useYn=Y&searchType=NAME&keyword=abc&page=0&size=10
    @GetMapping
    public Page<StandardResponse> list(
            @RequestParam(required = false) String useYn,
            @RequestParam(required = false, defaultValue = "NAME") String searchType,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return service.list(useYn, searchType, keyword, pageable);
    }

    // ✅ 단건
    @GetMapping("/{id}")
    public StandardResponse getOne(@PathVariable Long id) {
        return service.getOne(id);
    }

    // ✅ 등록
    @PostMapping
    public StandardResponse create(@Valid @RequestBody StandardRequest req) {
        return service.create(req);
    }

    // ✅ 수정
    @PutMapping("/{id}")
    public StandardResponse update(@PathVariable Long id, @Valid @RequestBody StandardRequest req) {
        return service.update(id, req);
    }

    // ✅ 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ✅ 사용중지(선택)
    @PatchMapping("/{id}/disable")
    public StandardResponse disable(@PathVariable Long id) {
        return service.disable(id);
    }
}
