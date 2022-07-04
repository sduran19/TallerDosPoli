package co.com.poli.bookingservice.clientFeing;

import co.com.poli.bookingservice.helpers.Response;
import co.com.poli.bookingservice.helpers.ResponseBuild;
import co.com.poli.bookingservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClientImplHystrixFallback implements UserClient {

    private final ResponseBuild build;

    @Override
    public Response findByUserId(Long id) {
        return build.success(new User());
    }
}
