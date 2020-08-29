package com.example.idaaspoc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Roles role;
}
