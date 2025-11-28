package com.Queensburry.hospital.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private UUID userId;

    private String firstName;
    private String lastName;

    @Column(unique = true,nullable = false)
    private String email;

    private String role;
    private String status;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Patient patient;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Doctor doctor;

    public User() {
        this.createdAt = new Date();
    }










}
