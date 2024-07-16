package io.training.catalyte.hotelapi.domains.reservations;

import static io.training.catalyte.hotelapi.constants.StringConstants.DATE_FORMAT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDate, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

    boolean validDate = isValidFormat(DATE_FORMAT, value);

    return validDate;
  }

  private static boolean isValidFormat(String format, String value) {
    Date date = null;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      if (value != null) {
        date = sdf.parse(value);
        if (!value.equals(sdf.format(date))) {
          date = null;
        }
      }

    } catch (ParseException ex) {
    }
    return date != null;
  }
}
