package com.Queensburry.hospital.services;

import com.Queensburry.hospital.dtos.request.DoctorAvailabilityRequestDto;
import com.Queensburry.hospital.dtos.response.DoctorAvailabilityResponseDto;
import com.Queensburry.hospital.entity.Doctor;
import com.Queensburry.hospital.entity.DoctorAvailability;
import com.Queensburry.hospital.repo.DoctorAvailabilityRepo;
import com.Queensburry.hospital.repo.DoctorRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorAvailabilityService {

    private final DoctorAvailabilityRepo availabilityRepo;
    private final DoctorRepo doctorRepo;

    public DoctorAvailabilityService(
            DoctorAvailabilityRepo availabilityRepo,
            DoctorRepo doctorRepo
    ) {
        this.availabilityRepo = availabilityRepo;
        this.doctorRepo = doctorRepo;
    }

    @Transactional
    public Long addAvailability(DoctorAvailabilityRequestDto dto) {

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        DoctorAvailability availability = new DoctorAvailability();
        availability.setAvailableDate(dto.getAvailableDate());
        availability.setStartTime(dto.getStartTime());
        availability.setEndTime(dto.getEndTime());
        availability.setMaxPatients(dto.getMaxPatients());
        availability.setBookedPatients(0);
        availability.setDoctor(doctor);

        return availabilityRepo.save(availability).getId();
    }

    public List<DoctorAvailabilityResponseDto> getByDoctor(String doctorId) {

        List<DoctorAvailability> list =
                availabilityRepo.findByDoctor_DoctorId(doctorId);

        return list.stream()
                .map(a -> new DoctorAvailabilityResponseDto(
                        a.getId(),
                        a.getAvailableDate(),
                        a.getStartTime(),
                        a.getEndTime(),
                        a.getMaxPatients(),
                        a.getBookedPatients()
                ))
                .toList();
    }


    @Transactional
    public void updateMaxPatients(Long id, int maxPatients) {
        DoctorAvailability availability = availabilityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        availability.setMaxPatients(maxPatients);
    }

    @Transactional
    public void deleteAvailability(Long id) {
        availabilityRepo.deleteById(id);
    }
}
