package ru.ald.officebooking.user.mapper;

import org.springframework.stereotype.Component;
import ru.ald.officebooking.user.dto.UserCreateRequestDto;
import ru.ald.officebooking.user.dto.UserResponseDto;
import ru.ald.officebooking.user.dto.UserUpdateRequestDto;
import ru.ald.officebooking.user.model.User;

@Component
public class UserMapper {

    public User userCreateDtoToEntity(UserCreateRequestDto dto) {
        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    public UserResponseDto userToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();

        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());

        return dto;
    }

    public void updateUser(User user, UserUpdateRequestDto dto) {
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
    }

}
