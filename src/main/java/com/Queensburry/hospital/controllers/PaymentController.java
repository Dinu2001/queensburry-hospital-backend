package com.Queensburry.hospital.controllers;

import com.Queensburry.hospital.dtos.request.PaymentRequestDto;
import com.Queensburry.hospital.services.PaymentService;
import com.Queensburry.hospital.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> savePayment(@RequestBody PaymentRequestDto paymentRequestDto){
        String message = paymentService.savePayment(paymentRequestDto);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "payment Save successfully" , message),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in payment saving", message),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
