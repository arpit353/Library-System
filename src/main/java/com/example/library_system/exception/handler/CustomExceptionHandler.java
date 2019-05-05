package com.example.library_system.exception.handler;

import com.example.library_system.dto.response.BookResponseDto;
import com.example.library_system.dto.response.StudentResponseDto;
import com.example.library_system.exception.BookException;
import com.example.library_system.exception.StudentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(value = StudentException.class)
  public ResponseEntity handleStudentNotFoundException(StudentException exception){
    StudentResponseDto studentResponseDto = StudentResponseDto
        .builder()
        .code(HttpStatus.OK.value())
        .status(HttpStatus.OK.toString())
        .messages(new ArrayList<>(Arrays.asList(exception.getMessage())))
        .studentDtos(null)
        .build();
    return new ResponseEntity<>(studentResponseDto,HttpStatus.OK);
  }

  @ExceptionHandler(value = BookException.class)
  public ResponseEntity handleBookNotFoundException(BookException exception){
    BookResponseDto bookResponseDto = BookResponseDto
        .builder()
        .code(HttpStatus.OK.value())
        .status(HttpStatus.OK.toString())
        .messages(new ArrayList<>(Arrays.asList(exception.getMessage())))
        .bookDtos(null)
        .build();
    return new ResponseEntity<>(bookResponseDto,HttpStatus.OK);
  }

}
