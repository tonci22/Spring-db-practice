package com.example.demo.domain;

import com.example.demo.enums.ReservationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
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

    public Reservation() {
        accommodation = new Accommodation();
    }
}
