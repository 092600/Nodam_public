package com.nodam.nodam_public.domain.clinic.search;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class SearchClinicDto {
    private int recordSize;       // 페이지당 출력할 데이터 개수
    private int page;             // 현재 페이지 번호
    private int pageSize;         // 화면 하단에 출력할 페이지 사이즈
    private String region;       // 검색 키워드

    public SearchClinicDto() {
        this.page = 1;
        this.recordSize = 15;
        this.pageSize = 10;
    }

    public void setPage(int page) {
        this.page = page -1 <= 0 ? 0 : this.page;
    }
}

