package io.training.catalyte.hotelapi.security;

public class AuthToken {

  private String token;

  public AuthToken(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
