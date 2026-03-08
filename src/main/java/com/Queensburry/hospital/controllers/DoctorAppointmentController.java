package com.Queensburry.hospital.controllers;

import com.Queensburry.hospital.dtos.request.DoctorAppointmentRequestDto;
import com.Queensburry.hospital.dtos.response.DoctorAppointmentResponseDto;
import com.Queensburry.hospital.services.DoctorAppointmentService;
import com.Queensburry.hospital.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/d-appointment")
public class DoctorAppointmentController {
    @Autowired
    private DoctorAppointmentService doctorAppointmentService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveDoctorAppointment(@RequestBody DoctorAppointmentRequestDto doctorAppointmentRequestDto) {
        String message = doctorAppointmentService.saveDoctorAppointment(doctorAppointmentRequestDto);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "appointment Save successfully", message),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in appointment saving", message),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    @GetMapping("/")
    public ResponseEntity<StandardResponse> getAllAppointment() {
        List<DoctorAppointmentResponseDto> doctorAppointmentResponseDtos = doctorAppointmentService.getAllAppointments();
        if (doctorAppointmentResponseDtos != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "All the appointments", doctorAppointmentResponseDtos),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in appointments getting", null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<StandardResponse> getByPatientsId(@PathVariable String id) {
        try{
            List<DoctorAppointmentResponseDto> doctorAppointmentResponseDtos =doctorAppointmentService.getDetailsByPatient(id);
            if (doctorAppointmentResponseDtos != null) {
                return new ResponseEntity<>(
                        new StandardResponse(200, "details of the patient appointment", doctorAppointmentResponseDtos),
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        new StandardResponse(400, "No details", doctorAppointmentResponseDtos),
                        HttpStatus.BAD_REQUEST
                );
            }
        }catch (Exception exception){
            System.out.println(exception);
            return null;
        }

    }


    @GetMapping("/doctor/{id}")
    public ResponseEntity<StandardResponse> getByDoctorId(@PathVariable String id) {
        try{
            List<DoctorAppointmentResponseDto> doctorAppointmentResponseDtos =doctorAppointmentService.getDetailsByDoctor(id);
            if (doctorAppointmentResponseDtos != null) {
                return new ResponseEntity<>(
                        new StandardResponse(200, "details of the patient appointment", doctorAppointmentResponseDtos),
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        new StandardResponse(400, "No details", doctorAppointmentResponseDtos),
                        HttpStatus.BAD_REQUEST
                );
            }
        }catch (Exception exception){
            System.out.println(exception);
            return null;
        }

    }



    @PutMapping("/update/{id}")
    public ResponseEntity<StandardResponse> updateDoctorAppointment(@RequestBody DoctorAppointmentRequestDto doctorAppointmentRequestDto, @PathVariable String id) {
        String message = doctorAppointmentService.updateDoctorAppointment(doctorAppointmentRequestDto, id);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "update successfully", message),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in appointment updating", message),
                    HttpStatus.BAD_REQUEST
            );
        }


    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> deleteAppointmentById(@PathVariable String id){
        String message = doctorAppointmentService.deleteById(id);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "delete appointment", message),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in delete appointment", null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


}

