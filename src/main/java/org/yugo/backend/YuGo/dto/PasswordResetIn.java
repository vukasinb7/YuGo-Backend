package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class PasswordResetIn {
    String newPassword;
    String code;

    public PasswordResetIn(String newPassword, String code){
        this.newPassword = newPassword;
        this.code = code;
    }
}
