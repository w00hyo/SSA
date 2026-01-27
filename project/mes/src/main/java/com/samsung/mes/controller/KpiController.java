package com.samsung.mes.controller;

import com.samsung.mes.dto.KpiRequest;
import com.samsung.mes.dto.KpiResponse;
import com.samsung.mes.service.KpiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kpis")
public class KpiController {

    private final KpiService service;

    @GetMapping
    public Page<KpiResponse> list(@PageableDefault(size=10) Pageable pageable){
        return service.list(pageable);
    }

    @GetMapping("/{id}")
    public KpiResponse getOne(@PathVariable Long id){
        return service.getOne(id);
    }

    @PostMapping
    public KpiResponse create(@Valid @RequestBody KpiRequest req){
        return service.create(req);
    }

    @PutMapping("/{id}")
    public KpiResponse update(@PathVariable Long id, @Valid @RequestBody KpiRequest req){
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }




}
