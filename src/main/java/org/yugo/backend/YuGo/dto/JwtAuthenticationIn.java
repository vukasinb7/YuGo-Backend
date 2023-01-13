package org.yugo.backend.YuGo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtAuthenticationIn {
    @NotBlank(message = "Field (email) is required")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotBlank(message = "Field (password) is required")
    @Size(min=6,max = 30,message = "password cannot be longer than 30 characters and cannot be less than 6 characters")
    private String password;

    public JwtAuthenticationIn() {
        super();
    }

    public JwtAuthenticationIn(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }
}
