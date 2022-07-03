package co.com.poli.userservice.mappers;

import co.com.poli.userservice.model.dto.UserDto;
import co.com.poli.userservice.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto from(User user);
    @Mapping(target = "id",ignore = true)
    User to(UserDto userDto);
}
