package com.example.library_system.controller;

import com.example.library_system.dto.StudentDto;
import com.example.library_system.dto.response.StudentResponseDto;
import com.example.library_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student-management")
public class StudentController {

  @Autowired
  StudentService studentService;

  /**
   * Gets all the students.
   *
   * @return All students
   */
  @RequestMapping(value = "/students", method = RequestMethod.GET)
  public ResponseEntity<StudentResponseDto> getStudents() {
    StudentResponseDto studentResponseDto;
    List<StudentDto> studentDtoList = studentService.getAllStudents();
    if (studentDtoList==null || studentDtoList.isEmpty()) {
      studentResponseDto = StudentResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("No Students Information Present")))
          .studentDtos(null)
          .build();
    } else {
      studentResponseDto = StudentResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Student Informations Found")))
          .studentDtos(studentDtoList)
          .build();
    }
    return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
  }

  /**
   * Get the student according to studentId.
   *
   * @param studentId student Id
   * @return student according to studentId
   */
  @RequestMapping(value = "students/{studentId}", method = RequestMethod.GET)
  public ResponseEntity<StudentResponseDto> getStudent(@PathVariable Integer studentId) {
    StudentResponseDto studentResponseDto;
    Optional<StudentDto> studentDto = studentService.getStudent(studentId);
    if (studentDto.isPresent()) {
      studentResponseDto = StudentResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Student's Information Found")))
          .studentDtos(new ArrayList<>(Arrays.asList(studentDto.get())))
          .build();
    } else {
      studentResponseDto = StudentResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("No student with this Id exists")))
          .studentDtos(null)
          .build();
    }
    return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
  }

  /**
   * Puts the student according to student id.
   *
   * @param studentId student id
   * @return Updated student
   */
  @RequestMapping(value = "students/{studentId}", method = RequestMethod.PUT)
  public ResponseEntity<StudentResponseDto> updateStudent(@RequestBody StudentDto studentDtoBody,
                                                          @PathVariable Integer studentId) {
    StudentResponseDto studentResponseDto;
    Optional<StudentDto> studentDto = studentService.updateStudent(studentDtoBody,studentId);
    if (studentDto.isPresent()) {
      studentResponseDto = StudentResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Student's Information Updated")))
          .studentDtos(new ArrayList<>(Arrays.asList(studentDto.get())))
          .build();
    } else {
      studentResponseDto = StudentResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Student's Information not Updated")))
          .studentDtos(null)
          .build();
    }
    return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
  }

  /**
   * Add the student.
   *
   * @return added student.
   */
  @RequestMapping(value = "students/", method = RequestMethod.POST)
  public ResponseEntity<StudentResponseDto> addStudent(@RequestBody StudentDto studentDtoBody) {
    StudentResponseDto studentResponseDto;
    Optional<StudentDto> studentDto = studentService.addStudent(studentDtoBody);
    if (studentDto.isPresent()) {
      studentResponseDto = StudentResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Student's Information added")))
          .studentDtos(new ArrayList<>(Arrays.asList(studentDto.get())))
          .build();
    } else {
      studentResponseDto = StudentResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Student's Information not added")))
          .studentDtos(null)
          .build();
    }
    return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
  }

  /**
   * Delete the student based on the id.
   *
   * @param studentId student's id to be deleted.
   * @return Delete student
   */
  @RequestMapping(value = "students/{studentId}", method = RequestMethod.DELETE)
  public ResponseEntity<StudentResponseDto> deleteStudent(@PathVariable Integer studentId) {
    StudentResponseDto studentResponseDto;
    Optional<StudentDto> studentDto = studentService.deleteStudent(studentId);
    if (studentDto.isPresent()) {
      studentResponseDto = StudentResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Student's Information Deleted")))
          .studentDtos(new ArrayList<>(Arrays.asList(studentDto.get())))
          .build();
    } else {
      studentResponseDto = StudentResponseDto.builder()
          .code(HttpStatus.OK.value())
          .status(HttpStatus.OK.toString())
          .messages(new ArrayList<>(Arrays.asList("Student's Information not deleted")))
          .studentDtos(null)
          .build();
    }
    return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
  }

}
