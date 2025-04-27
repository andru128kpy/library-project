package ru.javavision.loanservice.DTO;

import lombok.Data;

@Data
public class BookDTO {
    private Integer id;  // Добавлено поле id
    private String title;
    private String author;
    private String publisher;
    private Long price;
}