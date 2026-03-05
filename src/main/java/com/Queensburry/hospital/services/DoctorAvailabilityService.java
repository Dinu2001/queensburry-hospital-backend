package com.Queensburry.hospital.services;


import com.Queensburry.hospital.dtos.request.DoctorAvailabilityRequestDto;
import com.Queensburry.hospital.dtos.response.DateAndTimeResponseDto;
import com.Queensburry.hospital.dtos.response.DoctorAvailabilityResponseDto;
import com.Queensburry.hospital.dtos.response.DoctorDayResponseDto;
import com.Queensburry.hospital.entity.Doctor;
import com.Queensburry.hospital.entity.DoctorAvailability;
import com.Queensburry.hospital.repo.DoctorAvailabilityRepo;
import com.Queensburry.hospital.repo.DoctorRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorAvailabilityService {

    private final DoctorAvailabilityRepo availabilityRepo;
    private final DoctorRepo doctorRepo;

    public DoctorAvailabilityService(DoctorAvailabilityRepo availabilityRepo, DoctorRepo doctorRepo) {
        this.availabilityRepo = availabilityRepo;
        this.doctorRepo = doctorRepo;
    }

    @Transactional
    public Long addAvailability(DoctorAvailabilityRequestDto dto) {

        if (dto.getDoctorId() == null || dto.getDoctorId().isBlank()) {
            throw new RuntimeException("Doctor ID cannot be empty");
        }

        // Check doctor exists
        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor with ID " + dto.getDoctorId() + " not found"));

        List<DoctorAvailability> schedules = new ArrayList<>();

        for (DayOfWeek day : dto.getDays()) {
            for (var slot : dto.getTimeSlots()) {
                DoctorAvailability availability = new DoctorAvailability();
                availability.setDoctor(doctor);
                availability.setDay(day);
                availability.setStartTime(slot.getStartTime());
                availability.setEndTime(slot.getEndTime());
//                availability.setMaxPatients(slot.getMaxPatients());
//                availability.setBookedPatients(0);
                availability.setAvailableDate(LocalDate.now());
                schedules.add(availability);
            }
        }

        List<DoctorAvailability> saved = availabilityRepo.saveAll(schedules);
        return saved.isEmpty() ? null : saved.get(0).getId();
    }

    public List<DoctorAvailabilityResponseDto> getByDoctor(String doctorId) {
        return availabilityRepo.findByDoctor_DoctorId(doctorId)
                .stream()
                .map(a -> new DoctorAvailabilityResponseDto(
                        a.getId(),
                        a.getDay().toString(),
                        a.getStartTime(),
                        a.getEndTime()


                ))
                .toList();
    }

//    @Transactional
//    public void updateMaxPatients(Long id, int maxPatients) {
//        DoctorAvailability availability = availabilityRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Schedule not found"));
//        availability.setMaxPatients(maxPatients);
//    }

    @Transactional
    public void deleteAvailability(Long id) {
        availabilityRepo.deleteById(id);
    }


    public List<DoctorAvailabilityResponseDto> getByDoctorAndDay(String doctorId, String dayStr) {
        DayOfWeek day = DayOfWeek.valueOf(dayStr.toUpperCase());
        List<DoctorAvailability> list = availabilityRepo.findByDoctor_DoctorIdAndDay(doctorId, day);

        return list.stream()
                .map(a -> new DoctorAvailabilityResponseDto(
                        a.getId(),
                        a.getDay().toString(),
                        a.getStartTime(),
                        a.getEndTime()
                ))
                .toList();
    }

    public List<DoctorDayResponseDto> getAllDoctorDetails() {
        try {
            List<DoctorDayResponseDto> dtos = new ArrayList<>();
            List<DoctorAvailability> list = availabilityRepo.findAll();

            for (DoctorAvailability availability : list) {

                Doctor doctor = availability.getDoctor();

                DoctorDayResponseDto dto = new DoctorDayResponseDto();
                dto.setDoctorId(doctor.getDoctorId());
                dto.setDoctorFirstName(doctor.getUser().getFirstName());
                dto.setDoctorLastName(doctor.getUser().getLastName());
                dto.setSpecialization(doctor.getSpecification());
                dto.setEmail(doctor.getUser().getEmail());

                dto.setStartTime(availability.getStartTime());
                dto.setEndTime(availability.getEndTime());
                dto.setDay(availability.getDay().name());

                dtos.add(dto);
                System.out.println(dto);
            }

            System.out.println(dtos);
            return dtos;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public List<DateAndTimeResponseDto> getDateAndTime(String doctorId) {
        // Fetch all availabilities for the given doctor
        List<DoctorAvailability> availabilities = availabilityRepo.findByDoctor_DoctorId(doctorId);

        // Map to DateAndTimeResponseDto
        List<DateAndTimeResponseDto> result = availabilities.stream()
                .map(a -> {
                    DateAndTimeResponseDto dto = new DateAndTimeResponseDto();
                    dto.setDay(a.getDay().name()); // Enum to string
                    dto.setStartTime(Time.valueOf(a.getStartTime())); // LocalTime → java.sql.Time
                    dto.setEndTime(Time.valueOf(a.getEndTime()));
                    return dto;
                })
                .collect(Collectors.toList());

        return result;
    }
}
