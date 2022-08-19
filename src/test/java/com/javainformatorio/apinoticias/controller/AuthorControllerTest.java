package com.javainformatorio.apinoticias.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainformatorio.apinoticias.controller.util.PageResponse;
import com.javainformatorio.apinoticias.entities.AuthorEntity;
import com.javainformatorio.apinoticias.repository.AuthorRepository;
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


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthorRepository authorRepository;


    //------------------------------------Create Test Author -------------------------
    @Test
    void when_callMethodAddAuthorAndNonExistent_then_returnBadRequest() throws Exception {
        mockMvc.perform(post("/author").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void when_callMethodAddAuthorAndMissingRequiredAttributes_then_returnBadRequest() throws Exception {
        mockMvc.perform(post("/author").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author( null, null))))
                .andExpect(jsonPath("$.listMessage.[0]",is("firstName must not be blank")))
                .andExpect(status().isBadRequest());
    }

    //------------------------------------Update Test Author -------------------------
    @Test
    void when_callMethodUpdateAuthorAndNotExistId_then_returnNotFond() throws Exception {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/author/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author(1L, "Francisco"))))
                .andExpect(jsonPath("$.message", is("Not found id: 1")));

    }

    @Test
    void when_callMethodUpdateAuthor_then_returnOk() throws Exception {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author(1L, "Francisco")));
        when(authorRepository.save(author(1L, "Ariel"))).thenReturn(author(1L, "Ariel"));

        mockMvc.perform(put("/author/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author(1L, "Ariel"))))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Ariel")));
    }

    @Test
    void when_callMethodUpdateAuthorAndMissingRequiredAttributes_then_returnBadRequest() throws Exception {
        mockMvc.perform(put("/author/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author(1L, null))))
                .andExpect(jsonPath("$.listMessage.[0]",is("firstName must not be blank")))
                .andExpect(status().isBadRequest());
    }

    //------------------------------------Delete Test Author -------------------------

    @Test
    void when_callMethodDeleteAndIdNotExist_then_returnNotFound() throws Exception {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/author/1"))
                .andExpect(jsonPath("$.message", is("Not found id: 1")));
    }

    @Test
    void when_callMethodDelete_then_returnNotContent() throws Exception {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author(1L, "Francisco")));
        mockMvc.perform(delete("/author/1"))
                .andExpect(status().isNoContent());
    }

    //------------------------------------Read Test Pagination Author -------------------------

    @Test
    void findByPage() throws Exception {
        List<AuthorEntity> authorEntities = Arrays.asList(
                author(1L, "Ariel"),
                author(1L, "Ariel"),
                author(1L, "Ariel"));

        PageResponse<AuthorEntity> pageModel = PageResponse.<AuthorEntity>builder()
                .content(authorEntities)
                .build();

        when(authorRepository.findAll(PageRequest.of(0, 5))).thenReturn(new PageImpl<>(pageModel.getContent()));

        mockMvc.perform(get("/author")
                        .param("page", "0")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.content.[0].id", is(1)))
                .andExpect(jsonPath("$.content.[1].id", is(1)))
                .andExpect(jsonPath("$.content.[2].id", is(1)))
                .andExpect(jsonPath("$.content.[0].firstName", is("Ariel")))
                .andExpect(jsonPath("$.content.[1].firstName", is("Ariel")))
                .andExpect(jsonPath("$.content.[2].firstName", is("Ariel")))
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(status().isOk());
    }




    public AuthorEntity author(Long id, String firstName){
        return AuthorEntity.builder()
                .id(id).firstName(firstName).lastName("Gonzalez")
                .fullname("Lucas Moreira").createdAt(LocalDate.of(1991, 02, 13))
                .build();
    }

}