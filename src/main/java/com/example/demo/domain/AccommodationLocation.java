package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@MappedSuperclass
public class AccommodationLocation extends IdSuperClass {

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 200)
    private String subtitle;
}
