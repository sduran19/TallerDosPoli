package co.com.poli.bookingservice.model.dto;

import co.com.poli.bookingservice.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@Data
public class BookingDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long showtimeId;
}
