package io.training.catalyte.hotelapi.security;

public interface AuthService {

  AuthToken login(Credential credentials);
}
