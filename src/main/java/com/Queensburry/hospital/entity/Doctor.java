package com.Queensburry.hospital.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @Column(name = "doctor_id")
    private String doctorId;
    private String specification;

    @Column(nullable = false,unique = true)
    private String registrationNumber;
    private String phoneNumber;


    @ElementCollection
    @CollectionTable(
            name = "doctor_available_dates",
            joinColumns = @JoinColumn(name = "doctor_id")
    )
    @Column(name = "available_date")
    private List<String> availableDates;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Doctor() {
    }

    public Doctor(String doctorId, String specification, String registrationNumber, String phoneNumber, List<String> availableDates, User user) {
        this.doctorId = doctorId;
        this.specification = specification;
        this.registrationNumber = registrationNumber;
        this.phoneNumber = phoneNumber;
        this.availableDates = availableDates;
        this.user = user;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<String> availableDates) {
        this.availableDates = availableDates;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
