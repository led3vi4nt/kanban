package hu.progmasters.kanban.repository;

import hu.progmasters.kanban.domain.GlobalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalUserRepository extends JpaRepository<GlobalUser, Long> {
    GlobalUser findGlobalUserByName(String userName);
}
