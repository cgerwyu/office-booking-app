package ru.ald.officebooking.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundException(NotFoundException exception) {
        ExceptionResponseDto response = new ExceptionResponseDto(
                HttpStatus.NOT_FOUND.value(),
                "Not found",
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        ExceptionResponseDto response = new ExceptionResponseDto(
                HttpStatus.CONFLICT.value(),
                "User already exists",
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDto> handleAlreadyExistsException(AlreadyExistsException exception) {
        ExceptionResponseDto response = new ExceptionResponseDto(
                HttpStatus.CONFLICT.value(),
                "Already exists error",
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponseDto> handleValidationException(ValidationException exception) {
        ExceptionResponseDto response = new ExceptionResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // для всех остальных exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleVariousExceptions(Exception exception) {
        ExceptionResponseDto response = new ExceptionResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error",
                "Unexpected server error.",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

}
