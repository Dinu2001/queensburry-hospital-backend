package com.Queensburry.hospital.services;

import com.Queensburry.hospital.dtos.request.PatientRegistrationDto;
import com.Queensburry.hospital.dtos.response.PatientResponseDto;
import com.Queensburry.hospital.dtos.response.UserResponseDto;
import com.Queensburry.hospital.entity.Patient;
import com.Queensburry.hospital.entity.User;
import com.Queensburry.hospital.repo.PatientRepo;
import com.Queensburry.hospital.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private UserRepo userRepo;

    public String generateOneAfterOne(){
        String lastId = patientRepo.getLastPatientId();
        if (lastId == null) {
            return "P001";
        }
        int number = Integer.parseInt(lastId.substring(1));
        number++;
        return String.format("P%03d", number);
    }



    public String savePatient(PatientRegistrationDto patientRegistrationDto) {
        try{
            LocalDate localDate = LocalDate.now();
            String id =generateOneAfterOne();

            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            User user = new User(
                   null ,patientRegistrationDto.getFirstName(),patientRegistrationDto.getLastName(),patientRegistrationDto.getEmail(),
                    "PATIENT","ACTIVATE",patientRegistrationDto.getPassword(),date
            );
            User saveduser = userRepo.save(user);

            Patient patient = new Patient(
                    id,patientRegistrationDto.getAge(),patientRegistrationDto.getGender(),patientRegistrationDto.getAddress(),
                    patientRegistrationDto.getPhoneNumber(),saveduser
            );
            Patient savedPatient = patientRepo.save(patient);
            return "user saved successfully "+ savedPatient.getPatientId();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<PatientResponseDto> getAllPatients() {
        List<Patient> patients = patientRepo.findAll();

        return patients.stream().map(patient ->
                new PatientResponseDto(
                        patient.getPatientId(),
                        patient.getUser().getFirstName(),
                        patient.getUser().getLastName(),
                        patient.getUser().getEmail(),
                        patient.getAge(),
                        patient.getGender(),
                        patient.getAddress(),
                        patient.getPhoneNumber()
                )
        ).collect(Collectors.toList());
    }
}
