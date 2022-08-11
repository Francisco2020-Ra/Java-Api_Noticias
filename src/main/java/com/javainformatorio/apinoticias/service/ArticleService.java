package com.javainformatorio.apinoticias.service;

import com.javainformatorio.apinoticias.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {

    ArticleDTO createArticle(ArticleDTO articleDTO);

    List<ArticleDTO> getArticle();
}
