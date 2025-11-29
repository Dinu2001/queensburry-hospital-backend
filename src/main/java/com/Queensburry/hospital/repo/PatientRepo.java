package com.Queensburry.hospital.repo;

import com.Queensburry.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepo extends JpaRepository<Patient,String> {

    @Query(value = "SELECT patient_id FROM patient ORDER BY patient_id DESC LIMIT 1", nativeQuery = true)
    String getLastPatientId();

}
