package com.javainformatorio.apinoticias.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullname = this.firstName + " " + this.lastName;
    private LocalDate createdAt;
}
