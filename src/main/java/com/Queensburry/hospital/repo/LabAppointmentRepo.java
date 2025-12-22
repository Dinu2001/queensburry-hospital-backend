package com.Queensburry.hospital.repo;

import com.Queensburry.hospital.entity.LabAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface LabAppointmentRepo extends JpaRepository<LabAppointment,String> {

    @Query(value = "SELECT lab_appointment_id FROM lab_appointment ORDER BY lab_appointment_id DESC LIMIT 1", nativeQuery = true)
    String getLastAppointmentId();

}
