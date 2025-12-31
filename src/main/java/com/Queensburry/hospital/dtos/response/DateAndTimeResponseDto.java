package com.Queensburry.hospital.dtos.response;

import java.sql.Time;

public class DateAndTimeResponseDto {
    private String day;
    private Time startTime;
    private Time endTime;

    public DateAndTimeResponseDto() {
    }

    public DateAndTimeResponseDto(String day, Time startTime, Time endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
