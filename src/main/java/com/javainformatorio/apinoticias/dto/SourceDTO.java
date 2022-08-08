package com.javainformatorio.apinoticias.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
public class SourceDTO {

    private Long id;
    private String name;
    private String code;
    private LocalDate createdAt;
}
