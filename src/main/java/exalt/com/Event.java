package exalt.com;

import java.time.LocalDateTime;

public interface Event {
    Object getEventType();

    LocalDateTime getEventTime();

    String getEventName();
}
