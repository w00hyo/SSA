package com.samsung.mes.repository;

import com.samsung.mes.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long>, JpaSpecificationExecutor<InventoryItem>{
    Optional<InventoryItem> findByItemCode(String itemCode);
    boolean existsByItemCode(String itemCode);
}
