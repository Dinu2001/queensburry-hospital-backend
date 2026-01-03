package com.Queensburry.hospital.services;

import com.Queensburry.hospital.dtos.request.LabAppointmentRequestDto;
import com.Queensburry.hospital.dtos.response.LabAppointmentResponseDto;
import com.Queensburry.hospital.dtos.response.LabTestResponseDto;
import com.Queensburry.hospital.entity.LabAppointment;
import com.Queensburry.hospital.entity.LabTest;
import com.Queensburry.hospital.entity.Patient;
import com.Queensburry.hospital.entity.User;
import com.Queensburry.hospital.repo.LabAppointmentRepo;
import com.Queensburry.hospital.repo.LabTestRepo;
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

    @Autowired
    private LabTestRepo labTestRepo;






    public String saveLabAppointment(LabAppointmentRequestDto dto) {
        if (dto == null) {
            System.out.println("LabAppointmentRequestDto is null");
            return null;
        }

        if (dto.getPatientDto() == null || dto.getPatientDto().getPatientId() == null) {
            System.out.println("Patient ID is missing in the request");
            return null;
        }

        if (dto.getLabTestDto() == null || dto.getLabTestDto().getLabTestId() == null) {
            System.out.println("LabTest ID is missing in the request");
            return null;
        }

        try {
            // Fetch Patient
            Patient patient = patientRepo.findById(dto.getPatientDto().getPatientId())
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + dto.getPatientDto().getPatientId()));

            // Fetch LabTest
            LabTest labTest = labTestRepo.findById(dto.getLabTestDto().getLabTestId())
                    .orElseThrow(() -> new IllegalArgumentException("LabTest not found with ID: " + dto.getLabTestDto().getLabTestId()));

            // Create LabAppointment
            LabAppointment labAppointment = new LabAppointment(
                    generateOneAfterOne(),
                    dto.getAppointmentDate(),
                    dto.getAppointmentTime(),
                    dto.getStatus() != null ? dto.getStatus() : "Scheduled",
                    dto.getPayment_status() != null ? dto.getPayment_status() : true,
                    patient,
                    labTest
            );

            // Save appointment
            LabAppointment saved = labAppointmentRepo.save(labAppointment);

            // Send confirmation email if user exists
            User user = patient.getUser();
            if (user != null && user.getEmail() != null) {
                try {
                    String htmlContent = new String(Files.readAllBytes(
                            Paths.get("src/main/resources/templates/lab_appointment_email.html")
                    ));
                    htmlContent = htmlContent.replace("{{name}}", user.getFirstName() != null ? user.getFirstName() : "")
                            .replace("{{appointmentId}}", saved.getLabAppointmentId())
                            .replace("{{appointmentDate}}", saved.getAppointmentDate().toString())
                            .replace("{{appointmentTime}}", saved.getAppointmentTime().toString())
                            .replace("{{labTest}}", saved.getLabTest().getTestName());

                    emailSender.sendLabAppointmentEmail(user.getEmail(), "Lab Appointment Confirmation", htmlContent);
                } catch (Exception e) {
                    System.out.println("Error sending email: " + e.getMessage());
                }
            }

            return saved.getLabAppointmentId();

        } catch (Exception e) {
            System.out.println("Error saving lab appointment: " + e.getMessage());
            e.printStackTrace();
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



    public String updateLabAppointmentStatus(String labId) {
        LabAppointment appointment = labAppointmentRepo.findById(labId)
                .orElseThrow(() -> new RuntimeException("Lab appointment not found"));
        appointment.setStatus("COMPLETED");
        labAppointmentRepo.save(appointment);

        return "Updated successfully";
    }

    public List<LabAppointmentResponseDto> getAllByUserId(String patientId) {

        try{
            List<LabAppointmentResponseDto> labAppointmentResponseDtos = new ArrayList<>();
            List<LabAppointment> labAppointments = labAppointmentRepo.findAllByPatient_PatientId(patientId);

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
