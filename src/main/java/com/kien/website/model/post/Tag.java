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

}
