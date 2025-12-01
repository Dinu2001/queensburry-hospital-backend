package com.Queensburry.hospital.dtos.request;

import com.Queensburry.hospital.entity.Doctor;
import com.Queensburry.hospital.entity.Patient;


import java.sql.Date;
import java.util.UUID;

public class DoctorAppointmentRequestDto {
    private String appointmentId;
    private String appointment_type;
    private Date appointment_date;
    private String reason;
    private String status;
    private Boolean payment_status;
    private Boolean email_sent;
    private String doctorId;
    private String patientId;

    public DoctorAppointmentRequestDto() {
    }

    public DoctorAppointmentRequestDto(String appointmentId, String appointment_type, Date appointment_date, String reason, String status, Boolean payment_status, Boolean email_sent, String doctorId, String patientId) {
        this.appointmentId = appointmentId;
        this.appointment_type = appointment_type;
        this.appointment_date = appointment_date;
        this.reason = reason;
        this.status = status;
        this.payment_status = payment_status;
        this.email_sent = email_sent;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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



}
