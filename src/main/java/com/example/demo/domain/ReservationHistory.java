package com.example.demo.domain;

import com.example.demo.enums.ReservationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ReservationHistory extends IdSuperClass {


    @CreationTimestamp
    private Timestamp entryTimestamp;
    private ReservationType fromType;
    private ReservationType toType;
    private Long reservationId;
}
