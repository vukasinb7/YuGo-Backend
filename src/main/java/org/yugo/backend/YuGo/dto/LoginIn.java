package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter
@NoArgsConstructor
public class LoginIn {
    private String email;
    private String password;

    public LoginIn(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
