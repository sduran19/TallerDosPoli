package co.com.poli.bookingservice.controller;

import co.com.poli.bookingservice.helpers.ErrorMessage;
import co.com.poli.bookingservice.helpers.Response;
import co.com.poli.bookingservice.helpers.ResponseBuild;
import co.com.poli.bookingservice.mappers.BookingMapper;
import co.com.poli.bookingservice.model.dto.BookingDto;
import co.com.poli.bookingservice.model.entity.Booking;
import co.com.poli.bookingservice.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final ResponseBuild responseBuild;
    private final BookingMapper bookingMapper = Mappers.getMapper(BookingMapper.class);

    @PostMapping
    public Response save(@Valid @RequestBody BookingDto bookingDto, BindingResult result){
        if (result.hasErrors()){
            return responseBuild.failed(formatMessage(result));
        }
        Booking st = bookingService.save(bookingMapper.to(bookingDto));
        if(st==null){
            return responseBuild.failed("Registros no encontardos");
        }
        return responseBuild.created(bookingService.findById(st.getId()));
    }

    @GetMapping
    public Response findAll(){
        List<Booking> showtimes = bookingService.findAll();
        if (showtimes==null){
            return responseBuild.notFound();
        }
        return responseBuild.success(showtimes);
    }

    @GetMapping("{id}")
    public Response findById(@PathVariable("id") Long id){
        Optional<Booking> booking = bookingService.findById(id);
        if (!booking.isPresent()){
            return responseBuild.notFound();
        }
        return responseBuild.success(booking);
    }

    @GetMapping("/user/{id}")
    public Response findByUserId(@PathVariable("id") Long id){
        List<Booking> bookings = bookingService.findByUserId(id);
        if (bookings.isEmpty()){
            return responseBuild.notFound();
        }
        return responseBuild.success(bookings);
    }

    @DeleteMapping("{id}")
    public Response delete(@PathVariable("id") Long id){
        if(bookingService.delete(id)){
            return responseBuild.success();
        }
        return responseBuild.notFound();
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> err = new HashMap<>();
                    err.put(error.getField(), error.getDefaultMessage());
                    return err;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .error(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}
