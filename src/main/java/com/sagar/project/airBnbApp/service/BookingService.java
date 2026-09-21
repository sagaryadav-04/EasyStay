package com.sagar.project.airBnbApp.service;

import com.sagar.project.airBnbApp.dto.BookingDto;
import com.sagar.project.airBnbApp.dto.BookingRequest;
import com.sagar.project.airBnbApp.dto.GuestDto;

import java.util.List;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
