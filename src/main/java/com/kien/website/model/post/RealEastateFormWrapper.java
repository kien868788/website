package com.kien.website.model.post;

import lombok.Data;

import javax.validation.Valid;


@Data
public class RealEastateFormWrapper {
    @Valid
    private RealEstate realEstate;
    private Long category;
}
