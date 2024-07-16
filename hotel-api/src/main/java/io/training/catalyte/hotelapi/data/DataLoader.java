package io.training.catalyte.hotelapi.data;

import io.training.catalyte.hotelapi.domains.reservations.Reservation;
import io.training.catalyte.hotelapi.domains.reservations.ReservationRepository;
import io.training.catalyte.hotelapi.domains.roomtypes.RoomType;
import io.training.catalyte.hotelapi.domains.roomtypes.RoomTypeRepository;
import io.training.catalyte.hotelapi.domains.users.User;
import io.training.catalyte.hotelapi.domains.users.UserRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoomTypeRepository roomTypeRepository;

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void run(String... strings) {
    loadUsers();
    loadRoomTypes();
    loadReservations();
  }

  private void loadUsers() {
    String encodedPass = bCryptPasswordEncoder.encode("password");

    userRepository.save(new User("manager@hotelapi.com", encodedPass, "manager"));
    userRepository.save(new User("employee@hotelapi.com", encodedPass, "employee"));
  }

  private void loadRoomTypes() {
    roomTypeRepository
        .save(new RoomType("King", "Single king non-smoking", new BigDecimal(129.99), true));
    roomTypeRepository
        .save(new RoomType("King Double", "Double king non-smoking", new BigDecimal(149.99), true));
    roomTypeRepository
        .save(new RoomType("Executive Suite", "A well appointed room with a view", new BigDecimal(199.99), true));
    roomTypeRepository
        .save(new RoomType("Honeymoon Suite", "Large room with a hot tub, complimentary bottle of bubbly", new BigDecimal(179.99), true));
    roomTypeRepository
        .save(new RoomType("Queen", "Single queen non-smoking", new BigDecimal(99.99), true));
    roomTypeRepository
        .save(new RoomType("Queen Double", "Two queens non-smoking", new BigDecimal(109.99), true));
    roomTypeRepository
        .save(new RoomType("Extended Stay", "Small room with kitchenette, small stove and full size fridge", new BigDecimal(69.99), true));
  }

  private void loadReservations() {
    String MY_DATE = "01-01-2020";

    reservationRepository
        .save(new Reservation("employee@hotelapi.com", "bobRoss@gmail.com", 1L, MY_DATE, 2));
    reservationRepository
        .save(new Reservation("employee@hotelapi.com", "batman@gmail.com", 2L, MY_DATE, 1));
    reservationRepository
        .save(new Reservation("employee@hotelapi.com", "elrond@gmail.com", 3L, MY_DATE, 5));
    reservationRepository
        .save(new Reservation("employee@hotelapi.com", "elrond@gmail.com", 4L, MY_DATE, 5));
  }
}
