package com.Queensburry.hospital.controllers;

import com.Queensburry.hospital.dtos.request.LabTestRequestDto;
import com.Queensburry.hospital.dtos.response.LabTestResponseDto;
import com.Queensburry.hospital.services.LabTestService;
import com.Queensburry.hospital.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity<StandardResponse> getAllLabTest(){
        List<LabTestResponseDto> allLabTest = labTestService.getAllLabTest();
        if (allLabTest != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "All the tests", allLabTest),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in lab test getting", null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StandardResponse> saveLabTest(@RequestBody LabTestRequestDto labTestRequestDto, @PathVariable String id){
        String message = labTestService.updateLabTest(labTestRequestDto,id);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "update successfully", message),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in lab test updating", message),
                    HttpStatus.BAD_REQUEST
            );
        }
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> deleteTestById(@PathVariable String id){
        String message = labTestService.deleteById(id);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "delete test", message),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in delete test", null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }




}
