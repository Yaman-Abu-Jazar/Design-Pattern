package exalt.com.filters;

import java.util.List;
import java.util.stream.Stream;

import exalt.com.core.Event;
import exalt.com.core.EventSubscriber;

public class PriorityFilter implements MyFilter {

    @Override
    public List<EventSubscriber> filterSubscribers(List<EventSubscriber> subscribers, Event event) {
        Stream<EventSubscriber> stream = subscribers.stream()
            .filter(subscriber -> subscriber.getDesiredEventPriority().isEqualOrGreater(event.getPriority()));

        return stream.toList();
    }
    
}
