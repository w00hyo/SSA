package com.samsung.mes.member.repository;

import com.samsung.mes.member.entity.Kpi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KpiRepository extends JpaRepository<Kpi, Long>, JpaSpecificationExecutor<Kpi> {
}
