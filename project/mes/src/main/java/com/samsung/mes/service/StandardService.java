package com.samsung.mes.member.service;

import com.samsung.mes.member.dto.StandardRequest;
import com.samsung.mes.member.dto.StandardResponse;
import com.samsung.mes.member.entity.Standard;
import com.samsung.mes.member.repository.StandardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StandardService {

    private final StandardRepository repo;

    // ✅ 목록(페이징 + 검색)
    public Page<StandardResponse> list(String useYn, String searchType, String keyword, Pageable pageable) {
        String u = (useYn == null || useYn.isBlank()) ? "Y" : useYn;
        String k = (keyword == null) ? "" : keyword.trim();

        Page<Standard> page;
        if (k.isBlank()) {
            page = repo.findByUseYn(u, pageable);
        } else if ("GROUP".equalsIgnoreCase(searchType)) {
            page = repo.findByUseYnAndStdGroupContainingIgnoreCase(u, k, pageable);
        } else { // 기본 NAME
            page = repo.findByUseYnAndStdNameContainingIgnoreCase(u, k, pageable);
        }

        return page.map(this::toResponse);
    }

    // ✅ 단건
    public StandardResponse getOne(Long id) {
        Standard e = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Standard가 없습니다. id=" + id));
        return toResponse(e);
    }

    // ✅ 등록
    @Transactional
    public StandardResponse create(StandardRequest req) {
        String code = req.getStdCode().trim();
        if (repo.existsByStdCode(code)) {
            throw new IllegalArgumentException("이미 존재하는 stdCode 입니다: " + code);
        }

        Standard e = Standard.builder()
                .stdCode(code)
                .stdName(req.getStdName().trim())
                .stdGroup(req.getStdGroup().trim())
                .unit(req.getUnit())
                .useYn(req.getUseYn())   // null이면 prePersist가 Y로
                .remark(req.getRemark())
                .build();

        return toResponse(repo.save(e));
    }

    // ✅ 수정
    @Transactional
    public StandardResponse update(Long id, StandardRequest req) {
        Standard e = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Standard가 없습니다. id=" + id));

        // 코드 변경 허용 시: 중복 체크 필요
        String newCode = req.getStdCode().trim();
        if (!e.getStdCode().equals(newCode) && repo.existsByStdCode(newCode)) {
            throw new IllegalArgumentException("이미 존재하는 stdCode 입니다: " + newCode);
        }

        e.setStdCode(newCode);
        e.setStdName(req.getStdName().trim());
        e.setStdGroup(req.getStdGroup().trim());
        e.setUnit(req.getUnit());
        if (req.getUseYn() != null && !req.getUseYn().isBlank()) e.setUseYn(req.getUseYn());
        e.setRemark(req.getRemark());

        return toResponse(e); // JPA 변경감지로 저장됨
    }

    // ✅ 삭제(물리삭제)
    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException("Standard가 없습니다. id=" + id);
        repo.deleteById(id);
    }

    // (권장) ✅ 사용중지(소프트 처리 느낌)
    @Transactional
    public StandardResponse disable(Long id) {
        Standard e = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Standard가 없습니다. id=" + id));
        e.setUseYn("N");
        return toResponse(e);
    }

    private StandardResponse toResponse(Standard e) {
        return StandardResponse.builder()
                .id(e.getId())
                .stdCode(e.getStdCode())
                .stdName(e.getStdName())
                .stdGroup(e.getStdGroup())
                .unit(e.getUnit())
                .useYn(e.getUseYn())
                .remark(e.getRemark())
                .updatedAt(e.getUpdatedAt())
                .build();
    }
}
