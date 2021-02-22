package hu.progmasters.kanban.domain;

import hu.progmasters.kanban.dto.SprintCommand;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String badge;

    private String description;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.REMOVE)
    private List<UserStory> userStories;

    @Enumerated(EnumType.STRING)
    private SprintState state;

    private LocalDateTime createdAt;

    private LocalDateTime doneAt;

    public Sprint() {
    }

    public Sprint(SprintCommand sprintCommand) {
        this.badge = sprintCommand.getBadge();
        this.description = sprintCommand.getDescription();
        this.createdAt = LocalDateTime.now();
        this.doneAt = null;
        this.userStories = new ArrayList<>();
        if (sprintCommand.getSprintState() != null)
            this.state = SprintState.ofDisplayName(sprintCommand.getSprintState());
        else
            this.state = SprintState.READY;
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

    public List<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(List<UserStory> userStories) {
        this.userStories = userStories;
    }

    public SprintState getState() {
        return state;
    }

    public void setState(SprintState state) {
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(LocalDateTime doneAt) {
        this.doneAt = doneAt;
    }
}
