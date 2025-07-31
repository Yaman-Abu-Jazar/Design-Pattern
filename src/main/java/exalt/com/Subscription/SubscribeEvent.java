package exalt.com.Subscription;

import exalt.com.core.Event;
import exalt.com.core.EventManager;
import exalt.com.core.EventSubscriber;

public class SubscribeEvent {

    // function is used to add new subscriber to the list of subscribers
    public void subscribe(Event event, EventSubscriber subscriber){
        EventManager manager = EventManager.getInstance();
        if(manager.getSubscribers().containsKey(event)){
            if(!manager.getSubscribers().get(event).contains(subscriber)){
                manager.getSubscribers().get(event).add(subscriber);
                System.out.println("Subscriber : " + subscriber + " has subscribed to event : " + event);
                if(!manager.getSystemSubscribers().contains(subscriber)){
                    manager.getSystemSubscribers().add(subscriber);
                }
            } else {
                System.out.println("This user has subscribed to this event before.");
            }
        } else {
            System.out.println("There is no such event");
        }
    }
    
}
