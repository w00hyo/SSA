package com.samsung.mes.member.controller;

import com.samsung.mes.member.dto.SystemInfoRequest;
import com.samsung.mes.member.dto.SystemInfoResponse;
import com.samsung.mes.member.service.SystemInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/systems")
public class SystemInfoController {
    private final SystemInfoService service;

    // ✅ 목록
    @GetMapping
    public Page<SystemInfoResponse> list(
            @RequestParam(required = false) String useYn,
            @RequestParam(required = false, defaultValue = "NAME") String searchType,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return service.list(useYn, searchType, keyword, status, pageable);
    }

    // ✅ 단건
    @GetMapping("/{id}")
    public SystemInfoResponse getOne(@PathVariable Long id) {
        return service.getOne(id);
    }

    // ✅ 등록
    @PostMapping
    public SystemInfoResponse create(@Valid @RequestBody SystemInfoRequest req) {
        return service.create(req);
    }

    // ✅ 수정
    @PutMapping("/{id}")
    public SystemInfoResponse update(@PathVariable Long id, @Valid @RequestBody SystemInfoRequest req) {
        return service.update(id, req);
    }

    // ✅ 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ✅ 비활성
    @PatchMapping("/{id}/disable")
    public SystemInfoResponse disable(@PathVariable Long id) {
        return service.disable(id);
    }
}
