package ru.javavision.bookservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;
import ru.javavision.bookservice.dto.BookDTO;
import ru.javavision.bookservice.service.BookService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.readAll();
    }

    @GetMapping("find-book/{id}")
    public BookDTO findBookById(@PathVariable Long id) {
        return bookService.read(id.intValue());
    }

    @PostMapping("save-book")
    public BookDTO saveBook(@RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }

    @PutMapping("update-book/{id}")
    public BookDTO updateBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
        return bookService.update(bookDTO, id.intValue());
    }

    @DeleteMapping("delete-book/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id.intValue());
    }
}
