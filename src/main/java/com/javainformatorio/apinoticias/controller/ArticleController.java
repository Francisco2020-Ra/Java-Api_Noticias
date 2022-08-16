package com.javainformatorio.apinoticias.controller;

import com.javainformatorio.apinoticias.dto.ArticleDTO;
import com.javainformatorio.apinoticias.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody ArticleDTO articleDTO) {
        return new ResponseEntity<>(articleService.createArticle(articleDTO), HttpStatus.CREATED);
    }

    /*@GetMapping
    public ResponseEntity<?> getArticle(){
        return new ResponseEntity<>(articleService.getArticle(), HttpStatus.OK);
    }
*/
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO){
        return new ResponseEntity<>(articleService.updateArticle(id, articleDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id){
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> findByPage(@RequestParam int page){
        return new ResponseEntity<>(articleService.findByPage(page), HttpStatus.OK);
    }
}
