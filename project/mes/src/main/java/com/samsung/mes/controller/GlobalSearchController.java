package com.samsung.mes.controller;

import com.samsung.mes.service.GlobalSearchService;
import com.samsung.mes.search.GlobalSearchResponse;
import com.samsung.mes.search.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class GlobalSearchController {

    private final GlobalSearchService service;

    // 예) /api/search?keyword=볼트&type=all&page=0&size=10&sort=id,desc
    @GetMapping
    public GlobalSearchResponse search(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "all") SearchType type,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return service.search(keyword, type, pageable);
    }
}
