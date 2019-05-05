package com.example.library_system.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Table(name = "book")
public class BookEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "book_id")
  Integer bookId;

  @Column(name = "book_name")
  String bookName;

  @Column(name = "book_author")
  String bookAuthor;

}
