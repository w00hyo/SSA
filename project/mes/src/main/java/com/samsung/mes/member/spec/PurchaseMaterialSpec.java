package com.samsung.mes.member.spec;

import com.samsung.mes.entity.PurchaseMaterial;
import org.springframework.data.jpa.domain.Specification;

import static com.samsung.mes.member.spec.SpecUtil.lowerSafe;

public class PurchaseMaterialSpec {

    public static Specification<PurchaseMaterial> keywordLike(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) return cb.conjunction();

            String k = "%" + keyword.trim().toLowerCase() + "%";

            return cb.or(
                    cb.like(lowerSafe(cb, root.get("purchaseNo")), k),
                    cb.like(lowerSafe(cb, root.get("supplierCode")), k),
                    cb.like(lowerSafe(cb, root.get("supplierName")), k),
                    cb.like(lowerSafe(cb, root.get("itemCode")), k),
                    cb.like(lowerSafe(cb, root.get("itemName")), k),
                    cb.like(lowerSafe(cb, root.get("status")), k),
                    cb.like(lowerSafe(cb, root.get("remark")), k)
            );
        };
    }
}
