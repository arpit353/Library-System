package com.example.library_system.mapper;

import com.example.library_system.dto.BookDto;
import com.example.library_system.model.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
// Awesome, this Mapper should be appreciated more in the company as well, don't you think
public interface BookDtoBookEntityMapper {

  /**
   * Converts BookDto To BookEntity.
   * @param bookDto To be converted.
   * @return Converted Form
   */
  BookEntity bookDtoToBookEntity(BookDto bookDto);

  /**
   * Converts BookEntity To BookDto.
   * @param bookEntity To be converted.
   * @return Converted Form
   */
  BookDto bookEntityToBookDto(BookEntity bookEntity);
}
