package com.javainformatorio.apinoticias.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "url_to_image")
    private String urlToImage;

    @Column(name = "published_at")
    private LocalDate publishedAt;

    @Column(name = "content")
    private String content;

}
