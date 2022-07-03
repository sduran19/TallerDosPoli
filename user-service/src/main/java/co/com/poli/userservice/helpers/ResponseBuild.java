package co.com.poli.userservice.helpers;

import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public class ResponseBuild {

    public Response success() {
        return Response.builder()
                .data(OK)
                .code(OK.value()).build();
    }

    public Response success(Object data) {
        return Response.builder()
                .data(data)
                .code(OK.value()).build();
    }

    public Response created(Object data) {
        return Response.builder()
                .data(data)
                .code(CREATED.value()).build();
    }

    public Response failed(Object data) {
        return Response.builder()
                .data(data)
                .code(BAD_REQUEST.value()).build();
    }

    public Response notFound() {
        return Response.builder()
                .data(NOT_FOUND)
                .code(NOT_FOUND.value()).build();
    }



}
