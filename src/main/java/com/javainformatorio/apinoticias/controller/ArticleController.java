package com.javainformatorio.apinoticias.controller;

import com.javainformatorio.apinoticias.dto.ArticleDTO;
import com.javainformatorio.apinoticias.exception.ResourceNotFoundException;
import com.javainformatorio.apinoticias.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody ArticleDTO articleDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(articleService.createArticle(articleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(articleService.updateArticle(id, articleDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) throws ResourceNotFoundException {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> findByPage(@RequestParam int page){
        return new ResponseEntity<>(articleService.findByPage(page), HttpStatus.OK);
    }

    @GetMapping("/word")
    public ResponseEntity<?> findByTitleContainingAndDescriptionContainingAndAuthorByContentContainingAndFullnameContaining(
            @RequestParam @Size(min = 4, max = 255) String word, @RequestParam int page){
        return new ResponseEntity<>(
                articleService.findByTitleContainingAndDescriptionContainingAndAuthorByContentContainingAndFullnameContaining(word, page)
                , HttpStatus.OK);
    }
}
