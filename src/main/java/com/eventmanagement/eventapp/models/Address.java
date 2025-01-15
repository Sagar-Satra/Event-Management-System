package com.eventmanagement.eventapp.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String street;
    private String city;
    private String state;
    private Integer zip;
    private String country;
    private Boolean availableForEvent = false;

//    @ManyToOne
//    @JoinColumn(name = "owner_id" , nullable = false)
//    @OneToOne(cascade = CascadeType.ALL)
//    private User owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getAvailableForEvent() {
        return availableForEvent;
    }

    public void setAvailableForEvent(Boolean availableForEvent) {
        this.availableForEvent = availableForEvent;
    }

}
