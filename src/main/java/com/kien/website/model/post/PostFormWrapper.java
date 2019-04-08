package com.kien.website.model.post;

import lombok.Data;

import javax.validation.Valid;


@Data
public class PostFormWrapper {
    @Valid
    private Post post;
    private Long category;
}
