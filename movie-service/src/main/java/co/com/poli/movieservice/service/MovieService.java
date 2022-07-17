package co.com.poli.movieservice.service;

import co.com.poli.movieservice.model.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Movie save(Movie movie);
    String delete(Long id);
    List<Movie> findAll();
    Optional<Movie> findById(Long id);
}
