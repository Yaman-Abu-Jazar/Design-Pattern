package exalt.com.events;

import java.time.LocalDateTime;

import exalt.com.core.Event;
import exalt.com.models.EventType;
import exalt.com.models.Priority;

public class NewTaskEvent implements Event {

    private String name;
    private String description;
    private LocalDateTime eventTime;
    private Priority priority;
    private EventType type;

    public NewTaskEvent(String name) {
        this.name = name;
        this.priority = Priority.MEDIUM;
    }

    public NewTaskEvent(String name, String description, LocalDateTime eventTime, Priority priority, EventType type) {
        this.name = name;
        this.description = description;
        this.eventTime = eventTime;
        this.priority = priority;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    @Override
    public String getEventName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public LocalDateTime getEventTime() {
        return eventTime;
    }

    @Override
    public EventType getEventType() {
        return this.type;
    }

    @Override
    public Priority getPriority(){
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    
}
