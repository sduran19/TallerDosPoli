package co.com.poli.bookingservice.service;

import co.com.poli.bookingservice.clientFeing.MovieClient;
import co.com.poli.bookingservice.clientFeing.ShowtimeClient;
import co.com.poli.bookingservice.clientFeing.UserClient;
import co.com.poli.bookingservice.model.Movie;
import co.com.poli.bookingservice.model.Showtime;
import co.com.poli.bookingservice.model.entity.Booking;
import co.com.poli.bookingservice.persistence.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final UserClient userClient;
    private final ShowtimeClient showtimeClient;
    private final MovieClient movieClient;

    @Override
    public Booking save(Booking booking) {
        if (userExist(booking) && showtimeExist(booking)){
            return bookingRepository.save(booking);
        }
        return null;
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> bookings = bookingRepository.findAll();
        bookings.forEach(
                booking -> booking.setMovies(listMovies(booking.getShowtimeId()))
        );
        return bookings;
    }

    @Override
    public Optional<Booking> findById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        booking.get().setMovies(listMovies(booking.get().getShowtimeId()));
        return booking;
    }

    @Override
    public List<Booking> findByUserId(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        bookings.forEach(
                booking -> booking.setMovies(listMovies(booking.getShowtimeId()))
        );
        return bookings;
    }

    @Override
    public Boolean delete(Long id) {
        if (bookingRepository.existsById(id)){
            bookingRepository.deleteById(id);
            return true;
        }
        return bookingRepository.existsById(id);
    }

    private Boolean userExist(Booking booking) {
        Integer states = userClient.findByUserId(booking.getUserId()).getCode();
        return states != 404;
    }

    private Boolean showtimeExist(Booking booking) {
        Integer states = showtimeClient.findByShowtimeId(booking.getShowtimeId()).getCode();
        return states != 404;
    }

    private List<Movie> listMovies(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Showtime rs = modelMapper.map(showtimeClient.findByShowtimeId(id).getData(),Showtime.class);
        return rs.getMoviesId().stream()
                .map(ids -> modelMapper.map(movieClient.findByMovidId(ids).getData(), Movie.class))
                .collect(Collectors.toList());
    }
}
