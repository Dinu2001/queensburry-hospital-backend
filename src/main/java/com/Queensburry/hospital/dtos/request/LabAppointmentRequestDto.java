package com.Queensburry.hospital.dtos.request;
import com.Queensburry.hospital.dtos.common.LabTestDto;
import com.Queensburry.hospital.dtos.common.PatientDto;
import com.Queensburry.hospital.entity.LabTest;
import com.Queensburry.hospital.entity.Patient;
import java.sql.Date;
import java.sql.Time;

public class LabAppointmentRequestDto {

    private String labAppointmentId;
    private Date appointmentDate;
    private Time appointmentTime;
    private String status;
    private Boolean payment_status;
    private PatientDto patientDto;
    private LabTestDto labTestDto;

    public LabAppointmentRequestDto() {
    }

    public LabAppointmentRequestDto(String labAppointmentId, Date appointmentDate, Time appointmentTime, String status, Boolean payment_status, PatientDto patientDto, LabTestDto labTestDto) {
        this.labAppointmentId = labAppointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.payment_status = payment_status;
        this.patientDto = patientDto;
        this.labTestDto = labTestDto;
    }

    public PatientDto getPatientDto() {
        return patientDto;
    }

    public void setPatientDto(PatientDto patientDto) {
        this.patientDto = patientDto;
    }

    public LabTestDto getLabTestDto() {
        return labTestDto;
    }

    public void setLabTestDto(LabTestDto labTestDto) {
        this.labTestDto = labTestDto;
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
