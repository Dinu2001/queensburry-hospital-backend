package com.Queensburry.hospital.repo;

import com.Queensburry.hospital.entity.Doctor;
import com.Queensburry.hospital.entity.DoctorAppointment;
import com.Queensburry.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
@EnableJpaRepositories
public interface DoctorAppointmentRepo extends JpaRepository<DoctorAppointment, String> {
    List<DoctorAppointment> findByPatient(Patient patient);

    List<DoctorAppointment> findByDoctor(Doctor doctor);

    @Query(value = "SELECT  appointment_id FROM doctor_appointment ORDER BY  appointment_id DESC LIMIT 1", nativeQuery = true)
    String getLastDoctorAppointmentId();
}
