package com.javainformatorio.apinoticias.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainformatorio.apinoticias.controller.util.PageResponse;
import com.javainformatorio.apinoticias.entities.AuthorEntity;
import com.javainformatorio.apinoticias.entities.SourceEntity;
import com.javainformatorio.apinoticias.repository.SourceRepository;
import org.junit.jupiter.api.Test;

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
import java.util.Collections;
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
class SourceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private SourceRepository sourceRepository;

    //------------------------------------Create Test Source -------------------------
    @Test
    void when_callMethodCreateSourceAndNonExistent_then_returnBadRequest() throws Exception {
        mockMvc.perform(post("/source").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void when_callMethodCreateSourceAndMissingRequiredAttributes_then_returnBadRequest() throws Exception {
        mockMvc.perform(post("/source").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(source(null, null))))
                .andExpect(jsonPath("$.listMessage.[0]",is("name must not be null")))
                .andExpect(status().isBadRequest());
    }

    //------------------------------------Update Test Source -------------------------
    @Test
    void when_callMethodUpdateSourceAndNotExistId_then_returnNotFond() throws Exception {
        when(sourceRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/source/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(source(1L, "Francisco"))))
                .andExpect(jsonPath("$.message", is("Not found id: 1")));

    }

    //------------------------------------Delete Test Source -------------------------

    @Test
    void when_callMethodDeleteAndIdNotExist_then_returnNotFound() throws Exception {
        when(sourceRepository.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/source/1"))
                .andExpect(jsonPath("$.message", is("Not found id: 1")));
    }

    @Test
    void when_callMethodDelete_then_returnNotContent() throws Exception {
        when(sourceRepository.findById(1L)).thenReturn(Optional.of(source(1L, "Francisco")));
        mockMvc.perform(delete("/source/1"))
                .andExpect(status().isNoContent());
    }

    //------------------------------------Read Test Pagination Source -------------------------


    @Test
    void findByPage() throws Exception {
        List<SourceEntity> sourceEntities = Arrays.asList(
                source(1L, "Ariel"),
                source(1L, "Ariel"),
                source(1L, "Ariel"));

        PageResponse<SourceEntity> pageModel = PageResponse.<SourceEntity>builder()
                .content(sourceEntities)
                .build();

        when(sourceRepository.findAll(PageRequest.of(0, 5))).thenReturn(new PageImpl<>(pageModel.getContent()));

        mockMvc.perform(get("/source")
                        .param("page", "0")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.content.[0].id", is(1)))
                .andExpect(jsonPath("$.content.[1].id", is(1)))
                .andExpect(jsonPath("$.content.[2].id", is(1)))
                .andExpect(jsonPath("$.content.[0].name", is("Ariel")))
                .andExpect(jsonPath("$.content.[1].name", is("Ariel")))
                .andExpect(jsonPath("$.content.[2].name", is("Ariel")))
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(status().isOk());
    }




    public SourceEntity source(Long id, String name){
        return SourceEntity.builder()
                .id(id)
                .name(name)
                .code("la-mañana")
                .build();
    }
}