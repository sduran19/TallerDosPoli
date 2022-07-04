package co.com.poli.showtimeservice.service;

import co.com.poli.showtimeservice.clientFeign.MovieClient;
import co.com.poli.showtimeservice.model.Movie;
import co.com.poli.showtimeservice.model.entity.Showtime;
import co.com.poli.showtimeservice.persistence.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieClient movieClient;


    @Override
    public Showtime save(Showtime showtime) {
        if (moviesExist(showtime)) {
            return showtimeRepository.save(showtime);
        }
        return null;
    }

    private Boolean moviesExist(Showtime showtime) {
        List<Integer> states = showtime.getMoviesId().stream()
                .map(ids -> movieClient.findByMovidId(ids).getCode())
                .collect(Collectors.toList());
        return states.stream().noneMatch(integer -> integer == 404);
    }

    private List<Movie> listMovies(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Showtime> rs = showtimeRepository.findById(id);
        return rs.get().getMoviesId().stream()
                .map(ids -> modelMapper.map(movieClient.findByMovidId(ids).getData(), Movie.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Showtime> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<Showtime> showtimes = showtimeRepository.findAll();
        showtimes.stream().forEach(showtime -> {
            List<Movie> movies = showtime.getMoviesId().stream()
                    .map(ids -> modelMapper.map(movieClient.findByMovidId(ids).getData(), Movie.class))
                    .collect(Collectors.toList());
            showtime.setMovies(movies);
        });
        return showtimes;
    }

    @Override
    public Optional<Showtime> findById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Showtime> rs = showtimeRepository.findById(id);
        List<Movie> movies = rs.get().getMoviesId().stream()
                .map(ids -> modelMapper.map(movieClient.findByMovidId(ids).getData(), Movie.class))
                .collect(Collectors.toList());
        rs.get().setMovies(movies);
        return rs;
    }
}