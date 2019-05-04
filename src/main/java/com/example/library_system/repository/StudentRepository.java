package com.example.library_system.repository;

import com.example.library_system.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {

  /**
   * Find the student by Id.
   * @param id StudentEntity Id
   * @return StudentEntity of that Id
   */
  Optional<StudentEntity> findById(Integer id);

  /**
   * Returns all the students.
   * @return All Students
   */
  List<StudentEntity> findAll();

  /**
   * Save the student.
   * @param student The student to be saved.
   * @return Saved student.
   */
  StudentEntity save(StudentEntity student);

  /**
   * Delete the student by Id
   * @param student StudentEntity to be deleted
   */
  void deleteById(StudentEntity student);

}
