package exalt.com.filters;

import java.util.List;

import exalt.com.core.Event;
import exalt.com.core.EventSubscriber;

public interface MyFilter {
    
    List<EventSubscriber> filterSubscribers(List<EventSubscriber> subscribers, Event event);

}
