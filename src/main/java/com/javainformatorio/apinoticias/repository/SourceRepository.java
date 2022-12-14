package com.javainformatorio.apinoticias.repository;

import com.javainformatorio.apinoticias.entities.SourceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<SourceEntity, Long> {

    Page<SourceEntity> findByNameContaining(String word, Pageable page);

}
