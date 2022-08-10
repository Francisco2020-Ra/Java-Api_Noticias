package com.javainformatorio.apinoticias.service.impl;

import com.javainformatorio.apinoticias.dto.AuthorDTO;
import com.javainformatorio.apinoticias.entities.AuthorEntity;
import com.javainformatorio.apinoticias.mapper.AuthorMapper;
import com.javainformatorio.apinoticias.repository.AuthorRepository;
import com.javainformatorio.apinoticias.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorMapper authorMapper, AuthorRepository authorRepository){
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }
    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
       AuthorEntity authorEntity = authorMapper.toEntity(authorDTO);
       AuthorEntity authorSave =  authorRepository.save(authorEntity);

        return authorMapper.toDTO(authorSave);
    }
}
