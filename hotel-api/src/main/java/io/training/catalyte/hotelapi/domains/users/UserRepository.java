package io.training.catalyte.hotelapi.domains.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {

  User findUserByEmail(String email);
}
