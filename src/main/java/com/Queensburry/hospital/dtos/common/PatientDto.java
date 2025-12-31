package com.Queensburry.hospital.dtos.common;

public class PatientDto {
    private String patientId;

    public PatientDto() {
    }

    public PatientDto(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
