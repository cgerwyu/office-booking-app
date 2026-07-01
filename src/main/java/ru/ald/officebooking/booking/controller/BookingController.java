package ru.ald.officebooking.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ald.officebooking.booking.dto.CreateBookingDto;
import ru.ald.officebooking.booking.dto.ResponseBookingDto;
import ru.ald.officebooking.booking.dto.UpdateBookingDto;
import ru.ald.officebooking.booking.service.BookingService;
import ru.ald.officebooking.workspace.dto.UpdateWorkspaceDto;

import java.util.UUID;

import static ru.ald.officebooking.common.ApiPaths.ID_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<ResponseBookingDto> createBooking(@RequestBody CreateBookingDto dto) {
        ResponseBookingDto responseDto = bookingService.createBooking(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping(ID_PATH)
    public ResponseEntity<ResponseBookingDto> getBookingById(UUID id) {
        ResponseBookingDto responseDto = bookingService.getBookingById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseBookingDto>> getBookingsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ResponseBookingDto> bookingsPage = bookingService.getBookingsPage(page, size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookingsPage);
    }

    @PatchMapping(ID_PATH)
    public ResponseEntity<ResponseBookingDto> updateBooking(
        @PathVariable UUID id,
        @RequestBody UpdateBookingDto dto
    ) {
        ResponseBookingDto responseDto = bookingService.updateBooking(id, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<Void> deleteBooking(@PathVariable UUID id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

}
