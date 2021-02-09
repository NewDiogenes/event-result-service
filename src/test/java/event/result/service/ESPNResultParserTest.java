package event.result.service;

import com.gargoylesoftware.htmlunit.html.DomNode;
import event.result.model.Competitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ESPNResultParserTest {

  @Mock
  private ESPNCompetitionParser competitionParser;
  @Mock
  private ESPNCompetitorParser competitorParser;
  @Mock
  private DomNode mockNode;

  private Competitor homeComp;
  private Competitor awayComp;

  private ESPNResultParser resultParser;

  private static final String simplePageUrl = "src/test/resources/webpages/SimpleResultsPage.html";
  private static final String noCompleteGamesPageUrl = "src/test/resources/webpages/NoCompletedGames.html";
  private static final String homeWinPageUrl = "src/test/resources/webpages/HomeWin.html";
  private static final String awayWinPageUrl = "src/test/resources/webpages/AwayWin.html";
  private static final String drawPageUrl = "src/test/resources/webpages/Draw.html";

  @BeforeEach
  void setUp() {
    openMocks(this);

    resultParser = new ESPNResultParser(competitorParser, competitionParser);

    homeComp = new Competitor();
    awayComp = new Competitor();

    when(competitorParser.getCompetitor(any(), eq(ESPNResultParser.HOME_SCORE_SELECTOR), eq(Competitor.HOME_TEAM)))
        .thenReturn(homeComp);

    when(competitorParser.getCompetitor(any(), eq(ESPNResultParser.AWAY_SCORE_SELECTOR), eq(Competitor.AWAY_TEAM)))
        .thenReturn(awayComp);
  }

  @Test
  void mapPageDataToCompetition_callCompetitorParserToParseTheHomeCompetitor() {
    resultParser.mapPageDataToCompetition(mockNode);

    verify(competitorParser)
        .getCompetitor(eq(mockNode), eq(ESPNResultParser.HOME_SCORE_SELECTOR), eq(Competitor.HOME_TEAM));
  }

  @Test
  void mapPageDataToCompetition_callCompetitorParserToParseTheAwayCompetitor() {
    resultParser.mapPageDataToCompetition(mockNode);

    verify(competitorParser)
        .getCompetitor(eq(mockNode), eq(ESPNResultParser.AWAY_SCORE_SELECTOR), eq(Competitor.AWAY_TEAM));
  }

  @Test
  void givenCompetitorParserReturnsHomeAndAwayCompetitors_mapPageDataToCompetition_shouldCallCompetitorParserToSetTheWinner() {
    resultParser.mapPageDataToCompetition(mockNode);

    verify(competitorParser).setWinningTeam(eq(homeComp), eq(awayComp));
  }


  @Test
  void givenCompetitorParserReturnsHomeAndAway_mapPageDataToCompetition_shouldCallCompetitionParser() {
    resultParser.mapPageDataToCompetition(mockNode);

    verify(competitionParser).getCompetition(eq(mockNode), eq(homeComp), eq(awayComp));
  }
}