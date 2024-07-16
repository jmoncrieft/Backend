package io.training.catalyte.hotelapi.domains.reservations;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

  private final Logger logger = LoggerFactory.getLogger(ReservationController.class);

  @Autowired
  private ReservationService reservationService;

  /**
   * This method retrieves all reservations from the database
   *
   * @return a collection of reservations and 200 status code
   * @throws Exception
   */
  @GetMapping
  public ResponseEntity<List<Reservation>> getAllReservations() {
    logger.info(" Get all request received");
    return new ResponseEntity<>(reservationService.getAll(), HttpStatus.OK);
  }

  /**
   * This method retrieves a single reservation from the database
   *
   * @return a reservation by the id provided and 200 status code
   * @throws Exception
   */
  @GetMapping("/{id}")
  public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
    logger.info(" Get all request received");
    return new ResponseEntity<>(reservationService.getById(id), HttpStatus.OK);
  }

  /**
   * This method creates a new reservation record and saves it to the database
   *
   * @param reservation to be created
   * @return created reservation and 201 status code
   * @throws Exception
   */
  @PostMapping
  public ResponseEntity<Reservation> createReservation(
      @Valid @RequestBody Reservation reservation) {
    logger.info(" Post request received");
    return new ResponseEntity<>(
        reservationService.createReservation(reservation), HttpStatus.CREATED);
  }

  /**
   * This method updates an existing reservation record
   *
   * @param id          of the reservation to be updated
   * @param reservation updated reservation information
   * @return updated reservation and 200 status code
   * @throws Exception
   */
  @PutMapping("/{id}")
  public ResponseEntity<Reservation> updateReservation(
      @PathVariable Long id, @Valid @RequestBody Reservation reservation) {
    logger.info(" Put request received");
    return new ResponseEntity<>(
        reservationService.updateReservation(id, reservation), HttpStatus.OK);
  }

  /**
   * This method deletes an existing reservation record
   *
   * @param id of the reservation to be deleted
   * @return 204 no content
   * @throws Exception
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Reservation> deleteReservation(@PathVariable Long id) {
    logger.info(" Delete request received");
    reservationService.deleteReservation(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
