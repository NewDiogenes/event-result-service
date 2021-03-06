package event.result.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

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

  @ManyToOne(cascade= CascadeType.ALL)
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    Competitor that = (Competitor) o;

    return new EqualsBuilder().append(id, that.id).append(score, that.score).append(homeAway, that.homeAway).append(isWinner, that.isWinner).append(team, that.team).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(score).append(homeAway).append(isWinner).append(team).toHashCode();
  }
}
