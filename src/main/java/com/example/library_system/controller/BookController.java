package com.example.library_system.controller;

import com.example.library_system.dto.BookDto;
import com.example.library_system.dto.response.BookResponseDto;
import com.example.library_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book-management")
public class BookController {

  @Autowired
  BookService bookService;

  /**
   * Gets all the books.
   *
   * @return All Books
   */
  @RequestMapping(value = "/books", method = RequestMethod.GET)
  public ResponseEntity<BookResponseDto> getBooks() {
    BookResponseDto bookResponseDto;
    List<BookDto> bookDtoList = bookService.getAllBooks();
     if (bookDtoList == null || bookDtoList.isEmpty()) {
      bookResponseDto = BookResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("No Result")))
          .bookDtos(null)
          .build();
    } else {
      bookResponseDto = BookResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Books found")))
          .bookDtos(bookDtoList)
          .build();
    }
    return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
  }

  /**
   * Get the book according to bookId.
   *
   * @param bookId BookEntity Id
   * @return book according to bookId
   */
  @RequestMapping(value = "books/{bookId}", method = RequestMethod.GET)
  public ResponseEntity<BookResponseDto> getBook(@PathVariable Integer bookId) {
    BookResponseDto bookResponseDto;
    Optional<BookDto> bookDto = bookService.getBook(bookId);
    if (bookDto.isPresent()) {
      bookResponseDto = BookResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Found the book.")))
          .bookDtos(new ArrayList<BookDto>(Arrays.asList(bookDto.get())))
          .build();
    } else {
      bookResponseDto = BookResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("No book with this Id exists")))
          .bookDtos(null)
          .build();
    }

    return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
  }

  /**
   * Puts the book according to book id.
   *
   * @param bookDtoBody The book
   * @return Updated book
   */
  @RequestMapping(value = "books/{bookId}", method = RequestMethod.PUT)
  public ResponseEntity<BookResponseDto> updateBook(@RequestBody BookDto bookDtoBody,
                                                    @PathVariable Integer bookId) {
    BookResponseDto bookResponseDto;
    Optional<BookDto> bookDto = bookService.updateBook(bookId, bookDtoBody);
    if(bookDto.isPresent())
    {
      bookResponseDto = BookResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Book Information Updated.")))
          .bookDtos(new ArrayList<BookDto>(Arrays.asList(bookDto.get())))
          .build();
    }
    else {
      bookResponseDto = BookResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("No book with this Id exists")))
          .bookDtos(null)
          .build();
    }
    return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
  }

  /**
   * Add the book.
   *
   * @return added book.
   */
  @RequestMapping(value = "books", method = RequestMethod.POST)
  public ResponseEntity<BookResponseDto> addBook(@RequestBody BookDto bookDtoBody) {
    BookResponseDto bookResponseDto;
    Optional<BookDto> bookDto = bookService.addBook(bookDtoBody);
    if(bookDto.isPresent())
    {
      bookResponseDto = BookResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Book Added.")))
          .bookDtos(new ArrayList<BookDto>(Arrays.asList(bookDto.get())))
          .build();
    }
    else {
      bookResponseDto = BookResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Book couldn't be added")))
          .bookDtos(null)
          .build();
    }
    return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
  }

  /**
   * Delete the book based on the id.
   *
   * @param bookId book's id to be deleted.
   * @return Delete BookEntity
   */
  @RequestMapping(value = "books/{bookId}", method = RequestMethod.DELETE)
  public ResponseEntity<BookResponseDto> deleteBook(@PathVariable Integer bookId) {
    BookResponseDto bookResponseDto;
    Optional<BookDto> bookDto = bookService.deleteBook(bookId);

    if(bookDto.isPresent())
    {
      bookResponseDto = BookResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Book Deleted.")))
          .bookDtos(new ArrayList<BookDto>(Arrays.asList(bookDto.get())))
          .build();
    }
    else {
      bookResponseDto = BookResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Book couldn't be deleted")))
          .bookDtos(null)
          .build();
    }
    return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
  }
}
