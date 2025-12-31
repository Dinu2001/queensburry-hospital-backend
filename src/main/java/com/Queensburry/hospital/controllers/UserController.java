package com.Queensburry.hospital.controllers;

import com.Queensburry.hospital.dtos.request.UserRegistrationDto;
import com.Queensburry.hospital.dtos.response.LabTestResponseDto;
import com.Queensburry.hospital.dtos.response.UserResponseDto;
import com.Queensburry.hospital.services.UserService;
import com.Queensburry.hospital.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveUser(@RequestBody UserRegistrationDto userRegistrationDto){
        String message = userService.saveUser(userRegistrationDto);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "user Save successfully", message),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in user saving", message),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/")
    public ResponseEntity<StandardResponse> getAllUsers(){
        List<UserResponseDto> allUsers = userService.getAllUsers();
        if (allUsers != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "All the users", allUsers),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in getting user details", null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<StandardResponse> searchUser(@PathVariable String userName){
        List<UserResponseDto> allUsers = userService.searchUserByName(userName);
        if (allUsers != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "All the users "+userName, allUsers),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in getting user details "+userName, null),
                    HttpStatus.NOT_FOUND
            );
        }
    }



    @GetMapping("/role/{role}")
    public ResponseEntity<StandardResponse> getByRole(@PathVariable String role){
        List<UserResponseDto> allUsers = userService.getByUserRole(role);
        if (allUsers != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "All the users "+role, allUsers),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in getting user details "+role, null),
                    HttpStatus.NOT_FOUND
            );
        }
    }







    @PutMapping("/deactivate/{email}")
    public ResponseEntity<StandardResponse> deactivateUser(@PathVariable String email){
        String message = userService.deactivateUser(email);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "deactivate "+ email,null),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in getting user details "+email, null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/activate/{email}")
    public ResponseEntity<StandardResponse> activateUser(@PathVariable String email){
        String message = userService.activateUser(email);
        if (message != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "activate "+ email,null),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Error in getting user details "+email, null),
                    HttpStatus.NOT_FOUND
            );
        }
    }





}
