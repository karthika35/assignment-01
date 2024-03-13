package com.exam.orm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)

public class Users implements Serializable {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String birthDate;
    private Login login;
    private Address address;
    private String phone;
    private String website;
    private Company company;
    public Users() {
    }

    public Users(Long id, String firstname, String lastname, String email, String birthDate, Login login, Address address, String phone, String website, Company company) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthDate = birthDate;
        this.login = login;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }
}
