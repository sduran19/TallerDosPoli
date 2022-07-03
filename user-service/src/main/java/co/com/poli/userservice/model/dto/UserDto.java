package co.com.poli.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Builder
@AllArgsConstructor
@ToString
@Data
public class UserDto {
    @NotEmpty(message = "No puede estar en blanco")
    private String name;
    @NotEmpty(message = "No puede estar en blanco")
    private String lastName;
}
