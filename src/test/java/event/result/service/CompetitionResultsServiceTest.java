package event.result.service;

import event.result.model.Competition;
import event.result.respository.CompetitionResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CompetitionResultsServiceTest {

  @Mock
  private CompetitionResultRepository resultRepository;

  @Mock
  private Competition competition;

  private CompetitionResultsService resultsService;

  @BeforeEach
  private void setup() {
    openMocks(this);
    resultsService = new CompetitionResultsService(resultRepository);

    when(resultRepository.findAll()).thenReturn(List.of(competition));
  }

  @Test
  void getAllCompetitions_shouldQueryTheResultRepositoryForAllCompetitions() {
    resultsService.getAllCompetitions();
    verify(resultRepository, times(1)).findAll();
  }

  @Test
  void givenTheRepositoryReturnsANyValues_getAllCompetitions_shouldReturnThoseValues() {
    assertEquals(List.of(competition), resultsService.getAllCompetitions());
  }
}