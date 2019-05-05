package com.example.library_system.service.Implementation;

import com.example.library_system.cache.service.StudentCacheManager;
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

  @Autowired
  StudentCacheManager studentCacheManager;

  /**
   * Gets all the students.
   *
   * @return students
   */
  @Override
  public List<StudentDto> getAllStudents() {
//    List<StudentEntity> studentEntities = findStudentsInCache();
//
//    if(studentEntities!=null || !studentEntities.isEmpty()){
//      List<StudentDto> studentDtoArrayList = studentEntities.stream()
//          .map(studentEntity ->
//              studentDtoStudentEntityMapper.studentEntityToStudentDto(studentEntity))
//          .collect(Collectors.toList());
//      return studentDtoArrayList;
//    }

    try {
      List<StudentEntity> studentEntityList = studentRepository.findAll();
      if (studentEntityList.isEmpty()) {
        LogUtils.getInfoLogger().info("No Students Found");
        return null;
      } else {
        addStudentsToCache(studentEntityList);
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

  private void addStudentsToCache(List<StudentEntity> studentEntityList) {
   // studentCacheManager.
  }

//  private List<StudentEntity> findStudentsInCache() {
//
//  }

  /**
   * Return students.
   *
   * @param studentId Id of the student to be returned.
   * @return Student
   */
  @Override
  public Optional<StudentDto> getStudent(Integer studentId) {

    Optional<StudentEntity> cachedStudentEntity = findStudentInCache(studentId);

    if (cachedStudentEntity.isPresent()) {
      return Optional.of(studentDtoStudentEntityMapper
          .studentEntityToStudentDto(cachedStudentEntity.get()));
    }

    try {
      Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
      if (studentEntity.isPresent()) {
        StudentDto studentDto =
            studentDtoStudentEntityMapper.studentEntityToStudentDto(studentEntity.get());
        LogUtils.getInfoLogger().info("Student Found in main database: {}", studentDto.toString());
        LogUtils.getInfoLogger().info("Writing the student in the cache : {}",
            studentEntity.toString());
        studentCacheManager.cacheStudentDetails(studentEntity.get());
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
   * This looks into the cache for the student.
   * @param studentId id of student to be looked for
   * @return student if found else empty
   */
  private Optional<StudentEntity> findStudentInCache(Integer studentId) {
    Optional<StudentEntity> cachedStudentEntity = studentCacheManager.getCachedStudentDetail(studentId);
    if (cachedStudentEntity.isPresent()) {
      LogUtils.getInfoLogger().info("Found the student in the cache");
      return cachedStudentEntity;
    } else
      return Optional.empty();
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

    deleteCachedStudent(studentId);
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

  private void deleteCachedStudent(Integer studentId) {
    Long id = studentCacheManager.removeCachedStudent(studentId);
    LogUtils.getInfoLogger().info("Student with Id: {} removed from cache",id);
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
