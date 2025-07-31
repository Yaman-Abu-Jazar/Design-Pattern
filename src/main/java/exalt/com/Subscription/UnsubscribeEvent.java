package exalt.com.Subscription;

import exalt.com.core.Event;
import exalt.com.core.EventManager;
import exalt.com.core.EventSubscriber;

public class UnsubscribeEvent {

    // function is used to remove (delete) a subscriber from the list of subscribers
    public void unsubscribe(Event event, EventSubscriber subscriber){
        EventManager manager = EventManager.getInstance();
        if(manager.getSubscribers().containsKey(event)){
            if(manager.getSubscribers().get(event) != null){
                if(manager.getSubscribers().get(event).contains(subscriber)){
                    manager.getSubscribers().get(event).remove(subscriber);
                    System.out.println("Subscriber : " + subscriber + " has unsubscribed this event : " + event);
                } else {
                    System.out.println("This user hasn't subscribed to this event before.");
                }
            } else {
                System.out.println("This Event has no subscribers yet.");
            }
        } else {
            System.out.println("There is no such event");
        }
    }
    
}
