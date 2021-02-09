package event.result.service;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import event.result.model.Competition;
import event.result.model.Competitor;
import event.result.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ESPNResultParser {
  Logger logger = LoggerFactory.getLogger(ESPNResultParser.class);

  private static final String COMPLETED_GAME_SCORE_SELECTOR = "div.cscore--final";
  private static final String HOME_SCORE_SELECTOR = "li.cscore_item--home";
  private static final String AWAY_SCORE_SELECTOR = "li.cscore_item--away";
  private static final String SCORE_CLASS_SELECTOR = ".cscore_score";
  private static final String LONG_NAME_SELECTOR = ".cscore_name--long";
  private static final String SHORT_NAME_SELECTOR = ".cscore_name--short";
  private static final String ABBREVIATED_NAME_SELECTOR = ".cscore_name--abbrev";
  private static final String GAME_DATETIME_SELECTOR = ".cscore_date-time";
  private static final String GAME_LEAGUE_SELECTOR = ".cscore_notes_game";

  public List<Competition> getResultsFromWebPage(HtmlPage page) {
    return page
        .querySelectorAll(COMPLETED_GAME_SCORE_SELECTOR)
        .stream()
        .map(this::mapPageDataToCompetition)
        .collect(Collectors.toList());
  }

  private Competition mapPageDataToCompetition(DomNode division) {

    Competitor homeCompetitor = getCompetitor(division, HOME_SCORE_SELECTOR, Competitor.HOME_TEAM);

    Competitor awayCompetitor = getCompetitor(division, AWAY_SCORE_SELECTOR, Competitor.AWAY_TEAM);

    setWinningTeam(homeCompetitor, awayCompetitor);

    return getCompetition(division, homeCompetitor, awayCompetitor);
  }

  private Competitor getCompetitor(DomNode division, String awayScoreSelector, String homeOrAway) {
    DomNode node = division.querySelector(awayScoreSelector);

    Team team = getTeam(node);

    Competitor competitor = new Competitor();
    competitor.setTeam(team);
    competitor.setHomeAway(homeOrAway);

    String scoreText = node
        .querySelector(SCORE_CLASS_SELECTOR)
        .getTextContent()
        .replaceAll("\\s","");

    Integer score = Integer.valueOf(scoreText);
    competitor.setScore(score);

    return competitor;
  }

  private Competition getCompetition(DomNode division, Competitor homeCompetitor, Competitor awayCompetitor) {
    Competition competition = new Competition();

    competition.setName(getCompetitionName(division, homeCompetitor, awayCompetitor));

    String competitionDateTime = division.querySelector(GAME_DATETIME_SELECTOR).asText();
    competition.setCompetitionDate(competitionDateTime);

    competition.setCompetitors(List.of(homeCompetitor, awayCompetitor));
    return competition;
  }

  private String getCompetitionName(DomNode division,  Competitor homeCompetitor, Competitor awayCompetitor) {
    String competitionLeague = division.querySelector(GAME_LEAGUE_SELECTOR).asText();
    return String.format("%s vs %s, %s",
        homeCompetitor.getTeam().getLongName(),
        awayCompetitor.getTeam().getLongName(),
        competitionLeague);
  }

  private void setWinningTeam(Competitor homeCompetitor, Competitor awayCompetitor) {
    if (homeCompetitor.getScore() > awayCompetitor.getScore()) {
      homeCompetitor.setWinner(true);
      awayCompetitor.setWinner(false);
    } else if (homeCompetitor.getScore() < awayCompetitor.getScore()) {
      homeCompetitor.setWinner(false);
      awayCompetitor.setWinner(true);
    } else {
      homeCompetitor.setWinner(false);
      awayCompetitor.setWinner(false);
    }
  }

  private Team getTeam(DomNode node) {
    Team team = new Team();
    team.setLongName(node.querySelector(LONG_NAME_SELECTOR).getTextContent());
    team.setShortName(node.querySelector(SHORT_NAME_SELECTOR).getTextContent());
    team.setAbbreviatedName(node.querySelector(ABBREVIATED_NAME_SELECTOR).getTextContent());
    return team;
  }
}
