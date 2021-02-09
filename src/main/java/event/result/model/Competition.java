package event.result.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@JsonDeserialize
@JsonSerialize
public class Competition {
  @Id
  private String uid;
  private String id;
  private String competitionDate;
  private String name;

  @OneToMany
  private List<Competitor> competitors;

  public Competition() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getCompetitionDate() {
    return competitionDate;
  }

  public void setCompetitionDate(String competitionDate) {
    this.competitionDate = competitionDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Competitor> getCompetitors() {
    return competitors;
  }

  public void setCompetitors(List<Competitor> competitors) {
    this.competitors = competitors;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("uid", uid)
        .append("id", id)
        .append("competitionDate", competitionDate)
        .append("name", name)
        .append("competitors", competitors)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    Competition that = (Competition) o;

    return new EqualsBuilder().append(uid, that.uid).append(id, that.id).append(competitionDate, that.competitionDate).append(name, that.name).append(competitors, that.competitors).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(uid).append(id).append(competitionDate).append(name).append(competitors).toHashCode();
  }
}
