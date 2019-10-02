package ru.itis.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  @Column(name = "title")
    private String title;

  //  @Column(name = "description")
    private String description;

    private String urlImg;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
