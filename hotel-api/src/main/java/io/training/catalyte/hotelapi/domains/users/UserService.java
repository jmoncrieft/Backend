package io.training.catalyte.hotelapi.domains.users;

public interface UserService {

  User createUser(User user);

  User findByEmail(String email);

}
