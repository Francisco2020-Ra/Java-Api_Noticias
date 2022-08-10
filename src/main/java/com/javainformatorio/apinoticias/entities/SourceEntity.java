package com.javainformatorio.apinoticias.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "source")
@SQLDelete(sql = "UPDATE source Set deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class SourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;
    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;
}
