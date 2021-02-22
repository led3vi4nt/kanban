package hu.progmasters.kanban.dto;

import hu.progmasters.kanban.domain.Sprint;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SprintDetails {
    private Long id;
    private String badge;
    private String description;
    private String sprintState;
    private Integer userStoryCount;
    private String createdAt;
    private String doneAt;

    public SprintDetails(Sprint sprint) {
        this.id = sprint.getId();
        this.badge = sprint.getBadge();
        this.description = sprint.getDescription();
        this.sprintState = sprint.getState().getDisplayName();
        this.userStoryCount = sprint.getUserStories().size();
        this.createdAt = sprint.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime doneAt = sprint.getDoneAt();
        if (doneAt != null)
            this.doneAt = doneAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        else
            this.doneAt = "[in progress]";
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

    public Integer getUserStoryCount() {
        return userStoryCount;
    }

    public void setUserStoryCount(Integer userStoryCount) {
        this.userStoryCount = userStoryCount;
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
}
