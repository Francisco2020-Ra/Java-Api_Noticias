package com.javainformatorio.apinoticias.mapper;

import com.javainformatorio.apinoticias.dto.AuthorDTO;
import com.javainformatorio.apinoticias.entities.AuthorEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class AuthorMapper {

    public AuthorEntity toEntity(AuthorDTO authorDTO) {
        return AuthorEntity.builder()
                .firstName(authorDTO.getFirstName())
                .lastName(authorDTO.getLastName())
                .fullname(authorDTO.getFullname())
                .build();
    }

    public AuthorDTO toDTO(AuthorEntity authorEntity) {
        return AuthorDTO.builder()
                .id(authorEntity.getId())
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLastName())
                .fullname(authorEntity.getFullname())
                .createdAt(authorEntity.getCreatedAt().toString())
                .build();
    }

    public List<AuthorDTO> toListDTO(List<AuthorEntity> entityList) {
        return entityList.stream()
                .map(this::toDTO)
                .collect(toList());
    }

}
