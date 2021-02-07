package event.result.respository;


import event.result.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionResultRepository extends JpaRepository<Competition, String> {
}
