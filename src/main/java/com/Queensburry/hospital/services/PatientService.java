package com.Queensburry.hospital.services;

import com.Queensburry.hospital.dtos.request.PatientRegistrationDto;
import com.Queensburry.hospital.dtos.response.PatientResponseDto;
import com.Queensburry.hospital.dtos.response.UserResponseDto;
import com.Queensburry.hospital.entity.Patient;
import com.Queensburry.hospital.entity.User;
import com.Queensburry.hospital.repo.PatientRepo;
import com.Queensburry.hospital.repo.UserRepo;
import com.Queensburry.hospital.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private UserService userService;


    public String generateOneAfterOne(){
        String lastId = patientRepo.getLastPatientId();
        if (lastId == null) {
            return "P001";
        }
        int number = Integer.parseInt(lastId.substring(1));
        number++;
        return String.format("P%03d", number);
    }


    @Autowired
    private PasswordEncoder passwordEncoder;


    public String savePatient(PatientRegistrationDto patientRegistrationDto) {
        try {
            LocalDate localDate = LocalDate.now();
            String id = generateOneAfterOne();

            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            User user = new User(
                    userService.generateUserIdOneAfterOne(),
                    patientRegistrationDto.getFirstName(),
                    patientRegistrationDto.getLastName(),
                    patientRegistrationDto.getEmail(),
                    "PATIENT",
                    "ACTIVATE",
                    passwordEncoder.encode(patientRegistrationDto.getPassword()),
                    date
            );
            User savedUser = userRepo.save(user);

            Patient patient = new Patient(
                    id,
                    patientRegistrationDto.getAge(),
                    patientRegistrationDto.getGender(),
                    patientRegistrationDto.getAddress(),
                    patientRegistrationDto.getPhoneNumber(),
                    savedUser
            );
            Patient savedPatient = patientRepo.save(patient);


            Map<String, Object> vars = Map.of(
                    "patientName", savedUser.getFirstName() + " " + savedUser.getLastName(),
                    "patientId", savedPatient.getPatientId(),
                    "registrationDate", localDate.toString(),
                    "clinicName", "Queensburry Hospital",
                    "clinicDomain", "queensburryhospital.com",
                    "loginUrl", "https://queensburryhospital.com/patient-login",
                    "year", "2025"
            );

            emailSender.sendRegistrationEmail(savedUser.getEmail(), vars);

            return "user saved successfully " + savedPatient.getPatientId();

        } catch (Exception e) {
            e.printStackTrace();
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

    public String getPatientId(String userId) {
        try{
            Optional<User> user = userRepo.findById(userId);
            if(user != null){
                return user.get().getPatient().getPatientId();
            }
            else{
                return null;
            }


        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<PatientResponseDto> getPatientNameAndId(String userName) {
        List<PatientResponseDto> result = new ArrayList<>();
        try {
            // Assuming searchByName returns List<User>
            List<User> users = userRepo.searchByName(userName);

            if (users != null && !users.isEmpty()) {
                for (User user : users) {
                    Optional<Patient> patientOpt = Optional.ofNullable(user.getPatient());
                    if (patientOpt.isPresent()) {
                        Patient patient = patientOpt.get();
                        PatientResponseDto dto = new PatientResponseDto();
                        dto.setPatientId(patient.getPatientId());
                        dto.setFirstName(user.getFirstName());
                        dto.setLastName(user.getLastName());
                        dto.setEmail(user.getEmail());
                        dto.setAge(patient.getAge());
                        dto.setGender(patient.getGender());
                        dto.setAddress(patient.getAddress());
                        dto.setPhoneNumber(patient.getPhoneNumber());
                        result.add(dto);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error fetching patients: " + e);
        }
        return result;
    }



    public PatientResponseDto getUserDetailsUsingId(String id) {

        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));

        User user = patient.getUser();

        if (user == null) {
            throw new RuntimeException("User not linked with patient ID: " + id);
        }

        return new PatientResponseDto(
                patient.getPatientId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                patient.getAge(),
                patient.getGender(),
                patient.getAddress(),
                patient.getPhoneNumber()
        );
    }


}
