package co.com.poli.movieservice.controller;

import co.com.poli.movieservice.helpers.ErrorMessage;
import co.com.poli.movieservice.helpers.Response;
import co.com.poli.movieservice.helpers.ResponseBuild;
import co.com.poli.movieservice.mappers.MovieMapper;
import co.com.poli.movieservice.model.dto.MovieDto;
import co.com.poli.movieservice.model.entity.Movie;
import co.com.poli.movieservice.service.MovieService;
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
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ResponseBuild responseBuild;
    private final MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

    @PostMapping
    public Response save(@Valid @RequestBody MovieDto movieDto, BindingResult result){
        if (result.hasErrors()){
            return responseBuild.failed(formatMessage(result));
        }
        return responseBuild.created(movieService.save(movieMapper.to(movieDto)));
    }

    @GetMapping
    public Response findAll(){
        List<Movie> movies = movieService.findAll();
        return responseBuild.success(movies);
    }

    @GetMapping("{id}")
    public Response findById(@PathVariable("id") Long id){
        return responseBuild.success(movieService.findById(id));
    }

    @DeleteMapping("{id}")
    public Response deleteById(@PathVariable("id") Long id){
        if (movieService.delete(id)){
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
