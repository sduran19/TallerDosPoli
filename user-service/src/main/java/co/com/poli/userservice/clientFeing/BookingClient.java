package co.com.poli.userservice.clientFeing;

import co.com.poli.userservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service", fallback = BookingClientImplHystrixFallback.class)
public interface BookingClient {

    @GetMapping("/bookings/user/{id}")
    Response findBookingByUserId(@PathVariable("id") Long id);
}
