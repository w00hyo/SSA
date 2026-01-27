package com.samsung.mes.member.spec;

import com.samsung.mes.entity.SystemInfo;
import org.springframework.data.jpa.domain.Specification;

import static com.samsung.mes.member.spec.SpecUtil.lowerSafe;

public class SystemInfoSpec {

    public static Specification<SystemInfo> keywordLike(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) return cb.conjunction();

            String k = "%" + keyword.trim().toLowerCase() + "%";

            return cb.or(
                    cb.like(lowerSafe(cb, root.get("systemCode")), k),
                    cb.like(lowerSafe(cb, root.get("systemName")), k),
                    cb.like(lowerSafe(cb, root.get("systemGroup")), k),
                    cb.like(lowerSafe(cb, root.get("owner")), k),
                    cb.like(lowerSafe(cb, root.get("version")), k),
                    cb.like(lowerSafe(cb, root.get("status")), k),
                    cb.like(lowerSafe(cb, root.get("useYn")), k),
                    cb.like(lowerSafe(cb, root.get("remark")), k)
            );
        };
    }
}
