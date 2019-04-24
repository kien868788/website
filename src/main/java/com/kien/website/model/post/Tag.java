package com.kien.website.model.post;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Tag {

    private String name;

    private String phonenumber;

    private String zalo;

    private String skype;

    private String email;

    private String direction;

    private String status;

    public Tag() {}

    public Tag(String name, String phonenumber, String zalo, String skype, String email, String direction, String status) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.zalo = zalo;
        this.skype = skype;
        this.email = email;
        this.direction = direction;
        this.status = status;
    }
}
