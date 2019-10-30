package com.logicalsapien.springbootrestsample.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.util.List;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;



/**
 * Planet Controller Tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetControllerTest {

  /**
   * To bind the Random Port.
   */
  @LocalServerPort
  private int port;


  /**
   * Rest Template for testing the api.
   */
  @Autowired
  private TestRestTemplate restTemplate;

  /**
   * List all Planets test.
   * @throws Exception exception
   */
  @Test
  public void listAllPlanetsTest() throws Exception {

    ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/planet").toString(), String.class);

    List<String> expectedPlanetList
            = List.of("Mercury", "Venus", "Earth", "Mars",
            "Jupiter", "Saturn", "Uranus", "Neptune");
    assertThat(response.getBody(), equalTo(getJsonFromObject(expectedPlanetList)));
  }

  /**
   * To Convert an Object to JSON String.
   * @param o Object
   * @return Object as String
   * @throws JsonProcessingException throws JsonProcessingException
   */
  private static  String getJsonFromObject(Object o) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper.writeValueAsString(o);
  }
}
