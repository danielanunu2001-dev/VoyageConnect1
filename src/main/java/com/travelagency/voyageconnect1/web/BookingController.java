package com.travelagency.voyageconnect1.web;

import com.travelagency.voyageconnect1.domain.dto.BookingDto;
import com.travelagency.voyageconnect1.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/bookings")
@PreAuthorize("isAuthenticated()")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<BookingDto>> createBooking(@Valid @RequestBody BookingDto bookingDto) {
        BookingDto createdBooking = bookingService.createBooking(bookingDto);
        return ResponseEntity
                .created(linkTo(methodOn(BookingController.class).getBookingById(createdBooking.id())).toUri())
                .body(toModel(createdBooking));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BookingDto>> getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .map(this::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<EntityModel<BookingDto>> confirmBooking(@PathVariable Long id) {
        return ResponseEntity.ok(toModel(bookingService.confirmBooking(id)));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<EntityModel<BookingDto>> cancelBooking(@PathVariable Long id) {
        return ResponseEntity.ok(toModel(bookingService.cancelBooking(id)));
    }

    private EntityModel<BookingDto> toModel(BookingDto booking) {
        return EntityModel.of(booking,
                linkTo(methodOn(BookingController.class).getBookingById(booking.id())).withSelfRel(),
                linkTo(methodOn(BookingController.class).confirmBooking(booking.id())).withRel("confirm"),
                linkTo(methodOn(BookingController.class).cancelBooking(booking.id())).withRel("cancel"));
    }
}
