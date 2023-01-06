package org.yugo.backend.YuGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class PasswordChangeIn {
    String newPassword;
    String oldPassword;

    public PasswordChangeIn(String newPassword, String oldPassword){
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }
}
