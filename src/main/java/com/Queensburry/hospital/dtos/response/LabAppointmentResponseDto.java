package com.Queensburry.hospital.dtos.response;

import com.Queensburry.hospital.entity.LabTest;
import com.Queensburry.hospital.entity.Patient;

import java.sql.Date;
import java.sql.Time;

public class LabAppointmentResponseDto {
    private String labAppointmentId;
    private Date appointmentDate;
    private Time appointmentTime;
    private String status;
    private Boolean payment_status;
    private String patientName;
    private String patientEmail;
    private String labTestName;

    public String getPatientName() {
        return patientName;
    }

    public LabAppointmentResponseDto(String labAppointmentId, Date appointmentDate, Time appointmentTime, String status, Boolean payment_status, String patientName, String patientEmail, String labTestName) {
        this.labAppointmentId = labAppointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.payment_status = payment_status;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.labTestName = labTestName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getLabTestName() {
        return labTestName;
    }

    public void setLabTestName(String labTestName) {
        this.labTestName = labTestName;
    }

    public LabAppointmentResponseDto() {
    }



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




}
