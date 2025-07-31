package exalt.com.manageEvents;

import java.util.ArrayList;
import java.util.List;

import exalt.com.core.Event;
import exalt.com.core.EventManager;
import exalt.com.core.EventSubscriber;
import exalt.com.filters.MyFilter;
import exalt.com.filters.NoneFilter;
import exalt.com.filters.PriorityFilter;
import exalt.com.filters.WorkHoursFilter;

public class PublishEvent {

    // function to publish new event and add it to the hash map
    public void publish(Event event){
        EventManager manager = EventManager.getInstance();
        List<EventSubscriber> filteredList = new ArrayList<>();
        MyFilter filterPriority = new PriorityFilter();
        MyFilter filterWorkHours = new WorkHoursFilter();
        MyFilter filterNone = new NoneFilter();
        if(!manager.getSubscribers().containsKey(event)){
            manager.getSubscribers().put(event, new ArrayList<>());
            manager.setnEvents(manager.getSubscribers().size());
            System.out.println("This Event : " + event + " has been published");
            filteredList.addAll(manager.getSystemSubscribers());
            if(!filteredList.isEmpty()){
                filteredList = filterPriority.filterSubscribers(filteredList, event);
                filteredList = filterWorkHours.filterSubscribers(filteredList, event);
                filteredList = filterNone.filterSubscribers(filteredList, event);
                for(EventSubscriber subscriber : filteredList){
                    subscriber.update();
                }
            } else {
                System.out.println("The system has no subscribers yet.");
            }
            
        } 
        else
            System.out.println("This event has been fired before.");
    }
    
}
