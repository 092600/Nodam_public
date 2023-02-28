package com.nodam.nodam_public.domain.post.search;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SearchPostDto {
    private int page = 0;
    private int recordSize = 10;
    private int pageSize = 10;
    private String title;     // 검색 키워드
    private String writer;    // 검색 유형


    public SearchPostDto(int page, String title, String writer) {
        this.page = page;
        this.title = title;
        this.writer = writer;
    }

    public void defaultSetUp() {
        this.page = 0;
        this.recordSize = 10;
        this.pageSize = 10;
    }
}
