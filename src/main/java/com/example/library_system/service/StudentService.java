package com.example.library_system.service;

import com.example.library_system.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {

  List<StudentDto> getAllStudents();

  Optional<StudentDto> getStudent(Integer studentId);

  Optional<StudentDto> updateStudent(StudentDto studentDto,Integer studentId);

  Optional<StudentDto> addStudent(StudentDto studentDto);

  Optional<StudentDto> deleteStudent(Integer studentId);

}
