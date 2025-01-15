package com.eventmanagement.eventapp.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class UserRegisterDTO {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    public String email;

    @NotNull(message = "Password is required")
    @Length(min = 6, message = "Password should be at least 6 characters long")
    public String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Phone number is required")
    @Min(value = 1000000000L, message = "Phone number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Phone number must be no more than 10 digits")
    private Long phoneNumber;

    public @Email(message = "Email should be valid") @NotBlank(message = "Email is required")  String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email should be valid") @NotBlank(message = "Email is required") String email) {
        this.email = email;
    }

    public @NotNull(message = "Password is required") @Length(min = 6, message = "Password should be at least 6 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Password is required") @Length(min = 6, message = "Password should be at least 6 characters long") String password) {
        this.password = password;
    }

    public @NotBlank(message = "First name is required") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First name is required") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Last name is required") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last name is required") String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = "Phone number is required") @Min(value = 1000000000L, message = "Phone number must be at least 10 digits") @Max(value = 9999999999L, message = "Phone number must be no more than 10 digits") Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull(message = "Phone number is required") @Min(value = 1000000000L, message = "Phone number must be at least 10 digits") @Max(value = 9999999999L, message = "Phone number must be no more than 10 digits") Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
