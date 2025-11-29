package com.Queensburry.hospital.entity;

import jakarta.persistence.*;

import java.util.List;

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

    @Column(nullable = true)
    private String guardianName;

    @Column(nullable = true)
    private String guardianPhone;

    @Column(nullable = true)
    private String guardianRelationship;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<LabAppointment> labAppointments;


    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Payment> payments;

    public Patient() {
    }

    public Patient(String patientId, int age, String gender, String address, String phoneNumber, String guardianName, String guardianPhone, String guardianRelationship, User user, List<LabAppointment> labAppointments, List<Payment> payments) {
        this.patientId = patientId;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.guardianRelationship = guardianRelationship;
        this.user = user;
        this.labAppointments = labAppointments;
        this.payments = payments;
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

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public String getGuardianRelationship() {
        return guardianRelationship;
    }

    public void setGuardianRelationship(String guardianRelationship) {
        this.guardianRelationship = guardianRelationship;
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
