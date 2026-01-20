package com.Queensburry.hospital.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "lab_appointment")
public class LabAppointment {

    @Id
    @Column(name = "lab_appointment_id")
    private String labAppointmentId;

    private Date appointmentDate;
    private Time appointmentTime;

    private String status;
    private Boolean payment_status;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "lab_id", nullable = false)
    private LabTest labTest;

//    @OneToMany(mappedBy = "labAppointment", cascade = CascadeType.ALL)
//    private List<Payment> payments;

    public LabAppointment() {
    }

    public LabAppointment(String labAppointmentId, Date appointmentDate, Time appointmentTime, String status, Boolean payment_status, Patient patient, LabTest labTest) {
        this.labAppointmentId = labAppointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.payment_status = payment_status;
        this.patient = patient;
        this.labTest = labTest;
    }

//    public LabAppointment(String labAppointmentId, Date appointmentDate, Time appointmentTime, String status, Boolean payment_status, Patient patient, LabTest labTest, List<Payment> payments) {
//        this.labAppointmentId = labAppointmentId;
//        this.appointmentDate = appointmentDate;
//        this.appointmentTime = appointmentTime;
//        this.status = status;
//        this.payment_status = payment_status;
//        this.patient = patient;
//        this.labTest = labTest;
//        this.payments = payments;
//    }


    public String getLabAppointmentId() {
        return labAppointmentId;
    }

    public void setLabAppointmentId(String labAppointmentId) {
        this.labAppointmentId = labAppointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(Boolean payment_status) {
        this.payment_status = payment_status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LabTest getLabTest() {
        return labTest;
    }

    public void setLabTest(LabTest labTest) {
        this.labTest = labTest;
    }

//    public List<Payment> getPayments() {
//        return payments;
//    }
//
//    public void setPayments(List<Payment> payments) {
//        this.payments = payments;
//    }
}
