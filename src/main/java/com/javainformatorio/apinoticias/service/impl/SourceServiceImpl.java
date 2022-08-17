package com.javainformatorio.apinoticias.service.impl;

import com.javainformatorio.apinoticias.controller.util.PageResponse;
import com.javainformatorio.apinoticias.dto.SourceDTO;
import com.javainformatorio.apinoticias.entities.SourceEntity;
import com.javainformatorio.apinoticias.mapper.SourceMapper;
import com.javainformatorio.apinoticias.repository.SourceRepository;
import com.javainformatorio.apinoticias.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class SourceServiceImpl implements SourceService {
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private SourceMapper sourceMapper;


    @Override
    public SourceDTO createSource(SourceDTO sourceDTO) {
        SourceEntity sourceEntity = sourceMapper.toEntity(sourceDTO);
        SourceEntity sourceSave = sourceRepository.save(sourceEntity);
        SourceDTO sourceDTO1 = sourceMapper.toDTO(sourceSave);

        return sourceDTO1;
    }

    @Override
    public List<SourceDTO> getSource() {
        List<SourceEntity> sourceEntities = sourceRepository.findAll();
        List<SourceDTO> sourceDTOS = sourceMapper.toListDTO(sourceEntities);

        return sourceDTOS;
    }

    @Override
    public SourceDTO updateSource(Long id, SourceDTO sourceDTO) {
        Optional<SourceEntity> source = sourceRepository.findById(id);

        /*if(source.isEmpty()){
            //TODO: CAMBIAR CUANDO TOCA MANEJOR DE EXCEPTIONES
            throw  new RuntimeException();
        }*/

        SourceEntity sourceEntity = sourceMapper.toSetEntity(source.get(), sourceDTO);
        SourceEntity sourceSave = sourceRepository.save(sourceEntity);
        SourceDTO sourceDTO1 = sourceMapper.toDTO(sourceSave);

        return sourceDTO1;
    }

    @Override
    public void deleteSource(Long id) {
        SourceEntity sourceEntity = sourceRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found id: " + id)
        );

        sourceRepository.deleteById(id);
    }

    @Override
    public PageResponse<SourceDTO> findByPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<SourceEntity> pageResultSourceEntity = sourceRepository.findAll(pageable);

        if(page >= pageResultSourceEntity.getTotalPages()){
            throw new IllegalArgumentException("Incorrect index");
        }

        String nextPage = pageResultSourceEntity.isLast() ? "" : "/source?page=" + (page + 1);
        String previousPage = pageResultSourceEntity.isFirst() ? "" : "/source?page=" + (page - 1);

        return pageSource(pageResultSourceEntity, page, nextPage, previousPage );


    }

    public PageResponse<SourceDTO> pageSource(Page<SourceEntity> pageResultSourceEntity, int page, String nextPage, String previousPage){

        PageResponse response = PageResponse.builder()
                .content(pageResultSourceEntity
                        .getContent()
                        .stream()
                        .map(sourceMapper::toDTO)
                        .collect(toList()))
                .pageNumber(page +1)
                .totalPage(pageResultSourceEntity.getTotalPages())
                .totalElements(pageResultSourceEntity.getTotalElements())
                .nextPage(nextPage)
                .previousPage(previousPage)
                .build();
        return response;
    }
}
