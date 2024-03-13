package com.exam.orm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)

public class Address implements Serializable {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Location geo;


    public Address(String street, String suite, String city, String zipcode, Location geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }
}
