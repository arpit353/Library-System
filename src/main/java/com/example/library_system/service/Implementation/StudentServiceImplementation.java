package com.example.library_system.service.Implementation;

import com.example.library_system.dto.StudentDto;
import com.example.library_system.exception.StudentException;
import com.example.library_system.mapper.StudentDtoStudentEntityMapper;
import com.example.library_system.model.StudentEntity;
import com.example.library_system.repository.StudentRepository;
import com.example.library_system.service.StudentService;
import com.example.library_system.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class StudentServiceImplementation implements StudentService {
  @Autowired
  StudentRepository studentRepository;

  @Autowired
  StudentDtoStudentEntityMapper studentDtoStudentEntityMapper;

  /**
   * Gets all the students.
   *
   * @return students
   */
  @Override
  public List<StudentDto> getAllStudents() {
    try {
      List<StudentEntity> studentEntityList = studentRepository.findAll();
      if (studentEntityList.isEmpty()) {
        LogUtils.getInfoLogger().info("No Students Found");
        return null;
      } else {
        List<StudentDto> studentDtoArrayList = studentEntityList.stream()
            .map(studentEntity ->
                studentDtoStudentEntityMapper.studentEntityToStudentDto(studentEntity))
            .collect(Collectors.toList());
        LogUtils.getInfoLogger().info("Student Found: {}", studentDtoArrayList.toString());
        return studentDtoArrayList;
      }
    } catch (Exception exception) {
      throw new StudentException(exception.getMessage());
    }
  }

  /**
   * Return students.
   *
   * @param studentId Id of the student to be returned.
   * @return Student
   */
  @Override
  public Optional<StudentDto> getStudent(Integer studentId) {
    try {
      Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
      if (studentEntity.isPresent()) {
        StudentDto studentDto =
            studentDtoStudentEntityMapper.studentEntityToStudentDto(studentEntity.get());
        LogUtils.getInfoLogger().info("Student Found: {}", studentDto.toString());
        return Optional.of(studentDto);
      } else {
        LogUtils.getInfoLogger().info("Student Not Found for Id: {}", studentId);
        return Optional.empty();
      }
    } catch (Exception exception) {
      throw new StudentException(exception.getMessage());
    }
  }

  /**
   * Updates student information.
   *
   * @param studentDto updated student data
   * @param studentId  id of the student to be updated.
   * @return Updated Student Information.
   */
  @Override
  public Optional<StudentDto> updateStudent(StudentDto studentDto, Integer studentId) {
    try {
      Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
      if (studentEntity.isPresent()) {
        StudentEntity updatedStudentEntity = studentRepository.save(new StudentEntity(studentId,
            studentDto.getName(), studentDto.getIssuedBookId()));
        LogUtils.getInfoLogger().info("Student updated: {}", updatedStudentEntity.toString());
        return Optional.of(studentDtoStudentEntityMapper.studentEntityToStudentDto(updatedStudentEntity));
      } else {
        LogUtils.getInfoLogger().info("Student Information not updated");
        return Optional.empty();
      }
    } catch (Exception exception) {
      throw new StudentException(exception.getMessage());
    }
  }

  /**
   * Add student information into the database.
   *
   * @param studentDto student information
   * @return Added student detail
   */
  @Override
  public Optional<StudentDto> addStudent(StudentDto studentDto) {
    try {
      StudentEntity addedStudentEntity =
          studentRepository.save(studentDtoStudentEntityMapper.studentDtoToStudentEntity(studentDto));
      LogUtils.getInfoLogger().info("Student Added: {}", addedStudentEntity.toString());
      return Optional.of(studentDtoStudentEntityMapper.studentEntityToStudentDto(addedStudentEntity));
    } catch (Exception exception) {
      throw new StudentException(exception.getMessage());
    }
  }
  //TODO: Ask some senior why constraint violation exception can't be caught here?

  /**
   * Delete the student from the database.
   *
   * @param studentId id of the student to be deleted.
   * @return deleted student information.
   */
  @Override
  public Optional<StudentDto> deleteStudent(Integer studentId) {
    try {
      Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
      if (studentEntity.isPresent()) {
        studentRepository.deleteById(studentId);
        LogUtils.getInfoLogger().info("Student Deleted: {}", studentEntity.toString());
        return Optional.of(studentDtoStudentEntityMapper.studentEntityToStudentDto(studentEntity.get()));
      } else {
        LogUtils.getInfoLogger().info("Student not deleted.");
        return Optional.empty();
      }
    } catch (Exception exception) {
      throw new StudentException(exception.getMessage());
    }
  }
}
