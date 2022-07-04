package co.com.poli.showtimeservice.controller;

import co.com.poli.showtimeservice.helpers.ErrorMessage;
import co.com.poli.showtimeservice.helpers.Response;
import co.com.poli.showtimeservice.helpers.ResponseBuild;
import co.com.poli.showtimeservice.mappers.ShowtimeMapper;
import co.com.poli.showtimeservice.model.dto.ShowtimeDto;
import co.com.poli.showtimeservice.model.entity.Showtime;
import co.com.poli.showtimeservice.service.ShowtimeService;
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
public class ShowtimeController {

    private final ShowtimeService showtimeService;
    private final ResponseBuild responseBuild;
    private final ShowtimeMapper showtimeMapper = Mappers.getMapper(ShowtimeMapper.class);

    @PostMapping
    public Response save(@Valid @RequestBody ShowtimeDto showtimeDto, BindingResult result){
        if (result.hasErrors()){
            return responseBuild.failed(formatMessage(result));
        }
        Showtime st = showtimeService.save(showtimeMapper.to(showtimeDto));
        if(st==null){
            return responseBuild.failed("Movies no encontradas");
        }
        return responseBuild.created(showtimeService.findById(st.getId()));
    }

    @GetMapping
    public Response findAll(){
        List<Showtime> showtimes = showtimeService.findAll();
        if (showtimes==null){
            return responseBuild.notFound();
        }
        return responseBuild.success(showtimes);
    }

    @GetMapping("{id}")
    public Response findById(@PathVariable("id") Long id){
        Optional<Showtime> showtime = showtimeService.findById(id);
        if (!showtime.isPresent()){
            return responseBuild.notFound();
        }
        return responseBuild.success(showtime);
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
