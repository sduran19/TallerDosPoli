package co.com.poli.bookingservice.service;

import co.com.poli.bookingservice.model.entity.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking save(Booking booking);
    List<Booking> findAll();
    Optional<Booking> findById(Long id);
    List<Booking> findByUserId(Long userId);
    Boolean delete(Long id);
}
