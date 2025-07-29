package exalt.com.core;

import java.time.LocalDateTime;

public interface Event {
    Object getEventType();

    LocalDateTime getEventTime();

    String getEventName();

    Priority getPriority();
}
