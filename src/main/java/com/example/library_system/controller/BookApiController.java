package com.example.library_system.controller;

import com.example.library_system.api.BookApi;
import com.example.library_system.dto.BookDto;
import com.example.library_system.dto.response.BookResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sridharswain
 */
@RestController
/**
 * Now implement your API in controller.
 * Why? Yes, Points coming up again. X).
 * 1. Seeee, there's no Request Annotations here(Cover that @RestController up there with your palm and then see), only pure code.
 * 2. DTO validations can come here so that you don't have to write null checks and worry about data in your services.
 * 3. you can write Services which doesn't care about data, it will just do what it is supposed todo, which is not validations BTW.
 * 4. And Aesthetically Pleasing, for sure. XD.
 */
public class BookApiController implements BookApi {
    @Override
    public ResponseEntity<BookResponseDto> getBooks() {
        return null;
    }

    @Override
    public ResponseEntity<BookResponseDto> getBook(Integer bookId) {
        return null;
    }

    @Override
    public ResponseEntity<BookResponseDto> updateBook(BookDto bookDtoBody, Integer bookId) {
        return null;
    }

    @Override
    public ResponseEntity<BookResponseDto> addBook(BookDto bookDtoBody) {
        return null;
    }

    @Override
    public ResponseEntity<BookResponseDto> deleteBook(Integer bookId) {
        return null;
    }
}
