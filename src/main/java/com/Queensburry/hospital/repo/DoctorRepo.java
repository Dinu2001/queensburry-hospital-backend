package com.Queensburry.hospital.repo;

import com.Queensburry.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface DoctorRepo extends JpaRepository<Doctor,String> {

    @Query(value = "SELECT doctor_id FROM doctor ORDER BY doctor_id DESC LIMIT 1", nativeQuery = true)
    String getLastDoctorId();
}
