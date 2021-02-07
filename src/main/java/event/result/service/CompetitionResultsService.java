package event.result.service;

import event.result.model.Competition;
import event.result.respository.CompetitionResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionResultsService {

  private final CompetitionResultRepository competitionResultRepository;

  @Autowired
  public CompetitionResultsService(CompetitionResultRepository competitionResultRepository) {
    this.competitionResultRepository = competitionResultRepository;
  }

  public List<Competition> getAllCompetitions() {
    return competitionResultRepository
        .findAll();
  }
}
