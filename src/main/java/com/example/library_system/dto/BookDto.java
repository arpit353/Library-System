package com.example.library_system.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto {
  Integer bookId;
  String bookName;
  String bookAuthor;
}
