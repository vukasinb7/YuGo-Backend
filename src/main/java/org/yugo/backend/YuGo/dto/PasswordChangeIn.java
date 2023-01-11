package org.yugo.backend.YuGo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class PasswordChangeIn {
    @NotBlank(message = "Field (newPassword) is required")
    @Size(min=6,max = 30,message = "newPassword cannot be longer than 30 characters and cannot be less than 6 characters")
    String newPassword;
    @NotBlank(message = "Field (oldPassword) is required")
    @Size(min=6,max = 30,message = "oldPassword cannot be longer than 30 characters and cannot be less than 6 characters")
    String oldPassword;

    public PasswordChangeIn(String newPassword, String oldPassword){
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }
}
