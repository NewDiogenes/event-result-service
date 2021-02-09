package event.result.controller;

import event.result.model.Competition;
import event.result.service.ResultScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/scrape")
public class ResultScraperController {

  private ResultScraperService resultScraperService;

  @Autowired
  public ResultScraperController(ResultScraperService resultScraperService) {
    this.resultScraperService = resultScraperService;
  }

  @RequestMapping("/espn")
  public List<Competition> scrapeESPNPageForCompetitions(@RequestBody String espnSportUri) throws IOException {
    return resultScraperService.scrapePageForResults(espnSportUri);
  }
}
