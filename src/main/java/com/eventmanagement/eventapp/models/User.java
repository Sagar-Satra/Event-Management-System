package com.eventmanagement.eventapp.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import java.util.List;

@Component
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "First name is required")
    private String firstName;
    //@NotBlank(message = "Last name is required")
    private String lastName;

//    @Email(message = "Email should be valid")
//    @Column(unique=true)
    private String email;

//    @NotNull(message = "Phone number is required")
//    @Min(value = 1000000000L, message = "Phone number must be at least 10 digits")
//    @Max(value = 9999999999L, message = "Phone number must be no more than 10 digits")
    private Long phoneNumber;

    private Role role = Role.USER;

//    @OneToMany(mappedBy = "owner" , fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    @Fetch(FetchMode.JOIN)
//    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL)
    private Address primaryAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Address secondaryAddress;

//    @NotNull(message = "Password is required")
//    @Length(min = 3, message = "Password should be at least 6 characters long")
    private String password;

    @ManyToMany(mappedBy = "participants" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Event> userEvents;

    @OneToMany(mappedBy = "organizer" , cascade = CascadeType.ALL , orphanRemoval = true , fetch = FetchType.EAGER)
    private List<Event> organizedEvents;

    public Long getId() {
        return id;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(Address secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    public List<Event> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<Event> userEvents) {
        this.userEvents = userEvents;
    }

    public List<Event> getOrganizedEvents() {
        return organizedEvents;
    }

    public void setOrganizedEvents(List<Event> organizedEvents) {
        this.organizedEvents = organizedEvents;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
