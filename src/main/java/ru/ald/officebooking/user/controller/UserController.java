package ru.ald.officebooking.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ald.officebooking.user.dto.UserDto;
import ru.ald.officebooking.user.dto.UserResponseDto;
import ru.ald.officebooking.user.dto.UserUpdateRequestDto;
import ru.ald.officebooking.user.service.UserService;

import java.util.UUID;

import static ru.ald.officebooking.common.ApiPaths.ID_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public UserResponseDto createUser(@Valid @RequestBody UserDto dto) {
        return userService.createUser(dto);
    }

    @GetMapping
    public Page<UserResponseDto> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return userService.getUsers(page, size);
    }

    @GetMapping(ID_PATH)
    public UserResponseDto getById(@PathVariable UUID id) {
        return userService.getById(id);
    }

    @PatchMapping(ID_PATH)
    public UserResponseDto updateUser(
            @PathVariable UUID id,
            @Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto
    ) {
        return userService.updateUser(id, userUpdateRequestDto);
    }

    @DeleteMapping(ID_PATH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
