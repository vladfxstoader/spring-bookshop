package com.example.bookshop.dto;

import com.example.bookshop.model.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
    private Integer id;
    @NotNull(message = "Email is mandatory.")
    @NotBlank(message = "Email must have a value.")
    private String email;
    @NotNull(message = "First name is mandatory.")
    @NotBlank(message = "First name must have a value.")
    private String firstName;
    @NotNull(message = "Last name is mandatory.")
    @NotBlank(message = "Last name must have a value.")
    private String lastName;
    @NotNull(message = "Phone number is mandatory.")
    @Size(min = 10, max = 10, message = "Phone number must have exactly 10 digits.")
    @Pattern(regexp = "\\d+", message = "Phone number must contain only digits.")
    private String phoneNumber;
    private AddressDto address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
