package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege(){}
    public Privilege(String name) {
        this.name = name;
    }
}
