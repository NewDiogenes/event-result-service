package event.result.service;

import com.gargoylesoftware.htmlunit.html.DomNode;
import event.result.model.Competition;
import event.result.model.Competitor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESPNCompetitionParser {

  static final String GAME_DATETIME_SELECTOR = ".cscore_date-time";
  static final String GAME_LEAGUE_SELECTOR = ".cscore_notes_game";

  public Competition getCompetition(DomNode domNode, Competitor homeCompetitor, Competitor awayCompetitor) {

    Competition competition = new Competition();

    competition.setName(getCompetitionName(domNode, homeCompetitor, awayCompetitor));

    String competitionDateTime = domNode.querySelector(GAME_DATETIME_SELECTOR).getAttributes().getNamedItem("data-date").getTextContent();
    competition.setCompetitionDate(competitionDateTime);

    competition.setCompetitors(List.of(homeCompetitor, awayCompetitor));
    return competition;
  }

  private String getCompetitionName(DomNode domNode,  Competitor homeCompetitor, Competitor awayCompetitor) {
    String competitionLeague = domNode.querySelector(GAME_LEAGUE_SELECTOR).getTextContent();
    return String.format("%s vs %s, %s",
        homeCompetitor.getTeam().getLongName(),
        awayCompetitor.getTeam().getLongName(),
        competitionLeague);
  }
}
