package hu.progmasters.kanban.dto;

import hu.progmasters.kanban.domain.Sprint;
import hu.progmasters.kanban.domain.UserStory;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SprintExtDetails {
    private Long id;
    private String badge;
    private String description;
    private String sprintState;
    private String createdAt;
    private String doneAt;
    private List<UserStoryDetails> userStories;


    public SprintExtDetails(Sprint sprint) {
        this.id = sprint.getId();
        this.badge = sprint.getBadge();
        this.description = sprint.getDescription();
        this.sprintState = sprint.getState().getDisplayName();
        this.createdAt = sprint.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (sprint.getDoneAt() != null)
            this.doneAt = sprint.getDoneAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.setUserStories(sprint.getUserStories());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSprintState() {
        return sprintState;
    }

    public void setSprintState(String sprintState) {
        this.sprintState = sprintState;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(String doneAt) {
        this.doneAt = doneAt;
    }

    public List<UserStoryDetails> getUserStories() {
        return userStories;
    }

    public void setUserStories(List<UserStory> userStories) {
        this.userStories = userStories.stream().map(UserStoryDetails::new).collect(Collectors.toList());
    }
}
