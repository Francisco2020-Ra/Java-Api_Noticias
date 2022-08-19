package com.javainformatorio.apinoticias.service;

import com.javainformatorio.apinoticias.controller.util.PageResponse;
import com.javainformatorio.apinoticias.dto.AuthorDTO;
import com.javainformatorio.apinoticias.exception.ResourceNotFoundException;

import java.util.List;

public interface AuthorService {

    AuthorDTO createAuthor(AuthorDTO authorDTO);

    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) throws ResourceNotFoundException;

    void deleteAuthor(Long id) throws ResourceNotFoundException;

    PageResponse<AuthorDTO> findByPage(int page);

    PageResponse<AuthorDTO> findByCreatedAtIsAfter(String date, int page);

    PageResponse<AuthorDTO> findByFullnameContaining(String word, int page);

}
