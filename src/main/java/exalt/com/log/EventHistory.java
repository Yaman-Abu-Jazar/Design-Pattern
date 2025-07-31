package exalt.com.log;

import java.util.List;

import exalt.com.core.Event;
import exalt.com.core.EventManager;
import exalt.com.core.EventSubscriber;
import exalt.com.filters.LastHourFilter;
import exalt.com.filters.LastWeekfilter;

public class EventHistory {

    public void printEventsLastHour(EventSubscriber subscriber){
        if(subscriber.isAdmin()){
            LastHourFilter filter = new LastHourFilter();

            EventManager manager = EventManager.getInstance();
            List<Event> eventsInLastHour = filter.filterEvents(manager.getSubscribers());

            System.out.println(eventsInLastHour);
        } else {
            System.out.println("This functionality permitted for Admins only");
        }
        
    }

    public void printEventsLastweek(EventSubscriber subscriber){
        if(subscriber.isAdmin()){
            LastWeekfilter filter = new LastWeekfilter();

            EventManager manager = EventManager.getInstance();
            List<Event> eventsInLastHour = filter.filterEvents(manager.getSubscribers());

            System.out.println(eventsInLastHour);
        } else {
            System.out.println("This functionality permitted for Admins only");
        }
        
    }
    
}
