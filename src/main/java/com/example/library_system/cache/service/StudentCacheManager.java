package com.example.library_system.cache.service;

import com.example.library_system.model.StudentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface StudentCacheManager {

  void cacheStudentDetails(StudentEntity studentEntity);

  Optional<StudentEntity> getCachedStudentDetail(Integer id);

  Long removeCachedStudent(Integer id);

  ArrayList<StudentEntity> getCachedStudentsDetail();

  void updateCachedStudentsDetail(List<StudentEntity> studentEntityList);

}
