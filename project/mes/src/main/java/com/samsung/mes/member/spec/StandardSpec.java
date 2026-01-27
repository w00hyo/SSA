package com.samsung.mes.member.spec;

import com.samsung.mes.entity.Standard;
import org.springframework.data.jpa.domain.Specification;

import static com.samsung.mes.member.spec.SpecUtil.lowerSafe;

public class StandardSpec {

    public static Specification<Standard> keywordLike(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) return cb.conjunction();

            String k = "%" + keyword.trim().toLowerCase() + "%";

            return cb.or(
                    cb.like(lowerSafe(cb, root.get("stdCode")), k),
                    cb.like(lowerSafe(cb, root.get("stdName")), k),
                    cb.like(lowerSafe(cb, root.get("stdGroup")), k),
                    cb.like(lowerSafe(cb, root.get("unit")), k),
                    cb.like(lowerSafe(cb, root.get("useYn")), k),
                    cb.like(lowerSafe(cb, root.get("remark")), k)
            );
        };
    }
}
