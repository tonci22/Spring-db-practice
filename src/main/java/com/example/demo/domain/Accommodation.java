package com.example.demo.domain;

import com.example.demo.enums.AccommodationType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer categorization;
    private Integer personCount;
    private Byte[] image;
    private boolean freeCancelation = true;
    private double price;
    private AccommodationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;
}