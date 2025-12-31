package com.Queensburry.hospital.dtos.common;

import java.time.LocalTime;

public class TimeSlotDto {
    private LocalTime startTime;
    private LocalTime endTime;
    private int maxPatients;

    public TimeSlotDto() {
    }

    public TimeSlotDto(LocalTime startTime, LocalTime endTime, int maxPatients) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxPatients = maxPatients;
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
