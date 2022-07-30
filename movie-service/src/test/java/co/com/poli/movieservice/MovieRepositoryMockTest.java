package co.com.poli.movieservice;

import co.com.poli.movieservice.model.entity.Movie;
import co.com.poli.movieservice.persistence.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class MovieRepositoryMockTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void when_findAllMovies_return_ListMovies(){
        Movie movie = Movie.builder()
                .title("Superman")
                .director("DC")
                .rating(3).build();
        movieRepository.save(movie);
        List<Movie> movies = movieRepository.findAll();
        Assertions.assertThat(movies.size()).isEqualTo(1);
    }
}
