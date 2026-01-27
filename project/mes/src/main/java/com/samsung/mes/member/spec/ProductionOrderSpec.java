package com.samsung.mes.member.spec;

import com.samsung.mes.entity.ProductionOrder;
import org.springframework.data.jpa.domain.Specification;

import static com.samsung.mes.member.spec.SpecUtil.lowerSafe;

public class ProductionOrderSpec {
    public static Specification<ProductionOrder> keywordLike(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) return cb.conjunction();

            String k = "%" + keyword.trim().toLowerCase() + "%";

            return cb.or(
                    cb.like(lowerSafe(cb, root.get("workOrderNo")), k),
                    cb.like(lowerSafe(cb, root.get("itemCode")), k),
                    cb.like(lowerSafe(cb, root.get("itemName")), k),
                    cb.like(lowerSafe(cb, root.get("status")), k),
                    cb.like(lowerSafe(cb, root.get("remark")), k)
            );
        };
    }
}
