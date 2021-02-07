package event.result.controller;

import event.result.service.CompetitionResultsService;
import event.result.model.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CompetitionResultController {
  private CompetitionResultsService competitionResultsService;

  @Autowired
  public CompetitionResultController(CompetitionResultsService competitionResultsService) {
    this.competitionResultsService = competitionResultsService;
  }

  @GetMapping(value = "/competition")
  public List<Competition> getResults() {
    return competitionResultsService.getAllCompetitions();
  }
}
