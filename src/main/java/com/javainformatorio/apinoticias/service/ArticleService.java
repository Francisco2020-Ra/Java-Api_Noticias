package com.javainformatorio.apinoticias.service;

import com.javainformatorio.apinoticias.controller.util.PageResponse;
import com.javainformatorio.apinoticias.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {

    ArticleDTO createArticle(ArticleDTO articleDTO);

    List<ArticleDTO> getArticle();

    ArticleDTO updateArticle(Long id, ArticleDTO articleDTO);

    void deleteArticle(Long id);

    PageResponse<ArticleDTO> findByPage(int page);

    PageResponse<ArticleDTO> findByTitleContainingAndDescriptionContainingAndAuthorByContentContainingAndFullnameContaining(String word, int page);

}
