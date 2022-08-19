package com.javainformatorio.apinoticias.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainformatorio.apinoticias.controller.util.PageResponse;
import com.javainformatorio.apinoticias.entities.ArticleEntity;
import com.javainformatorio.apinoticias.entities.AuthorEntity;
import com.javainformatorio.apinoticias.entities.SourceEntity;
import com.javainformatorio.apinoticias.repository.ArticleRepository;
import com.javainformatorio.apinoticias.repository.SourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ArticleRepository articleRepository;
    @MockBean
    private SourceRepository sourceRepository;

    /*---------------------------------Create Article Test ------------------------------*/
    @Test
    void when_receiveArticleDTOWhitSourceNonExistent_then_returnNotFound() throws Exception {
        when(sourceRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(post("/article").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleEntity(null, "El Pato", source(), author()))))
                .andExpect(jsonPath("$.message", is("Not found source id: 1")))
                .andExpect(status().isNotFound());
    }

    @Test
    void when_receiveAArticleDTO_then_saveArticleAndReturnWhithId() throws Exception {
        when(sourceRepository.findById(1L)).thenReturn(Optional.of(source()));
        when(articleRepository.save(articleEntity(null, "El Pato", source(), author()))).thenReturn(articleEntity(1L, "El Pato", source(), author()));

        mockMvc.perform(post("/article")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleEntity(null, "El Pato", source(), author()))))
                .andExpect(jsonPath("$.title", is("El Pato")));
    }

    /*---------------------------------Update Article Test ------------------------------*/
    @Test
    void when_receiveAIdArticleNonExistent_then_returnNotFound() throws Exception {
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/article/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleEntity(null, "El Pato", source(), author()))))
                .andExpect(jsonPath("$.message", is("Not found source id: 1")))
                .andExpect(status().isNotFound());
    }

    @Test
    void when_updateAArticle_then_returnOk() throws Exception {
        when(articleRepository.findById(1L))
                .thenReturn(Optional.of(articleEntity(1L, "El Pato", source(), author())));

        when(articleRepository.save(articleEntity(1L, "El Pato", source(), author())))
                .thenReturn(articleEntity(1L, "El Pato", source(), author()));

        mockMvc.perform(put("/article/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(articleEntity(1L, "El Pato", source(), author()))))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(status().isCreated());
    }

    /*---------------------------------Delete Article Test ------------------------------*/
    @Test
    void when_callDeleteMethodUnregisteredId_then_returnNotFound() throws Exception {
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/article/1"))
                .andExpect(jsonPath("$.message", is("Not found source id: 1")))
                .andExpect(status().isNotFound());
    }

    @Test
    void when_callDeleteMethod_then_returnIsNoContent() throws Exception {
        when(articleRepository.findById(1L)).thenReturn(Optional.of(articleEntity(1L, "El Pato", source(), author())));

        mockMvc.perform(delete("/article/1")).andExpect(status().isNoContent());
    }

    /*---------------------------------Read Pagination Article Test ------------------------------*/

    @Test
    void findByPage() throws Exception {
        List<ArticleEntity> articleEntities = Arrays.asList(
                articleEntity(1L, "El Pato", source(), author()),
                articleEntity(1L, "El Pato", source(), author()),
                articleEntity(1L, "El Pato", source(), author()));

        PageResponse<ArticleEntity> pageModel = PageResponse.<ArticleEntity>builder()
                        .content(articleEntities)
                        .build();

        when(articleRepository.findAll(PageRequest.of(0, 5))).thenReturn(new PageImpl<>(pageModel.getContent()));

        mockMvc.perform(get("/article")
                        .param("page", "0")
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andExpect(jsonPath("$.content.[0].id", is(1)))
                .andExpect(jsonPath("$.content.[1].id", is(1)))
                .andExpect(jsonPath("$.content.[2].id", is(1)))
                .andExpect(jsonPath("$.content.[0].title", is("El Pato")))
                .andExpect(jsonPath("$.content.[1].title", is("El Pato")))
                .andExpect(jsonPath("$.content.[2].title", is("El Pato")))
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(status().isOk());
    }



    public ArticleEntity articleEntity(Long id, String title, SourceEntity source, AuthorEntity author) {
        return ArticleEntity
                .builder()
                .id(id).title(title).description("El pato es")
                .url("www.google.com").urlToImage("www.google.com/imagen")
                .publishedAt(LocalDate.of(1991,02,13))
                .content("El Lucas")
                .source(source())
                .author(author())
                .build();
    }

    public SourceEntity source(){
        return SourceEntity.builder()
                .id(1L)
                .name("La Manaña")
                .code("la-mañana")
                .build();
    }

    public AuthorEntity author(){
        return AuthorEntity.builder()
                .id(1L).firstName("Lucas").lastName("Moreira")
                .fullname("Lucas Moreira").createdAt(LocalDate.of(1991, 02, 13)).build();
    }


}