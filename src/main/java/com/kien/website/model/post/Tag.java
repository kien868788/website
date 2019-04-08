package com.kien.website.model.post;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
public class Tag {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phonenumber;

    private String zalo;

    private String skype;

    @Email
    @Column(nullable = false)
    private String email;

    private String direction;

    private String status;

    public Tag() {}

}
