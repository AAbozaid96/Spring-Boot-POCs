package com.example.idaaspoc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
}
