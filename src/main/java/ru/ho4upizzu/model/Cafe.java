package ru.ho4upizzu.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@Entity
@Table
public class Cafe extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
    private String description;
    private Boolean hasDelivery;
    private BigDecimal deliveryPrice;
    private Boolean deliveryPriceFrom;
    private Boolean minimalOrderPrice;
    private Integer deliveryTime;
    private LocalTime deliveryWorkStart;
    private LocalTime deliveryWorkEnd;
    private LocalTime workStart;
    private LocalTime workEnd;
    private String siteUrl;
    private Integer rank;
    private String phoneNumbers;
    private String viewLink;
}
