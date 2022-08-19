package com.javainformatorio.apinoticias.service;

import com.javainformatorio.apinoticias.controller.util.PageResponse;
import com.javainformatorio.apinoticias.dto.ArticleDTO;
import com.javainformatorio.apinoticias.exception.ResourceNotFoundException;

public interface ArticleService {

    ArticleDTO createArticle(ArticleDTO articleDTO) throws ResourceNotFoundException;

    ArticleDTO updateArticle(Long id, ArticleDTO articleDTO) throws ResourceNotFoundException;

    void deleteArticle(Long id) throws ResourceNotFoundException;

    PageResponse<ArticleDTO> findByPage(int page);

    PageResponse<ArticleDTO> findByTitleContainingAndDescriptionContainingAndAuthorByContentContainingAndFullnameContaining(String word, int page);

}
