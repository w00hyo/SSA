package com.samsung.mes.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.samsung.mes.entity.SalesOrder;

public interface SalesOrderRepository
        extends JpaRepository<SalesOrder, Long>, JpaSpecificationExecutor<SalesOrder> {

    Page<SalesOrder> findByOrderDateBetween(LocalDate from, LocalDate to, Pageable pageable);

    Page<SalesOrder> findByOrderDateGreaterThanEqual(LocalDate from, Pageable pageable);

    Page<SalesOrder> findByOrderDateLessThanEqual(LocalDate to, Pageable pageable);
}
