package es.artyhub.banco_back.spring.exception;

import es.artyhub.banco_back.domain.exception.BusinessException;
import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiHandlerException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ErrorMessage handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorMessage(ex);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class,IllegalArgumentException.class, BusinessException.class})
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ErrorMessage handleValidationException(ResourceNotFoundException ex) {
        return new ErrorMessage(ex);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class,RuntimeException.class})
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ErrorMessage handleGeneralException(Exception exception) {
        return new ErrorMessage(exception);
    }


}
