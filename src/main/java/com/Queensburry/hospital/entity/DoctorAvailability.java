package com.Queensburry.hospital.entity;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "doctor_available_schedule")
public class DoctorAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek day;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

//    @Column(nullable = false)
//    private int maxPatients;

//    @Column(nullable = false)
//    private int bookedPatients = 0;

    @Column(nullable = false)
    private LocalDate availableDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    public DoctorAvailability() {}

//    public DoctorAvailability(DayOfWeek day, LocalTime startTime, LocalTime endTime,
//                              int maxPatients, Doctor doctor, LocalDate availableDate) {
//        this.day = day;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.maxPatients = maxPatients;
//        this.bookedPatients = 0;
//        this.doctor = doctor;
//        this.availableDate = availableDate;
//    }


    public DoctorAvailability(Long id, DayOfWeek day, LocalTime startTime, LocalTime endTime, LocalDate availableDate, Doctor doctor) {
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableDate = availableDate;
        this.doctor = doctor;
    }

    // Getters & setters
    public Long getId() { return id; }
    public DayOfWeek getDay() { return day; }
    public void setDay(DayOfWeek day) { this.day = day; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
//    public int getMaxPatients() { return maxPatients; }
//    public void setMaxPatients(int maxPatients) { this.maxPatients = maxPatients; }
//    public int getBookedPatients() { return bookedPatients; }
//    public void setBookedPatients(int bookedPatients) { this.bookedPatients = bookedPatients; }
    public LocalDate getAvailableDate() { return availableDate; }
    public void setAvailableDate(LocalDate availableDate) { this.availableDate = availableDate; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}
