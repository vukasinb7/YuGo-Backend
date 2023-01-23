package org.yugo.backend.YuGo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class TokenRefreshIn {

    @NotBlank(message = "Field (refreshToken) is required!")
    @Size(max = 100, message = "Field (refreshToken) cannot be longer than 100 characters!")
    private String refreshToken;

    public void TokenRefreshIn(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
