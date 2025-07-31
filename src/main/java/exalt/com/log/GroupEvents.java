package exalt.com.log;

import java.util.List;

import exalt.com.core.Event;
import exalt.com.core.EventManager;
import exalt.com.filters.SameTypeEventFilter;

public class GroupEvents {

    public void groupEventsWithSameType(){
        EventManager manager = EventManager.getInstance();

        SameTypeEventFilter filter = new SameTypeEventFilter();

        List<Event> scheduledEvents = filter.scheduledEvents(manager.getSubscribers());
        System.out.println("Scheduled Events : ");
        System.out.println(scheduledEvents);

        List<Event> unscheduledEvents = filter.unscheduledEvents(manager.getSubscribers());
        System.out.println("Unscheduled Events : ");
        System.out.println(unscheduledEvents);
    }
    
}
