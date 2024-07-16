package io.training.catalyte.hotelapi.constants;

public class StringConstants {

  public static final String NOT_FOUND = "Not Found";
  public static final String SERVER_ERROR = "Server Error";
  public static final String BAD_REQUEST = "Bad Request, check your input and try again";

  // Auth
  public static final String UNAUTHORIZED = "Unauthorized";
  public static final String INVALID_EMAIL_PASSWORD = "Invalid email or password.";
  public static final String ISSUER = "hotel-api";
  public static final String SECRET_KEY = "secret";
  public static final String CLAIMS_ATTRIBUTE = "claims";
  public static final String EMAIL_ATTRIBUTE = "email";
  public static final String ROLES_ATTRIBUTE = "roles";
  public static final String MANAGER_ROLE_TYPE = "manager";
  public static final String EMPLOYEE_ROLE_TYPE = "employee";
  public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String MISSING_INVALID_ERROR = "Missing or invalid Authorization header";
  public static final String APPLICATION_JSON = "application/json";

  // RoomType
  public static final String NAME_VALIDATION_ERROR = "must have length greater than 3";
  public static final String RATE_VALIDATION_ERROR = "rate should be a positive value greater than zero";

  // Reservations
  public static final String GUEST_EMAIL_VALIDATION_ERROR = "must be an email format";
  public static final String POSITIVE_ID_VALIDATION_ERROR = "id must be a postive";
  public static final String NUMBER_OF_NIGHTS_POSITIVE_VALIDATION_ERROR = "must be an email format";
  public static final String DATE_FORMAT = "MM-dd-yyyy";
  public static final String DATE_FORMAT_ERROR = "date did not meet required format of MM-dd-yyyy";
  public static final String ROOM_TYPE_ID_NULL_ERROR = "NotNull.reservation.roomTypeId";
  public static final String GUEST_EMAIL_BLANK_ERROR = "NotBlank.reservation.guestEmail";

}
