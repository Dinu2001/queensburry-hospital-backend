package com.Queensburry.hospital.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "guardian_details")
public class Guardian {

    @Id
    private String id;

    private String guardianName;
    private String guardianPhone;
    private String guardianRelationship;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public Guardian() {
    }

    public Guardian(String id, String guardianName, String guardianPhone, String guardianRelationship) {
        this.id = id;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.guardianRelationship = guardianRelationship;
    }

    public Guardian(String id, String guardianName, String guardianPhone, String guardianRelationship, Patient patient) {
        this.id = id;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.guardianRelationship = guardianRelationship;
        this.patient = patient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
