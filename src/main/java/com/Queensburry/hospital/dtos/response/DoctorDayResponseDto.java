package com.Queensburry.hospital.dtos.response;

import java.time.LocalTime;

public class DoctorDayResponseDto {
    private String doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private String specialization;
    private String email;
    private LocalTime startTime;
    private LocalTime endTime;
    private String day;


    public DoctorDayResponseDto() {
    }

    public DoctorDayResponseDto(String doctorId, String doctorFirstName, String doctorLastName, String specialization, String email, LocalTime startTime, LocalTime endTime, String day) {
        this.doctorId = doctorId;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.specialization = specialization;
        this.email = email;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }


    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
