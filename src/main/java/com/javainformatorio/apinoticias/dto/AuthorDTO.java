package com.javainformatorio.apinoticias.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullname;
    private String createdAt;

}
