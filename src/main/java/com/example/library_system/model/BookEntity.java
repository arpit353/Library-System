package com.example.library_system.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
//If you want to use Both @Getter and @Setter use @Data instead.
@ToString
@Table(name = "book")
public class BookEntity implements Serializable {

  @Id
  // Use Generation Type as IDENTITY, AUTO might use hibernate_sequence. Trust me, you dont want that.
  // It generate continuous id's through out scope of schema, but Auto increment id with in table in enough, i guess.
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "book_id")
  Integer bookId;

  @Column(name = "book_name")
  String bookName;

  @Column(name = "book_author")
  String bookAuthor;

}
