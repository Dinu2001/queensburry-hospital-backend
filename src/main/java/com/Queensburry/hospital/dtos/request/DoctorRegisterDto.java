package com.Queensburry.hospital.dtos.request;

public class DoctorRegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    private String registrationNumber;
    private String specification;
    private String phoneNumber;

//    private List<DoctorAvailabilityDto> availabilities;

    public DoctorRegisterDto() {
    }

    public DoctorRegisterDto(String firstName, String lastName, String email, String password, String registrationNumber, String specification, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.registrationNumber = registrationNumber;
        this.specification = specification;
        this.phoneNumber = phoneNumber;

    }

//    public List<DoctorAvailabilityDto> getAvailabilities() {
//        return availabilities;
//    }
//
//    public void setAvailabilities(List<DoctorAvailabilityDto> availabilities) {
//        this.availabilities = availabilities;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
