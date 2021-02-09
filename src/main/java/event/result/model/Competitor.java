package event.result.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@JsonDeserialize
@JsonSerialize
public class Competitor {

  public static String HOME_TEAM = "home";
  public static String AWAY_TEAM = "away";

  @Id
  @GeneratedValue
  private Integer id;
  private Integer score;
  private String homeAway;
  private Boolean isWinner;

  @ManyToOne
  private Team team;

  public Competitor() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public String getHomeAway() {
    return homeAway;
  }

  public void setHomeAway(String homeAway) {
    this.homeAway = homeAway;
  }

  public Boolean getWinner() {
    return isWinner;
  }

  public void setWinner(Boolean winner) {
    isWinner = winner;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("score", score)
        .append("homeAway", homeAway)
        .append("isWinner", isWinner)
        .append("team", team)
        .toString();
  }
}
