package io.training.catalyte.hotelapi.exceptions;

import static io.training.catalyte.hotelapi.constants.StringConstants.BAD_REQUEST;
import static io.training.catalyte.hotelapi.constants.StringConstants.NOT_FOUND;
import static io.training.catalyte.hotelapi.constants.StringConstants.SERVER_ERROR;

import java.rmi.ServerError;
import java.util.Date;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A controller advice allows you to use exactly the same exception handling techniques but apply
 * them across the whole application, not just to an individual controller. You can think of them as
 * an annotation driven interceptor. More info: https://www.baeldung.com/exception-handling-for-rest-with-spring
 */
@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler
  protected ResponseEntity<ExceptionResponse> dataAccess(DataAccessException exception) {

    ExceptionResponse response;

    if (exception.getRootCause().getMessage() != null
        && exception.getRootCause().getMessage().contains("violates unique constraint")
    ) {
      response = new ExceptionResponse(SERVER_ERROR, new Date(),
          exception.getRootCause().getMessage());
      return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    } else {
      response = new ExceptionResponse(SERVER_ERROR, new Date(), exception.getMessage());
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ExceptionHandler(ResourceNotFound.class)
  protected ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFound exception) {
    ExceptionResponse response = new ExceptionResponse(NOT_FOUND, new Date(),
        exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ServerError.class)
  protected ResponseEntity<ExceptionResponse> serverError(ServerError exception) {
    ExceptionResponse response = new ExceptionResponse(SERVER_ERROR, new Date(),
        exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(BadRequest.class)
  protected ResponseEntity<ExceptionResponse> badRequest(BadRequest exception) {
    ExceptionResponse response = new ExceptionResponse(BAD_REQUEST, new Date(),
        exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

}
