package com.Queensburry.hospital.dtos.request;

import com.Queensburry.hospital.entity.DoctorAppointment;
import com.Queensburry.hospital.entity.Patient;


import java.sql.Date;
import java.sql.Time;

public class PaymentRequestDto {

    private String patientId;
    private String appointmentId;
    private Double amount;
    private String paymentMethod;
    private Date paymentDate;
    private Time paymentTime;

    public PaymentRequestDto() {
    }

    public PaymentRequestDto(String patientId, String appointmentId, Double amount, String paymentMethod, Date paymentDate, Time paymentTime) {
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
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
