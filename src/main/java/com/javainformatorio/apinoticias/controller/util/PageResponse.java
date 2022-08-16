package com.javainformatorio.apinoticias.controller.util;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageResponse<T> {

    private List<T> content;
    private int pageNumber;
    private Long totalElements;
    private int totalPage;
    private String nextPage;
    private String previousPage;

    public void setResponse(String path,
                            int pageNumber,
                            int totalPages,
                            Long totalElements,
                            boolean isFirst,
                            boolean isLast){
        if(pageNumber >= totalPages){
            throw new IllegalArgumentException("Incorrect index");
        }
        this.pageNumber = (pageNumber + 1);
        this.totalPage = totalPages;
        this.totalElements = totalElements;
        this.nextPage = isLast ? "" : path + "?page=" + (pageNumber + 1);
        this.previousPage = isFirst ? "" : path + "?page=" + (pageNumber -1);
    }

}
