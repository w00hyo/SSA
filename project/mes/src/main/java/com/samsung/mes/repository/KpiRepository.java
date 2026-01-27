package com.samsung.mes.repository;

import com.samsung.mes.entity.Kpi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KpiRepository extends JpaRepository<Kpi, Long>, JpaSpecificationExecutor<Kpi> {
}
