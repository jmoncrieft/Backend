package io.training.catalyte.hotelapi.domains.roomtypes;

import java.util.List;

public interface RoomTypeService {

  List<RoomType> getAll();

  RoomType getById(Long id);

  RoomType createRoom(RoomType roomType);

  RoomType updateRoom(Long id, RoomType roomType);

}
