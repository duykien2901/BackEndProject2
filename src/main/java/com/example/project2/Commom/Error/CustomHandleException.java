package com.example.project2.Commom.Error;

import com.example.project2.Commom.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.Time;

@RestControllerAdvice
public class CustomHandleException {
    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModelResponse notFound(IdNotFoundException ex, WebRequest request) {
        return new ErrorModelResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({UsernameOrPasswordNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModelResponse usernamePasswordNotFound(UsernameOrPasswordNotFound ex) {
        return new ErrorModelResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({PermissRoleError.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorModelResponse permissionError(PermissRoleError ex) {
        return new ErrorModelResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UnAuthorException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorModelResponse unAuthor(UnAuthorException ex) {
        return new ErrorModelResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(DeviceIsExist.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorModelResponse serverError(DeviceIsExist ex) {
        return new ErrorModelResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(TimetableIsExist.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorModelResponse badRequest(TimetableIsExist ex) {
        return new ErrorModelResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(UsernameIsExist.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModelResponse usernameIsExist(UsernameIsExist ex) {
        return new ErrorModelResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorModelResponse badRequest(InternalServerError ex) {
        return new ErrorModelResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
