package hu.progmasters.kanban.dto;

import hu.progmasters.kanban.domain.UserStory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserStoryDetails {
    private Long id;
    private String title;
    private String description;
    private String state;
    private String createdAt;
    private String doneAt;

    public UserStoryDetails(UserStory task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.state = task.getState().getDisplayName();
        this.createdAt = task.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime doneAt = task.getDoneAt();
        if (doneAt != null)
            this.doneAt = doneAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        else
            this.doneAt = "[In Progress]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
