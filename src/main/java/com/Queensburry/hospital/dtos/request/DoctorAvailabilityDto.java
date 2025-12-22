package com.Queensburry.hospital.dtos.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class DoctorAvailabilityDto {
    private LocalDate availableDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int maxPatients;

    public DoctorAvailabilityDto(LocalDate availableDate, LocalTime startTime, LocalTime endTime, int maxPatients) {
        this.availableDate = availableDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxPatients = maxPatients;
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
}
