package com.Queensburry.hospital.dtos.response;

import java.sql.Date;
import java.util.UUID;

public class DoctorAppointmentResponseDto {
    private String appointmentId;
    private String appointment_type;
    private Date appointment_date;
    private String reason;
    private String status;
    private Boolean payment_status;
    private Boolean email_sent;
    private String doctorFirstName;
    private String doctorLastName;
    private String doctorId;
    private String patientId;
    private String patientFirstName;
    private String patientLastName;

    public DoctorAppointmentResponseDto() {
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

    public DoctorAppointmentResponseDto(String appointmentId, String appointment_type, Date appointment_date, String reason, String status, Boolean payment_status, Boolean email_sent, String doctorFirstName, String doctorLastName, String doctorId, String patientId, String patientFirstName, String patientLastName) {
        this.appointmentId = appointmentId;
        this.appointment_type = appointment_type;
        this.appointment_date = appointment_date;
        this.reason = reason;
        this.status = status;
        this.payment_status = payment_status;
        this.email_sent = email_sent;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
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

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }
}
