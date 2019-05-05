package com.example.library_system.service.Implementation;

import com.example.library_system.dto.BookDto;
import com.example.library_system.mapper.BookDtoBookEntityMapper;
import com.example.library_system.model.BookEntity;
import com.example.library_system.repository.BookRepository;
import com.example.library_system.service.BookService;
import com.example.library_system.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImplementation implements BookService {

  @Autowired
  BookRepository bookRepository;

  @Autowired
  BookDtoBookEntityMapper bookDtoBookEntityMapper;

  /**
   * Gets all the books.
   *
   * @return All Books
   */
  @Override
  public List<BookDto> getAllBooks() {
    List<BookEntity> bookEntityArrayList = bookRepository.findAll();

    if (bookEntityArrayList.isEmpty()) {
      return null;
    } else {
      List<BookDto> bookDtoArrayList = bookEntityArrayList.stream()
          .map(bookEntity ->
              bookDtoBookEntityMapper.bookEntityToBookDto(bookEntity))
          .collect(Collectors.toList());
      return bookDtoArrayList;
    }
  }

  /**
   * Get the book according to bookId.
   *
   * @param bookId BookEntity Id
   * @return book according to bookId
   */
  @Override
  public Optional<BookDto> getBook(Integer bookId) {
    Optional<BookEntity> bookEntity = bookRepository.findByBookId(bookId);
    if (bookEntity.isPresent()) {
      LogUtils.getInfoLogger().info("Found the book: {}",bookEntity.get());
      return Optional.of(bookDtoBookEntityMapper.bookEntityToBookDto(bookEntity.get()));
    } else {
      LogUtils.getInfoLogger().info("Book not found");
      return Optional.empty();
    }

  }

  /**
   * Puts the book according to book id.
   *
   * @param bookId  id of the book which has to updated
   * @param bookDto updated bookDto
   * @return
   */
  @Override
  public Optional<BookDto> updateBook(Integer bookId, BookDto bookDto) {
    Optional<BookEntity> bookEntity = bookRepository.findByBookId(bookId);
    if (bookEntity.isPresent()) {
      BookEntity updatedBookEntity = bookRepository.save(new BookEntity(bookId,
          bookDto.getBookName(), bookDto.getBookAuthor()));
      return Optional.of(bookDtoBookEntityMapper.bookEntityToBookDto(updatedBookEntity));
    } else {
      return Optional.empty();
    }
  }

  /**
   * Add the book.
   *
   * @return added book.
   */
  @Override
  public Optional<BookDto> addBook(BookDto bookDto) {
    BookEntity addedBookEntity =
        bookRepository.save(bookDtoBookEntityMapper.bookDtoToBookEntity(bookDto));
    return Optional.of(bookDtoBookEntityMapper.bookEntityToBookDto(addedBookEntity));
  }

  /**
   * Delete the book based on the id.
   *
   * @param bookId book's id to be deleted.
   * @return Delete BookEntity
   */
  @Override
  public Optional<BookDto> deleteBook(Integer bookId) {
    Optional<BookEntity> bookEntity = bookRepository.findByBookId(bookId);
    if (bookEntity.isPresent()) {
      bookRepository.deleteByBookId(bookId);
      return Optional.of(bookDtoBookEntityMapper.bookEntityToBookDto(bookEntity.get()));
    } else {
      return Optional.empty();
    }
  }
}
