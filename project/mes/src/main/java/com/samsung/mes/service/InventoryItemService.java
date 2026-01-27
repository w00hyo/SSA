package com.samsung.mes.service;

import com.samsung.mes.dto.InventoryItemRequest;
import com.samsung.mes.dto.InventoryItemResponse;
import com.samsung.mes.entity.InventoryItem;
import com.samsung.mes.repository.InventoryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryItemService {

    private final InventoryItemRepository repo;

    public Page<InventoryItemResponse> list(Pageable pageable) {
        return repo.findAll(pageable).map(this::toRes);
    }

    public InventoryItemResponse getOne(Long id) {
        InventoryItem e = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("재고 품목이 없습니다. id=" + id));
        return toRes(e);
    }

    @Transactional
    public InventoryItemResponse create(InventoryItemRequest req) {
        // itemCode 중복 체크
        String code = req.getItemCode().trim();
        if (repo.existsByItemCode(code)) {
            throw new IllegalArgumentException("이미 존재하는 품목코드입니다. itemCode=" + code);
        }

        InventoryItem e = InventoryItem.builder()
                .itemCode(code)
                .itemName(req.getItemName().trim())
                .itemGroup(nullIfBlank(req.getItemGroup()))
                .spec(nullIfBlank(req.getSpec()))
                .warehouse(nullIfBlank(req.getWarehouse()))
                .location(nullIfBlank(req.getLocation()))
                .stockQty(req.getStockQty() == null ? 0 : req.getStockQty())
                .safetyStock(req.getSafetyStock())
                .inPrice(req.getInPrice())
                .outPrice(req.getOutPrice())
                .useYn(req.getUseYn() == null || req.getUseYn().isBlank() ? "Y" : req.getUseYn())
                .remark(req.getRemark() == null ? "" : req.getRemark())
                .build();

        return toRes(repo.save(e));
    }

    @Transactional
    public InventoryItemResponse update(Long id, InventoryItemRequest req) {
        InventoryItem e = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("재고 품목이 없습니다. id=" + id));

        // itemCode 변경 허용 시: 중복 체크 필요
        String newCode = req.getItemCode().trim();
        if (!e.getItemCode().equals(newCode) && repo.existsByItemCode(newCode)) {
            throw new IllegalArgumentException("이미 존재하는 품목코드입니다. itemCode=" + newCode);
        }

        e.setItemCode(newCode);
        e.setItemName(req.getItemName().trim());
        e.setItemGroup(nullIfBlank(req.getItemGroup()));
        e.setSpec(nullIfBlank(req.getSpec()));
        e.setWarehouse(nullIfBlank(req.getWarehouse()));
        e.setLocation(nullIfBlank(req.getLocation()));
        e.setStockQty(req.getStockQty() == null ? 0 : req.getStockQty());
        e.setSafetyStock(req.getSafetyStock());
        e.setInPrice(req.getInPrice());
        e.setOutPrice(req.getOutPrice());
        e.setUseYn(req.getUseYn() == null || req.getUseYn().isBlank() ? "Y" : req.getUseYn());
        e.setRemark(req.getRemark() == null ? "" : req.getRemark());

        return toRes(e);
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("재고 품목이 없습니다. id=" + id);
        }
        repo.deleteById(id);
    }

    private InventoryItemResponse toRes(InventoryItem e) {
        return InventoryItemResponse.builder()
                .id(e.getId())
                .itemCode(e.getItemCode())
                .itemName(e.getItemName())
                .itemGroup(e.getItemGroup())
                .spec(e.getSpec())
                .warehouse(e.getWarehouse())
                .location(e.getLocation())
                .stockQty(e.getStockQty())
                .safetyStock(e.getSafetyStock())
                .inPrice(e.getInPrice())
                .outPrice(e.getOutPrice())
                .useYn(e.getUseYn())
                .remark(e.getRemark())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

    private String nullIfBlank(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }
}
