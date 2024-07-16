package io.training.catalyte.hotelapi.domains.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Email data was not supplied")
  private String email;

  @NotBlank(message = "Password data was not supplied")
  private String password;

  @NotBlank(message = "Role data was not supplied")
  private String role;

  public User() {
  }

  public User(
      @NotBlank(message = "Email data was not supplied") String email,
      @NotBlank(message = "Password data was not supplied") String password,
      @NotBlank(message = "Role data was not supplied") String role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User user = (User) o;

    if (!getEmail().equals(user.getEmail())) {
      return false;
    }
    if (!getPassword().equals(user.getPassword())) {
      return false;
    }
    return getRole().equals(user.getRole());
  }

  @Override
  public int hashCode() {
    int result = getEmail().hashCode();
    result = 31 * result + getPassword().hashCode();
    result = 31 * result + getRole().hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", role='" + role + '\'' +
        '}';
  }
}
