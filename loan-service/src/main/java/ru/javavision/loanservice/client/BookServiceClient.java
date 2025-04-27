package ru.javavision.loanservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.javavision.loanservice.DTO.BookDTO;

@FeignClient(name = "book-service", url = "http://localhost:8080")
public interface BookServiceClient {

    @GetMapping("/api/v1/books/find-book/{id}")
    BookDTO getBookById(@PathVariable Long id);

    @PutMapping("/api/v1/books/update-book/{id}")
    BookDTO updateBookStatus(@PathVariable Long id, @RequestBody BookDTO bookDTO);
}