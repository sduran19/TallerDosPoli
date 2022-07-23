package co.com.poli.movieservice;

import co.com.poli.movieservice.clientFeing.ShowtimeClient;
import co.com.poli.movieservice.model.entity.Movie;
import co.com.poli.movieservice.persistence.repository.MovieRepository;
import co.com.poli.movieservice.service.MovieService;
import co.com.poli.movieservice.service.MovieServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MovieServiceMockTest {

    @Mock
    private MovieRepository movieRepository;
    private ShowtimeClient showtimeClient;
    private MovieService movieService;

    @BeforeEach
    public void begin(){
        MockitoAnnotations.openMocks(this);
        movieService = new MovieServiceImpl(movieRepository,showtimeClient);

        Movie movie = Movie.builder()
                .id(5L)
                .title("IronMan")
                .director("MARVEL")
                .rating(4).build();
        Mockito.when(movieRepository.findById(5L))
                .thenReturn(Optional.of(movie));
    }

    @Test
    public void when_findById_return_Movie(){
        Optional<Movie> movie = movieService.findById(5L);
        Assertions.assertThat(movie.get().getTitle()).isEqualTo("IronMan");
        Assertions.assertThat(movie.get().getDirector()).isEqualTo("MARVEL");
        Assertions.assertThat(movie.get().getRating()).isEqualTo(4);
    }
}
