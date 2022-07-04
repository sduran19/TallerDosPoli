package co.com.poli.bookingservice.mappers;

import co.com.poli.bookingservice.model.dto.BookingDto;
import co.com.poli.bookingservice.model.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDto from(Booking booking);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "movies",ignore = true)
    Booking to(BookingDto bookingDto);
}
