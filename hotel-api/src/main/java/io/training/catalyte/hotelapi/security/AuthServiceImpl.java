package io.training.catalyte.hotelapi.security;

import static io.training.catalyte.hotelapi.constants.StringConstants.EMPLOYEE_ROLE_TYPE;
import static io.training.catalyte.hotelapi.constants.StringConstants.INVALID_EMAIL_PASSWORD;
import static io.training.catalyte.hotelapi.constants.StringConstants.ISSUER;
import static io.training.catalyte.hotelapi.constants.StringConstants.MANAGER_ROLE_TYPE;
import static io.training.catalyte.hotelapi.constants.StringConstants.ROLES_ATTRIBUTE;
import static io.training.catalyte.hotelapi.constants.StringConstants.SECRET_KEY;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.training.catalyte.hotelapi.domains.users.User;
import io.training.catalyte.hotelapi.domains.users.UserService;
import io.training.catalyte.hotelapi.exceptions.BadRequest;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public AuthServiceImpl(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userService = userService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  private static boolean userIsManager(String role) {
    return role.equals(MANAGER_ROLE_TYPE);
  }

  @Override
  public AuthToken login(Credential credentials) {
    if (credentials.getEmail() == null || credentials.getPassword() == null) {
      throw new BadRequest(INVALID_EMAIL_PASSWORD);
    }

    String email = credentials.getEmail();
    String password = credentials.getPassword();

    User user = userService.findByEmail(email);

    if (user == null) {
      throw new BadRequest(INVALID_EMAIL_PASSWORD);
    }

    String passwordOnFile = user.getPassword();

    if (!bCryptPasswordEncoder.matches(password, passwordOnFile)) {
      throw new BadRequest(INVALID_EMAIL_PASSWORD);
    }

    String userRole = EMPLOYEE_ROLE_TYPE;

    if (userIsManager(user.getRole())) {
      userRole = MANAGER_ROLE_TYPE;
    }

    Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
    String jwtToken =
        JWT.create()
            .withIssuer(ISSUER)
            .withClaim(ROLES_ATTRIBUTE, userRole)
            .withSubject(email)
            .withIssuedAt(new Date())
            .sign(algorithm);

    return new AuthToken(jwtToken);
  }
}
