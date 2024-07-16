package io.training.catalyte.hotelapi.exceptions;

public class BadRequest extends RuntimeException {

  public BadRequest() {
  }

  public BadRequest(String message) {
    super(message);
  }
}
