package co.com.poli.bookingservice.clientFeing;

import co.com.poli.bookingservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "showtime-service")
public interface ShowtimeClient {

    @GetMapping("/showtime/{id}")
    Response findByShowtimeId(@PathVariable("id") Long id);
}
