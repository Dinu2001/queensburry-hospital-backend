package com.Queensburry.hospital.dtos.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class DoctorAvailabilityResponseDto {

    private Long id;
    private LocalDate availableDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int maxPatients;
    private int bookedPatients;

    public DoctorAvailabilityResponseDto(
            Long id,
            LocalDate availableDate,
            LocalTime startTime,
            LocalTime endTime,
            int maxPatients,
            int bookedPatients
    ) {
        this.id = id;
        this.availableDate = availableDate;
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

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
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
