package hu.progmasters.kanban.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SprintCommand {
    private Long id;

    @Size(min = 3, max = 16)
    private String badge;

    @NotBlank
    private String description;

    private String sprintState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSprintState() {
        return sprintState;
    }

    public void setSprintState(String sprintState) {
        this.sprintState = sprintState;
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
}
