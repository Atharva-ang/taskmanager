package com.atharva.taskmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // This Lombok annotation automatically generates your getters, setters, and constructors!
@Table(name = "users") // We use "users" because "user" is a reserved keyword in PostgreSQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // A User can be in many teams, and a Team can have many users.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_teams", // This tells PostgreSQL to create a brand new 4th table to link them!
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private java.util.Set<Team> teams = new java.util.HashSet<>();
}