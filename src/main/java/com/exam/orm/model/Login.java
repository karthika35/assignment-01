package com.exam.orm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Login implements Serializable {

    private String uuid;
    private String username;
    private String password;
    private String md5;
    private String sha1;
    private String registered;

    public Login(String uuid, String username, String password, String md5, String sha1, String registered) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.md5 = md5;
        this.sha1 = sha1;
        this.registered = registered;
    }
}
