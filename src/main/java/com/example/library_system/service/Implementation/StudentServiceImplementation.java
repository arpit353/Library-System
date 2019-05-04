package com.example.library_system.service.Implementation;

import com.example.library_system.dto.StudentDto;
import com.example.library_system.mapper.StudentDtoStudentEntityMapper;
import com.example.library_system.model.StudentEntity;
import com.example.library_system.repository.StudentRepository;
import com.example.library_system.service.StudentService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImplementation implements StudentService {
  @Autowired
  StudentRepository studentRepository;

  StudentDtoStudentEntityMapper studentDtoStudentEntityMapper =
      Mappers.getMapper(StudentDtoStudentEntityMapper.class);


  @Override
  public List<StudentDto> getAllStudents() {
    List<StudentEntity> studentEntityList = studentRepository.findAll();
    if (studentEntityList.isEmpty()) {
      return null;
    } else {
      List<StudentDto> studentDtoArrayList = studentEntityList.stream()
          .map(studentEntity ->
              studentDtoStudentEntityMapper.studentEntityToStudentDto(studentEntity))
          .collect(Collectors.toList());
      return studentDtoArrayList;
    }
  }

  @Override
  public Optional<StudentDto> getStudent(Integer studentId) {
    Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
    if (studentEntity.isPresent()) {
      StudentDto studentDto =
          studentDtoStudentEntityMapper.studentEntityToStudentDto(studentEntity.get());
      return Optional.of(studentDto);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<StudentDto> updateStudent(StudentDto studentDto, Integer studentId) {
    Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
    if (studentEntity.isPresent()) {
      StudentEntity updatedStudentEntity = studentRepository.save(new StudentEntity(studentId,
          studentDto.getName(), studentDto.getIssuedBookId()));
      return Optional.of(studentDtoStudentEntityMapper.studentEntityToStudentDto(updatedStudentEntity));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<StudentDto> addStudent(StudentDto studentDto) {
    StudentEntity addedStudentEntity =
        studentRepository.save(studentDtoStudentEntityMapper.studentDtoTostudentEntity(studentDto));
    return Optional.of(studentDtoStudentEntityMapper.studentEntityToStudentDto(addedStudentEntity));
  }

  @Override
  public Optional<StudentDto> deleteStudent(Integer studentId) {
    Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
    if (studentEntity.isPresent()) {
      studentRepository.deleteById(studentId);
      return Optional.of(studentDtoStudentEntityMapper.studentEntityToStudentDto(studentEntity.get()));
    } else {
      return Optional.empty();
    }
  }
}
