package ru.javavision.bookservice.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.javavision.bookservice.dto.BookDTO;
import ru.javavision.bookservice.mapper.BookMapper;
import ru.javavision.bookservice.model.Book;
import ru.javavision.bookservice.repository.BookRepository;
import ru.javavision.bookservice.service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    @Override
    public BookDTO create(BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.bookToBookDTO(savedBook);
    }

    @Override
    public List<BookDTO> readAll() {
        return bookRepository.findAll().stream().
                map(bookMapper::bookToBookDTO).
                collect(Collectors.toList());

    }

    @Override
    public BookDTO read(Integer id) {
        Optional<Book> book = bookRepository.findById(id.longValue());
        return book.map(bookMapper::bookToBookDTO).orElse(null);
    }

    @Override
    public BookDTO update(BookDTO bookDTO, Integer id) {
        if(bookRepository.existsById(id.longValue())){
            Book book = bookMapper.bookDTOToBook(bookDTO);
            book.setId(id.longValue());
            Book uppedBook = bookRepository.save(book);
            return bookMapper.bookToBookDTO(uppedBook);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if(bookRepository.existsById(id.longValue())){
            bookRepository.deleteById(id.longValue());
            return true;
        }
        return false;
    }
}
