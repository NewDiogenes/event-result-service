package event.result;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import event.result.model.Competition;
import event.result.service.ResultScraperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventResultIntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ResultScraperService scraperService;

  private static final String testPageUrl = "src/test/resources/webpages/TestPage.html";

  private WebClient webClient;
  private static HtmlPage testPage;

  @BeforeEach
  void scrapeTestPage() {
    webClient = new WebClient();

    webClient.getOptions().setJavaScriptEnabled(false);

    loadTestPage(testPageUrl);
    scraperService.getResultsFromWebPage(testPage);
  }

  @Test
  void shouldReturnCompetitionDetailsFromScrapedTestPage() throws IOException {
    String response = restTemplate.getForObject("http://localhost:" + port + "/competition/results", String.class);

    assertEquals(getExpectedValueFromFile("src/test/resources/expected-return.json"), response);
  }

  private String getExpectedValueFromFile(String filename) throws IOException {
    return Files.readString(ResourceUtils.getFile(filename).toPath());
  }

  private void loadTestPage(String pageUrl) {
    try {
      testPage = webClient.getPage(ResourceUtils.getFile(pageUrl).toURI().toURL());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
