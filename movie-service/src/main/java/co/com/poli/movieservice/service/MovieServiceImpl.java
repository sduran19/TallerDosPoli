package co.com.poli.movieservice.service;

import co.com.poli.movieservice.clientFeing.ShowtimeClient;
import co.com.poli.movieservice.model.entity.Movie;
import co.com.poli.movieservice.persistence.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ShowtimeClient showtimeClient;

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public String delete(Long id) {
        if (movieRepository.existsById(id) && !moviePresent(id)) {
            movieRepository.deleteById(id);
            return "PELICULA ELIMINADA";
        } else if (movieRepository.existsById(id)) {
            return "PELICULA EN USO";
        }
        return "PELICULA NO ENCONTRADA";
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    private Boolean moviePresent(Long idMovie) {
        Integer states = showtimeClient.findMoviePresentById(idMovie).getCode();
        System.out.println("SE ENCONTRO MOVIE EN SHOWTIME: " + states);
        return states != 404;
    }
}
