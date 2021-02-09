package event.result.service;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import event.result.model.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultScraperService {
  private static final String COMPLETED_GAME_SCORE_SELECTOR = "div.cscore--final";

  private ESPNResultParser espnResultParser;

  @Autowired
  public ResultScraperService(ESPNResultParser espnResultParser) {
    this.espnResultParser = espnResultParser;
  }

  public List<Competition> getResultsFromWebPage(HtmlPage page) {
    return page
        .querySelectorAll(COMPLETED_GAME_SCORE_SELECTOR)
        .stream()
        .map(espnResultParser::mapPageDataToCompetition)
        .collect(Collectors.toList());
  }
}
