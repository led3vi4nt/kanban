package hu.progmasters.kanban.domain;

import hu.progmasters.kanban.dto.UserStoryCommand;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sprint sprint;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private UserStoryState state;


    private LocalDateTime createdAt;

    private LocalDateTime doneAt;

    public UserStory() {
    }

    public UserStory(UserStoryCommand userStoryCommand) {
        this.title = userStoryCommand.getTitle();
        this.description = userStoryCommand.getDescription();
        if (userStoryCommand.getState() != null) {
            this.state = UserStoryState.ofDisplayName(userStoryCommand.getState());
        } else {
            this.state = UserStoryState.TO_DO;
        }
        this.createdAt = LocalDateTime.now();
        this.doneAt = null;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
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

    public UserStoryState getState() {
        return state;
    }

    public void setState(UserStoryState state) {
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
