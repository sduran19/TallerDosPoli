package co.com.poli.movieservice.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "El titulo no puede ser vacio")
    private String title;
    @NotEmpty(message = "El director no puede ser vacio")
    private String director;
    @Min(1)
    @Max(5)
    private Integer rating;
}
