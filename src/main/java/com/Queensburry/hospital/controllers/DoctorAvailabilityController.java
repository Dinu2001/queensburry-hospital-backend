package com.Queensburry.hospital.controllers;

import com.Queensburry.hospital.dtos.request.DoctorAvailabilityRequestDto;
import com.Queensburry.hospital.dtos.response.DoctorAvailabilityResponseDto;

import com.Queensburry.hospital.services.DoctorAvailabilityService;
import com.Queensburry.hospital.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor-availability")
public class DoctorAvailabilityController {

    @Autowired
    private DoctorAvailabilityService service;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> addAvailability(
            @RequestBody DoctorAvailabilityRequestDto dto
    ) {
        Long id = service.addAvailability(dto);

        return new ResponseEntity<>(
                new StandardResponse(201, "Doctor schedule added successfully", id),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<StandardResponse> getByDoctor(
            @PathVariable String doctorId
    ) {

        List<DoctorAvailabilityResponseDto> dto = service.getByDoctor(doctorId);
        return ResponseEntity.ok(
                new StandardResponse(
                        200,
                        "Doctor schedules fetched successfully",
                        dto
                )
        );
    }


    @PutMapping("/{id}/max-patients")
    public ResponseEntity<StandardResponse> updateMaxPatients(
            @PathVariable Long id,
            @RequestParam int maxPatients
    ) {
        service.updateMaxPatients(id, maxPatients);

        return ResponseEntity.ok(
                new StandardResponse(200, "Max patients updated successfully", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteAvailability(
            @PathVariable Long id
    ) {
        service.deleteAvailability(id);

        return ResponseEntity.ok(
                new StandardResponse(200, "Doctor schedule deleted successfully", null)
        );
    }
}
