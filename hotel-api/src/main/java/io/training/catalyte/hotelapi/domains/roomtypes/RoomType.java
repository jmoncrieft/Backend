package io.training.catalyte.hotelapi.domains.roomtypes;

import static io.training.catalyte.hotelapi.constants.StringConstants.NAME_VALIDATION_ERROR;
import static io.training.catalyte.hotelapi.constants.StringConstants.RATE_VALIDATION_ERROR;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class RoomType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(min = 3, message = NAME_VALIDATION_ERROR)
  private String name;

  private String description;

  @NotNull
  @Positive(message = RATE_VALIDATION_ERROR)
  private BigDecimal rate;

  private boolean active;

  public RoomType(String name, String description, BigDecimal rate, boolean active) {
    this.name = name;
    this.description = description;
    this.rate = rate;
    this.active = active;
  }

  public RoomType() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String roomType) {
    this.name = roomType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    RoomType roomType = (RoomType) o;

    if (isActive() != roomType.isActive()) {
      return false;
    }
    if (!getName().equals(roomType.getName())) {
      return false;
    }
    if (!getDescription().equals(roomType.getDescription())) {
      return false;
    }
    return getRate().equals(roomType.getRate());
  }

  @Override
  public int hashCode() {
    int result = getDescription().hashCode();
    result = 31 * result + getRate().hashCode();
    result = 31 * result + (isActive() ? 1 : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Room{" +
        "id=" + id +
        ", roomType='" + name + '\'' +
        ", description='" + description + '\'' +
        ", rate=" + rate +
        ", active=" + active +
        '}';
  }
}
