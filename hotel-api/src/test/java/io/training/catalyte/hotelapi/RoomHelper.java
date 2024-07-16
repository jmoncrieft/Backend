package io.training.catalyte.hotelapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.training.catalyte.hotelapi.domains.roomtypes.RoomType;
import java.math.BigDecimal;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

public class RoomHelper {

  public static final String ROOM_TYPES_PATH = "/room-types";
  public static final String ROOM_TYPES_PATH_WITH_SLASH = ROOM_TYPES_PATH + "/";
  public static final String ROOM_TYPE_NAME_JSON = "$.name";
  public static final String ROOM_TYPE_DESCRIPTION_JSON = "$.description";
  public static final String ROOM_TYPE_RATE_JSON = "$.rate";
  public static final String ROOM_TYPE_ACTIVE_JSON = "$.active";
  public static final String VALID_TEST_DESCRIPTION = "test description";
  public static final String VALID_TEST_ROOMTYPE_NAME = "test room type";
  public static final Double VALID_TEST_RATE = 100.00;
  public static final boolean VALID_TEST_ACTIVE = true;

  public static RoomType constructValidRoom() {
    return new RoomType(VALID_TEST_ROOMTYPE_NAME, VALID_TEST_DESCRIPTION, new BigDecimal(
        VALID_TEST_RATE), VALID_TEST_ACTIVE);
  }

  public static RoomType postRoom(MockMvc mockMvc, RoomType roomType) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String token = LoginHelper.authenticateAndReturnToken(mockMvc, true);

    MvcResult result = mockMvc.perform(post(ROOM_TYPES_PATH)
        .header("authentication", "Bearer " + token)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(roomType)))
        .andReturn();

    return objectMapper.readValue(result.getResponse().getContentAsString(), RoomType.class);
  }


}
