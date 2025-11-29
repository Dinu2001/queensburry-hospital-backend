package com.Queensburry.hospital.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "lab_test")
public class LabTest {

    @Id
    private String labId;

    @Column(nullable = false)
    private String testName;

    @Column(length = 500)
    private String description;

    private Double test_amount;

    @Column(length = 1000)
    private String preparationInstructions;

    public LabTest() {
    }

    @OneToMany(mappedBy = "labTest")
    private List<LabAppointment> labAppointments;


    public LabTest(String labId, String testName, String description, Double test_amount, String preparationInstructions, List<LabAppointment> labAppointments) {
        this.labId = labId;
        this.testName = testName;
        this.description = description;
        this.test_amount = test_amount;
        this.preparationInstructions = preparationInstructions;
        this.labAppointments = labAppointments;
    }

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTest_amount() {
        return test_amount;
    }

    public void setTest_amount(Double test_amount) {
        this.test_amount = test_amount;
    }

    public String getPreparationInstructions() {
        return preparationInstructions;
    }

    public void setPreparationInstructions(String preparationInstructions) {
        this.preparationInstructions = preparationInstructions;
    }

    public List<LabAppointment> getLabAppointments() {
        return labAppointments;
    }

    public void setLabAppointments(List<LabAppointment> labAppointments) {
        this.labAppointments = labAppointments;
    }
}
