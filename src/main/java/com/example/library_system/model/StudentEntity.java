package com.example.library_system.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class StudentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  String name;

  @Column(name = "issued_book_id")
  Integer IssuedBookId;

}
