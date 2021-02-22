package hu.progmasters.kanban.repository;

import hu.progmasters.kanban.domain.Sprint;
import hu.progmasters.kanban.domain.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, Long> {
    List<UserStory> findAllBySprint(Sprint sprint);
}
