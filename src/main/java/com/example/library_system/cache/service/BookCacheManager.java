package com.example.library_system.cache.service;

import com.example.library_system.model.BookEntity;

public interface BookCacheManager {

  void cacheBookDetails(BookEntity bookEntity);
}
