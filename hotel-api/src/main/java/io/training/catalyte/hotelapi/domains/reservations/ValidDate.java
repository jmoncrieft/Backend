package io.training.catalyte.hotelapi.domains.reservations;

import static io.training.catalyte.hotelapi.constants.StringConstants.DATE_FORMAT_ERROR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface ValidDate {

  String message() default DATE_FORMAT_ERROR;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
