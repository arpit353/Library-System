package com.example.library_system.cache.implementation;

import com.example.library_system.cache.RedisUtil;
import com.example.library_system.cache.service.StudentCacheManager;
import com.example.library_system.model.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class StudentCacheMangerImplementation implements StudentCacheManager {

  private static final String STUDENT_TABLE = "student";

  @Autowired
  RedisUtil<StudentEntity> redisUtilStudent;

  @Override
  public void cacheStudentDetails(StudentEntity studentEntity) {
    redisUtilStudent.putMap(STUDENT_TABLE,studentEntity.getId(),studentEntity);
    redisUtilStudent.setExpire(STUDENT_TABLE,1, TimeUnit.DAYS);
  }

  @Override
  public ArrayList<StudentEntity> getCachedStudentsDetail() {
//    ArrayList<StudentEntity> studentEntityArrayList =
//         new ArrayList<>(redisUtilStudent.getMapsAsAll(STUDENT_TABLE).values());
//    if(studentEntityArrayList == null || studentEntityArrayList.isEmpty()){
//      return new ArrayList<>(Arrays.asList());
//    }
    return null;
  }

  @Override
  public void updateCachedStudentsDetail(List<StudentEntity> studentEntityList) {

  }

  @Override
  public Long removeCachedStudent(Integer id) {
    return redisUtilStudent.removeMap(STUDENT_TABLE,id);
  }

  @Override
  public Optional<StudentEntity> getCachedStudentDetail(Integer id) {
    Optional<StudentEntity> studentEntity = Optional.ofNullable(redisUtilStudent.getMapAsSingleEntry(STUDENT_TABLE,id));
    if(studentEntity.isPresent())
    {
      return studentEntity;
    }
    else {
      return Optional.empty();
    }
  }


}
