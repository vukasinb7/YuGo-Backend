package org.yugo.backend.YuGo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class PasswordResetIn {
    @NotBlank(message = "Field (newPassword) is required")
    @Size(min=6,max = 30,message = "password cannot be longer than 30 characters and cannot be less than 6 characters")
    String newPassword;
    @NotNull(message = "Field (code) is required")
    Integer code;

    public PasswordResetIn(String newPassword, Integer code){
        this.newPassword = newPassword;
        this.code = code;
    }
}
