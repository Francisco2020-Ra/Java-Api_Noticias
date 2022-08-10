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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")
@SQLDelete(sql = "UPDATE author SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "fullname")
    private String fullname = firstName +" "+ lastName;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

}
