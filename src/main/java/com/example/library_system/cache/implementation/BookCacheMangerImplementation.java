package com.example.library_system.cache.implementation;

import com.example.library_system.cache.RedisUtil;
import com.example.library_system.cache.service.BookCacheManager;
import com.example.library_system.model.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BookCacheMangerImplementation implements BookCacheManager {

  public static final String BOOK_TABLE = "book";

  @Autowired
  RedisUtil<BookEntity> redisUtilBook;

  @Override
  public void cacheBookDetails(BookEntity bookEntity) {
    redisUtilBook.putMap(BOOK_TABLE,bookEntity.getBookId(),bookEntity);
    redisUtilBook.setExpire(BOOK_TABLE,1, TimeUnit.DAYS);
  }

}
