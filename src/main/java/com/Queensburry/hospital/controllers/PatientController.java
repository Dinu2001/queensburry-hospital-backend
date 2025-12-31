package com.Queensburry.hospital.controllers;

import com.Queensburry.hospital.dtos.request.PatientRegistrationDto;
import com.Queensburry.hospital.dtos.request.UserRegistrationDto;
import com.Queensburry.hospital.dtos.response.PatientResponseDto;
import com.Queensburry.hospital.dtos.response.UserResponseDto;
import com.Queensburry.hospital.services.PatientService;
import com.Queensburry.hospital.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> savePatient(@RequestBody PatientRegistrationDto patientRegistrationDto){
        String message = patientService.savePatient(patientRegistrationDto);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "patient Save successfully" , message),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in patient saving", message),
                    HttpStatus.BAD_REQUEST
            );
        }
    }



    @GetMapping("/")
    public ResponseEntity<StandardResponse> getAllPatients(){
        List<PatientResponseDto> allUsers = patientService.getAllPatients();
        if (allUsers != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "All the patients", allUsers),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in getting patients details", null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    @GetMapping("/{userId}")
    public ResponseEntity<StandardResponse> getPatientId(@PathVariable String userId){
        String id = patientService.getPatientId(userId);
        if (id != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "id ", id),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in getting patients details", null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/search/{userName}")
    public ResponseEntity<StandardResponse> getPatientNameAndId(@PathVariable String userName){
        List<PatientResponseDto> dtos = patientService.getPatientNameAndId(userName);
        if (dtos != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "id ", dtos),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in getting patients details", null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    @GetMapping("/get-patient/{id}")
    public ResponseEntity<StandardResponse> getUserDetailsUsingId(@PathVariable String id){
        PatientResponseDto patientResponseDto = patientService.getUserDetailsUsingId(id);
        if (patientResponseDto != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "details ", patientResponseDto),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in getting patients details", null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }



}
