package com.javainformatorio.apinoticias.service.impl;

import com.javainformatorio.apinoticias.dto.AuthorDTO;
import com.javainformatorio.apinoticias.entities.AuthorEntity;
import com.javainformatorio.apinoticias.mapper.AuthorMapper;
import com.javainformatorio.apinoticias.repository.AuthorRepository;
import com.javainformatorio.apinoticias.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<AuthorDTO> getAuthor() {
        List<AuthorEntity> authorEntityList = authorRepository.findAll();
        if(authorEntityList.isEmpty()){
            System.out.println("Lista vacias: codigo no_content");
        }

        return authorMapper.toListDTO(authorEntityList);
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        AuthorEntity authorEntity = authorRepository.findById(id).orElseThrow(
                //TODO: cambiar cuando creamos la clase excepciones
                () -> new RuntimeException("Not found id: " + id)
        );
        AuthorEntity authorSet = authorMapper.toSetEntity(authorEntity, authorDTO);
        AuthorEntity authorSave = authorRepository.save(authorSet);
                
        return authorMapper.toDTO(authorSave);
    }
}
