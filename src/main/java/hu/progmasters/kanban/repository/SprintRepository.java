package hu.progmasters.kanban.repository;

import hu.progmasters.kanban.domain.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
