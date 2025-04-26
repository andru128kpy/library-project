package ru.javavision.bookservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Изменено с int на Long для соответствия репозиторию

    @Column(unique=true)
    private String title;

    @Column(unique=true)
    private String author;

    @Column(unique=true)
    private String publisher;

    @Column(unique=true)
    private Long price;
}