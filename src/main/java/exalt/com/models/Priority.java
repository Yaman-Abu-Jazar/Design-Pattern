package exalt.com.models;

public enum Priority {
    LOW(0, "low priority"),
    MEDIUM(1, "medium priority"),
    HIGH(2, "high priority");

    private final int level;
    private final String description;

    Priority(int level, String description){
        this.level = level;
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }
}
