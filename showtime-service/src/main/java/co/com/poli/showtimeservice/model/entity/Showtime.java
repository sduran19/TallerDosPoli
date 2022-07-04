package co.com.poli.showtimeservice.model.entity;

import co.com.poli.showtimeservice.model.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "showtimes")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@NotEmpty(message = "Fecha no puede ser vacia")
    private Date date;
    @ElementCollection
    private List<Long> moviesId;
    @Transient
    private List<Movie> movies;
}
