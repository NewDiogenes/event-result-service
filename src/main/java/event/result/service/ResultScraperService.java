package event.result.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import event.result.model.Competition;
import event.result.respository.CompetitionResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultScraperService {
  private static final String COMPLETED_GAME_SCORE_SELECTOR = "div.cscore--final";
  private static final String ESPN_ROOT = "https://www.espn.com.au/";

  private ESPNResultParser espnResultParser;
  private CompetitionResultRepository resultRepository;
  private WebClient webClient;

  @Autowired
  public ResultScraperService(ESPNResultParser espnResultParser, CompetitionResultRepository resultRepository) {
    this.espnResultParser = espnResultParser;
    this.resultRepository = resultRepository;
    this.webClient = new WebClient();

    this.webClient.getOptions().setJavaScriptEnabled(false);
  }

  public List<Competition> scrapePageForResults(String espnUri) throws IOException {
    return getResultsFromWebPage(webClient.getPage(ESPN_ROOT + espnUri));
  }

  public List<Competition> getResultsFromWebPage(HtmlPage page) {
    List<Competition> competitionList =page
        .querySelectorAll(COMPLETED_GAME_SCORE_SELECTOR)
        .stream()
        .map(espnResultParser::mapPageDataToCompetition)
        .collect(Collectors.toList());
    return resultRepository.saveAll(competitionList);
  }

  @PostConstruct
  private void scrapeTestPage() {
    try {
      HtmlPage testPage = webClient.getPage(ResourceUtils
          .getFile("src/main/resources/TestPage.html").toURI().toURL());
      getResultsFromWebPage(testPage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
