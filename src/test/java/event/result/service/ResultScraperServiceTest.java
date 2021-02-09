package event.result.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class ResultScraperServiceTest {
  private static final String simplePageUrl = "src/test/resources/webpages/SimpleResultsPage.html";
  private static final String noCompleteGamesPageUrl = "src/test/resources/webpages/NoCompletedGames.html";

  private ResultScraperService resultScraperService;

  @Mock
  private ESPNResultParser resultParser;

  private WebClient webClient;
  private static HtmlPage testPage;

  @BeforeEach
  void setUp() {
    openMocks(this);
    webClient = new WebClient();

    resultScraperService = new ResultScraperService(resultParser);
  }


  @Test
  void givenAPageWithIncompleteGames_getResultsFromWebPage_shouldOnlyPassResultsFromCompletedGamesToResultParser() {
    loadTestPage(simplePageUrl);
    int numberOfCompletedGames = 3;

    resultScraperService.getResultsFromWebPage(testPage);

    verify(resultParser, times(numberOfCompletedGames)).mapPageDataToCompetition(any());
  }

  @Test
  void givenAPageThatShowsNoCompletedGames_getResultsFromWebpage_shouldNotPassAnythingToResultParser() {
    loadTestPage(noCompleteGamesPageUrl);
    int numberOfCompletedGames = 0;

    resultScraperService.getResultsFromWebPage(testPage);

    verify(resultParser, times(numberOfCompletedGames)).mapPageDataToCompetition(any());
  }

  private void loadTestPage(String pageUrl) {
    try {
      testPage = webClient.getPage(ResourceUtils.getFile(pageUrl).toURI().toURL());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}