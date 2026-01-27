package com.samsung.mes.member.spec;

import com.samsung.mes.entity.InventoryItem;
import org.springframework.data.jpa.domain.Specification;

public class InventoryItemSpec {
    public static Specification<InventoryItem> keywordLike(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) {
                return cb.conjunction();
            }
            String k = "%" + keyword.trim() + "%";

            return cb.or(
                    cb.like(root.get("itemCode"), k),
                    cb.like(root.get("itemName"), k),
                    cb.like(root.get("itemGroup"), k),
                    cb.like(root.get("spec"), k),
                    cb.like(root.get("warehouse"), k),
                    cb.like(root.get("location"), k),
                    cb.like(root.get("remark"), k)
            );
        };
    }
}
