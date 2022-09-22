package com.example.demo.domain;

import com.example.demo.enums.AccommodationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Accommodation extends AccommodationLocation {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer categorization;
    @Min(1)
    private Integer personCount;
    private Byte[] image;
    private boolean freeCancellation = true;
    @NotNull
    private double price;
    private AccommodationType type;

    @ManyToOne
    private Location location;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;
}