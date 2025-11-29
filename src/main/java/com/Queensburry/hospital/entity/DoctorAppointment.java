package com.Queensburry.hospital.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "doctor_appointment")
public class DoctorAppointment {
    @Id
    @GeneratedValue
    private UUID appointmentId;

    private String appointment_type;
    private Date appointment_date;

    private String reason;
    private String status;
    private Boolean payment_status;

    private Boolean email_sent;



    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;


    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "doctorAppointment", cascade = CascadeType.ALL)
    private List<Payment> payments;

    public DoctorAppointment() {
    }

    public DoctorAppointment(UUID appointmentId, String appointment_type, Date appointment_date, String reason, String status, Boolean payment_status, Boolean email_sent, Doctor doctor, Patient patient, List<Payment> payments) {
        this.appointmentId = appointmentId;
        this.appointment_type = appointment_type;
        this.appointment_date = appointment_date;
        this.reason = reason;
        this.status = status;
        this.payment_status = payment_status;
        this.email_sent = email_sent;
        this.doctor = doctor;
        this.patient = patient;
        this.payments = payments;
    }

    public UUID getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(UUID appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointment_type() {
        return appointment_type;
    }

    public void setAppointment_type(String appointment_type) {
        this.appointment_type = appointment_type;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public Boolean getEmail_sent() {
        return email_sent;
    }

    public void setEmail_sent(Boolean email_sent) {
        this.email_sent = email_sent;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
