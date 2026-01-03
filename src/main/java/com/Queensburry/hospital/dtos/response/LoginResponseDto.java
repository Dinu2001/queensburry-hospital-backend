package com.Queensburry.hospital.dtos.response;

public class LoginResponseDto {

    private String token;
    private UserResponseDto userResponseDto;


    public LoginResponseDto() {
    }

    public LoginResponseDto(String token, UserResponseDto userResponseDto) {
        this.token = token;
        this.userResponseDto = userResponseDto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }
}
