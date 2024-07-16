package io.training.catalyte.hotelapi.domains.reservations;

import static io.training.catalyte.hotelapi.constants.StringConstants.GUEST_EMAIL_VALIDATION_ERROR;
import static io.training.catalyte.hotelapi.constants.StringConstants.NUMBER_OF_NIGHTS_POSITIVE_VALIDATION_ERROR;
import static io.training.catalyte.hotelapi.constants.StringConstants.POSITIVE_ID_VALIDATION_ERROR;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /*
    postgres throws up if you try to name a column "user", so we map it to hotelUser in the db but
    we keep the field as user on requests / responses
   */
  @Column(name = "hotelUser")
  @NotBlank
  private String user;

  @NotBlank
  @Email(message = GUEST_EMAIL_VALIDATION_ERROR)
  private String guestEmail;

  @Positive(message = POSITIVE_ID_VALIDATION_ERROR)
  @NotNull
  private Long roomTypeId;

  @ValidDate()
  private String checkInDate;

  @Positive(message = NUMBER_OF_NIGHTS_POSITIVE_VALIDATION_ERROR)
  @NotNull
  private int numberOfNights;

  public Reservation() {
  }

  public Reservation(String user, String guestEmail, Long roomTypeId, String checkInDate,
      int numberOfNights) {
    this.user = user;
    this.guestEmail = guestEmail;
    this.roomTypeId = roomTypeId;
    this.checkInDate = checkInDate;
    this.numberOfNights = numberOfNights;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getGuestEmail() {
    return guestEmail;
  }

  public void setGuestEmail(String guestEmail) {
    this.guestEmail = guestEmail;
  }

  public Long getRoomTypeId() {
    return roomTypeId;
  }

  public void setRoomTypeId(Long roomId) {
    this.roomTypeId = roomId;
  }

  public String getCheckInDate() {
    return checkInDate;
  }

  public void setCheckInDate(String checkInDate) {
    this.checkInDate = checkInDate;
  }

  public int getNumberOfNights() {
    return numberOfNights;
  }

  public void setNumberOfNights(int numberOfNights) {
    this.numberOfNights = numberOfNights;
  }
}
