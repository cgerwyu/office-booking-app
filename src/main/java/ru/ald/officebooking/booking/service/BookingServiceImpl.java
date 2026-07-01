package ru.ald.officebooking.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ald.officebooking.booking.dto.ResponseBookingDto;
import ru.ald.officebooking.booking.dto.CreateBookingDto;
import ru.ald.officebooking.booking.dto.UpdateBookingDto;
import ru.ald.officebooking.booking.mapper.BookingMapper;
import ru.ald.officebooking.booking.model.Booking;
import ru.ald.officebooking.booking.repository.BookingRepository;
import ru.ald.officebooking.exception.NotFoundException;
import ru.ald.officebooking.exception.ValidationException;
import ru.ald.officebooking.user.model.User;
import ru.ald.officebooking.user.service.UserService;
import ru.ald.officebooking.workspace.model.Workspace;
import ru.ald.officebooking.workspace.service.WorkspaceService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    UserService userService;
    WorkspaceService workspaceService;

    public ResponseBookingDto createBooking(CreateBookingDto dto) {
        User booker = userService.getUserEntityById(dto.getBookerId());
        Workspace workspace = workspaceService.getWorkspaceEntityById(dto.getWorkspaceId());

        if (dto.getEndsAt().isAfter(dto.getStartsAt()) || dto.getEndsAt() == dto.getStartsAt()) {
            throw new ValidationException("startsAt should be before endsAt.");
        }

        Booking booking = bookingMapper.mapCreateDtoToBooking(dto, booker, workspace);
        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.mapBookingToResponseDto(savedBooking);
    }

    public ResponseBookingDto getBookingById(UUID id) {
        Booking booking = getBookingByIdIfExists(id);

        return bookingMapper.mapBookingToResponseDto(booking);
    }

    public Page<ResponseBookingDto> getBookingsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Booking> bookingsPage = bookingRepository.findAll(pageable);

        return bookingsPage.map(bookingMapper::mapBookingToResponseDto);
    }

    public ResponseBookingDto updateBooking(UUID id, UpdateBookingDto dto) {
        Booking booking = getBookingByIdIfExists(id);

        User newBooker = userService.getUserEntityById(dto.getBookerId());
        booking.setBooker(newBooker);

        Workspace newWorkspace = workspaceService.getWorkspaceEntityById(dto.getWorkspaceId());
        booking.setWorkspace(newWorkspace);

        validateDates(
            booking.getStartsAt(), booking.getEndsAt(),
            dto.getStartsAt(), dto.getEndsAt()
        );
        if (dto.getStartsAt() != null) {
            booking.setStartsAt(dto.getStartsAt());
        }
        if (dto.getEndsAt() != null) {
            booking.setEndsAt(dto.getEndsAt());
        }

        return bookingMapper.mapBookingToResponseDto(booking);
    }

    public void deleteBooking(UUID id) {
        Booking booking = getBookingByIdIfExists(id);

        bookingRepository.delete(booking);
    }

    private Booking getBookingByIdIfExists(UUID id) {
        return bookingRepository.getBookingById(id).orElseThrow(
            () -> new NotFoundException(
                String.format("Booking with id = %s not found.", id)
            )
        );
    }

    private void validateDates(
            LocalDateTime currStartsAt, LocalDateTime currEndsAt,
            LocalDateTime newStartsAt, LocalDateTime newEndsAt) {
        if (newStartsAt != null) {
            if (newEndsAt != null) {
                if (newStartsAt.isAfter(newEndsAt) || newStartsAt.isEqual(newEndsAt)) {
                    throw new ValidationException("startsAt should be before endsAt.");
                }
            } else {
                if (newStartsAt.isAfter(currEndsAt) || newStartsAt.isEqual(currEndsAt)) {
                    throw new ValidationException("startsAt should be before endsAt.");
                }
            }
        } else if (newEndsAt != null) {
            if (newEndsAt.isBefore(currStartsAt) || newEndsAt.isEqual(currStartsAt)) {
                throw new ValidationException("endsAt should be after startsAt.");
            }
        }
    }

}
