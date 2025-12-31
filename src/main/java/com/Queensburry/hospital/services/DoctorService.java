package com.Queensburry.hospital.services;

import com.Queensburry.hospital.dtos.request.DoctorRegisterDto;
import com.Queensburry.hospital.dtos.response.DoctorResponseDto;
import com.Queensburry.hospital.entity.Doctor;
import com.Queensburry.hospital.entity.User;
import com.Queensburry.hospital.repo.DoctorRepo;
import com.Queensburry.hospital.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


@Service
public class DoctorService {

   @Autowired
    private DoctorRepo doctorRepo;

   @Autowired
   private UserRepo userRepo;

   @Autowired
   private UserService userService;

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

            Date createdDate = Date.from(
                    localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            );

            User user = new User(
                    userService.generateUserIdOneAfterOne(),
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
            doctor.setUser(savedUser);


//            List<DoctorAvailability> availabilityList = new ArrayList<>();

//            for (DoctorAvailabilityDto dto : doctorRegisterDto.getAvailabilities()) {
//
//                DoctorAvailability availability = new DoctorAvailability();
//                availability.setAvailableDate(dto.getAvailableDate());
//                availability.setStartTime(dto.getStartTime());
//                availability.setEndTime(dto.getEndTime());
//                availability.setMaxPatients(dto.getMaxPatients());
//                availability.setDoctor(doctor);
//
//                availabilityList.add(availability);
//            }
//
//            doctor.setAvailabilities(availabilityList);


            Doctor savedDoctor = doctorRepo.save(doctor);

            return "Doctor saved successfully: " + savedDoctor.getDoctorId();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();

        }
    }


    public List<DoctorResponseDto> getAllDoctor() {
        try{
            List<DoctorResponseDto> doctorResponseDtos = new ArrayList<>();
            List<Doctor> doctors = doctorRepo.findAll();
            if(doctors != null){
                for(Doctor doctor:doctors){
                    DoctorResponseDto doctorResponseDto = new DoctorResponseDto(
                            doctor.getDoctorId(),doctor.getUser().getFirstName(),doctor.getUser().getLastName(),
                            doctor.getSpecification()
                    );

                    doctorResponseDtos.add(doctorResponseDto);
                }
            }
            return doctorResponseDtos;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    public String getDoctorId(String userId) {
        try{
            Optional<User> user = userRepo.findById(userId);
            if(user != null){
                return user.get().getDoctor().getDoctorId();
            }
            else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    public DoctorResponseDto getDoctorDetailById(String id) {
        try{
            Doctor doctor = doctorRepo.findByDoctorId(id);
            DoctorResponseDto doctorResponseDto = new DoctorResponseDto(
                    doctor.getDoctorId(),doctor.getUser().getFirstName(),doctor.getUser().getLastName(),
                    doctor.getSpecification()
            );
            return doctorResponseDto;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
