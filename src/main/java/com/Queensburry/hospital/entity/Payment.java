package com.Queensburry.hospital.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue
    private UUID payment_id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_appointment_id", nullable = false)
    private DoctorAppointment doctorAppointment;

    private Double amount;
    private String paymentMethod;

    private Date paymentDate;
    private Time paymentTime;


    //    @ManyToOne
//    @JoinColumn(name = "lab_appointment_id", nullable = true)
//    private LabAppointment labAppointment;



    public Payment() {
    }

    public Payment(UUID payment_id, Patient patient, DoctorAppointment doctorAppointment, Double amount, String paymentMethod, Date paymentDate, Time paymentTime) {
        this.payment_id = payment_id;
        this.patient = patient;
        this.doctorAppointment = doctorAppointment;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
    }

    public UUID getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(UUID payment_id) {
        this.payment_id = payment_id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public DoctorAppointment getDoctorAppointment() {
        return doctorAppointment;
    }

    public void setDoctorAppointment(DoctorAppointment doctorAppointment) {
        this.doctorAppointment = doctorAppointment;
    }



    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Time getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Time paymentTime) {
        this.paymentTime = paymentTime;
    }
}
