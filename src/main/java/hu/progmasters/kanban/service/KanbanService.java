package hu.progmasters.kanban.service;

import hu.progmasters.kanban.domain.Sprint;
import hu.progmasters.kanban.domain.SprintState;
import hu.progmasters.kanban.domain.UserStory;
import hu.progmasters.kanban.domain.UserStoryState;
import hu.progmasters.kanban.dto.*;
import hu.progmasters.kanban.repository.SprintRepository;
import hu.progmasters.kanban.repository.UserStoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class KanbanService {

    private UserStoryRepository userStoryRepository;
    private SprintRepository sprintRepository;

    public KanbanService(UserStoryRepository userStoryRepository, SprintRepository sprintRepository) {
        this.userStoryRepository = userStoryRepository;
        this.sprintRepository = sprintRepository;
    }

    public List<SprintDetails> getSprints() {
        return sprintRepository.findAll().stream().map(SprintDetails::new).collect(Collectors.toList());
    }

    public Long createSprint(SprintCommand sprintCommand) {
        Long sprintId = sprintCommand.getId();
        if (sprintId != null) {
            Sprint sprint = sprintRepository.getOne(sprintId);
            sprint.setBadge(sprintCommand.getBadge());
            sprint.setDescription(sprintCommand.getDescription());
            sprint.setState(SprintState.ofDisplayName(sprintCommand.getSprintState()));
            return sprintId;
        } else {
            return sprintRepository.save(new Sprint(sprintCommand)).getId();
        }
    }

    public List<UserStoryDetails> getUserStories() {
        return userStoryRepository.findAll().stream().map(UserStoryDetails::new).collect(Collectors.toList());
    }

    public List<UserStoryDetails> getUserStoriesBySprintId(Long sprintId) {
        Sprint sprint = sprintRepository.getOne(sprintId);
        return userStoryRepository.findAllBySprint(sprint).stream().map(UserStoryDetails::new).collect(Collectors.toList());
    }

    public Long createUserStory(UserStoryCommand userStoryCommand) {
        Long sprintId = userStoryCommand.getSprintId();
        Long usId = userStoryCommand.getId();
        if (usId != null) {
            UserStory userStory = userStoryRepository.getOne(usId);
            userStory.setState(UserStoryState.ofDisplayName(userStoryCommand.getState()));
            userStory.setTitle(userStoryCommand.getTitle());
            userStory.setDescription(userStoryCommand.getDescription());
            return usId;
        } else {
            if (sprintId != null) {
                Sprint sprint = sprintRepository.getOne(sprintId);
                if (userStoryCommand.getTitle() == null) {
                    int suffix = userStoryRepository.findAllBySprint(sprint).size() + 1;
                    userStoryCommand.setTitle(String.format("User Story %d", suffix));
                }
                UserStory userStory = new UserStory(userStoryCommand);
                userStory.setSprint(sprint);
                return userStoryRepository.save(userStory).getId();
            } else {
                return -1L;
            }
        }
    }

    public void updateUserStoryState(UserStoryStateUpdateCommand userStoryStateUpdateCommand) {
        UserStory userStory = userStoryRepository.getOne(userStoryStateUpdateCommand.getUserStoryId());
        userStory.setState(UserStoryState.ofDisplayName(userStoryStateUpdateCommand.getUserStoryState()));
    }

    public void deleteUserStory(Long userStoryId) {
        Optional<UserStory> optionalUserStory = userStoryRepository.findById(userStoryId);
        optionalUserStory.ifPresent(userStory -> userStoryRepository.delete(userStory));
    }

    public void deleteSprint(Long sprintId) {
        Optional<Sprint> optionalSprint = sprintRepository.findById(sprintId);
        optionalSprint.ifPresent(sprint -> sprintRepository.delete(sprint));
    }

    public SprintExtDetails getSprintExtendedDetails(Long sprintId) {
        Sprint sprint = sprintRepository.getOne(sprintId);
        return new SprintExtDetails(sprint);
    }
}
