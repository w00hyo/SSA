package com.samsung.mes.member.repository;

import com.samsung.mes.member.entity.SystemInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SystemInfoRepository extends JpaRepository<SystemInfo, Long>, JpaSpecificationExecutor<SystemInfo> {

    Optional<SystemInfo> findBySystemCode(String systemCode);
    boolean existsBySystemCode(String systemCode);

    Page<SystemInfo> findByUseYn(String useYn, Pageable pageable);

    // 검색 (인덱스에 맞춰 name/group/owner/status)
    Page<SystemInfo> findByUseYnAndSystemNameContainingIgnoreCase(String useYn, String keyword, Pageable pageable);
    Page<SystemInfo> findByUseYnAndSystemGroupContainingIgnoreCase(String useYn, String keyword, Pageable pageable);
    Page<SystemInfo> findByUseYnAndOwnerContainingIgnoreCase(String useYn, String keyword, Pageable pageable);
    Page<SystemInfo> findByUseYnAndStatus(String useYn, String status, Pageable pageable);

    // status + keyword(이름) 같이 쓰고 싶을 때(선택)
    Page<SystemInfo> findByUseYnAndStatusAndSystemNameContainingIgnoreCase(String useYn, String status, String keyword, Pageable pageable);
}
