package com.example.library_system.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "student")
public class StudentEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  String name;

  @Column(name = "issued_book_id")
  Integer issuedBookId;

}
