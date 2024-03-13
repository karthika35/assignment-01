package com.exam.orm.model;

import lombok.Getter;

@Getter
public class DataResponse {
    private final String elapsedTime;
    private final Users[] users;
    private final Posts[] posts;


    public DataResponse(String elapsedTime, Users[] users, Posts[] posts) {
        this.elapsedTime = elapsedTime;
        this.users = users;
        this.posts = posts;
    }


}
