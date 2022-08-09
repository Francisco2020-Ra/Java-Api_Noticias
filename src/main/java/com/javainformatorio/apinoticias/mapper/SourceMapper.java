package com.javainformatorio.apinoticias.mapper;

import com.javainformatorio.apinoticias.dto.SourceDTO;
import com.javainformatorio.apinoticias.entities.SourceEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import static java.util.stream.Collectors.toList;

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

    public List<SourceDTO> toListDTO(List<SourceEntity> sourceEntities){
        return sourceEntities
                .stream()
                .map(this::toDTO)
                .collect(toList());
    }

    public SourceEntity toSetEntity(SourceEntity sourceEntity, SourceDTO sourceDTO){
        sourceEntity.setId(sourceDTO.getId());
        sourceEntity.setName(sourceDTO.getName());
        sourceEntity.setCode(sourceDTO.getCode());
        return sourceEntity;
    }

}
