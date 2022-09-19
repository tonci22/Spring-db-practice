package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"postalCode"})})
public class Location extends AccommodationLocation {

    private Integer postalCode;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Accommodation> accommodations;

    public Location() {
        accommodations = new ArrayList<>();
    }
}
