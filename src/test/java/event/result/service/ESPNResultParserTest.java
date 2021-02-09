package event.result.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ESPNResultParserTest {

  private ESPNResultParser resultParser;

  private static HtmlPage page;

  private static final String simplePageUrl = "src/test/resources/webpages/SimpleResultsPage.html";

  private static HtmlPage simpleTestPage;

  @BeforeAll
  static void beforeAll() throws IOException {
    WebClient webClient = new WebClient();
    webClient.getOptions().setJavaScriptEnabled(false);

    simpleTestPage = webClient.getPage(ResourceUtils.getFile(simplePageUrl).toURI().toURL());
  }

  @BeforeEach
  void setUp() {
    resultParser = new ESPNResultParser();
  }


  @Test
  void getResultsFromWebPage_shouldOnlyGetResultsFromCompletedGames() {
    int expectedNumberOfCompetitions = 3;
    assertEquals(expectedNumberOfCompetitions, resultParser.getResultsFromWebPage(simpleTestPage).size());
  }
}