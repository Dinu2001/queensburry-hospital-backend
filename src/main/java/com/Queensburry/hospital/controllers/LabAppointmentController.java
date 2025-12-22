package com.Queensburry.hospital.controllers;

import com.Queensburry.hospital.dtos.request.LabAppointmentRequestDto;
import com.Queensburry.hospital.dtos.request.PatientRegistrationDto;
import com.Queensburry.hospital.dtos.response.LabAppointmentResponseDto;
import com.Queensburry.hospital.services.LabAppointmentService;
import com.Queensburry.hospital.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lab-appointment")
public class LabAppointmentController {
    @Autowired
    private LabAppointmentService labAppointmentService;


    @PostMapping("/save")
    public ResponseEntity<StandardResponse> savePatient(@RequestBody LabAppointmentRequestDto labAppointmentRequestDto){
        String message = labAppointmentService.saveLabAppointment(labAppointmentRequestDto);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "lab appointment Save successfully" , message),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in lab appointment saving", message),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/")
    public ResponseEntity<StandardResponse> getAllLabAppointment(){
        List<LabAppointmentResponseDto> labAppointmentResponseDtos = labAppointmentService.getAll();
        if(labAppointmentResponseDtos == null){
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in lab appointment ", null),
                    HttpStatus.BAD_REQUEST
            );
        }else{
            return new ResponseEntity<>(
                    new StandardResponse(200, "lab appointment" , labAppointmentResponseDtos),
                    HttpStatus.OK
            );
        }
    }

}
