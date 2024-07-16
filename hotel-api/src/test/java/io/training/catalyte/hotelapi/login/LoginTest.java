package io.training.catalyte.hotelapi.login;

import static io.training.catalyte.hotelapi.LoginHelper.EMPLOYEE_EMAIL;
import static io.training.catalyte.hotelapi.LoginHelper.EMPLOYEE_PASSWORD;
import static io.training.catalyte.hotelapi.LoginHelper.MANAGER_EMAIL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class LoginTest {

  @Autowired
  private static MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;
  private JSONObject validCredentialObject;

  @Before
  public void setUp() throws JSONException {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    validCredentialObject = new JSONObject();
    validCredentialObject.put("email", EMPLOYEE_EMAIL);
    validCredentialObject.put("password", EMPLOYEE_PASSWORD);
  }

  @Test
  public void successfulManagerLoginReturnsToken() throws Exception {
    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(validCredentialObject.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.token").exists())
        .andReturn();
  }

  @Test
  public void successfulEmployeeLoginReturnsToken() throws Exception {
    JSONObject credentialObject = validCredentialObject;
    credentialObject.put("email", MANAGER_EMAIL);

    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(validCredentialObject.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.token").exists())
        .andReturn();
  }

  @Test
  public void loginWithBadPasswordIs400() throws Exception {
    JSONObject credentialObject = validCredentialObject;
    credentialObject.put("password", "not a password");

    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(credentialObject.toString()))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.token").doesNotExist())
        .andReturn();
  }

  @Test
  public void loginWithBadUsernameIs400() throws Exception {
    JSONObject credentialObject = validCredentialObject;
    credentialObject.put("email", "scooby@hotelapi.com");

    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(credentialObject.toString()))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.token").doesNotExist())
        .andReturn();
  }

}
