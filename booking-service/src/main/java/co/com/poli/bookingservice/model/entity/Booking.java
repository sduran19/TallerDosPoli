package co.com.poli.bookingservice.model.entity;

import co.com.poli.bookingservice.model.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "userid")
    private Long userId;
    @Column(name = "showtimeid")
    private Long showtimeId;
    @Transient
    private List<Movie> movies;
}
