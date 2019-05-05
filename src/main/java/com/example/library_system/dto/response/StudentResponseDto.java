package com.example.library_system.dto.response;

import com.example.library_system.dto.StudentDto;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentResponseDto {
  String status;
  Integer code;
  List<String> messages;
  List<StudentDto> studentDtos;
}
