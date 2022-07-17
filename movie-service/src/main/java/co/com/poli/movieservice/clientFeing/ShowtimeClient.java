package co.com.poli.movieservice.clientFeing;

import co.com.poli.movieservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "showtime-service")
public interface ShowtimeClient {

    @GetMapping("/showtime/movie/{id}")
    Response findMoviePresentById(@PathVariable("id") Long id);
}
