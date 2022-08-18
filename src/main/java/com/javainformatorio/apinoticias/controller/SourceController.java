package com.javainformatorio.apinoticias.controller;


import com.javainformatorio.apinoticias.dto.SourceDTO;
import com.javainformatorio.apinoticias.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/source")
public class SourceController {
    @Autowired
    private SourceService sourceService;

    @PostMapping
    public ResponseEntity<?> createSource(@RequestBody SourceDTO sourceDTO){
        return new ResponseEntity<>(sourceService.createSource(sourceDTO), HttpStatus.CREATED);
    }

    /*@GetMapping
    public ResponseEntity<?> getSource(){
        return new ResponseEntity<>(sourceService.getSource(), HttpStatus.OK);
    }
*/
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSource(@PathVariable Long id, @RequestBody SourceDTO sourceDTO){
        return new ResponseEntity<>(sourceService.updateSource(id, sourceDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSource(@PathVariable Long id){
        sourceService.deleteSource(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //pagination
    @GetMapping
    public ResponseEntity<?> findByPage(@RequestParam int page){
        return new ResponseEntity<>(sourceService.findByPage(page), HttpStatus.OK);
    }

    @GetMapping("/word")
    public ResponseEntity<?> findByNameContaining(@RequestParam String word, @RequestParam int page){
        return new ResponseEntity<>(sourceService.findByNameContaining(word, page), HttpStatus.OK);
    }
}
