package com.samsung.mes.member.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.samsung.mes.member.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> , JpaSpecificationExecutor<SalesOrder> {

	//기간 조회
List<SalesOrder> findByOrderDateBetweenOrderByOrderDateDesc(LocalDate from, LocalDate to);
List<SalesOrder> findAllByOrderByOrderDateDesc();

//페이징
Page<SalesOrder> findByOrderDateBetween(
LocalDate from, LocalDate to, Pageable pageable		
);

Page<SalesOrder> findAll(Pageable pageable);










}
