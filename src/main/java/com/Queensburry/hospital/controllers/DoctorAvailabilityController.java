package com.Queensburry.hospital.controllers;

import com.Queensburry.hospital.dtos.request.DoctorAvailabilityRequestDto;
import com.Queensburry.hospital.dtos.response.DateAndTimeResponseDto;
import com.Queensburry.hospital.dtos.response.DoctorAvailabilityResponseDto;
import com.Queensburry.hospital.dtos.response.DoctorDayResponseDto;
import com.Queensburry.hospital.dtos.response.DoctorResponseDto;
import com.Queensburry.hospital.services.DoctorAvailabilityService;
import com.Queensburry.hospital.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor-availability")
@CrossOrigin
public class DoctorAvailabilityController {

    @Autowired
    private DoctorAvailabilityService service;


    @PostMapping("/save")
    public ResponseEntity<StandardResponse> addAvailability(
            @RequestBody DoctorAvailabilityRequestDto dto
    ) {
        service.addAvailability(dto);

        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Doctor schedules added successfully",
                        null
                ),
                HttpStatus.CREATED
        );
    }



    @GetMapping("/")
    public ResponseEntity<StandardResponse> getDoctorDetails(){
        List<DoctorDayResponseDto> allDoctors = service.getAllDoctorDetails();
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



    @GetMapping("/date/{id}")
    public ResponseEntity<StandardResponse> getDoctorDates(@PathVariable String id){
        List<DateAndTimeResponseDto> dateAndTime = service.getDateAndTime(id);
        if (dateAndTime != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "All the doctors dates", dateAndTime),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in getting doctor dates", null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


//    @GetMapping("/{doctorId}")
//    public ResponseEntity<StandardResponse> getByDoctor(
//            @PathVariable String doctorId
//    ) {
//        List<DoctorAvailabilityResponseDto> dto = service.getByDoctor(doctorId);
//
//        return ResponseEntity.ok(
//                new StandardResponse(
//                        200,
//                        "Doctor schedules fetched successfully",
//                        dto
//                )
//        );
//    }


//    @PutMapping("/{id}/max-patients")
//    public ResponseEntity<StandardResponse> updateMaxPatients(
//            @PathVariable Long id,
//            @RequestParam int maxPatients
//    ) {
//        service.updateMaxPatients(id, maxPatients);
//
//        return ResponseEntity.ok(
//                new StandardResponse(
//                        200,
//                        "Max patients updated successfully",
//                        null
//                )
//        );
//    }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<StandardResponse> deleteAvailability(
//            @PathVariable Long id
//    ) {
//        service.deleteAvailability(id);
//
//        return ResponseEntity.ok(
//                new StandardResponse(
//                        200,
//                        "Doctor schedule deleted successfully",
//                        null
//                )
//        );
//    }
}
