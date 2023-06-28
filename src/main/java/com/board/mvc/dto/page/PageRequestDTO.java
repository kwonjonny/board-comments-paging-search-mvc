package com.board.mvc.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;
    
    // 검색 조건 
    private String searchType;  // 검색 타입
    private String keyword;    // 검색 키워드

    // if page 요청이 0 보다 작으면 강제 1 page
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    // if size 요청이 0보다 작고 100 보다 크면 강제 10
    public void setSize(int size) {
        if(size <= 0 || size > 100) {
            this.size = 10;
        } else {
            this.size = size;
        }
    }

    public int getSkip() {
        return (this.page - 1) * this.size;
    }

    public int getEnd() {
        return this.page * this.size;
    }

    // 한번에 가져오는 list 101
    public int getCountEnd() {
        int temp = (int) (Math.ceil(this.page / 10.0) * (10 * size));
        return temp + 1;
    }
}
