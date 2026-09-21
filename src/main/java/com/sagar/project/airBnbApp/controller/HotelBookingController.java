package com.sagar.project.airBnbApp.controller;

import com.sagar.project.airBnbApp.dto.BookingDto;
import com.sagar.project.airBnbApp.dto.BookingRequest;
import com.sagar.project.airBnbApp.dto.GuestDto;
import com.sagar.project.airBnbApp.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {
    private final BookingService bookingService;
    @PostMapping("/init")
    public ResponseEntity<BookingDto> initialiseRequest(@RequestBody BookingRequest bookingRequest) {
        return  ResponseEntity.ok(bookingService.initialiseBooking(bookingRequest));
    }


    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<BookingDto> addGuests(@PathVariable Long bookingId, @RequestBody List<GuestDto> guestDtoList) {
        return  ResponseEntity.ok(bookingService.addGuests(bookingId,guestDtoList));
    }
}
