package com.javainformatorio.apinoticias.service;

import com.javainformatorio.apinoticias.controller.util.PageResponse;
import com.javainformatorio.apinoticias.dto.ArticleDTO;
import com.javainformatorio.apinoticias.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {

    AuthorDTO createAuthor(AuthorDTO authorDTO);

    List<AuthorDTO> getAuthor();

    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);

    void deleteAuthor(Long id);

    PageResponse<AuthorDTO> findByPage(int page);
}
