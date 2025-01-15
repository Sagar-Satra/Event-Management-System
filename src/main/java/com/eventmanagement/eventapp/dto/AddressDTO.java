package com.eventmanagement.eventapp.dto;

import jakarta.validation.constraints.*;

public class AddressDTO {
    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Country is required")
    private String country;

    @NotNull(message = "ZIP code is required")
    @Min(value = 0000, message = "ZIP must be at least 4 digits")
    @Max(value = 9999, message = "ZIP must be no more than 4 digits")
    private Integer zip;


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {

        this.street = street;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {

        this.country = country;
    }


}


