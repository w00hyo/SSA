package com.samsung.mes.member.repository;

import com.samsung.mes.member.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long>, JpaSpecificationExecutor<InventoryItem>{
    Optional<InventoryItem> findByItemCode(String itemCode);
    boolean existsByItemCode(String itemCode);
}
