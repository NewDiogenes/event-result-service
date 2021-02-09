package event.result.service;

import com.gargoylesoftware.htmlunit.html.DomNode;
import event.result.model.Competitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ESPNCompetitorParserTest {

  private ESPNCompetitorParser competitorParser;


  private Competitor homeComp;
  private Competitor awayComp;
  private Competitor testCompetitor;

  private String teamSelector = "teamSelector";
  private String homeAway = "homeAway";

  private String shortName = "shortName";
  private String longName = "longName";
  private String abbrevName = "abbrevName";
  private Integer score = 0;
  private String scoreText = "\n" + score + "\n";

  @Mock
  private DomNode requestNode;
  @Mock
  private DomNode stubNode;
  @Mock
  private DomNode shortNameNode;
  @Mock
  private DomNode longNameNode;
  @Mock
  private DomNode abbrevNameNode;
  @Mock
  private DomNode scoreNode;

  @BeforeEach
  void setUp() {
    openMocks(this);
    competitorParser = new ESPNCompetitorParser();

    homeComp = new Competitor();
    awayComp = new Competitor();

    stubDomNodes();

  }

  private void stubDomNodes() {
    when(requestNode.querySelector(any())).thenReturn(stubNode);

    when(stubNode.querySelector(eq(ESPNCompetitorParser.SCORE_CLASS_SELECTOR))).thenReturn(scoreNode);
    when(scoreNode.getTextContent()).thenReturn(scoreText);

    when(stubNode.querySelector(eq(ESPNCompetitorParser.SHORT_NAME_SELECTOR))).thenReturn(shortNameNode);
    when(shortNameNode.getTextContent()).thenReturn(shortName);

    when(stubNode.querySelector(eq(ESPNCompetitorParser.LONG_NAME_SELECTOR))).thenReturn(longNameNode);
    when(longNameNode.getTextContent()).thenReturn(longName);

    when(stubNode.querySelector(eq(ESPNCompetitorParser.ABBREVIATED_NAME_SELECTOR))).thenReturn(abbrevNameNode);
    when(abbrevNameNode.getTextContent()).thenReturn(abbrevName);
  }

  @Test
  void getCompetitor_shouldQueryTheDomNodeWithTheTeamSelector() {
    competitorParser.getCompetitor(requestNode, teamSelector, homeAway);

    verify(requestNode, times(1)).querySelector(eq(teamSelector));
  }

  @Test
  void givenTheTeamSelectorReturnsANode_getCompetitor_shouldQueryTheNewDomNodeForTheTeamSHortName() {
    competitorParser.getCompetitor(requestNode, teamSelector, homeAway);

    verify(stubNode, times(1)).querySelector(eq(ESPNCompetitorParser.SHORT_NAME_SELECTOR));
  }

  @Test
  void givenTheDOmNodeReturnAShortName_getCompetitor_shouldMapTheQueryValueToTheTeamShortName() {
    testCompetitor = competitorParser.getCompetitor(requestNode, teamSelector, homeAway);

    assertEquals(shortName, testCompetitor.getTeam().getShortName());
  }

  @Test
  void givenTheTeamSelectorReturnsANode_getCompetitor_shouldQueryTheNewDomNodeForTheTeamLongName() {
    competitorParser.getCompetitor(requestNode, teamSelector, homeAway);

    verify(stubNode, times(1)).querySelector(eq(ESPNCompetitorParser.LONG_NAME_SELECTOR));
  }

  @Test
  void givenTheDomNodeReturnALongName_getCompetitor_shouldMapTheQueryValueToTheTeamLongName() {
    testCompetitor = competitorParser.getCompetitor(requestNode, teamSelector, homeAway);

    assertEquals(longName, testCompetitor.getTeam().getLongName());
  }

  @Test
  void givenTheTeamSelectorReturnsANode_getCompetitor_shouldQueryTheNewDomNodeForTheTeamAbbreviatedName() {
    competitorParser.getCompetitor(requestNode, teamSelector, homeAway);

    verify(stubNode, times(1))
        .querySelector(eq(ESPNCompetitorParser.ABBREVIATED_NAME_SELECTOR));
  }

  @Test
  void givenTheDomNodeReturnAnAbbreviatedName_getCompetitor_shouldMapTheQueryValueToTheTeamAbbreviatedName() {
    testCompetitor = competitorParser.getCompetitor(requestNode, teamSelector, homeAway);

    assertEquals(abbrevName, testCompetitor.getTeam().getAbbreviatedName());
  }

  @Test
  void givenTheTeamSelectorReturnsANode_getCompetitor_shouldQueryTheNewDomNodeForTheTeamScore() {
    competitorParser.getCompetitor(requestNode, teamSelector, homeAway);

    verify(stubNode, times(1))
        .querySelector(eq(ESPNCompetitorParser.SCORE_CLASS_SELECTOR));
  }

  @Test
  void givenTheDomNodeReturnAScore_getCompetitor_shouldRemoveAnyNewlinesAndMapTheQueryValueToTheCompetitorScore() {
    testCompetitor = competitorParser.getCompetitor(requestNode, teamSelector, homeAway);

    assertEquals(score, testCompetitor.getScore());
  }

  @Test
  void getCompetitor_shouldSetTheCompetitionHomOrAwayValueToTheHomeOrAwayParameter() {
    testCompetitor = competitorParser.getCompetitor(requestNode, teamSelector, homeAway);

    assertEquals(homeAway, testCompetitor.getHomeAway());
  }

  @Test
  void givenAwayHasAHigherScoreThanHome_setWinningTeam_shouldSetAwayAsTheWinner() {
    homeComp.setScore(0);
    awayComp.setScore(1);

    competitorParser.setWinningTeam(homeComp, awayComp);

    assertEquals(false, homeComp.getWinner());
    assertEquals(true, awayComp.getWinner());
  }

  @Test
  void givenHomeHasAHigherScoreThanHome_setWinningTeam_shouldSetHomeAsTheWinner() {
    homeComp.setScore(1);
    awayComp.setScore(0);

    competitorParser.setWinningTeam(homeComp, awayComp);

    assertEquals(true, homeComp.getWinner());
    assertEquals(false, awayComp.getWinner());
  }

  @Test
  void giveEqualScores_setWinningTeam_shouldBothCompetitorsAsTheWinner() {
    homeComp.setScore(1);
    awayComp.setScore(1);

    competitorParser.setWinningTeam(homeComp, awayComp);

    assertEquals(true, homeComp.getWinner());
    assertEquals(true, awayComp.getWinner());
  }
}