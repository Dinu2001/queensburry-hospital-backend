package com.Queensburry.hospital.repo;

import com.Queensburry.hospital.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DoctorAvailabilityRepo extends JpaRepository<DoctorAvailability, Long> {

    List<DoctorAvailability> findByDoctor_DoctorId(String doctorId);

    List<DoctorAvailability> findByDoctor_DoctorIdAndAvailableDate(
            String doctorId, LocalDate date
    );
}
