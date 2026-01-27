package com.samsung.mes.member.spec;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;

public class SpecUtil {

    // 문자열 컬럼이 null이어도 like 비교 가능하게: lower(coalesce(col, '')) like %keyword%
    public static Expression<String> lowerSafe(CriteriaBuilder cb, Path<String> path) {
        return cb.lower(cb.coalesce(path, ""));
    }
}
