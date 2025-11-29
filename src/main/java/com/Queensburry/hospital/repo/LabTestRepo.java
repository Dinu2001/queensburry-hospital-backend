package com.Queensburry.hospital.repo;

import com.Queensburry.hospital.entity.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface LabTestRepo extends JpaRepository<LabTest,String> {
}
