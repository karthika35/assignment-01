package com.exam.orm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location implements Serializable {
    private String lat;
    private  String lng;


    public Location(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
