package ru.javavision.bookservice.service;

import ru.javavision.bookservice.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO create(BookDTO bookDTO);
    List<BookDTO> readAll();
    BookDTO read(Integer id);
    BookDTO update(BookDTO bookDTO, Integer id);
    boolean delete(Integer id);
}
