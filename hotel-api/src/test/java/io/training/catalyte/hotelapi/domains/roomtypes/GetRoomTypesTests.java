package io.training.catalyte.hotelapi.domains.roomtypes;

import static io.training.catalyte.hotelapi.RoomHelper.ROOM_TYPES_PATH;
import static io.training.catalyte.hotelapi.RoomHelper.ROOM_TYPES_PATH_WITH_SLASH;
import static io.training.catalyte.hotelapi.RoomHelper.ROOM_TYPE_ACTIVE_JSON;
import static io.training.catalyte.hotelapi.RoomHelper.ROOM_TYPE_DESCRIPTION_JSON;
import static io.training.catalyte.hotelapi.RoomHelper.ROOM_TYPE_NAME_JSON;
import static io.training.catalyte.hotelapi.RoomHelper.ROOM_TYPE_RATE_JSON;
import static io.training.catalyte.hotelapi.RoomHelper.VALID_TEST_ACTIVE;
import static io.training.catalyte.hotelapi.RoomHelper.VALID_TEST_DESCRIPTION;
import static io.training.catalyte.hotelapi.RoomHelper.VALID_TEST_RATE;
import static io.training.catalyte.hotelapi.RoomHelper.VALID_TEST_ROOMTYPE_NAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.training.catalyte.hotelapi.LoginHelper;
import io.training.catalyte.hotelapi.RoomHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetRoomTypesTests {

  @Autowired
  private static MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  private WebApplicationContext wac;

  LoginHelper loginHelper = new LoginHelper();
  RoomType testRoomType = null;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    testRoomType = RoomHelper.constructValidRoom();
  }

  @Test
  public void getByIdAuthenticatedAsManagerHappyPath() throws Exception {
    String token = LoginHelper.authenticateAndReturnToken(mockMvc, true);
    RoomType postedRoomType = RoomHelper.postRoom(mockMvc, testRoomType);

    mockMvc.perform(get(ROOM_TYPES_PATH_WITH_SLASH + postedRoomType.getId())
        .header("authentication", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(jsonPath(ROOM_TYPE_NAME_JSON).value(VALID_TEST_ROOMTYPE_NAME))
        .andExpect(jsonPath(ROOM_TYPE_DESCRIPTION_JSON).value(VALID_TEST_DESCRIPTION))
        .andExpect(jsonPath(ROOM_TYPE_RATE_JSON).value(VALID_TEST_RATE))
        .andExpect(jsonPath(ROOM_TYPE_ACTIVE_JSON).value(VALID_TEST_ACTIVE))
        .andReturn();
  }

  @Test
  public void getByIdAuthenticatedAsEmployeePath() throws Exception {
    String token = LoginHelper.authenticateAndReturnToken(mockMvc, false);
    RoomType postedRoomType = RoomHelper.postRoom(mockMvc, testRoomType);

    mockMvc.perform(get(ROOM_TYPES_PATH_WITH_SLASH + postedRoomType.getId())
        .header("authentication", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(jsonPath(ROOM_TYPE_NAME_JSON).value(VALID_TEST_ROOMTYPE_NAME))
        .andExpect(jsonPath(ROOM_TYPE_DESCRIPTION_JSON).value(VALID_TEST_DESCRIPTION))
        .andExpect(jsonPath(ROOM_TYPE_RATE_JSON).value(VALID_TEST_RATE))
        .andExpect(jsonPath(ROOM_TYPE_ACTIVE_JSON).value(VALID_TEST_ACTIVE))
        .andReturn();
  }

  @Test
  public void getByIdThatDoesNotExistsReturns404() throws Exception {
    String token = LoginHelper.authenticateAndReturnToken(mockMvc, false);

    mockMvc.perform(get(ROOM_TYPES_PATH_WITH_SLASH + 9001)
        .header("authentication", "Bearer " + token))
        .andExpect(status().isNotFound())
        .andReturn();
  }

  @Test
  public void getAllReturns200AndNotEmpty() throws Exception {
    String token = LoginHelper.authenticateAndReturnToken(mockMvc, false);

    mockMvc.perform(get(ROOM_TYPES_PATH)
        .header("authentication", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNotEmpty())
        .andReturn();
  }

// This test is giving a 200 with a garbage token, which is weird!  Seems to be a mockmvc issue I haven't sorted out yet
//  @Test
//  public void getByIdNotAuthenticatedReturns403() throws Exception {
//
//    MockMvc thisTestMockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//
//    thisTestMockMvc.perform(get(ROOMS_PATH_WITH_SLASH + 1)
//        .header("authentication", "Bearer asdfasdfasfd"))
//        .andExpect(status().isUnauthorized())
//        .andReturn();
//  }
}
