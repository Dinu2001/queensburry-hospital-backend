package com.Queensburry.hospital.services;

import com.Queensburry.hospital.dtos.request.LabAppointmentRequestDto;
import com.Queensburry.hospital.dtos.response.LabAppointmentResponseDto;
import com.Queensburry.hospital.dtos.response.LabTestResponseDto;
import com.Queensburry.hospital.entity.LabAppointment;
import com.Queensburry.hospital.entity.Patient;
import com.Queensburry.hospital.entity.User;
import com.Queensburry.hospital.repo.LabAppointmentRepo;
import com.Queensburry.hospital.repo.PatientRepo;
import com.Queensburry.hospital.repo.UserRepo;
import com.Queensburry.hospital.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LabAppointmentService {
    @Autowired
    private LabAppointmentRepo labAppointmentRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private UserRepo userRepo;

    public String generateOneAfterOne() {
        String lastId = labAppointmentRepo.getLastAppointmentId();
        if (lastId == null) {
            return "APP001";
        }
        int number = Integer.parseInt(lastId.substring(3));
        number++;
        return String.format("APP%03d", number);
    }



    @Autowired
    private EmailSender emailSender;

    public String saveLabAppointment(LabAppointmentRequestDto labAppointmentRequestDto) {
        try {
            LabAppointment labAppointment = new LabAppointment(
                    generateOneAfterOne(),
                    labAppointmentRequestDto.getAppointmentDate(),
                    labAppointmentRequestDto.getAppointmentTime(),
                    labAppointmentRequestDto.getStatus(),
                    labAppointmentRequestDto.getPayment_status(),
                    labAppointmentRequestDto.getPatient(),
                    labAppointmentRequestDto.getLabTest()
            );

            LabAppointment saved = labAppointmentRepo.save(labAppointment);

            if (saved != null) {
                Optional<Patient> patientOpt = patientRepo.findById(saved.getPatient().getPatientId());
                if (patientOpt.isPresent()) {
                    Optional<User> userOpt = userRepo.findById(patientOpt.get().getUser().getUserId());
                    if (userOpt.isPresent()) {
                        User user = userOpt.get();
                        String email = user.getEmail();


                        String htmlContent = new String(
                                Files.readAllBytes(Paths.get("src/main/resources/templates/lab_appointment_email.html"))
                        );
                        htmlContent = htmlContent.replace("{{name}}", user.getFirstName())
                                .replace("{{appointmentId}}", saved.getLabAppointmentId())
                                .replace("{{appointmentDate}}", saved.getAppointmentDate().toString())
                                .replace("{{appointmentTime}}", saved.getAppointmentTime().toString())
                                .replace("{{labTest}}", saved.getLabTest().getTestName());

                        emailSender.sendLabAppointmentEmail(email, "Lab Appointment Confirmation", htmlContent);
                    }
                }
            }

            return saved.getLabAppointmentId();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<LabAppointmentResponseDto> getAll() {
        try{
            List<LabAppointmentResponseDto> labAppointmentResponseDtos = new ArrayList<>();
            List<LabAppointment> labAppointments = labAppointmentRepo.findAll();

            for(LabAppointment labAppointment:labAppointments){
                LabAppointmentResponseDto labAppointmentResponseDto = new LabAppointmentResponseDto(
                        labAppointment.getLabAppointmentId(),labAppointment.getAppointmentDate(),labAppointment.getAppointmentTime(),
                        labAppointment.getStatus(),labAppointment.getPayment_status(),labAppointment.getPatient().getUser().getFirstName(),labAppointment.getPatient().getUser().getEmail(),labAppointment.getLabTest().getTestName()
                );

                labAppointmentResponseDtos.add(labAppointmentResponseDto);
            }
            return labAppointmentResponseDtos;

        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
