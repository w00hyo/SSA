package com.samsung.mes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samsung.mes.entity.PurchaseMaterial;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PurchaseMaterialRepository extends JpaRepository<PurchaseMaterial, Long>, JpaSpecificationExecutor<PurchaseMaterial> {
    boolean existsByPurchaseNo(String purchaseNo);
}
