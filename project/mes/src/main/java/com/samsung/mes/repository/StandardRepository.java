package com.samsung.mes.repository;

import com.samsung.mes.entity.Standard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface StandardRepository extends JpaRepository<Standard, Long> , JpaSpecificationExecutor<Standard> {

    Optional<Standard> findByStdCode(String stdCode);
    boolean existsByStdCode(String stdCode);

    // 검색(이름/그룹 like) + useYn
    Page<Standard> findByUseYnAndStdNameContainingIgnoreCase(String useYn, String stdName, Pageable pageable);
    Page<Standard> findByUseYnAndStdGroupContainingIgnoreCase(String useYn, String stdGroup, Pageable pageable);
    Page<Standard> findByUseYn(String useYn, Pageable pageable);
}
