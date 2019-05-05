package com.example.library_system.exception.handler;

import com.example.library_system.dto.response.BookResponseDto;
import com.example.library_system.dto.response.StudentResponseDto;
import com.example.library_system.exception.BookException;
import com.example.library_system.exception.StudentException;
import com.example.library_system.util.LogUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;

@ControllerAdvice
// NICE
public class CustomExceptionHandler {

  /**
   * Handles the Student Exception.
   * @param exception student exception
   * @return Response
   */
  @ExceptionHandler(value = StudentException.class)
  public ResponseEntity handleStudentException(StudentException exception){
    StudentResponseDto studentResponseDto = StudentResponseDto
        .builder()
        .code(HttpStatus.OK.value())
        .status(HttpStatus.OK.toString())
        .messages(new ArrayList<>(Arrays.asList(exception.getMessage())))
        .studentDtos(null)
        .build();
    LogUtils.getErrorLogger().error("Student Exception: {}",exception.getMessage());
    return new ResponseEntity<>(studentResponseDto,HttpStatus.OK);
  }

  /**
   * Handles the Book Exception.
   * @param exception book exception
   * @return Response
   */
  @ExceptionHandler(value = BookException.class)
  public ResponseEntity handleBookException(BookException exception){
    BookResponseDto bookResponseDto = BookResponseDto
        .builder()
        .code(HttpStatus.OK.value())
        .status(HttpStatus.OK.toString())
        .messages(new ArrayList<>(Arrays.asList(exception.getMessage())))
        .bookDtos(null)
        .build();
    LogUtils.getErrorLogger().error("Book Exception: {}",exception.getMessage());
    return new ResponseEntity<>(bookResponseDto,HttpStatus.OK);
  }

}
