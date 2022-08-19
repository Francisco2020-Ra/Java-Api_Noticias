package com.javainformatorio.apinoticias.service;

import com.javainformatorio.apinoticias.controller.util.PageResponse;
import com.javainformatorio.apinoticias.dto.SourceDTO;
import com.javainformatorio.apinoticias.exception.ResourceNotFoundException;

import java.util.List;

public interface SourceService {

    SourceDTO createSource(SourceDTO sourceDTO);

    SourceDTO updateSource(Long id, SourceDTO sourceDTO) throws ResourceNotFoundException;

    void deleteSource(Long id) throws ResourceNotFoundException;

    PageResponse<SourceDTO> findByPage(int page);

    PageResponse<SourceDTO> findByNameContaining(String word, int page);
}
