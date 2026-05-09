package ru.ald.officebooking.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserResponseDto {

    UUID id;

    String name;

    String email;
}
