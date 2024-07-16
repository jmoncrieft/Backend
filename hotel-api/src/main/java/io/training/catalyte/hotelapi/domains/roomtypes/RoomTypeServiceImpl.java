package io.training.catalyte.hotelapi.domains.roomtypes;

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
public class RoomTypeServiceImpl implements RoomTypeService {

  private final Logger logger = LoggerFactory.getLogger(RoomTypeServiceImpl.class);

  @Autowired private RoomTypeRepository roomTypeRepository;

  /**
   * Retrieves all rooms from the database.
   *
   * @return a list of all rooms.
   */
  @Override
  public List<RoomType> getAll() {
    List<RoomType> roomTypeList = new ArrayList<>();

    try {
      roomTypeList = roomTypeRepository.findAll();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    return roomTypeList.stream()
        .sorted(Comparator.comparing(RoomType::getId))
        .collect(Collectors.toList());
  }

  /**
   * Retrieves a room from the database by its id.
   *
   * @param id the id of the room to retrieve
   * @return the specified room
   */
  @Override
  public RoomType getById(Long id) {
    Optional<RoomType> room = Optional.empty();

    try {
      room = roomTypeRepository.findById(id);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    if (room.isEmpty()) {
      throw new ResourceNotFoundException();
    } else {
      return room.get();
    }
  }

  /**
   * Persists a new room to the database.
   *
   * @param roomType the room object to persist
   * @return the persisted room
   */
  @Override
  public RoomType createRoom(RoomType roomType) {
    RoomType postedRoomType = null;

    try {
      postedRoomType = roomTypeRepository.save(roomType);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    return postedRoomType;
  }

  /**
   * Updates a specified record in the database.
   *
   * @param id the id of the record to update
   * @param roomType the provided room information to persist
   * @return the updated room
   */
  @Override
  public RoomType updateRoom(Long id, RoomType roomType) {
    try {
      Optional<RoomType> roomToUpdate = roomTypeRepository.findById(id);
      if (roomToUpdate.isEmpty()) {
        throw new ResourceNotFoundException();
      } else {
        roomType.setId(id);
        roomTypeRepository.save(roomType);
      }
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    return roomType;
  }
}
