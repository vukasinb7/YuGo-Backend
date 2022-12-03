package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String surName;

    @Getter @Setter
    private String profilePicture;

    @Getter @Setter
    private String telephoneNumber;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String password;

    public UserRequest(String name, String surName, String profilePicture, String telephoneNumber, String email, String address, String password) {
        this.name = name;
        this.surName = surName;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }
}
