package ru.ald.officebooking.user.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ald.officebooking.exception.NotFoundException;
import ru.ald.officebooking.exception.UserAlreadyExistsException;
import ru.ald.officebooking.user.dto.UserCreateRequestDto;
import ru.ald.officebooking.user.dto.UserResponseDto;
import ru.ald.officebooking.user.dto.UserUpdateRequestDto;
import ru.ald.officebooking.user.model.User;
import ru.ald.officebooking.user.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import ru.ald.officebooking.user.mapper.UserMapper;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto createUser(UserCreateRequestDto userCreateRequestDto) {
        String userEmail = userCreateRequestDto.getEmail();

        checkEmailIsAvailable(userEmail);

        User user = userMapper.userCreateDtoToEntity(userCreateRequestDto);
        user.setPassword(passwordEncoder.encode(userCreateRequestDto.getPassword()));
        User savedUser = userRepository.save(user);
        UserResponseDto responseDto = userMapper.userToResponseDto(savedUser);

        return responseDto;
    }

    public Page<UserResponseDto> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> usersPage = userRepository.findAll(pageable);

        return usersPage.map(userMapper::userToResponseDto);
    }

    public UserResponseDto getById(UUID id) {
        User user = getUserIfExists(id);

        UserResponseDto responseDto = userMapper.userToResponseDto(user);

        return responseDto;
    }

    @Transactional
    public UserResponseDto updateUser(UUID id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = getUserIfExists(id);

        String newEmail = userUpdateRequestDto.getEmail();
        if (newEmail != null && !newEmail.equals(user.getEmail())) {
            checkEmailIsAvailable(newEmail);
        }
        userMapper.updateUser(user, userUpdateRequestDto);

        if (userUpdateRequestDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateRequestDto.getPassword()));
        }

        User savedUser = userRepository.save(user);
        return userMapper.userToResponseDto(savedUser);
    }

    @Transactional
    public void deleteUser(UUID id) {
        User user = getUserIfExists(id);

        userRepository.delete(user);
    }

    private User getUserIfExists(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() ->
                new NotFoundException(
                    String.format("Пользователь с id = %s не найден", id)
                )
            );

        return user;
    }

    private void checkEmailIsAvailable(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(
                    String.format("Пользователь с почтой %s уже зарегистрирован.", email)
            );
        }
    }
}
