package com.example.project2.Commom.Error;

import com.example.project2.Commom.Exception.IdNotFoundException;
import com.example.project2.Commom.Exception.PermissRoleError;
import com.example.project2.Commom.Exception.UnAuthorException;
import com.example.project2.Commom.Exception.UsernameOrPasswordNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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
    public ErrorModelResponse unauthor(UnAuthorException ex) {
        return new ErrorModelResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }
}
