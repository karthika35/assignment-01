package com.exam.orm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)

public class Posts implements Serializable {
    private int id;
    private String slug;
    private String url;
    private String title;
    private String content;
    private String image;
    private String thumbnail;
    private String status;
    private String category;
    private String publishedAt;
    private String updatedAt;
    private String userId;


    public Posts(int id, String slug, String url, String title, String content, String image, String thumbnail, String status, String category, String publishedAt, String updatedAt, String userId) {
        this.id = id;
        this.slug = slug;
        this.url = url;
        this.title = title;
        this.content = content;
        this.image = image;
        this.thumbnail = thumbnail;
        this.status = status;
        this.category = category;
        this.publishedAt = publishedAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }
}
