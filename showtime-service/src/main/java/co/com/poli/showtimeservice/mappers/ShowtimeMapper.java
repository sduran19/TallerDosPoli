package co.com.poli.showtimeservice.mappers;

import co.com.poli.showtimeservice.model.dto.ShowtimeDto;
import co.com.poli.showtimeservice.model.entity.Showtime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {

    ShowtimeDto from(Showtime showtime);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "movies",ignore = true)
    Showtime to(ShowtimeDto showtimeDto);
}
