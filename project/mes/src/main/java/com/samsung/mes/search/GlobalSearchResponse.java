package com.samsung.mes.search;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalSearchResponse {

    @Builder.Default
    private Map<String, Page<?>> results = new LinkedHashMap<>();
}
