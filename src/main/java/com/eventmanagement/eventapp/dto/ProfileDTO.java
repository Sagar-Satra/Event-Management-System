package com.eventmanagement.eventapp.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class ProfileDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;


    @NotNull(message = "Phone number is required")
    @Min(value = 1000000000L, message = "Phone number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Phone number must be no more than 10 digits")
    private Long phoneNumber;

    @Valid
    private AddressDTO primaryAddress;
    @Valid
    private AddressDTO secondaryAddress;

    @NotBlank(message = "Password is required")
    @Size(min = 3, message = "Password must be at least 6 characters")
    private String password;

    // Getters and setters
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


    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public AddressDTO getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(AddressDTO primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public AddressDTO getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(AddressDTO secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
