package event.result.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@JsonDeserialize
@JsonSerialize
public class Competitor {
  @Id
  @GeneratedValue
  private Integer id;
  private String type;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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
}
