package com.example.library_system.dto.response;

import com.example.library_system.dto.BookDto;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResponseDto {
  String status;
  Integer code;
  List<String> messages;
  List<BookDto> bookDtos;
}
