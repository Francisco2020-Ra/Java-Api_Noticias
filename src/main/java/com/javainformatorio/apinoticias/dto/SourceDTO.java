package com.javainformatorio.apinoticias.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SourceDTO {

    private Long id;
    private String name;
    private String code;
    private LocalDate createdAt;
}
