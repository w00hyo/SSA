package com.samsung.mes.member.spec;

import com.samsung.mes.entity.Member;
import org.springframework.data.jpa.domain.Specification;

import static com.samsung.mes.member.spec.SpecUtil.lowerSafe;

public class MemberSpec {
    public static Specification<Member> keywordLike(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) return cb.conjunction();

            String k = "%" + keyword.trim().toLowerCase() + "%";

            return cb.or(
                    cb.like(lowerSafe(cb, root.get("firstName")), k),
                    cb.like(lowerSafe(cb, root.get("lastName")), k),
                    cb.like(lowerSafe(cb, root.get("email")), k),
                    cb.like(lowerSafe(cb, root.get("tel")), k),
                    cb.like(lowerSafe(cb, root.get("companyName")), k),
                    cb.like(lowerSafe(cb, root.get("position")), k),
                    cb.like(lowerSafe(cb, root.get("address")), k),
                    cb.like(lowerSafe(cb, root.get("detailAddress")), k),
                    cb.like(lowerSafe(cb, root.get("provider")), k)
            );
        };
    }
}
