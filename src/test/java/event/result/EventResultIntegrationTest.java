package event.result;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventResultIntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void results_shouldReturnDefaultCompetitionsWhenCalled() throws IOException {
    String response = restTemplate.getForObject("http://localhost:" + port + "/competition/results", String.class);

    assertEquals(getExpectedValueFromFile("src/test/resources/expected-return.json"), response);
  }

  private String getExpectedValueFromFile(String filename) throws IOException {
    return Files.readString(ResourceUtils.getFile(filename).toPath());
  }
}
