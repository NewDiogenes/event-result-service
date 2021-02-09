package event.result.service;

import com.gargoylesoftware.htmlunit.html.DomNode;
import event.result.model.Competition;
import event.result.model.Competitor;
import event.result.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.w3c.dom.NamedNodeMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ESPNCompetitionParserTest {

  private ESPNCompetitionParser competitionParser;

  @Mock
  private DomNode requestNode;
  @Mock
  private DomNode dateNode;
  @Mock
  private NamedNodeMap attrNode;
  @Mock
  private DomNode namedItemNode;
  @Mock
  private DomNode actualDateNode;
  @Mock
  private DomNode leagueNode;

  private String date = "date";
  private String league = "league";

  private Competitor homeComp;
  private String homeName = "home";
  private Competitor awayComp;
  private String awayName = "away";

  private Competition testCompetition;

  @BeforeEach
  void setUp() {
    openMocks(this);
    competitionParser = new ESPNCompetitionParser();

    homeComp = new Competitor();
    Team homeTeam = new Team();
    homeTeam.setLongName(homeName);
    homeComp.setTeam(homeTeam);

    awayComp = new Competitor();
    Team awayTeam = new Team();
    awayTeam.setLongName(awayName);
    awayComp.setTeam(awayTeam);

    stubDomNodes();
  }

  private void stubDomNodes() {
    when(requestNode.querySelector(eq(ESPNCompetitionParser.GAME_DATETIME_SELECTOR))).thenReturn(dateNode);
    when(dateNode.getAttributes()).thenReturn(attrNode);
    when(attrNode.getNamedItem(any())).thenReturn(actualDateNode);
    when(actualDateNode.getTextContent()).thenReturn(date);

    when(requestNode.querySelector(eq(ESPNCompetitionParser.GAME_LEAGUE_SELECTOR))).thenReturn(leagueNode);
    when(leagueNode.getTextContent()).thenReturn(league);
  }

  @Test
  void getCompetition_shouldQueryTheDomNodeForTheLeagueName() {
    competitionParser.getCompetition(requestNode, homeComp, awayComp);

    verify(requestNode, times(1)).querySelector(eq(ESPNCompetitionParser.GAME_LEAGUE_SELECTOR));
  }

  @Test
  void givenTheDomNodeReturnsALeagueName_getCompetition_shouldUseTheTeamsNamesAndTheLeagueNameToNameTheCompetition() {
    testCompetition = competitionParser.getCompetition(requestNode, homeComp, awayComp);
    String expectedValue = "home vs away, league";
    assertEquals(expectedValue, testCompetition.getName());
  }

  @Test
  void getCompetition_shouldQueryTheDomNodeForCompetitionDateTime() {
    competitionParser.getCompetition(requestNode, homeComp, awayComp);

    verify(requestNode, times(1)).querySelector(eq(ESPNCompetitionParser.GAME_DATETIME_SELECTOR));
  }

  @Test
  void givenTheDomNodeReturnsDateTime_getCompetition_shouldMapTheQueryValueToCompetitionDate() {
    testCompetition = competitionParser.getCompetition(requestNode, homeComp, awayComp);

    assertEquals(date, testCompetition.getCompetitionDate());
  }

  @Test
  void getCompetition_shouldMapTheHomeAndAwayCompetitorsToCompetitionCompetitors() {
    testCompetition = competitionParser.getCompetition(requestNode, homeComp, awayComp);

    assertEquals(List.of(homeComp, awayComp), testCompetition.getCompetitors());
  }
}