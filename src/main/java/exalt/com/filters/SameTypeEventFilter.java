package exalt.com.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import exalt.com.core.Event;
import exalt.com.core.EventSubscriber;
import exalt.com.models.EventType;

public class SameTypeEventFilter {

    public List<Event> scheduledEvents(Map<Event, List<EventSubscriber>> subscribers) {

        EventType type = EventType.SCHEDULED;

        List<Event> filteredList = new ArrayList<>(subscribers.keySet()).stream()
        .filter(event -> (event.getEventType().getType() == type.getType()))
        .collect(Collectors.toList());

        return filteredList;
    }

    public List<Event> unscheduledEvents(Map<Event, List<EventSubscriber>> subscribers) {

        EventType type = EventType.UNSCHEDULED;

        List<Event> filteredList = new ArrayList<>(subscribers.keySet()).stream()
        .filter(event -> (event.getEventType().getType() == type.getType()))
        .collect(Collectors.toList());

        return filteredList;
    }
    
}
