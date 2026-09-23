package com.sagar.project.airBnbApp.repository;

import com.sagar.project.airBnbApp.entity.Booking;
import com.sagar.project.airBnbApp.entity.Hotel;
import com.sagar.project.airBnbApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByPaymentSessionId(String sessionId);

    List<Booking> findByHotel(Hotel hotel);

    List<Booking> findByHotelAndCreatedAtBetween(Hotel hotel, LocalDateTime startDateTime, LocalDateTime endDateTime);

    Collection<Object> findByUser(User user);
}