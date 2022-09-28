package com.example.demo.domain;

import com.example.demo.enums.ReservationType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Reservation extends IdSuperClass {

    private ReservationType reservationType;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private Integer personsCount;
    private boolean submitted;

    @ManyToOne
    private Accommodation accommodation;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "FK_reservation_id")
    private List<ReservationHistory> reservationHistories = new ArrayList<>();
}