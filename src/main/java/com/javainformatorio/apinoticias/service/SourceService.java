package com.javainformatorio.apinoticias.service;

import com.javainformatorio.apinoticias.dto.SourceDTO;

import java.util.List;

public interface SourceService {

    SourceDTO createSource(SourceDTO sourceDTO);

    List<SourceDTO> getSource();

    SourceDTO updateSource(Long id, SourceDTO sourceDTO);

    void deleteSource(Long id);
}
