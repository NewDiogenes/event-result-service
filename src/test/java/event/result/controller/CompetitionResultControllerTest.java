package event.result.controller;

import event.result.model.Competition;
import event.result.service.CompetitionResultsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CompetitionResultControllerTest {

  @Mock
  private CompetitionResultsService competitionResultsService;
  @Mock
  private Competition testCompetition;

  private CompetitionResultController controller;

  @BeforeEach
  private void setup() {
    openMocks(this);
    controller = new CompetitionResultController(competitionResultsService);

    when(competitionResultsService.getAllCompetitions()).thenReturn(List.of(testCompetition));
  }

  @Test
  void getResults_shouldGetAllResultsFromCompetitionResultsService() {
    controller.getResults();
    verify(competitionResultsService, times(1)).getAllCompetitions();
  }

  @Test
  void givenCompetitionResultsServiceReturnsAList_getResults_ShouldReturnTheList() {
    assertEquals(List.of(testCompetition), controller.getResults());
  }
}