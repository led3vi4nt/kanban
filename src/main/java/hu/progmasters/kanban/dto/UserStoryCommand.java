package hu.progmasters.kanban.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserStoryCommand {

    private Long id;

    @NotNull
    private Long sprintId;

    @Size(min = 3, max = 16)
    private String title;

    private String description;

    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
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
}
