package com.samsung.mes.member.spec;

import com.samsung.mes.entity.Kpi;
import org.springframework.data.jpa.domain.Specification;

public class KpiSpec {
    public static Specification<Kpi> keywordLike(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) {
                return cb.conjunction();
            }
            String k = "%" + keyword.trim() + "%";

            return cb.or(
                    cb.like(root.get("kpiName"), k),
                    cb.like(root.get("kpiGroup"), k),
                    cb.like(root.get("owner"), k),
                    cb.like(root.get("periodType"), k),
                    cb.like(root.get("periodValue"), k),
                    cb.like(root.get("status"), k),
                    cb.like(root.get("remark"), k)
            );
        };
    }
}
