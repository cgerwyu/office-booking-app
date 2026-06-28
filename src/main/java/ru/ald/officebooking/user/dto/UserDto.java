package ru.ald.officebooking.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserDto {

    @NotBlank
    @Size(max = 64)
    String name;

    @Email
    @NotBlank
    @Size(max = 255)
    String email;

    @NotBlank
    @Size(min = 6, max=255)
    String password;
}
