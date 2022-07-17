package co.com.poli.userservice.clientFeing;

import co.com.poli.userservice.helpers.Response;
import co.com.poli.userservice.helpers.ResponseBuild;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingClientImplHystrixFallback implements BookingClient{

    private final ResponseBuild build;

    @Override
    public Response findBookingByUserId(Long id) {
        return build.failed(null);
    }
}
