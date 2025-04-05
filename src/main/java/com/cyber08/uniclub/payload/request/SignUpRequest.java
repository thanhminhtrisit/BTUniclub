package com.cyber08.uniclub.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotNull(message = "The email is null!")
    @NotEmpty(message = "The email is empty!")
    @Email(message = "Wrong email format!")
    private String email;

    @NotNull(message = "Passwork is null!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~]).{8,}$")
    private String password;

    private String fullName;

    private String passCormfid;
}
