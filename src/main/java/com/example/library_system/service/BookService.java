package com.example.library_system.service;

import com.example.library_system.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BookService {

  List<BookDto> getAllBooks();

  Optional<BookDto> getBook(Integer bookId);

  Optional<BookDto> updateBook(Integer bookId, BookDto bookDto);

  Optional<BookDto> addBook(BookDto bookDto);

  Optional<BookDto> deleteBook(Integer bookId);

}
