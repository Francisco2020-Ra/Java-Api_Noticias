package com.javainformatorio.apinoticias.service;

import com.javainformatorio.apinoticias.dto.SourceDTO;

import java.util.List;

public interface SourceService {

    SourceDTO createSource(SourceDTO sourceDTO);

    List<SourceDTO> getSource();
}
