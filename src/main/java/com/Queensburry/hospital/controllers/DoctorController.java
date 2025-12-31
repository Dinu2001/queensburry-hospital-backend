package com.Queensburry.hospital.controllers;

import com.Queensburry.hospital.dtos.request.DoctorRegisterDto;
import com.Queensburry.hospital.dtos.response.DoctorResponseDto;
import com.Queensburry.hospital.dtos.response.PatientResponseDto;
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

    @GetMapping("/")
    public ResponseEntity<StandardResponse> getAllDoctor(){
        List<DoctorResponseDto> allDoctors = doctorService.getAllDoctor();
        if (allDoctors != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "All the doctors", allDoctors),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in getting doctor details", null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<StandardResponse> getDoctorId(@PathVariable String userId) {
        String id = doctorService.getDoctorId(userId);
        if (id != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "id", id),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in getting doctor details", null),
                    HttpStatus.BAD_REQUEST
            );
        }


    }


    @GetMapping("/get-doctor/{id}")
    public ResponseEntity<StandardResponse> getDoctorDetails(@PathVariable String id){
            DoctorResponseDto doctorResponseDto = doctorService.getDoctorDetailById(id);
            if (doctorResponseDto != null) {
                return new ResponseEntity<>(
                        new StandardResponse(200, "doctor details", doctorResponseDto),
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        new StandardResponse(400, "Error in getting doctor details", null),
                        HttpStatus.BAD_REQUEST
                );
            }
    }





}







