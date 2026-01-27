package com.samsung.mes.search;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchHit {
    private String type;   // inventory/kpi/...
    private Long id;       // PK
    private String title;  // 화면에 보일 대표 텍스트(예: 품목명, KPI명 등)
    private String sub;    // 보조 텍스트(예: 코드/담당자 등)
    private String extra;  // 추가 텍스트(예: 상태/그룹 등)
}
