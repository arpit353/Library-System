package com.example.library_system.dto.response;

import com.example.library_system.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
  String status;
  Integer code;
  List<String> messages;
  List<BookDto> bookDtos;
}
