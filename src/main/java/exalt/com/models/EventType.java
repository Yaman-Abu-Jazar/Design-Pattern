package exalt.com.models;

public enum EventType {
    NEWTASK(0, "New Task"),
    SCHEDULED(1, "Time-Based Event");

    private final int type;
    private final String description;

    EventType(int type, String description){
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
