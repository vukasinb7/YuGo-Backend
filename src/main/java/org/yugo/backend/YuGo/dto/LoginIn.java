package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class LoginIn {
    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    public LoginIn(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
