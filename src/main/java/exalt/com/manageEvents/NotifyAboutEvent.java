package exalt.com.manageEvents;

import exalt.com.core.Event;
import exalt.com.core.EventManager;
import exalt.com.core.EventSubscriber;

public class NotifyAboutEvent {

    // function is used to notify subscribers to a specific event about it
    public void notifySubscribers(Event event){
        EventManager manager = EventManager.getInstance();
        if(!manager.getSubscribers().get(event).isEmpty()){
            for(EventSubscriber subscriber : manager.getSubscribers().get(event)){
                subscriber.update();
            }
        } else {
            System.out.println("There are no subscribers yet for this Event " + event + ".");
        }
    }
    
}
