package hu.progmasters.kanban.domain;

public enum UserStoryState {
    TO_DO("To Do"),
    IN_PROGRESS("In Progress"),
    TESTING("Testing"),
    DONE("Done");

    private String displayName;

    UserStoryState(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static UserStoryState ofDisplayName(String displayName) {
        for (UserStoryState value : UserStoryState.values()) {
            if (value.displayName.equals(displayName))
                return value;
        }
        return TO_DO;
    }
}
