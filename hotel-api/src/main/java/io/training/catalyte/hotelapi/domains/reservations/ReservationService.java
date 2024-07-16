package io.training.catalyte.hotelapi.domains.reservations;

import java.util.List;

public interface ReservationService {

  List<Reservation> getAll();

  Reservation getById(Long id);

  Reservation createReservation(Reservation reservation);

  Reservation updateReservation(Long id, Reservation reservation);

  void deleteReservation(Long id);

}
