package com.javainformatorio.apinoticias.mapper;

import com.javainformatorio.apinoticias.dto.SourceDTO;
import com.javainformatorio.apinoticias.entities.SourceEntity;
import org.springframework.stereotype.Component;

@Component
public class SourceMapper {

    public SourceEntity toEntity(SourceDTO sourceDTO){
        return SourceEntity.builder()
                .name(sourceDTO.getName())
                .code(sourceDTO.getCode())
                .build();
    }

    public SourceDTO toDTO ( SourceEntity sourceEntity){
        return SourceDTO.builder()
                .id(sourceEntity.getId())
                .name(sourceEntity.getName())
                .code(sourceEntity.getCode())
                .createdAt(sourceEntity.getCreatedAt().toString())
                .build();
    }

}
