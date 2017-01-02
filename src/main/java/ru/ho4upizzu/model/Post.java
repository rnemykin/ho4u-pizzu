package ru.ho4upizzu.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Post extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private LocalDateTime publishDate;
    private String title;
    private String shortDescription;

    @Lob
    private String content;
}
