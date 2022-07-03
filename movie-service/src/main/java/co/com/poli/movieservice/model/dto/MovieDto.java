package co.com.poli.movieservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Builder
@AllArgsConstructor
@ToString
@Data
public class MovieDto {
    @NotEmpty(message = "El titulo no puede ser vacio")
    private String title;
    @NotEmpty(message = "El director no puede ser vacio")
    private String director;
    @Min(1)
    @Max(5)
    private Integer rating;
}
