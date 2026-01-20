package com.Queensburry.hospital.repo;

import com.Queensburry.hospital.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@EnableJpaRepositories
@Repository
public interface PaymentRepo extends JpaRepository<Payment, UUID> {
}
