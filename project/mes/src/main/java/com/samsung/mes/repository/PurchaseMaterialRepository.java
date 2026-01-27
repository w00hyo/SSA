package com.samsung.mes.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samsung.mes.member.entity.PurchaseMaterial;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PurchaseMaterialRepository extends JpaRepository<PurchaseMaterial, Long>, JpaSpecificationExecutor<PurchaseMaterial> {
    boolean existsByPurchaseNo(String purchaseNo);
}
