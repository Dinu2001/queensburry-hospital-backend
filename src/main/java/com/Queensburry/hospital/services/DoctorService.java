package com.Queensburry.hospital.services;

import com.Queensburry.hospital.dtos.request.DoctorRegisterDto;
import com.Queensburry.hospital.entity.Doctor;
import com.Queensburry.hospital.entity.Patient;
import com.Queensburry.hospital.entity.User;
import com.Queensburry.hospital.repo.DoctorRepo;
import com.Queensburry.hospital.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class DoctorService {

   @Autowired
    private DoctorRepo doctorRepo;

   @Autowired
   private UserRepo userRepo;

    public String generateOneAfterOne(){
        String lastId = doctorRepo.getLastDoctorId();
        if (lastId == null) {
            return "D001";
        }
        int number = Integer.parseInt(lastId.substring(2));
        number++;
        return String.format("D%03d", number);
    }



    public String saveDoctor(DoctorRegisterDto doctorRegisterDto) {
        try {
            LocalDate localDate = LocalDate.now();
            String doctorId = generateOneAfterOne();

            Date createdDate = Date.from(localDate.atStartOfDay(
                    ZoneId.systemDefault()).toInstant());

            User user = new User(
                    null,
                    doctorRegisterDto.getFirstName(),
                    doctorRegisterDto.getLastName(),
                    doctorRegisterDto.getEmail(),
                    "DOCTOR",
                    "ACTIVATE",
                    doctorRegisterDto.getPassword(),
                    createdDate
            );

            User savedUser = userRepo.save(user);
            Doctor doctor = new Doctor();
            doctor.setDoctorId(doctorId);
            doctor.setSpecification(doctorRegisterDto.getSpecification());
            doctor.setRegistrationNumber(doctorRegisterDto.getRegistrationNumber());
            doctor.setPhoneNumber(doctorRegisterDto.getPhoneNumber());
            doctor.setAvailableDates(doctorRegisterDto.getAvailableDates());
            doctor.setUser(savedUser);

            Doctor savedDoctor = doctorRepo.save(doctor);

            return "Doctor saved successfully: " + savedDoctor.getDoctorId();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

}
