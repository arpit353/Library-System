package com.example.library_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StudentDto {
  Integer id;
  String name;
  Integer IssuedBookId;
}
