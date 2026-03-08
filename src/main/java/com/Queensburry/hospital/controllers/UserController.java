package com.Queensburry.hospital.controllers;

import com.Queensburry.hospital.dtos.request.LoginRequestDto;
import com.Queensburry.hospital.dtos.request.UserRegistrationDto;
import com.Queensburry.hospital.dtos.response.LoginResponseDto;
import com.Queensburry.hospital.dtos.response.UserResponseDto;
import com.Queensburry.hospital.entity.User;
import com.Queensburry.hospital.repo.UserRepo;
import com.Queensburry.hospital.services.UserService;
import com.Queensburry.hospital.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;


    @PostMapping("/authentication")
    public ResponseEntity<StandardResponse> userLogin(@RequestBody LoginRequestDto loginRequestDto){
        String token = userService.authentication(loginRequestDto);
        UserResponseDto userResponseDto = userService.findUserByEmail(loginRequestDto.getEmail());
        LoginResponseDto loginResponseDto= new LoginResponseDto(
                token,userResponseDto
        );
        if(userResponseDto != null){
            return new ResponseEntity<>(
                    new StandardResponse(200,"login successfull",loginResponseDto),HttpStatus.OK
            );
        }else{
            return null;
        }
    }


    @GetMapping("/my-role")
    public ResponseEntity<?> getMyRole(Authentication authentication) {

        String email = authentication.getName();
        User user = userRepo.findByEmail(email);

        Map<String,String> response = new HashMap<>();
        response.put("role", user.getRole());
        System.out.println(user.getRole());

        return ResponseEntity.ok(response);
    }







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


    @GetMapping("/get-by-id/{userId}")
    public ResponseEntity<StandardResponse> getUsers(@PathVariable String userId){
        List<UserResponseDto> allUsers = userService.getUserDetails(userId);
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
