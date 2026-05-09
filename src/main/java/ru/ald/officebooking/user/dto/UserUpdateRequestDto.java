package ru.ald.officebooking.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserUpdateRequestDto {

    @Size(max = 64)
    String name;

    @Email
    @Size(max = 255)
    String email;

    @Size(min = 6, max=255)
    String password;
}
