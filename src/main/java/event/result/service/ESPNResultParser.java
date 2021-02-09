package event.result.service;

import com.gargoylesoftware.htmlunit.html.DomNode;
import event.result.model.Competition;
import event.result.model.Competitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ESPNResultParser {

  static final String HOME_SCORE_SELECTOR = "li.cscore_item--home";
  static final String AWAY_SCORE_SELECTOR = "li.cscore_item--away";

  private final ESPNCompetitorParser competitorParser;
  private final ESPNCompetitionParser competitionParser;

  @Autowired
  public ESPNResultParser(ESPNCompetitorParser competitorParser, ESPNCompetitionParser competitionParser) {
    this.competitorParser = competitorParser;
    this.competitionParser = competitionParser;
  }

  public Competition mapPageDataToCompetition(DomNode domNode) {

    Competitor homeCompetitor = parseHomeCompetitor(domNode);
    Competitor awayCompetitor = parseAwayCompetitor(domNode);

    competitorParser.setWinningTeam(homeCompetitor, awayCompetitor);

    return competitionParser.getCompetition(domNode, homeCompetitor, awayCompetitor);
  }

  private Competitor parseHomeCompetitor(DomNode node) {
    return competitorParser.getCompetitor(node, HOME_SCORE_SELECTOR, Competitor.HOME_TEAM);
  }

  private Competitor parseAwayCompetitor(DomNode node) {
    return competitorParser.getCompetitor(node, AWAY_SCORE_SELECTOR, Competitor.AWAY_TEAM);
  }

}
