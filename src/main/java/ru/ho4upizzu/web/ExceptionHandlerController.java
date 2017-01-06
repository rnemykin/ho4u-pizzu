package ru.ho4upizzu.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.ho4upizzu.exception.InvalidParameterException;
import ru.ho4upizzu.exception.ObjectNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void badRequestHandle(Exception ex) {
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFoundHandle(ObjectNotFoundException ex) {
    }
}
