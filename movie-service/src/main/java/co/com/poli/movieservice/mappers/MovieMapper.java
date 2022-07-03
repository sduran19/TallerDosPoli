package co.com.poli.movieservice.mappers;

import co.com.poli.movieservice.model.dto.MovieDto;
import co.com.poli.movieservice.model.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDto from(Movie movie);
    @Mapping(target = "id",ignore = true)
    Movie to(MovieDto movieDto);
}
