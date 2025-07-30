package exalt.com.builders;

import java.time.LocalDateTime;

import exalt.com.core.Event;
import exalt.com.events.ScheduledEvent;
import exalt.com.events.UnScheduledEvent;
import exalt.com.models.EventType;
import exalt.com.models.Priority;

public class EventBuilder {
    
    private String name;
    private String description = "";
    private LocalDateTime eventTime = LocalDateTime.of(2025, 10, 30, 0, 0);
    private Priority priority = Priority.MEDIUM;
    private EventType type = EventType.UNSCHEDULED;

    public  EventBuilder(String name){
        this.name = name;
    }

    public EventBuilder setDescription(String description){
        this.description = description;
        return this;
    }

    public EventBuilder setEventTime(LocalDateTime eventTime){
        this.eventTime = eventTime;
        return this;
    }

    public EventBuilder setEventPriority(Priority priority){
        this.priority = priority;
        return this;
    }

    public EventBuilder setEventType(EventType type){
        this.type = type;
        return this;
    }

    public Event build(){
        switch (this.type) {
            case EventType.UNSCHEDULED -> {
                return new UnScheduledEvent(this.name, this.description, this.eventTime, this.priority, this.type);
            }
            case EventType.SCHEDULED -> {
                return new ScheduledEvent(this.name, this.description, this.eventTime, this.priority, this.type);
            }
            default -> throw new AssertionError();
        }
    }

}
