package com.Queensburry.hospital.services;

import com.Queensburry.hospital.configurations.JwtService;
import com.Queensburry.hospital.dtos.request.LoginRequestDto;
import com.Queensburry.hospital.dtos.request.UserRegistrationDto;
import com.Queensburry.hospital.dtos.response.UserResponseDto;
import com.Queensburry.hospital.entity.User;
import com.Queensburry.hospital.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String generateUserIdOneAfterOne() {
        String lastId = userRepo.getLastUserId();
        if (lastId == null) {
            return "U001";
        }
        int number = Integer.parseInt(lastId.substring(1));
        number++;
        return String.format("U%03d", number);
    }


    public UserResponseDto findUserByEmail(String userName){
        User user= userRepo.findByEmail(userName);
        if(user == null){
            return null;
        }else{
            return new UserResponseDto(
                    user.getUserId(),user.getFirstName(),user.getLastName(),
                    user.getEmail(),user.getRole(),user.getStatus(),user.getCreatedAt()
            );
        }

    }


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public String authentication(LoginRequestDto loginRequestDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),loginRequestDto.getPassword()
                )
        );
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(loginRequestDto.getEmail());
        }
        throw new RuntimeException();
    }






    public String saveUser(UserRegistrationDto userRegistrationDto) {
        try{
            LocalDate localDate = LocalDate.now();
            Date createdDate = Date.from(
                    localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            );

            User user = new User(
                    generateUserIdOneAfterOne(),userRegistrationDto.getFirstName(),userRegistrationDto.getLastName(),
                    userRegistrationDto.getEmail(),userRegistrationDto.getRole(),userRegistrationDto.getStatus(),
                    passwordEncoder.encode(userRegistrationDto.getPassword()),createdDate
            );
            User saved =userRepo.save(user);
            if(saved != null){
                return saved.getFirstName()+" "+saved.getLastName()+" save successfully";
            }
            return null;

        }catch(Exception exception){
            System.out.println(exception);
            return null;
        }
    }

    public List<UserResponseDto> getAllUsers() {
        try{
            List<UserResponseDto> userResponseDtoList = new ArrayList<>();
            List<User> users = userRepo.findAllByStatus("ACTIVE");

            for(User user:users){
                UserResponseDto userResponseDto = new UserResponseDto(
                        user.getUserId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getRole(),user.getStatus(),
                        user.getCreatedAt()
                );
                userResponseDtoList.add(userResponseDto);
            }

            return userResponseDtoList;

        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }

    }







    public List<UserResponseDto> searchUserByName(String userName) {
        try{
            List<UserResponseDto> userResponseDtoList = new ArrayList<>();
            List<User> users = userRepo.searchByName(userName);

            for(User user:users){
                UserResponseDto userResponseDto = new UserResponseDto(
                        user.getUserId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getRole(),user.getStatus(),
                        user.getCreatedAt()
                );
                userResponseDtoList.add(userResponseDto);
            }

            return userResponseDtoList;

        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }

    public String deactivateUser(String email) {
        try{
            User user = userRepo.findByEmail(email);
            if(user != null){
                user.setStatus("DEACTIVE");
            }
            userRepo.save(user);

            return "Deactivate user "+email;

        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }

    }

    public List<UserResponseDto> getByUserRole(String role) {
        try{
            List<UserResponseDto> userResponseDtoList = new ArrayList<>();
            List<User> users = userRepo.findAllByRole(role);

            for(User user:users){
                UserResponseDto userResponseDto = new UserResponseDto(
                        user.getUserId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getRole(),user.getStatus(),
                        user.getCreatedAt()
                );
                userResponseDtoList.add(userResponseDto);
            }

            return userResponseDtoList;

        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }

    public String activateUser(String email) {
        try{
            User user = userRepo.findByEmail(email);
            if(user != null){
                user.setStatus("ACTIVE");
            }
            userRepo.save(user);

            return "Activate user "+email;

        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }

    public List<UserResponseDto> getUserDetails(String userId) {
        try{
            List<UserResponseDto> userResponseDtoList = new ArrayList<>();
            List<User> users = userRepo.findByUserId(userId);

            for(User user:users){
                UserResponseDto userResponseDto = new UserResponseDto(
                        user.getUserId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getRole(),user.getStatus(),
                        user.getCreatedAt()
                );
                userResponseDtoList.add(userResponseDto);
            }

            return userResponseDtoList;

        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }
}
