package io.training.catalyte.hotelapi.domains.reservations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

  private final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

  @Autowired private ReservationRepository reservationRepository;

  /**
   * Retrieves all reservations from the database.
   *
   * @return a list of all reservations.
   */
  @Override
  public List<Reservation> getAll() {
    List<Reservation> reservationList = new ArrayList<>();

    try {
      reservationList = reservationRepository.findAll();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    return reservationList.stream()
        .sorted(Comparator.comparing(Reservation::getId))
        .collect(Collectors.toList());
  }

  /**
   * Retrieves a reservation from the database by its id.
   *
   * @param id the id of the reservation to retrieve
   * @return the specified reservation
   */
  @Override
  public Reservation getById(Long id) {
    Optional<Reservation> reservation = Optional.empty();

    try {
      reservation = reservationRepository.findById(id);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    if (reservation.isEmpty()) {
      throw new ResourceNotFoundException();
    } else {
      return reservation.get();
    }
  }

  /**
   * Persists a new reservation to the database.
   *
   * @param reservation the reservation object to persist
   * @return the persisted reservation
   */
  @Override
  public Reservation createReservation(Reservation reservation) {
    Reservation postedReservation = null;

    try {
      postedReservation = reservationRepository.save(reservation);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    return postedReservation;
  }

  /**
   * Updates a specified record in the database.
   *
   * @param id the id of the record to update
   * @param reservation the provided reservation information to persist
   * @return the updated reservation
   */
  @Override
  public Reservation updateReservation(Long id, Reservation reservation) {
    try {
      Optional<Reservation> reservationToUpdate = reservationRepository.findById(id);
      if (reservationToUpdate.isEmpty()) {
        throw new ResourceNotFoundException();
      } else {
        reservation.setId(id);
        reservationRepository.save(reservation);
      }
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    return reservation;
  }

  /**
   * Deletes a reservation by id from the database.
   *
   * @param id the id of the reservation to delete
   */
  @Override
  public void deleteReservation(Long id) {
    try {
      reservationRepository.deleteById(id);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }
  }
}
