package org.yugo.backend.YuGo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter
@NoArgsConstructor
public class ReasonIn {
    @NotBlank(message = "Field (reason) is required")
    @Size(max = 300,message = "reason cannot be longer than 300 characters")
    String reason;
}
