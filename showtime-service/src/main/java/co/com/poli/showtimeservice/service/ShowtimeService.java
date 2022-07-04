package co.com.poli.showtimeservice.service;

import co.com.poli.showtimeservice.model.entity.Showtime;

import java.util.List;
import java.util.Optional;

public interface ShowtimeService {
    Showtime save(Showtime showtime);
    List<Showtime> findAll();
    Optional<Showtime> findById(Long id);
    Showtime update(Showtime showtime,Long id);
}
