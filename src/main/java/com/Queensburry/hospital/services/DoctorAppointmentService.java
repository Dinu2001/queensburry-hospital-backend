package com.Queensburry.hospital.services;

import com.Queensburry.hospital.dtos.request.DoctorAppointmentRequestDto;
import com.Queensburry.hospital.dtos.response.DoctorAppointmentResponseDto;
import com.Queensburry.hospital.entity.*;
import com.Queensburry.hospital.repo.DoctorAppointmentRepo;
import com.Queensburry.hospital.repo.DoctorRepo;
import com.Queensburry.hospital.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorAppointmentService {
    @Autowired
    private DoctorAppointmentRepo doctorAppointmentRepo;
    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;


    public String generateOneAfterOne(){
        String lastId = doctorAppointmentRepo.getLastDoctorAppointmentId();
        if (lastId == null) {
            return "A001";
        }
        int number = Integer.parseInt(lastId.substring(1));
        number++;
        return String.format("A%03d", number);
    }

    public String saveDoctorAppointment(DoctorAppointmentRequestDto dto) {
        try {
            Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));

            Patient patient = patientRepo.findById(dto.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));

            DoctorAppointment appointment = new DoctorAppointment(
                    generateOneAfterOne(),
                    dto.getAppointment_type(),
                    dto.getAppointment_date(),
                    dto.getReason(),
                    dto.getStatus(),
                    dto.getPayment_status(),
                    dto.getEmail_sent(),
                    doctor,
                    patient
            );

            doctorAppointmentRepo.save(appointment);

            return "saved successfully";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    private DoctorAppointmentResponseDto convertToDto(DoctorAppointment appointment) {
        DoctorAppointmentResponseDto dto = new DoctorAppointmentResponseDto();

        dto.setAppointmentId(appointment.getAppointmentId());
        dto.setAppointment_type(appointment.getAppointment_type());
        dto.setAppointment_date(appointment.getAppointment_date());
        dto.setReason(appointment.getReason());
        dto.setStatus(appointment.getStatus());
        dto.setPayment_status(appointment.getPayment_status());
        dto.setEmail_sent(appointment.getEmail_sent());

        dto.setDoctorFirstName(appointment.getDoctor().getUser().getFirstName());
        dto.setDoctorLastName(appointment.getDoctor().getUser().getLastName());
        dto.setDoctorId(appointment.getDoctor().getDoctorId());

        dto.setPatientId(appointment.getPatient().getPatientId());
        dto.setPatientFirstName(appointment.getPatient().getUser().getFirstName());
        dto.setPatientLastName(appointment.getPatient().getUser().getLastName());

        return dto;
    }



    public List<DoctorAppointmentResponseDto> getAllAppointments() {
        List<DoctorAppointment> appointments = doctorAppointmentRepo.findAll();

        return appointments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<DoctorAppointmentResponseDto> getDetailsByPatient(String id) {
        Optional<Patient> optionalPatient = patientRepo.findById(id);
        if (optionalPatient.isEmpty()) {
            return null;
        }

        Patient patient = optionalPatient.get();
        List<DoctorAppointment> appointments = doctorAppointmentRepo.findByPatient(patient);

        return appointments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<DoctorAppointmentResponseDto> getDetailsByDoctor(String id) {
        Optional<Doctor> optionalDoctor = doctorRepo.findById(id);
        if (optionalDoctor.isEmpty()) {
            return null;
        }

        Doctor doctor = optionalDoctor.get();
        List<DoctorAppointment> appointments = doctorAppointmentRepo.findByDoctor(doctor);

        return appointments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }




    public String updateDoctorAppointment(DoctorAppointmentRequestDto dto, String id) {

        Optional<DoctorAppointment> optionalAppointment = doctorAppointmentRepo.findById(id);
        if (optionalAppointment.isEmpty()) {
            return null;
        }
        DoctorAppointment appointment = optionalAppointment.get();


        Optional<Doctor> optionalDoctor = doctorRepo.findById(dto.getDoctorId());
        if (optionalDoctor.isEmpty()) {
            return null;
        }
        Doctor doctor = optionalDoctor.get();


        Optional<Patient> optionalPatient = patientRepo.findById(dto.getPatientId());
        if (optionalPatient.isEmpty()) {
            return null;
        }
        Patient patient = optionalPatient.get();

        appointment.setAppointment_type(dto.getAppointment_type());
        appointment.setAppointment_date(dto.getAppointment_date());
        appointment.setReason(dto.getReason());
        appointment.setStatus(dto.getStatus());
        appointment.setPayment_status(dto.getPayment_status());
        appointment.setEmail_sent(dto.getEmail_sent());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        // Save updated appointment
        doctorAppointmentRepo.save(appointment);

        return "Update successfully";
    }


    public String deleteById(String id) {
        try{
            Optional<DoctorAppointment> selected = doctorAppointmentRepo.findById(id);
            if(selected.isEmpty()){
                return null;
            }else{
                doctorAppointmentRepo.deleteById(id);
                return "deleted test" + id;
            }
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }
}
