package com.Queensburry.hospital.dtos.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class DoctorAvailabilityResponseDto {

    private Long id;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private int maxPatients;
    private int bookedPatients;

    public DoctorAvailabilityResponseDto() {
    }

    public DoctorAvailabilityResponseDto(Long id, String day, LocalTime startTime, LocalTime endTime, int maxPatients, int bookedPatients) {
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxPatients = maxPatients;
        this.bookedPatients = bookedPatients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public int getMaxPatients() {
        return maxPatients;
    }

    public void setMaxPatients(int maxPatients) {
        this.maxPatients = maxPatients;
    }

    public int getBookedPatients() {
        return bookedPatients;
    }

    public void setBookedPatients(int bookedPatients) {
        this.bookedPatients = bookedPatients;
    }
}
