package hu.progmasters.kanban.dto;

public class UserStoryStateUpdateCommand {
    private Long userStoryId;
    private String userStoryState;

    public Long getUserStoryId() {
        return userStoryId;
    }

    public void setUserStoryId(Long userStoryId) {
        this.userStoryId = userStoryId;
    }

    public String getUserStoryState() {
        return userStoryState;
    }

    public void setUserStoryState(String userStoryState) {
        this.userStoryState = userStoryState;
    }
}
