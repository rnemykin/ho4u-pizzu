package ru.ho4upizzu.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class City extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
}
