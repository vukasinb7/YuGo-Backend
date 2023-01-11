package org.yugo.backend.YuGo.dto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserDetailedIn {
    @NotBlank(message = "Field (name) is required!")
    @Size(max = 50, message = "Field (name) cannot be longer than 50 characters!")
    private String name;
    @NotBlank(message = "Field (surname) is required!")
    @Size(max = 50, message = "Field (surname) cannot be longer than 50 characters!")
    private String surname;
    @NotBlank(message = "Field (profilePicture) is required!")
    @Size(max = 50, message = "Field (profilePicture) cannot be longer than 50 characters!")
    private String profilePicture;
    @NotBlank(message = "Field (telephoneNumber) is required!")
    @Pattern(regexp = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
    private String telephoneNumber;
    @NotBlank(message = "Field (email) is required!")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NotBlank(message = "Field (address) is required!")
    @Size(max = 100, message = "Field (address) cannot be longer than 50 characters!")
    private String address;
    @NotBlank(message = "Field (password) is required!")
    @Size(min=6, max = 30, message = "Field (password) cannot be longer than 50 characters!")
    private String password;

    public UserDetailedIn(String name, String surname, String profilePicture, String telephoneNumber, String email, String address, String password) {
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }
}
