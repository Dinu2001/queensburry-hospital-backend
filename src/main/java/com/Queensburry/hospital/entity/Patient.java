package com.Queensburry.hospital.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "patient_id")
    private String patientId;

    private int age;
    private String gender;
    private String address;

    private String phoneNumber;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private Guardian guardian;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<LabAppointment> labAppointments;


    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Payment> payments;


    public Patient() {
    }

    public Patient(String patientId, int age, String gender, String address, String phoneNumber) {
        this.patientId = patientId;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    public Patient(String patientId, int age, String gender, String address, String phoneNumber, Guardian guardian, User user, List<LabAppointment> labAppointments, List<Payment> payments) {
        this.patientId = patientId;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.guardian = guardian;
        this.user = user;
        this.labAppointments = labAppointments;
        this.payments = payments;
    }

    public Patient(String id, int age, String gender, String address, String phoneNumber, User saveduser) {
        this.patientId = id;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.user = saveduser;

    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<LabAppointment> getLabAppointments() {
        return labAppointments;
    }

    public void setLabAppointments(List<LabAppointment> labAppointments) {
        this.labAppointments = labAppointments;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }


}
