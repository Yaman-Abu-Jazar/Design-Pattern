package exalt.com.filters;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import exalt.com.core.Event;
import exalt.com.core.EventSubscriber;

public class LastHourFilter {

    public List<Event> filterEvents(Map<Event, List<EventSubscriber>> subscribers) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime anHourAgo = now.minusHours(1);

        List<Event> filteredList = new ArrayList<>(subscribers.keySet()).stream()
        .filter(event -> event.getEventTime().isAfter(anHourAgo) && event.getEventTime().isBefore(now))
        .collect(Collectors.toList());

        return filteredList;
    }
    
}
