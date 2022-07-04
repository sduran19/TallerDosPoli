package co.com.poli.userservice.controller;

import co.com.poli.userservice.helpers.ErrorMessage;
import co.com.poli.userservice.helpers.Response;
import co.com.poli.userservice.helpers.ResponseBuild;
import co.com.poli.userservice.mappers.UserMapper;
import co.com.poli.userservice.model.dto.UserDto;
import co.com.poli.userservice.model.entity.User;
import co.com.poli.userservice.service.UserService;
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
public class UserController {

    private final UserService userService;
    private final ResponseBuild responseBuild;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @PostMapping
    public Response save(@Valid @RequestBody UserDto userDto, BindingResult result){
        if (result.hasErrors()){
            return responseBuild.failed(formatMessage(result));
        }
        return responseBuild.created(userService.save(userMapper.to(userDto)));
    }

    @GetMapping
    public Response findAll(){
        List<User> users = userService.findAll();
        return responseBuild.success(users);
    }

    @DeleteMapping("{id}")
    public Response deleteById(@PathVariable("id") Long id){
        if (userService.findById(id).isPresent()){
            userService.delete(id);
            return responseBuild.success();
        }
        return responseBuild.success("No encontrado");
    }

    @GetMapping("{id}")
    public Response findUserById(@PathVariable("id") Long id){
        if (userService.findById(id).isPresent()){
            return responseBuild.success(userService.findById(id));
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
