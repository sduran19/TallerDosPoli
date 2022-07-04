package co.com.poli.showtimeservice.model.dto;

import co.com.poli.showtimeservice.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Builder
@AllArgsConstructor
@ToString
@Data
public class ShowtimeDto {
    //@NotEmpty(message = "Fecha no puede ser vacia")
    private Date date;
    private List<Long> moviesId;
}
