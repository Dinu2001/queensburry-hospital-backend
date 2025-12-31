package com.Queensburry.hospital.dtos.request;

import com.Queensburry.hospital.dtos.common.TimeSlotDto;

import java.time.DayOfWeek;
import java.util.List;

public class DoctorAvailabilityRequestDto {

    private String doctorId;
    private List<DayOfWeek> days;
    private List<TimeSlotDto> timeSlots;

    public DoctorAvailabilityRequestDto() {
    }

    public DoctorAvailabilityRequestDto(String doctorId, List<DayOfWeek> days, List<TimeSlotDto> timeSlots) {
        this.doctorId = doctorId;
        this.days = days;
        this.timeSlots = timeSlots;
    }

    public List<DayOfWeek> getDays() {
        return days;
    }

    public void setDays(List<DayOfWeek> days) {
        this.days = days;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }



    public List<TimeSlotDto> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlotDto> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
