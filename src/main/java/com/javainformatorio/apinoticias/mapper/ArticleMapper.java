package com.javainformatorio.apinoticias.mapper;

import com.javainformatorio.apinoticias.dto.ArticleDTO;
import com.javainformatorio.apinoticias.entities.ArticleEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ArticleMapper {

    public ArticleEntity toEntity(ArticleDTO articleDTO){
        return ArticleEntity.builder()
                .title(articleDTO.getTitle())
                .description(articleDTO.getDescription())
                .url(articleDTO.getUrl())
                .urlToImage(articleDTO.getUrlToImage())
                .publishedAt(LocalDate.parse(articleDTO.getPublishedAt()))
                .content(articleDTO.getContent())
                .build();
    }

    public ArticleDTO toDTO(ArticleEntity articleEntity){
        return ArticleDTO.builder()
                .id(articleEntity.getId())
                .title(articleEntity.getTitle())
                .description(articleEntity.getDescription())
                .url(articleEntity.getUrl())
                .urlToImage(articleEntity.getUrlToImage())
                .publishedAt(articleEntity.getPublishedAt().toString())
                .content(articleEntity.getContent())
                .build();
    }
}
