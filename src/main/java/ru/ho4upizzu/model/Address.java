package ru.ho4upizzu.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Address extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idCafe;
    private String city;
    private String street;
    private String house;
    private String latitude;
    private String longitude;
}
