package com.samsung.mes.member.service;

import com.samsung.mes.member.entity.*;
import com.samsung.mes.member.repository.*;
import com.samsung.mes.member.search.*;
import com.samsung.mes.member.spec.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GlobalSearchService {

    private final InventoryItemRepository inventoryRepo;
    private final KpiRepository kpiRepo;
    private final MemberRepository memberRepo;
    private final ProductionOrderRepository productionRepo;
    private final PurchaseMaterialRepository purchaseRepo;
    private final SalesOrderRepository salesRepo;
    private final StandardRepository standardRepo;
    private final SystemInfoRepository systemRepo;

    public GlobalSearchResponse search(String keyword, SearchType type, Pageable pageable) {
        GlobalSearchResponse res = GlobalSearchResponse.builder().build();

        if (keyword == null || keyword.trim().isEmpty()) return res;

        switch (type) {
            case inventory -> res.getResults().put("inventory",
                    inventoryRepo.findAll(InventoryItemSpec.keywordLike(keyword), pageable)
                            .map(toInventoryHit()));

            case kpi -> res.getResults().put("kpi",
                    kpiRepo.findAll(KpiSpec.keywordLike(keyword), pageable)
                            .map(toKpiHit()));

            case member -> res.getResults().put("member",
                    memberRepo.findAll(MemberSpec.keywordLike(keyword), pageable)
                            .map(toMemberHit()));

            case production -> res.getResults().put("production",
                    productionRepo.findAll(ProductionOrderSpec.keywordLike(keyword), pageable)
                            .map(toProductionHit()));

            case purchase -> res.getResults().put("purchase",
                    purchaseRepo.findAll(PurchaseMaterialSpec.keywordLike(keyword), pageable)
                            .map(toPurchaseHit()));

            case sales -> res.getResults().put("sales",
                    salesRepo.findAll(SalesOrderSpec.keywordLike(keyword), pageable)
                            .map(toSalesHit()));

            case standard -> res.getResults().put("standard",
                    standardRepo.findAll(StandardSpec.keywordLike(keyword), pageable)
                            .map(toStandardHit()));

            case system -> res.getResults().put("system",
                    systemRepo.findAll(SystemInfoSpec.keywordLike(keyword), pageable)
                            .map(toSystemHit()));

            case all -> {
                res.getResults().put("inventory",
                        inventoryRepo.findAll(InventoryItemSpec.keywordLike(keyword), pageable).map(toInventoryHit()));
                res.getResults().put("kpi",
                        kpiRepo.findAll(KpiSpec.keywordLike(keyword), pageable).map(toKpiHit()));
                res.getResults().put("member",
                        memberRepo.findAll(MemberSpec.keywordLike(keyword), pageable).map(toMemberHit()));
                res.getResults().put("production",
                        productionRepo.findAll(ProductionOrderSpec.keywordLike(keyword), pageable).map(toProductionHit()));
                res.getResults().put("purchase",
                        purchaseRepo.findAll(PurchaseMaterialSpec.keywordLike(keyword), pageable).map(toPurchaseHit()));
                res.getResults().put("sales",
                        salesRepo.findAll(SalesOrderSpec.keywordLike(keyword), pageable).map(toSalesHit()));
                res.getResults().put("standard",
                        standardRepo.findAll(StandardSpec.keywordLike(keyword), pageable).map(toStandardHit()));
                res.getResults().put("system",
                        systemRepo.findAll(SystemInfoSpec.keywordLike(keyword), pageable).map(toSystemHit()));
            }
        }

        return res;
    }

    // ---------- Entity -> SearchHit 매핑 ----------

    private Function<InventoryItem, SearchHit> toInventoryHit() {
        return e -> SearchHit.builder()
                .type("inventory")
                .id(e.getId())
                .title(nvl(e.getItemName()))
                .sub("코드: " + nvl(e.getItemCode()))
                .extra("창고: " + nvl(e.getWarehouse()))
                .build();
    }

    private Function<Kpi, SearchHit> toKpiHit() {
        return e -> SearchHit.builder()
                .type("kpi")
                .id(e.getId())
                .title(nvl(e.getKpiName()))
                .sub("담당: " + nvl(e.getOwner()))
                .extra("상태: " + nvl(e.getStatus()))
                .build();
    }

    private Function<Member, SearchHit> toMemberHit() {
        return e -> SearchHit.builder()
                .type("member")
                .id(e.getId())
                .title((nvl(e.getFirstName()) + " " + nvl(e.getLastName())).trim())
                .sub("이메일: " + nvl(e.getEmail()))
                .extra("회사: " + nvl(e.getCompanyName()))
                .build();
    }

    private Function<ProductionOrder, SearchHit> toProductionHit() {
        return e -> SearchHit.builder()
                .type("production")
                .id(e.getId())
                .title(nvl(e.getWorkOrderNo()))
                .sub("품목: " + nvl(e.getItemName()))
                .extra("상태: " + nvl(e.getStatus()))
                .build();
    }

    private Function<PurchaseMaterial, SearchHit> toPurchaseHit() {
        return e -> SearchHit.builder()
                .type("purchase")
                .id(e.getId())
                .title(nvl(e.getPurchaseNo()))
                .sub("업체: " + nvl(e.getSupplierName()))
                .extra("상태: " + nvl(e.getStatus()))
                .build();
    }

    private Function<SalesOrder, SearchHit> toSalesHit() {
        return e -> SearchHit.builder()
                .type("sales")
                .id(e.getId())
                .title(nvl(e.getCustomerName()))
                .sub("품목: " + nvl(e.getItemName()))
                .extra("거래처코드: " + nvl(e.getCustomerCode()))
                .build();
    }

    private Function<Standard, SearchHit> toStandardHit() {
        return e -> SearchHit.builder()
                .type("standard")
                .id(e.getId())
                .title(nvl(e.getStdName()))
                .sub("코드: " + nvl(e.getStdCode()))
                .extra("그룹: " + nvl(e.getStdGroup()))
                .build();
    }

    private Function<SystemInfo, SearchHit> toSystemHit() {
        return e -> SearchHit.builder()
                .type("system")
                .id(e.getId())
                .title(nvl(e.getSystemName()))
                .sub("코드: " + nvl(e.getSystemCode()))
                .extra("상태: " + nvl(e.getStatus()))
                .build();
    }

    private String nvl(String s) { return s == null ? "" : s; }
}
