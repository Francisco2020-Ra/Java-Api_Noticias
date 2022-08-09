package com.javainformatorio.apinoticias.service.impl;

import com.javainformatorio.apinoticias.dto.SourceDTO;
import com.javainformatorio.apinoticias.entities.SourceEntity;
import com.javainformatorio.apinoticias.mapper.SourceMapper;
import com.javainformatorio.apinoticias.repository.SourceRepository;
import com.javainformatorio.apinoticias.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SourceServiceImpl implements SourceService {
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private SourceMapper sourceMapper;

    @Override
    public SourceDTO createSource(SourceDTO sourceDTO) {
        SourceEntity sourceEntity = sourceMapper.toEntity(sourceDTO);
        SourceEntity sourceSave = sourceRepository.save(sourceEntity);
        SourceDTO sourceDTO1 = sourceMapper.toDTO(sourceSave);

        return sourceDTO1;
    }

    @Override
    public List<SourceDTO> getSource() {
        List<SourceEntity> sourceEntities = sourceRepository.findAll();
        List<SourceDTO> sourceDTOS = sourceMapper.toListDTO(sourceEntities);

        return sourceDTOS;
    }

    @Override
    public SourceDTO updateSource(Long id, SourceDTO sourceDTO) {
        Optional<SourceEntity> source = sourceRepository.findById(id);

        if(source.isEmpty()){
            //TODO: CAMBIAR CUANDO TOCA MANEJOR DE EXCEPTIONES
            throw  new RuntimeException();
        }

        SourceEntity sourceEntity = sourceMapper.toSetEntity(source.get(), sourceDTO);
        SourceEntity sourceSave = sourceRepository.save(sourceEntity);
        SourceDTO sourceDTO1 = sourceMapper.toDTO(sourceSave);

        return sourceDTO1;
    }
}
