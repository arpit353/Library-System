package com.example.library_system.dto.response;

import com.example.library_system.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
  String status;
  Integer code;
  List<String> messages;
  List<StudentDto> studentDtos;
}
