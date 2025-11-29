package com.Queensburry.hospital.controllers;

import com.Queensburry.hospital.dtos.request.LabTestRequestDto;
import com.Queensburry.hospital.services.LabTestService;
import com.Queensburry.hospital.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lab-test")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveLabTest(@RequestBody LabTestRequestDto labTestRequestDto){
        String message = labTestService.saveLabTest(labTestRequestDto);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "Save successfully", message),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in lab test saving", message),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}
