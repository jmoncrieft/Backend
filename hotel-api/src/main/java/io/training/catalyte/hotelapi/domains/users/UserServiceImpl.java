package io.training.catalyte.hotelapi.domains.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserRepository userRepository;

  @Override
  public User createUser(User user) {

    User postedUser = null;

    try {
      postedUser = userRepository.save(user);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    return postedUser;
  }

  @Override
  public User findByEmail(String email) {
    User foundUser = null;

    try {
      foundUser = userRepository.findUserByEmail(email);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    return foundUser;
  }

}
