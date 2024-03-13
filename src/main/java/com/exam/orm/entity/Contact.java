package com.exam.orm.entity;

import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;
    private String name;
    private String emailAddress;
    private String phone;
    private String address;

    public Contact() {
    }

    public Contact(int contactId, String name, String emailAddress, String phone, String address) {
        this.contactId = contactId;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.address = address;
    }

}
