package hu.progmasters.kanban.domain;

public enum SprintState {
    READY("Ready"),
    IN_PROGRESS("In Progress"),
    DELAYED("Delayed"),
    ON_HOLD("On Hold"),
    ALL_DONE("All Done");

    private String displayName;

    SprintState(String displayName) {
        this.displayName = displayName;
    }

    public static SprintState ofDisplayName(String stateDisplayName) {
        for (SprintState value : SprintState.values()) {
            if (value.displayName.equals(stateDisplayName))
                return value;
        }
        return READY;
    }

    public String getDisplayName() {
        return displayName;
    }
}
