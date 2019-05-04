package com.example.library_system.mapper;

import com.example.library_system.dto.StudentDto;
import com.example.library_system.model.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentDtoStudentEntityMapper {

  /**
   * Converts StudentEntity To StudentDto.
   * @param studentEntity To be converted.
   * @return Converted Form
   */
  StudentDto studentEntityToStudentDto(StudentEntity studentEntity);

  /**
   * Converts StudentDto To StudentEntity
   * @param studentDto To be converted.
   * @return Converted Form
   */
  StudentEntity studentDtoToStudentEntity(StudentDto studentDto);
}
