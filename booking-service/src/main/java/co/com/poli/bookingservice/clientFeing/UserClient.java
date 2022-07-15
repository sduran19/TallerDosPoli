package co.com.poli.bookingservice.clientFeing;

import co.com.poli.bookingservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", fallback = UserClientImplHystrixFallback.class)
public interface UserClient {

    @GetMapping("/users/{id}")
    Response findByUserId(@PathVariable("id") Long id);
}
