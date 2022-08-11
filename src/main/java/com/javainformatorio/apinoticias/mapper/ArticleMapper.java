package com.javainformatorio.apinoticias.mapper;

import com.javainformatorio.apinoticias.dto.ArticleDTO;
import com.javainformatorio.apinoticias.dto.AuthorDTO;
import com.javainformatorio.apinoticias.dto.SourceDTO;
import com.javainformatorio.apinoticias.entities.ArticleEntity;
import com.javainformatorio.apinoticias.entities.AuthorEntity;
import com.javainformatorio.apinoticias.entities.SourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ArticleMapper {

    private final SourceMapper sourceMapper;
    private final AuthorMapper authorMapper;
    @Autowired
    public ArticleMapper(SourceMapper sourceMapper, AuthorMapper authorMapper){
        this.sourceMapper = sourceMapper;
        this.authorMapper = authorMapper;
    }
    public ArticleEntity toEntity(ArticleDTO articleDTO){
        return ArticleEntity.builder()
                .title(articleDTO.getTitle())
                .description(articleDTO.getDescription())
                .url(articleDTO.getUrl())
                .urlToImage(articleDTO.getUrlToImage())
                .publishedAt(LocalDate.parse(articleDTO.getPublishedAt()))
                .content(articleDTO.getContent())
                .source(getSourceEntity(articleDTO))
                .author(getAuthorEntity(articleDTO))
                .build();
    }

    public SourceEntity getSourceEntity(ArticleDTO articleDTO){
        return sourceMapper.toEntity(articleDTO.getSource());
    }

    public AuthorEntity getAuthorEntity(ArticleDTO articleDTO){
        return authorMapper.toEntity(articleDTO.getAuthor());
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
                .source(getSourceDTO(articleEntity))
                .author(getAuthorDTO(articleEntity))
                .build();
    }

    public SourceDTO getSourceDTO(ArticleEntity articleEntity){
        return sourceMapper.toDTO(articleEntity.getSource());
    }

    public AuthorDTO getAuthorDTO(ArticleEntity articleEntity){
        return authorMapper.toDTO(articleEntity.getAuthor());
    }
}
