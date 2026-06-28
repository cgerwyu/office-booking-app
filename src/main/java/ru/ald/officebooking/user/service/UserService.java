package ru.ald.officebooking.user.service;

import org.springframework.data.domain.Page;
import ru.ald.officebooking.user.dto.UserDto;
import ru.ald.officebooking.user.dto.UserResponseDto;
import ru.ald.officebooking.user.dto.UserUpdateRequestDto;

import java.util.UUID;

public interface UserService {

    public UserResponseDto createUser(UserDto userDto);

    public Page<UserResponseDto> getUsers(int page, int size);

    public UserResponseDto getById(UUID id);

    public UserResponseDto updateUser(UUID id, UserUpdateRequestDto userUpdateRequestDto);

    public void deleteUser(UUID id);

}
