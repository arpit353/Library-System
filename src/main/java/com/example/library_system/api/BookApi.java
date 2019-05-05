package com.example.library_system.api;

import com.example.library_system.dto.BookDto;
import com.example.library_system.dto.response.BookResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author sridharswain
 */
@RequestMapping("/book-management/v2")
/**
 * Use an API interface to write all you request mappings as.
 * 1. Someone must have said you that, business shouldn't go in your controller,
 * well i took it a little more seriously XB.
 * 2. All Path Strings, Request Annotation like PathVariable, RequestBody ends here, no huss puss in controller.
 * 3. I think all Autowiring services and stuff should not be shown to the people looking just for request mappings.
 * 4. And, there's a reason people call Google API and Not Google Controller. So an API is supposed to be an interface.
 * 5. And I think aesthetically pleasing XP.
 */
public interface BookApi {

    @GetMapping("/books")
    ResponseEntity<BookResponseDto> getBooks();

    @GetMapping("/books/{bookId}")
    ResponseEntity<BookResponseDto> getBook(@PathVariable Integer bookId);

    @PutMapping("/books/{bookId}")
    ResponseEntity<BookResponseDto> updateBook(@RequestBody BookDto bookDtoBody,
                                               @PathVariable Integer bookId);

    @PostMapping("/books")
    ResponseEntity<BookResponseDto> addBook(@RequestBody BookDto bookDtoBody);

    @DeleteMapping("/books/{bookId}")
    ResponseEntity<BookResponseDto> deleteBook(@PathVariable Integer bookId);

}
