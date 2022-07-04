package co.com.poli.bookingservice.clientFeing;

import co.com.poli.bookingservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service", fallback = MovieClientImplHystrixFallback.class)
public interface MovieClient {

    @GetMapping("/movies/{id}")
    Response findByMovidId(@PathVariable("id") Long id);
}
