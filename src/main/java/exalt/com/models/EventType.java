package exalt.com.models;

public enum EventType {
    UNSCHEDULED(0, "Unscheduled Event"),
    SCHEDULED(1, "Scheduled Event");

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
