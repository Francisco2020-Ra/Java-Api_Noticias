package com.javainformatorio.apinoticias.service;

import com.javainformatorio.apinoticias.controller.util.PageResponse;
import com.javainformatorio.apinoticias.dto.SourceDTO;

import java.util.List;

public interface SourceService {

    SourceDTO createSource(SourceDTO sourceDTO);

    List<SourceDTO> getSource();

    SourceDTO updateSource(Long id, SourceDTO sourceDTO);

    void deleteSource(Long id);

    PageResponse<SourceDTO> findByPage(int page);

    PageResponse<SourceDTO> findByNameContaining(String word, int page);
}
