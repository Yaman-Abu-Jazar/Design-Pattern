package exalt.com.core;

import java.time.LocalDateTime;

import exalt.com.models.EventType;
import exalt.com.models.Priority;

public interface Event {
    EventType getEventType();

    LocalDateTime getEventTime();

    String getEventName();

    Priority getPriority();
}
