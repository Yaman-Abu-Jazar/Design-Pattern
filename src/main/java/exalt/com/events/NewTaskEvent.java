package exalt.com.events;

import java.time.LocalDateTime;

import exalt.com.core.Event;

public class NewTaskEvent implements Event {

    private String name;
    private String description;
    private LocalDateTime eventTime;

    public NewTaskEvent() {}

    public NewTaskEvent(String name, String description, LocalDateTime eventTime) {
        this.name = name;
        this.description = description;
        this.eventTime = eventTime;
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
    public Object getEventType() {
        return this.getClass();
    }
    
}
