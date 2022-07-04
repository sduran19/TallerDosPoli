package co.com.poli.bookingservice.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Showtime {
    private Long id;
    private Date date;
    private List<Long> moviesId;
}
