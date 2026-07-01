package ru.ald.officebooking.exception;

import java.time.LocalDateTime;

public record ExceptionResponseDto(
        int status,
        String name,
        String message,
        LocalDateTime timestamp
) {}
