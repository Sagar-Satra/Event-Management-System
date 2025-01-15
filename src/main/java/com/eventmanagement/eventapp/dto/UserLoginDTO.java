package com.eventmanagement.eventapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class UserLoginDTO {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    public String email;

    @NotNull(message = "Password is required")
    @Length(min = 6, message = "Password should be at least 6 characters long")
    public String password;

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
}