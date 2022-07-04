package co.com.poli.bookingservice.clientFeing;

import co.com.poli.bookingservice.helpers.Response;
import co.com.poli.bookingservice.helpers.ResponseBuild;
import co.com.poli.bookingservice.model.Showtime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowtimeClientImplHystrixFallback implements ShowtimeClient {

    private final ResponseBuild build;
    @Override
    public Response findByShowtimeId(Long id) {
        return build.success(new Showtime());
    }
}
