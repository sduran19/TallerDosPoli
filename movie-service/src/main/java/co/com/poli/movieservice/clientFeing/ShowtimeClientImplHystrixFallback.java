package co.com.poli.movieservice.clientFeing;

import co.com.poli.movieservice.helpers.Response;
import co.com.poli.movieservice.helpers.ResponseBuild;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowtimeClientImplHystrixFallback implements ShowtimeClient{

    private final ResponseBuild build;

    @Override
    public Response findMoviePresentById(Long id) {
        return build.failed(id);
    }
}
