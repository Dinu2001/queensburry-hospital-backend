package com.Queensburry.hospital.controllers;

import com.Queensburry.hospital.dtos.request.DoctorRegisterDto;
import com.Queensburry.hospital.dtos.request.UserRegistrationDto;
import com.Queensburry.hospital.dtos.response.DoctorAppointmentResponseDto;
import com.Queensburry.hospital.dtos.response.LabTestResponseDto;
import com.Queensburry.hospital.services.DoctorService;
import com.Queensburry.hospital.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;


    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveDoctor(@RequestBody DoctorRegisterDto doctorRegisterDto){
        String message = doctorService.saveDoctor(doctorRegisterDto);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "doctor Save successfully", message),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in doctor saving", message),
                    HttpStatus.BAD_REQUEST
            );
        }
    }



}
