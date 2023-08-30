package com.clean.code.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public class Otp {
    @Id
    @NotNull
    @Column(unique = true)
    public UUID otp;

    @NotNull
    private String check;

    @NotNull
    private Date expirationTime = new Date();

    private Boolean verified = false;



}
