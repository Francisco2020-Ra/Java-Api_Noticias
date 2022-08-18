package com.javainformatorio.apinoticias.controller;

import com.javainformatorio.apinoticias.dto.AuthorDTO;
import com.javainformatorio.apinoticias.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDTO authorDTO){
        return new ResponseEntity<>(authorService.createAuthor(authorDTO), HttpStatus.CREATED);
    }

    /*@GetMapping
    public ResponseEntity<?> getAuthor(){
        return new ResponseEntity<>(authorService.getAuthor(), HttpStatus.OK);
    }
*/
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO){
        return new ResponseEntity<>(authorService.updateAuthor(id, authorDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> findByPage(@RequestParam int page){
        return new ResponseEntity<>(authorService.findByPage(page), HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<?> findByCreationDateIsAfter(@RequestParam String date, @RequestParam int page){
        return new ResponseEntity<>(authorService.findByCreatedAtIsAfter(date, page), HttpStatus.OK);
    }

    @GetMapping("/word")
    public ResponseEntity<?> findByFullnameContaining(@RequestParam String word, @RequestParam int page){
        return new ResponseEntity<>(authorService.findByFullnameContaining(word, page), HttpStatus.OK);
    }
}
