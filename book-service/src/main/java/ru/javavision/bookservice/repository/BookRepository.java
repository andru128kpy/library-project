package ru.javavision.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavision.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
