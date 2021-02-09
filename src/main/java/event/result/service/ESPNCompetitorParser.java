package event.result.service;

import com.gargoylesoftware.htmlunit.html.DomNode;
import event.result.model.Competitor;
import event.result.model.Team;
import org.springframework.stereotype.Service;

@Service
public class ESPNCompetitorParser {
  static final String SCORE_CLASS_SELECTOR = ".cscore_score";
  static final String LONG_NAME_SELECTOR = ".cscore_name--long";
  static final String SHORT_NAME_SELECTOR = ".cscore_name--short";
  static final String ABBREVIATED_NAME_SELECTOR = ".cscore_name--abbrev";

  public Competitor getCompetitor(DomNode division, String teamSelector, String homeOrAway) {
    DomNode node = division.querySelector(teamSelector);

    Team team = getTeam(node);

    Competitor competitor = new Competitor();
    competitor.setTeam(team);
    competitor.setHomeAway(homeOrAway);

    competitor.setScore(getScore(node));

    return competitor;
  }

  private Integer getScore(DomNode node) {
    String scoreText = node
        .querySelector(SCORE_CLASS_SELECTOR)
        .getTextContent()
        .replaceAll("\\s","");

    return Integer.valueOf(scoreText);
  }

  private Team getTeam(DomNode node) {
    Team team = new Team();
    team.setLongName(node.querySelector(LONG_NAME_SELECTOR).getTextContent());
    team.setShortName(node.querySelector(SHORT_NAME_SELECTOR).getTextContent());
    team.setAbbreviatedName(node.querySelector(ABBREVIATED_NAME_SELECTOR).getTextContent());
    return team;
  }

  public void setWinningTeam(Competitor homeCompetitor, Competitor awayCompetitor) {
    if (homeCompetitor.getScore() > awayCompetitor.getScore()) {
      homeCompetitor.setWinner(true);
      awayCompetitor.setWinner(false);
    } else if (homeCompetitor.getScore() < awayCompetitor.getScore()) {
      homeCompetitor.setWinner(false);
      awayCompetitor.setWinner(true);
    } else {
      homeCompetitor.setWinner(true);
      awayCompetitor.setWinner(true);
    }
  }
}
