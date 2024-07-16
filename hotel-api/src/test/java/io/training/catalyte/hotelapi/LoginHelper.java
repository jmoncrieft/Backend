package io.training.catalyte.hotelapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

public class LoginHelper {

  @Autowired
  private static MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;

  public static final String MANAGER_EMAIL = "manager@hotelapi.com";
  public static final String EMPLOYEE_EMAIL = "employee@hotelapi.com";
  public static final String EMPLOYEE_PASSWORD = "password";

  public static String authenticateAndReturnToken(MockMvc mockMvc, boolean asManager)
      throws Exception {

    JSONObject validCredentialObject = new JSONObject();
    String testEmail = asManager ? MANAGER_EMAIL : EMPLOYEE_EMAIL;
    validCredentialObject.put("email", testEmail);
    validCredentialObject.put("password", EMPLOYEE_PASSWORD);

    MvcResult result =
        mockMvc
            .perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validCredentialObject.toString()))
            .andReturn();

    return result.getResponse().getContentAsString();
  }


}
