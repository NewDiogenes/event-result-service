package event.result.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import event.result.model.Competition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ResultScraperIntegrationTest {

  @Autowired
  private ResultScraperService scraperService;

  private static final String simplePageUrl = "src/test/resources/webpages/SimpleResultsPage.html";

  private WebClient webClient;
  private static HtmlPage testPage;

  @BeforeEach
  void setUp() {
    webClient = new WebClient();
  }

  @Test
  void givenAPageWithIncompleteGames_getResultsFromWebPage_shouldOnlyPassResultsFromCompletedGamesToResultParser() {
    loadTestPage(simplePageUrl);

    List<Competition> competitionList = scraperService.getResultsFromWebPage(testPage);

    assertEquals(Collections.EMPTY_LIST, competitionList);
  }

  private void loadTestPage(String pageUrl) {
    try {
      testPage = webClient.getPage(ResourceUtils.getFile(pageUrl).toURI().toURL());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
