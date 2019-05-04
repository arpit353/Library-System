package com.example.library_system.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class BookEntity {

  @Column(name = "book_id")
  Integer bookId;

  @Column(name = "book_name")
  String bookName;

  @Column(name = "book_author")
  String bookAuthor;

}
