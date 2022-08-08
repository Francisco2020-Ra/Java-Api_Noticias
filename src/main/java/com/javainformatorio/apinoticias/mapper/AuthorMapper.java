package com.javainformatorio.apinoticias.mapper;

import com.javainformatorio.apinoticias.dto.AuthorDTO;
import com.javainformatorio.apinoticias.entities.AuthorEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorEntity toEntity(AuthorDTO authorDTO){
        return AuthorEntity.builder()
                .firstName(authorDTO.getFirstName())
                .lastName(authorDTO.getLastName())
                .fullname(authorDTO.getFullname())
                .build();
    }

    public AuthorDTO toDTO(AuthorEntity authorEntity){
        return AuthorDTO.builder()
                .id(authorEntity.getId())
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLastName())
                .fullname(authorEntity.getFullname())
                .createdAt(authorEntity.getCreatedAt().toString())
                .build();
    }

}
